package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class IntakeCommand extends CommandBase {

    private final IntakeMotorSubsystem intakeMotorSubsystem;
    private final IntakeMotorSubsystem.Direction direction;

    public IntakeCommand(IntakeMotorSubsystem intakeMotorSubsystem, IntakeMotorSubsystem.Direction direction) {
        this.intakeMotorSubsystem = intakeMotorSubsystem;
        this.direction = direction;
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(direction == IntakeMotorSubsystem.Direction.Intake)
            intakeMotorSubsystem.intake();
        else
            intakeMotorSubsystem.eject();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        intakeMotorSubsystem.stop();
    }
}
