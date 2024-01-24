package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

public class DriveToTeamPropCommand extends CommandBase {
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private TrajectorySequence trajectorySequence;

    public DriveToTeamPropCommand(MecanumDriveSubsystem mecanumDriveSubsystem) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;

        addRequirements(mecanumDriveSubsystem);
    }

    @Override
    public void initialize(){
        trajectorySequence = mecanumDriveSubsystem.trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                .forward(AutonomousState.getInstance().determineDistanceToTeamProp())
                .build();
        mecanumDriveSubsystem.followTrajectorySequenceAsync(trajectorySequence);
    }

    @Override
    public void execute() {
        mecanumDriveSubsystem.update();
    }

    @Override
    public boolean isFinished(){
        return Thread.currentThread().isInterrupted() || !mecanumDriveSubsystem.isBusy();
    }

    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.stop();
    }
}
