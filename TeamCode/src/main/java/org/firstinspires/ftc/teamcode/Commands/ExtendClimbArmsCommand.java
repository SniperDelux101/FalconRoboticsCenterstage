package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class ExtendClimbArmsCommand extends CommandBase {
    private final ClimbSubsystem climbSubsystem;

    public ExtendClimbArmsCommand(ClimbSubsystem subsystem){
        climbSubsystem = subsystem;
        addRequirements(climbSubsystem);
    }

    @Override
    public void initialize(){
        climbSubsystem.ClimbOut();
    }

    @Override
    public boolean isFinished(){
        return climbSubsystem.getCurrentMotorPositions()[0] > Configuration.CLIMB_OUT * .9;
    }
}
