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
import org.opencv.core.Mat;

import java.util.List;

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
        //visionSubsystem.resumeAprilStreaming();

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
        AprilTagDetection detection = this.visionSubsystem.findAprilTag();
        if (detection != null) {
            MatchConfig.telemetry.addData("Bearing: ", detection.ftcPose.bearing);
            MatchConfig.telemetry.addData("Yaw: ", detection.ftcPose.yaw);

            if (Math.abs(detection.ftcPose.yaw) > Configuration.APRIL_TAG_YAW) {
                MatchConfig.telemetry.addData("Turning to Yaw : ", detection.ftcPose.yaw);
                mecanumDriveSubsystem.turn(Math.toRadians(detection.ftcPose.yaw));
            }

            if (Math.abs(detection.ftcPose.bearing) > Configuration.APRIL_TAG_BEARING) {
                double strafeFinal = betterCalculatedDistanceToTravel(detection.ftcPose.bearing, detection.ftcPose.range, detection.ftcPose.yaw);

                TrajectorySequence t = mecanumDriveSubsystem.trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.STRAFE_TO_APRIL_TAG_VEL, Configuration.TRACKWIDTH))
                        .strafeRight(strafeFinal)
                        .build();

                mecanumDriveSubsystem.followTrajectorySequenceAsync(t);
                MatchConfig.telemetry.addData("Strafing to : ", strafeFinal);
                MatchConfig.telemetry.update();
                hasExecuted = true;
            }

        } else if(hasExecuted && !mecanumDriveSubsystem.isBusy()){
            //we did find any tags look for others
            List<AprilTagDetection> currentDetections = visionSubsystem.getAprilTags();
            for (AprilTagDetection newDetection : currentDetections) {
                if(newDetection.id == 1 || newDetection.id == 4){
                    //strafe right
                    TrajectorySequence t = mecanumDriveSubsystem.trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                            .setVelConstraint(new MecanumVelocityConstraint(Configuration.STRAFE_TO_APRIL_TAG_VEL, Configuration.TRACKWIDTH))
                            .strafeLeft(8)
                            .build();

                    mecanumDriveSubsystem.followTrajectorySequenceAsync(t);
                }
                else if(newDetection.id == 3 || newDetection.id == 6){
                    //strafe left
                    TrajectorySequence t = mecanumDriveSubsystem.trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                            .setVelConstraint(new MecanumVelocityConstraint(Configuration.STRAFE_TO_APRIL_TAG_VEL, Configuration.TRACKWIDTH))
                            .strafeRight(8)
                            .build();

                    mecanumDriveSubsystem.followTrajectorySequenceAsync(t);
                }
            }
        }
    }

