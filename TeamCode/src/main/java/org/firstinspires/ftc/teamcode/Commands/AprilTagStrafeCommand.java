package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;

public class AprilTagStrafeCommand extends CommandBase {

    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private Trajectory trajectory;
    private boolean isFinished = false;
    public AprilTagStrafeCommand(MecanumDriveSubsystem mDrive) {
        mecanumDriveSubsystem = mDrive;
        addRequirements(mecanumDriveSubsystem);
    }

    @Override
    public void initialize() {
        if(MatchConfig.TeamPropPosition == TeamPropPosition.Left) {
            trajectory = mecanumDriveSubsystem.trajectoryBuilder(mecanumDriveSubsystem.getPoseEstimate())
                    .strafeRight(Configuration.AUTO_STRAFE_DISTANCE)
                    .build();
        }
        else if(MatchConfig.TeamPropPosition == TeamPropPosition.Right) {
            trajectory = mecanumDriveSubsystem.trajectoryBuilder(mecanumDriveSubsystem.getPoseEstimate())
                    .strafeLeft(Configuration.AUTO_STRAFE_DISTANCE)
                    .build();
        }

    }

    @Override
    public void execute() {
        mecanumDriveSubsystem.followTrajectory(trajectory);
        isFinished = true;
    }

    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
