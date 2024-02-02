package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class RunLinearSlideAddition extends CommandBase {

    private final LinearSlideSubsystem linearSlideSubsystem;
    private final int targetPosition;

    public RunLinearSlideAddition(LinearSlideSubsystem subsystem, int position) {
        linearSlideSubsystem = subsystem;
        targetPosition = position;
        addRequirements(linearSlideSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        linearSlideSubsystem.runToPosition(targetPosition);
    }

    @Override
    public boolean isFinished() {
        boolean flag = false;
        try {
            int currentPosition = linearSlideSubsystem.getCurrentPosition();
            if ((currentPosition < targetPosition + Configuration.TICK_RANGE) && (currentPosition > targetPosition - Configuration.TICK_RANGE))
                flag = true;
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    @Override
    public void end(boolean interrupted) {
        linearSlideSubsystem.stop();
    }
}