package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;

public class PlacePixelOnSpikeCommand extends CommandBase {

    private final IntakeMotorSubsystem intakeMotorSubsystem;

    public PlacePixelOnSpikeCommand(IntakeMotorSubsystem subsystem) {
        intakeMotorSubsystem = subsystem;
    }

    @Override
    public void execute() {
        intakeMotorSubsystem.eject(0.3);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
