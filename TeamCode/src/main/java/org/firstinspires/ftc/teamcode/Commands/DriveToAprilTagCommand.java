package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@Config
public class DriveToAprilTagCommand extends CommandBase {

    ///region Drive Constants
    //  Set the GAIN constants to control the relationship between the measured position error, and how much power is
    //  applied to the drive motors to correct the error.
    //  Drive = Error * Gain    Make these values smaller for smoother control, or larger for a more aggressive response.
    public static double SPEED_GAIN  =  0.02  ;   //  Forward Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    public static double STRAFE_GAIN =  0.015 ;   //  Strafe Speed Control "Gain".  eg: Ramp up to 25% power at a 25 degree Yaw error.   (0.25 / 25.0)
    public static double TURN_GAIN   =  0.01  ;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    public static double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    public static double MAX_AUTO_STRAFE= 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    public static double MAX_AUTO_TURN  = 0.3;   //  Clip the turn speed to this max value (adjust for your robot)
    ///endregion
    private final VisionSubsystem visionSubsystem;
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private boolean isFinished;
    private int executeCount;

    public DriveToAprilTagCommand(VisionSubsystem v_system, MecanumDriveSubsystem m_system){
        visionSubsystem = v_system;
        mecanumDriveSubsystem = m_system;
        isFinished = false;
        executeCount = 0;

        addRequirements(visionSubsystem, mecanumDriveSubsystem);
    }

    @Override
    public void execute() {
        if(executeCount == 0)
            buildAndExecuteStrafeSequence();
        else if( executeCount >= 1 && executeCount <= 25)
            isFinished = driveToTag(visionSubsystem.getAprilTags(), getTargetId());
        else //we've executed this 25 times, it's time to bail out
            isFinished = true;

        executeCount++;
    }

    private int getTargetId(){
        if(MatchConfig.Alliance == Alliance.Red) {
            if(MatchConfig.TeamPropPosition == TeamPropPosition.Left)
                return 4;
            else if( MatchConfig.TeamPropPosition == TeamPropPosition.Center)
                return 5;
            else
                return 6;
        }
        else {
            if(MatchConfig.TeamPropPosition == TeamPropPosition.Left)
                return 1;
            else if( MatchConfig.TeamPropPosition == TeamPropPosition.Center)
                return 2;
            else
                return 3;
        }
    }

    private boolean driveToTag(List<AprilTagDetection> detections, int targetTagId){
        double  drive           = 0;        // Desired forward power/speed (-1 to +1)
        double  strafe          = 0;        // Desired strafe power/speed (-1 to +1)
        double  turn            = 0;        // Desired turning power/speed (-1 to +1)
        boolean foundId = false;

        for(AprilTagDetection detection : detections) {
            if(detection.id == targetTagId) {
                if(mecanumDriveSubsystem.isBusy()) {
                    mecanumDriveSubsystem.stop();
                    mecanumDriveSubsystem.breakFollowing();
                }

                // Determine heading, range and Yaw (tag image rotation) error so we can use them to control the robot automatically.
                double rangeError = (detection.ftcPose.range - Configuration.BACKDROP_DISTANCE);
                double headingError = detection.ftcPose.bearing;
                double yawError = detection.ftcPose.yaw;
                MatchConfig.telemetry.addData("DrivetoAprilTagCommand", "RangeError %5.2f, HeadingError %5.2f, Yaw Error %5.2f",
                        rangeError, headingError, yawError);

                // Use the speed and turn "gains" to calculate how we want the robot to move.
                drive = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
                turn = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
                strafe = Range.clip(yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);
                foundId = true;
                break;
            }
        }
        //we didn't find the tag we are looking for and need to try and go back to it
        if(!foundId && detections.size() > 0){
            int detectedID = detections.get(0).id; //just get the first one to determine where we should go
            MatchConfig.telemetry.addData("DrivetoAprilTagCommand", "We didn't find the target tag but found %5.2f", detectedID);
            if(detectedID > targetTagId)    //we are right of the intended target
                strafe = -MAX_AUTO_STRAFE;
            else
                strafe = MAX_AUTO_STRAFE;
        }



        if( drive != 0 || turn != 0 || strafe != 0 ){
            MatchConfig.telemetry.addData("DrivetoAprilTagCommand","Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);
            mecanumDriveSubsystem.drive(drive, turn, strafe, Configuration.DRIVE_FORWARD_POWER);
            return false;
        }
        else if(foundId && drive == 0 && turn == 0 && strafe == 0)
            return true;
        else
            return false;
    }

    private void buildAndExecuteStrafeSequence() {
        TrajectorySequence sequence;
        if(MatchConfig.Alliance == Alliance.Blue) {
            sequence = mecanumDriveSubsystem.trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.STRAFE_TO_APRIL_TAG_VEL, Configuration.TRACKWIDTH))
                    .strafeRight(Configuration.VISION_STRAFE_DIS)
                    .build();
        }
        else {
            sequence = mecanumDriveSubsystem.trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.STRAFE_TO_APRIL_TAG_VEL, Configuration.TRACKWIDTH))
                    .strafeLeft(Configuration.VISION_STRAFE_DIS)
                    .build();
        }
        mecanumDriveSubsystem.followTrajectorySequenceAsync(sequence);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.stop();
    }
}
