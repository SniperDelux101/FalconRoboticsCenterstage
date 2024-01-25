package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@Config
public class DriveToAprilTagSynchronousCommand extends CommandBase {

    ///region Drive Constants
    //  Set the GAIN constants to control the relationship between the measured position error, and how much power is
    //  applied to the drive motors to correct the error.
    //  Drive = Error * Gain    Make these values smaller for smoother control, or larger for a more aggressive response.
    public static double SPEED_GAIN = 0.02;   //  Forward Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    public static double STRAFE_GAIN = 0.015;   //  Strafe Speed Control "Gain".  eg: Ramp up to 25% power at a 25 degree Yaw error.   (0.25 / 25.0)
    public static double TURN_GAIN = 0.01;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    public static double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    public static double MAX_AUTO_STRAFE = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    public static double MAX_AUTO_TURN = 0.3;   //  Clip the turn speed to this max value (adjust for your robot)

    public static double DRIVE_POWER = 1;

    ///endregion
    private final VisionSubsystem visionSubsystem;
    private final MecanumDriveSubsystem mecanumDriveSubsystem;

    public DriveToAprilTagSynchronousCommand(VisionSubsystem v_system, MecanumDriveSubsystem m_system) {
        visionSubsystem = v_system;
        mecanumDriveSubsystem = m_system;

        addRequirements(visionSubsystem, mecanumDriveSubsystem);
    }

    @Override
    public void execute() {
        strafeToBearing();

        driveToTag();
    }

    private void strafeToBearing() {
        AprilTagDetection targetTag = getAprilTag(visionSubsystem.getAprilTags(), getTargetId());
        double strafe;
        while (targetTag == null || targetTag.ftcPose.bearing > Configuration.APRIL_TAG_BEARING) {
            if (MatchConfig.Alliance == Alliance.Blue)
                strafe = MAX_AUTO_STRAFE;
            else
                strafe = -MAX_AUTO_STRAFE;
            mecanumDriveSubsystem.drive(0, 0, strafe, DRIVE_POWER);

            sleep(50);
            targetTag = getAprilTag(visionSubsystem.getFreshAprilTags(), getTargetId());
            if (targetTag != null) {
                MatchConfig.telemetry.addData("DriveToApril Sync, Bearing: ", targetTag.ftcPose.bearing);
                MatchConfig.telemetry.update();
            }
        }
        mecanumDriveSubsystem.stop();
    }

    private void driveToTag() {
        double drive = 0;        // Desired forward power/speed (-1 to +1)
        double strafe = 0;        // Desired strafe power/speed (-1 to +1)
        double turn = 0;        // Desired turning power/speed (-1 to +1)

        AprilTagDetection targetTag = getAprilTag(visionSubsystem.getFreshAprilTags(), getTargetId());
        boolean isComplete = false;

        while (!isComplete) {
            // Determine heading, range and Yaw (tag image rotation) error so we can use them to control the robot automatically.
            double rangeError = (targetTag.ftcPose.range - Configuration.BACKDROP_DISTANCE);
            double headingError = targetTag.ftcPose.bearing;
            double yawError = targetTag.ftcPose.yaw;

            MatchConfig.telemetry.addData("RangeError: ", rangeError);
            MatchConfig.telemetry.addData("HeadingError: ", headingError);
            MatchConfig.telemetry.addData("YawError: ", yawError);
            MatchConfig.telemetry.update();

            if (Math.abs(rangeError) < Configuration.BACKDROP_ERROR_DISTANCE
                    && Math.abs(headingError) < Configuration.APRIL_TAG_BEARING
                    && Math.abs(yawError) < Configuration.APRIL_TAG_YAW) {
                mecanumDriveSubsystem.stop();
                isComplete = true;
            } else {
                // Use the speed and turn "gains" to calculate how we want the robot to move.
                drive = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
                turn = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
                strafe = -Range.clip(yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);

                MatchConfig.telemetry.addData("DrivetoAprilTagCommand", "Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);
                MatchConfig.telemetry.update();

                mecanumDriveSubsystem.drive(drive, turn, strafe, DRIVE_POWER);
                sleep(50);

                targetTag = getAprilTag(visionSubsystem.getFreshAprilTags(), getTargetId());
                if (targetTag == null) {
                    mecanumDriveSubsystem.stop();
                    MatchConfig.telemetry.addLine("LOST APRIL TAG");
                    MatchConfig.telemetry.update();
                    int count = 0;
                    while (targetTag == null && count < 10) {
                        targetTag = getAprilTag(visionSubsystem.getFreshAprilTags(), getTargetId());
                        count++;
                        sleep(50);
                    }
                    if (targetTag == null)
                        isComplete = true;
                }
            }
        }
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

    private AprilTagDetection getAprilTag(List<AprilTagDetection> detections, int targetTagId){
        AprilTagDetection tag = null;
        for(AprilTagDetection detection : detections) {
            if (detection.id == targetTagId) {
                tag = detection;
                break;
            }
        }
        return tag;
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.stop();
    }
}
