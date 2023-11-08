package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;

public class RunLinearSlideAndCenterPixelBoxCommand extends SequentialCommandGroup {
    private final ExtakeSubsystem extakeSubsystem;
    private final LinearSlideSubsystem linearSlideSubsystem;
    public RunLinearSlideAndCenterPixelBoxCommand(ExtakeSubsystem eSubSystem, LinearSlideSubsystem lSubSystem, int position){
        extakeSubsystem = eSubSystem;
        linearSlideSubsystem = lSubSystem;

        addCommands(
                new MoveToPixelBoxPosition(extakeSubsystem, PixelBoxPosition.Center),
                new RunLinearSlideToPosition(linearSlideSubsystem, position)
        );

        addRequirements(extakeSubsystem, linearSlideSubsystem);
    }
}
