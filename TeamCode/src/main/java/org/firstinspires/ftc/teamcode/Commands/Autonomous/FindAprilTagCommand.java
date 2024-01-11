package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

public class FindAprilTagCommand extends CommandBase {
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final VisionSubsystem visionSubsystem;
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
            Trajectory trajectory;

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
            this.mecanumDriveSubsystem.followTrajectoryAsync(trajectory);
        }
        else {
            ///TODO: We should try to find the tag if we can.  Based on the current Pose and Alliance we should be able to find a starting point to start over
            MatchConfig.telemetry.addLine("NO April tag found, WHAT SHOULD WE DO?????");

        }

        MatchConfig.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        AprilTagDetection detection = this.visionSubsystem.findAprilTag();
        if(detection != null) {
            MatchConfig.telemetry.addLine("Tag ID " + visionSubsystem.GetAprilTagID());
            MatchConfig.telemetry.addData("Bearing: ", detection.ftcPose.bearing);
            MatchConfig.telemetry.addData("Range: ", detection.ftcPose.range);
            MatchConfig.telemetry.addData("Yaw: ", detection.ftcPose.yaw);
            MatchConfig.telemetry.update();

            return Math.abs(detection.ftcPose.bearing) < Configuration.APRIL_TAG_BEARING;
        }
        else
            return false;
    }

    @Override
    public void end(boolean interrupted) {
        this.mecanumDriveSubsystem.stop();
        visionSubsystem.stopAprilStreaming();
    }



}
