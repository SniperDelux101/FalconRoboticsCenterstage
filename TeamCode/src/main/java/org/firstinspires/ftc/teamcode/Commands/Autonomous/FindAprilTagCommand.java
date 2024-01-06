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
        AprilTagDetection detection = this.visionSubsystem.findAprilTag(GetAprilTagID());
        if(detection != null && !hasExecuted) {
            MatchConfig.telemetry.addLine("Tag ID " + GetAprilTagID());
            MatchConfig.telemetry.addData("Bearing: ", detection.ftcPose.bearing);
            MatchConfig.telemetry.addData("Range: ", detection.ftcPose.range);
            MatchConfig.telemetry.addData("Yaw: ", detection.ftcPose.yaw);
            double strafeDistance = Math.abs(detection.ftcPose.range * (Math.sin(Math.toRadians(detection.ftcPose.bearing))));

            if (detection.ftcPose.bearing > 0) {
                trajectory = this.mecanumDriveSubsystem.trajectoryBuilder(this.mecanumDriveSubsystem.getPoseEstimate())
                        .strafeRight(strafeDistance)
//                        .forward(detection.ftcPose.range * (Math.cos(detection.ftcPose.bearing)))
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
    public void end(boolean interrupted) {
        this.mecanumDriveSubsystem.stop();
        visionSubsystem.stopAprilStreaming();
        MatchConfig.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        AprilTagDetection detection = this.visionSubsystem.findAprilTag(GetAprilTagID());
        if(detection != null) {
            MatchConfig.telemetry.addLine("Tag ID " + GetAprilTagID());
            MatchConfig.telemetry.addData("Bearing: ", detection.ftcPose.bearing);
            MatchConfig.telemetry.addData("Range: ", detection.ftcPose.range);
            MatchConfig.telemetry.addData("Yaw: ", detection.ftcPose.yaw);

            return (Math.abs(detection.ftcPose.bearing) < 1);
        }
        return false;
    }

    private int GetAprilTagID()
    {
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
}
