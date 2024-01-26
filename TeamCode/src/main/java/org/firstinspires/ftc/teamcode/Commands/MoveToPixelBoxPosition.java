package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;


public class MoveToPixelBoxPosition extends CommandBase {
    private final ExtakeSubsystem extakeSubsystem;
    private final PixelBoxPosition pixelBoxPosition;

    public MoveToPixelBoxPosition (ExtakeSubsystem subsystem , PixelBoxPosition position) {
        extakeSubsystem = subsystem;
        pixelBoxPosition = position;
        addRequirements(extakeSubsystem);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        if()
        switch (pixelBoxPosition) {
            case Left:
                extakeSubsystem.leftRotation();
                break;
            case Right:
                extakeSubsystem.rightRotation();
                break;
            default:
                extakeSubsystem.centerRotation();
        }
    }
    @Override
    public boolean isFinished(){
        return true ;
    }



}
