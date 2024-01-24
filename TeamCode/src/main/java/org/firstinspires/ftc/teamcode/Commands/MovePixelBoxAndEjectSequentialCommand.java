package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;

public class MovePixelBoxAndEjectSequentialCommand extends SequentialCommandGroup {

    public MovePixelBoxAndEjectSequentialCommand(LinearSlideSubsystem linearSlideSubsystem, ExtakeSubsystem extakeSubsystem, PixelBoxPosition position){
        addCommands(
                new MoveToPixelBoxPosition( extakeSubsystem, position),
                new WaitCommand(500),
                new EjectPixelCommand(extakeSubsystem, EjectPixelCommand.EjectPixelState.One),
                new WaitCommand(500),
//                new SelectCommand(
//                        new HashMap<Object, Command>() {{
//                            put(0,  new StopPixelBoxReset(extakeSubsystem, linearSlideSubsystem));
//                        }},
//                        // the selector
//                        extakeSubsystem::getPixelCount
//                )
                new ConditionalCommand(
                        new StopPixelBoxReset(extakeSubsystem,linearSlideSubsystem),
                        new InstantCommand(extakeSubsystem::pixelStop, extakeSubsystem),
                        () -> extakeSubsystem.getPixelCount() == 0
                )
        );

        addRequirements(linearSlideSubsystem, extakeSubsystem);
    }
}
