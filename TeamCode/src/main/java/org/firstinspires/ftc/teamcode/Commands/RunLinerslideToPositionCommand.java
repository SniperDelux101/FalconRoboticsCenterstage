package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;

public class RunLinerslideToPositionCommand extends CommandBase {
    private final LinearSlideSubsystem linearSlideSubsystem;
    private final int targetPosition;

    public RunLinerslideToPositionCommand(LinearSlideSubsystem subsystem, int position){
        linearSlideSubsystem = subsystem;
        targetPosition = position;
        addRequirements(linearSlideSubsystem);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        linearSlideSubsystem.runToPosition(targetPosition);
    }
    @Override
    public boolean isFinished(){
        return linearSlideSubsystem.isSlideAtTargetPosition();
    }

    @Override
    public void end(boolean interrupted){
        if(interrupted)
            linearSlideSubsystem.stop();
    }
}