//    @Override
//    public void execute() {
//        MatchConfig.telemetry.addLine("Executing StrafeToFindAprilTagCommand");
//        MatchConfig.telemetry.addLine("Looking for Tag: " + visionSubsystem.GetAprilTagID());
//        MatchConfig.telemetry.addData("April Tag Camera FPS: ", visionSubsystem.getAprilFPS());
//        MatchConfig.telemetry.addData("April Tag Camera State: ", visionSubsystem.getAprilTagCameraState().toString());
//
//        AprilTagDetection detection = this.visionSubsystem.findAprilTag();
//        if(detection != null) {
//
//            MatchConfig.telemetry.addData("Bearing: ", detection.ftcPose.bearing);
//            MatchConfig.telemetry.addData("Yaw: ", detection.ftcPose.yaw);
//
//            if(Math.abs(detection.ftcPose.yaw) > Configuration.APRIL_TAG_YAW && !mecanumDriveSubsystem.isBusy()) {
//                MatchConfig.telemetry.addData("Turning to Yaw : ", detection.ftcPose.yaw);
//                mecanumDriveSubsystem.turn(Math.toRadians(detection.ftcPose.yaw));
//            }
//
//            if(Math.abs(detection.ftcPose.bearing) > Configuration.APRIL_TAG_BEARING && !mecanumDriveSubsystem.isBusy()) {
//
//                double strafeFinal = betterCalculatedDistanceToTravel(detection.ftcPose.bearing, detection.ftcPose.range, detection.ftcPose.yaw);
//
//                TrajectorySequence t = mecanumDriveSubsystem.trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
//                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.STRAFE_TO_APRIL_TAG_VEL, Configuration.TRACKWIDTH))
//                        .strafeRight(strafeFinal)
//                        .build();
//
//                mecanumDriveSubsystem.followTrajectorySequenceAsync(t);
//                MatchConfig.telemetry.addData("Strafing to : ", strafeFinal);
//                MatchConfig.telemetry.update();
//                detection = this.visionSubsystem.findAprilTag();
//                int counter = 0;
//                while(counter < 10){
//                    detection = this.visionSubsystem.findAprilTag();
//                    MatchConfig.telemetry.addData("April Tag Camera FPS: ", visionSubsystem.getAprilFPS());
//                    MatchConfig.telemetry.addData("April Tage Camera State: ", visionSubsystem.getAprilTagCameraState().toString());
//                    MatchConfig.telemetry.addData("Counter: ", counter);
//                    if(detection != null){
//                        MatchConfig.telemetry.addData("Bearing: ", detection.ftcPose.bearing);
//                        MatchConfig.telemetry.addData("Yaw: ", detection.ftcPose.yaw);
//                        MatchConfig.telemetry.update();
//                    }
//                    if(detection != null && Math.abs(detection.ftcPose.bearing) <= Configuration.APRIL_TAG_BEARING && Math.abs(detection.ftcPose.yaw) <= Configuration.APRIL_TAG_YAW) {
//                        mecanumDriveSubsystem.stop();
//                        mecanumDriveSubsystem.breakFollowing();
//                        MatchConfig.telemetry.addLine("FOUND Tag " + visionSubsystem.GetAprilTagID());
//                        isFinished = true;
//                        counter = Integer.MAX_VALUE;
//                    }
//                    else if(detection!= null && Math.abs(detection.ftcPose.bearing) > Configuration.APRIL_TAG_BEARING && !mecanumDriveSubsystem.isBusy()){
//                        t = mecanumDriveSubsystem.trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
//                                .setVelConstraint(new MecanumVelocityConstraint(Configuration.STRAFE_TO_APRIL_TAG_VEL, Configuration.TRACKWIDTH))
//                                .strafeRight(strafeFinal)
//                                .build();
//
//                        mecanumDriveSubsystem.followTrajectorySequenceAsync(t);
//                    }
//                    MatchConfig.telemetry.update();
//                    counter++;
//                    try {
//                        Thread.sleep(250);
//                    }catch(Exception ex){
//
//                    }
//                }
//            }
//
//            if(detection != null && (detection.ftcPose.bearing) <= Configuration.APRIL_TAG_BEARING && (detection.ftcPose.bearing) >= -Configuration.APRIL_TAG_BEARING && Math.abs(detection.ftcPose.yaw) <= Configuration.APRIL_TAG_YAW) {
//                mecanumDriveSubsystem.stop();
//                mecanumDriveSubsystem.breakFollowing();
//                MatchConfig.telemetry.addLine("FOUND Tag " + visionSubsystem.GetAprilTagID());
//                isFinished = true;
//            }
//        }
//        else {
//            MatchConfig.telemetry.addLine("Didn't Find ID: " + visionSubsystem.GetAprilTagID());
//            try {
//                Thread.sleep(250);
//            }catch(Exception ex){}
//        }
//
//
//        MatchConfig.telemetry.addData("Counter: ", executeCounter);
//        MatchConfig.telemetry.update();
//    }

    private double betterCalculatedDistanceToTravel(double bearing, double range, double yaw) {

        double deltaCOMX = range * Math.sin(Math.toRadians(bearing));
        double absDeltaCOMX = Math.abs(deltaCOMX);

        double yawD = 11.5 * (Math.sin(Math.toRadians(yaw)));
        double absYawD = Math.abs(yawD);

        //  The 2 represents an offest to center the bot more
        double finalStrafe = absDeltaCOMX + absYawD + Configuration.AUTO_APRILTAG_STRAFE_OFFSET;

        return finalStrafe;
    }

//    private double returnBearingStrafe(double bearing, double range, double yaw) {
//
//        double returnStrafe = 0.0;
//        if(bearing <= Configuration.APRIL_TAG_BEARING) {
//             returnStrafe = betterCalculatedDistanceToTravel(bearing, range, yaw);
//        }
//        else if(bearing >= -Configuration.APRIL_TAG_BEARING) {
//             returnStrafe = -betterCalculatedDistanceToTravel(bearing, range, yaw);
//        }
//
//        return returnStrafe;
//    }

    @Override
    public boolean isFinished() {
        AprilTagDetection detection = visionSubsystem.findAprilTag();
        if (detection != null && Math.abs(detection.ftcPose.bearing) <= Configuration.APRIL_TAG_BEARING && Math.abs(detection.ftcPose.yaw) <= Configuration.APRIL_TAG_YAW) {
            mecanumDriveSubsystem.stop();
            mecanumDriveSubsystem.breakFollowing();
            MatchConfig.telemetry.addLine("FOUND Tag " + visionSubsystem.GetAprilTagID());
            return true;
        }
        else
            return false;
    }
    @Override
    public void end(boolean interrupted) {

        mecanumDriveSubsystem.stop();
        mecanumDriveSubsystem.breakFollowing();

        AprilTagDetection detection = this.visionSubsystem.findAprilTag();
        if(detection != null) {
            mecanumDriveSubsystem.turn(Math.toRadians(detection.ftcPose.yaw));
        }

        //visionSubsystem.stopAprilStreaming();
    }
}
