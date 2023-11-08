package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.AirplaneLauncherSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class LaunchDrone extends CommandBase {

    private final AirplaneLauncherSubsystem airplaneLauncherSubsystem;
    public LaunchDrone(AirplaneLauncherSubsystem subsystem){
        airplaneLauncherSubsystem = subsystem;
        addRequirements(airplaneLauncherSubsystem);
    }
    @Override
    public void initialize(){
        airplaneLauncherSubsystem.release();
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted){
        if(interrupted)
            airplaneLauncherSubsystem.stop();
    }

    @Override
    public boolean isFinished(){
        return (airplaneLauncherSubsystem.getLanucherServoPosition() == Configuration.LAUNCH_RELEASE);
    }

}
