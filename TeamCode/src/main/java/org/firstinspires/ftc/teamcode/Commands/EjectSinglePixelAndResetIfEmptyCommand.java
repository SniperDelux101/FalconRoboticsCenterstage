package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;

public class EjectSinglePixelAndResetIfEmptyCommand extends SequentialCommandGroup {

    private final ExtakeSubsystem extakeSubsystem;
    private final LinearSlideSubsystem linearSlideSubsystem;

    public EjectSinglePixelAndResetIfEmptyCommand(ExtakeSubsystem e_subSystem, LinearSlideSubsystem l_subSystem){
        extakeSubsystem = e_subSystem;
        linearSlideSubsystem = l_subSystem;

        addCommands(
                new EjectPixelCommand(extakeSubsystem, EjectPixelCommand.EjectPixelState.One),
                new ConditionalCommand(
                        new StopPixelBoxReset(extakeSubsystem, linearSlideSubsystem),
                        new WaitCommand(1),
                        () -> {
                            return extakeSubsystem.getPixelCount() == 0;
                        }
                )
        );

        addRequirements(extakeSubsystem, linearSlideSubsystem);
    }
}
