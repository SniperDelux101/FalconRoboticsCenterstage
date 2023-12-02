package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;

public class PlacePixelOnSpikeCommand extends CommandBase {

    private final IntakeMotorSubsystem intakeMotorSubsystem;

    public PlacePixelOnSpikeCommand(IntakeMotorSubsystem subsystem) {
        intakeMotorSubsystem = subsystem;
        addRequirements(intakeMotorSubsystem);
    }

    @Override
    public void execute() {
        intakeMotorSubsystem.eject(0.6);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        intakeMotorSubsystem.stop();
    }
}
