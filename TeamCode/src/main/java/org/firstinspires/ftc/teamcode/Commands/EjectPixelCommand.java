package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;

public class EjectPixelCommand extends CommandBase {
    public enum EjectPixelState{
        One,
        All
    }
    private final ExtakeSubsystem extakeSubsystem;
    private final EjectPixelState ejectPixelState;

    public EjectPixelCommand(ExtakeSubsystem system, EjectPixelState state){
        extakeSubsystem = system;
        ejectPixelState = state;
        addRequirements(extakeSubsystem);
    }

    @Override
    public void initialize() {
        if(ejectPixelState == EjectPixelState.One)
            extakeSubsystem.ejectSinglePixel();
        else
            extakeSubsystem.pixelEject();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
