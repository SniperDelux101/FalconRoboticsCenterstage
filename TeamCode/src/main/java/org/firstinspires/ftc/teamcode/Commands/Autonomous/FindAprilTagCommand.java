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
    private final Direction strafeDirection;
    public static double APRIL_TAG_STRAFE_DISTANCE = 60;
    public double distance_to_Tag = 0.0;
    private Trajectory trajectory;


    public FindAprilTagCommand(MecanumDriveSubsystem mecanumDriveSubsystem, VisionSubsystem visionSubsystem, Direction direction) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.visionSubsystem = visionSubsystem;
        this.strafeDirection = direction;

        addRequirements(this.mecanumDriveSubsystem, this.visionSubsystem);
    }

    @Override
    public void initialize() {

        this.visionSubsystem.resumeStreaming();

        if(this.strafeDirection == Direction.Right) {
            trajectory = this.mecanumDriveSubsystem.trajectoryBuilder(this.mecanumDriveSubsystem.getPoseEstimate())
                    .strafeRight(APRIL_TAG_STRAFE_DISTANCE)
                    .build();
        }
        else{
            trajectory = this.mecanumDriveSubsystem.trajectoryBuilder(this.mecanumDriveSubsystem.getPoseEstimate())
                    .strafeLeft(APRIL_TAG_STRAFE_DISTANCE)
                    .build();
        }
    }

    @Override
    public void execute() {
        this.mecanumDriveSubsystem.followTrajectoryAsync(trajectory);
    }

    @Override
    public void end(boolean interrupted) {
        this.mecanumDriveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return FoundAprilTag();
    }

    private boolean FoundAprilTag() {
        AprilTagDetection detection = this.visionSubsystem.findAprilTag(GetAprilTagID());
        if(detection == null)
            return false;
        else if(Math.abs(detection.ftcPose.bearing) < .05 ){
            distance_to_Tag = detection.ftcPose.range;
            return true;
        }
        return false;
    }

    private int GetAprilTagID()
    {
        if(MatchConfig.Alliance == Alliance.Red) {
            return 5;
        }
        else {
            return 2;
        }
    }
}
