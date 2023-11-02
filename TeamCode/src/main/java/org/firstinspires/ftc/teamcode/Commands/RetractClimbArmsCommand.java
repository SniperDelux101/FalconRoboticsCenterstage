package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsystem;

public class RetractClimbArmsCommand extends CommandBase {
    private final ClimbSubsystem climbSubsystem;

    public RetractClimbArmsCommand(ClimbSubsystem subsystem){
        climbSubsystem = subsystem;
        addRequirements(climbSubsystem);
    }

    @Override
    public void initialize()
    {
        climbSubsystem.ClimbIn();
    }
    @Override
    public boolean isFinished(){
       return (climbSubsystem.getCurrentMotorPositions()[0] < 100);
    }
}
