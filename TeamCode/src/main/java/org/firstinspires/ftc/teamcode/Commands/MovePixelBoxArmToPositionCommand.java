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
            case Default:
                extakeSubsystem.setControlArmToRest();
                break;
            case Intake:
                extakeSubsystem.setControlArmToIntake();
                break;
            case Extake:
                extakeSubsystem.setControlArmToExtake();
        }
    }
}
