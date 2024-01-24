package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
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

    public static double DRIVE_POWER = 1;

    public static int MAX_EXECUTION_COUNT = 250;
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
        if(!isFinished) {
            if (executeCount == 0)
                buildAndExecuteStrafeSequence();
            else if (executeCount >= 1 && executeCount <= MAX_EXECUTION_COUNT)
                isFinished = driveToTag(visionSubsystem.getAprilTags(), getTargetId());
            else //we've executed this 25 times, it's time to bail out
                isFinished = true;
        }

        executeCount++;
    }

    private int getTargetId(){
        if(MatchConfig.Alliance == Alliance.Red) {
//            if(MatchConfig.TeamPropPosition == TeamPropPosition.Left)
//                return 4;
//            else if( MatchConfig.TeamPropPosition == TeamPropPosition.Center)
//                return 5;
//            else
//                return 6;
            return 5;
        }
        else {
//            if(MatchConfig.TeamPropPosition == TeamPropPosition.Left)
//                return 1;
//            else if( MatchConfig.TeamPropPosition == TeamPropPosition.Center)
//                return 2;
//            else
//                return 3;
            return 2;
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

                MatchConfig.telemetry.addData("RangeError: ", rangeError);
                MatchConfig.telemetry.addData("HeadingError: ", headingError);
                MatchConfig.telemetry.addData("YawError: ", yawError);
                MatchConfig.telemetry.update();

                // Use the speed and turn "gains" to calculate how we want the robot to move.
                if(Math.abs(rangeError) > Configuration.BACKDROP_ERROR_DISTANCE )
                    drive = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
                else
                    drive = 0;

                if(Math.abs(headingError) > Configuration.APRIL_TAG_BEARING)
                    turn = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
                else
                    turn = 0;

                if(Math.abs(yawError) > Configuration.APRIL_TAG_YAW)
                    strafe = -Range.clip(yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);
                else
                    strafe = 0;

                foundId = true;
                break;
            }
        }
        ///TODO: Implement function if it didn't find anything
        //we didn't find the tag we are looking for and need to try and go back to it
//        if(!foundId && detections.size() > 0){
//            int detectedID = detections.get(0).id; //just get the first one to determine where we should go
//            MatchConfig.telemetry.addData("DrivetoAprilTagCommand: We didn't find the target tag but found: ", detectedID);
//            if(detectedID > targetTagId)    //we are right of the intended target
//                strafe = -MAX_AUTO_STRAFE;
//            else
//                strafe = MAX_AUTO_STRAFE;
//        }


        MatchConfig.telemetry.addData("DrivetoAprilTagCommand","Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);
        MatchConfig.telemetry.addData("DrivetoAprilTagCommand Found it: ", foundId);
        MatchConfig.telemetry.update();

        if(foundId && isInRange(drive, .1) && isInRange(turn, .1) && isInRange(strafe, .1)) {
            mecanumDriveSubsystem.stop();
            return true;
        }
        else{
            if(!mecanumDriveSubsystem.isBusy())
                mecanumDriveSubsystem.drive(drive, turn, strafe, DRIVE_POWER);
            return false;
        }
    }

    private boolean isInRange(double value, double error){
        return (value > -error && value < error);
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
        MatchConfig.telemetry.addData("DriveAprilTagCommand executeCount: ", executeCount);
        MatchConfig.telemetry.addData("DriveAprilTagCommand isFinished: ", isFinished);
        MatchConfig.telemetry.update();

        return isFinished;
    }
    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.stop();
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
