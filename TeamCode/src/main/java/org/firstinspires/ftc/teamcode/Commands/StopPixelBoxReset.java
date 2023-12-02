package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class StopPixelBoxReset extends SequentialCommandGroup {

    private final ExtakeSubsystem extakeSubsystem;
    private final LinearSlideSubsystem linearSlideSubsystem;

    public StopPixelBoxReset (ExtakeSubsystem eSubsystem , LinearSlideSubsystem lSubsystem){
        extakeSubsystem = eSubsystem;
        linearSlideSubsystem = lSubsystem;
        addCommands(
                new InstantCommand(()-> {
                    extakeSubsystem.pixelStop();
                }),
                new MoveToPixelBoxPosition(extakeSubsystem, PixelBoxPosition.Center),
                new MovePixelBoxArmToPositionCommand(extakeSubsystem, PixelBoxArmPosition.PrepExchange),
                new RunLinearSlideToPosition(linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_TRANSFER),
                new MovePixelBoxArmToPositionCommand(extakeSubsystem, PixelBoxArmPosition.Exchange),
                new RunLinearSlideToPosition(linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_HOME),
                new InstantCommand(()->{
                    extakeSubsystem.pixelIntake();
                })
        );
        addRequirements(extakeSubsystem, linearSlideSubsystem );
    }
}
