package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

public class FindAprilTagCommand extends CommandBase {
    public enum Direction{
        Left,
        Right
    }
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final VisionSubsystem visionSubsystem;

    public double distance_to_Tag = 0.0;
    private Trajectory trajectory;
    private Trajectory forwardTrajectory;
    private boolean hasExecuted = false;


    public FindAprilTagCommand(MecanumDriveSubsystem mecanumDriveSubsystem, VisionSubsystem visionSubsystem) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.visionSubsystem = visionSubsystem;

        addRequirements(this.mecanumDriveSubsystem, this.visionSubsystem);
    }

    @Override
    public void initialize() {
        this.visionSubsystem.resumeAprilStreaming();
    }

    @Override
    public void execute() {
        MatchConfig.telemetry.addLine("Executing " + this.getClass().getName());

        AprilTagDetection detection = this.visionSubsystem.findAprilTag();
        if(detection != null && !hasExecuted) {
            MatchConfig.telemetry.addLine("Tag ID " + visionSubsystem.GetAprilTagID());
            MatchConfig.telemetry.addData("Bearing: ", detection.ftcPose.bearing);
            MatchConfig.telemetry.addData("Range: ", detection.ftcPose.range);
            MatchConfig.telemetry.addData("Yaw: ", detection.ftcPose.yaw);

            double strafeSin = Math.sin(Math.toRadians(detection.ftcPose.bearing));
            double strafeDistance = Math.abs(detection.ftcPose.range * (strafeSin));
//            double backCos = Math.cos(detection.ftcPose.bearing);
//            double backDistance = ((Math.abs(detection.ftcPose.range * (backCos))) - Configuration.BACKDROP_DISTANCE);

            if (detection.ftcPose.bearing > 0) {
                trajectory = this.mecanumDriveSubsystem.trajectoryBuilder(this.mecanumDriveSubsystem.getPoseEstimate())
                     .strafeRight(strafeDistance)
                        .build();
            } else {
                trajectory = this.mecanumDriveSubsystem.trajectoryBuilder(this.mecanumDriveSubsystem.getPoseEstimate())
                        .strafeLeft(strafeDistance)
                        .build();
            }
            hasExecuted = true;
            this.mecanumDriveSubsystem.followTrajectory(trajectory);
        }
        else
            MatchConfig.telemetry.addLine("NO April tag found");

        MatchConfig.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        AprilTagDetection detection = this.visionSubsystem.findAprilTag(visionSubsystem.GetAprilTagID());
        if(detection != null) {
            MatchConfig.telemetry.addLine("Tag ID " + visionSubsystem.GetAprilTagID());
            MatchConfig.telemetry.addData("Bearing: ", detection.ftcPose.bearing);
            MatchConfig.telemetry.addData("Range: ", detection.ftcPose.range);
            MatchConfig.telemetry.addData("Yaw: ", detection.ftcPose.yaw);
            MatchConfig.telemetry.update();

//            return (Math.abs(detection.ftcPose.bearing) < 1
//                    && Math.abs(Configuration.BACKDROP_DISTANCE - detection.ftcPose.range) <= Configuration.DISTANCE_ERROR_DISTANCE);
        }
//        return false;

        return !this.mecanumDriveSubsystem.getDrive().isBusy();
    }

    @Override
    public void end(boolean interrupted) {
        this.mecanumDriveSubsystem.stop();
        visionSubsystem.stopAprilStreaming();
        MatchConfig.telemetry.update();
    }



}
