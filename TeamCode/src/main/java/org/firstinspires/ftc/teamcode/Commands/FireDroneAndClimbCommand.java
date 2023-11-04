package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.AirplaneLauncherSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsystem;

public class FireDroneAndClimbCommand extends SequentialCommandGroup {
    private final AirplaneLauncherSubsystem airplaneLauncherSubsystem;
    private final ClimbSubsystem climbSubsystem;

    public FireDroneAndClimbCommand(AirplaneLauncherSubsystem airplanSubSystem, ClimbSubsystem cSubSystem){
        airplaneLauncherSubsystem = airplanSubSystem;
        climbSubsystem = cSubSystem;
        addCommands(new InstantCommand(()->{
                    airplaneLauncherSubsystem.release();
        }),
                new RetractClimbArmsCommand(climbSubsystem));

        addRequirements(airplanSubSystem, climbSubsystem);
    }
}
