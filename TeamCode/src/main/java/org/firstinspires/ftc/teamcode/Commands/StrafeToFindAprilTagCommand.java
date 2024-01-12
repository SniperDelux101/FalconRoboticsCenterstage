package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

public class StrafeToFindAprilTagCommand extends CommandBase {

    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final VisionSubsystem visionSubsystem;
    private boolean isFinished;
    private boolean hasExecuted;
    int executeCounter;

    public StrafeToFindAprilTagCommand(MecanumDriveSubsystem mDrive, VisionSubsystem vSystem) {
        mecanumDriveSubsystem = mDrive;
        visionSubsystem = vSystem;
        isFinished = false;
        hasExecuted = false;

        addRequirements(mecanumDriveSubsystem, visionSubsystem);
    }

    @Override
    public void initialize() {
        executeCounter = 0;
        visionSubsystem.resumeAprilStreaming();

        buildAndExecuteStrafeSequence();
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
    public void execute() {
        MatchConfig.telemetry.addLine("Executing StrafeToFindAprilTagCommand");
        MatchConfig.telemetry.addLine("Looking for Tag: " + visionSubsystem.GetAprilTagID());

        AprilTagDetection detection = this.visionSubsystem.findAprilTag();
        if(detection != null) {
            MatchConfig.telemetry.addData("Bearing: ", detection.ftcPose.bearing);
            MatchConfig.telemetry.addData("Yaw: ", detection.ftcPose.yaw);

            if( Math.abs(detection.ftcPose.bearing) <= Configuration.APRIL_TAG_BEARING && Math.abs(detection.ftcPose.yaw) <= Configuration.APRIL_TAG_YAW) {
                mecanumDriveSubsystem.stop();
                mecanumDriveSubsystem.breakFollowing();
                MatchConfig.telemetry.addLine("FOUND Tag " + visionSubsystem.GetAprilTagID());
                isFinished = true;
            }
            else if( Math.abs(detection.ftcPose.bearing) > Configuration.APRIL_TAG_BEARING || Math.abs(detection.ftcPose.yaw) > Configuration.APRIL_TAG_YAW
                && !mecanumDriveSubsystem.isBusy()) {
                if(executeCounter >= 2)
                {
                    isFinished = true;
                    return;
                }

                mecanumDriveSubsystem.stop();
                mecanumDriveSubsystem.breakFollowing();

//                double  strafeDistance = calculatedDistanceToTravel(detection.ftcPose.bearing, detection.ftcPose.range, detection.ftcPose.yaw);
                double strafeDistance = betterCalculatedDistanceToTravel(detection.ftcPose.bearing, detection.ftcPose.range, detection.ftcPose.yaw);

                mecanumDriveSubsystem.turn(Math.toRadians(detection.ftcPose.yaw));

                TrajectorySequence t = mecanumDriveSubsystem.trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.STRAFE_TO_APRIL_TAG_VEL, Configuration.TRACKWIDTH))
                        .strafeRight(strafeDistance)
                        .build();
                mecanumDriveSubsystem.followTrajectorySequenceAsync(t);
                executeCounter++;
            }
        }
        else
            MatchConfig.telemetry.addLine("Didn't Find ID: " + visionSubsystem.GetAprilTagID());


        MatchConfig.telemetry.addData("Counter: ", executeCounter);
        MatchConfig.telemetry.update();
    }

    public double betterCalculatedDistanceToTravel(double bearing, double range, double yaw) {

        double length = range * Math.cos(bearing);

        double sqrdRange = range * range;
        double sqrdLength = length * length;

        double ve = sqrdRange - sqrdLength;

        double sqrtVE = Math.sqrt(ve);

        return sqrtVE;

    }

    public double calculatedDistanceToTravel(double bearing, double range, double yaw) {
        double bearingRadians = Math.toRadians(bearing);
        double yawRadians = Math.toRadians(yaw);

        double xComponent = range * Math.cos(bearingRadians);
        double yComponent = range * Math.sin(bearingRadians);

        double rotatedX = xComponent * Math.cos(yawRadians) - yComponent * Math.sin(yawRadians);
        double rotatedy = xComponent * Math.sin(yawRadians) + yComponent * Math.cos(yawRadians);

        return Math.abs(rotatedX);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.stop();
        mecanumDriveSubsystem.breakFollowing();
        visionSubsystem.stopAprilStreaming();
    }
}
