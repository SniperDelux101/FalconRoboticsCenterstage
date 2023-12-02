package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;

public class MovePixelBoxArmToPositionCommand extends CommandBase {
    private final ExtakeSubsystem extakeSubsystem;
    private final PixelBoxArmPosition pixelBoxArmPosition;

    public MovePixelBoxArmToPositionCommand (ExtakeSubsystem subsystem, PixelBoxArmPosition position){
        extakeSubsystem = subsystem;
        pixelBoxArmPosition =position;
        addRequirements(extakeSubsystem);
    }
    @Override
    public void execute(){
        switch (pixelBoxArmPosition) {
            case PrepExchange:
                extakeSubsystem.setControlArmToPrepExchange();
                break;
            case Exchange:
                extakeSubsystem.setControlArmToExchange();
                break;
            case Extake:
                extakeSubsystem.setControlArmToExtake();
        }
    }

    @Override
    public void end(boolean interrupted){
    }

    @Override
    public boolean isFinished(){
        double percentOff = Math.abs(extakeSubsystem.getControlArm1Position() - pixelBoxArmPosition.getValue())/ pixelBoxArmPosition.getValue();
        return (percentOff <= .1);
    }
}
