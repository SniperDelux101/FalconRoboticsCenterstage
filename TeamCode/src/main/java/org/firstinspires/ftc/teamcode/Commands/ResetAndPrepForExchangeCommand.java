package org.firstinspires.ftc.teamcode.Commands;

import android.annotation.SuppressLint;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class ResetAndPrepForExchangeCommand extends SequentialCommandGroup {
    private final ExtakeSubsystem extakeSubsystem;
    private final LinearSlideSubsystem linearSlideSubsystem;

    @SuppressLint("NotConstructor")
    public ResetAndPrepForExchangeCommand(ExtakeSubsystem eSub, LinearSlideSubsystem lSub){
        extakeSubsystem = eSub;
        linearSlideSubsystem = lSub;

        addCommands(
                new InstantCommand(extakeSubsystem::centerRotation, extakeSubsystem),
                new MovePixelBoxArmToPositionCommand(extakeSubsystem, PixelBoxArmPosition.PrepExchange),
                new RunLinearSlideAndCenterPixelBoxCommand(extakeSubsystem, linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_TRANSFER),
                new MovePixelBoxArmToPositionCommand(extakeSubsystem, PixelBoxArmPosition.Extake),
                new InstantCommand(extakeSubsystem::pixelIntake, extakeSubsystem)
        );

        addRequirements(extakeSubsystem, linearSlideSubsystem);
    }

}
