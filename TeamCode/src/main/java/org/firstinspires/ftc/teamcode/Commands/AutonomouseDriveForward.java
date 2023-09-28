package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DriveBaseSubsystem;

public class AutonomouseDriveForward extends CommandBase {

    private final DriveBaseSubsystem driveBaseSubsystem;
    private final double forward, speed;

    public AutonomouseDriveForward(DriveBaseSubsystem driveBase, double fwd, double spd) {
        driveBaseSubsystem = driveBase;
        forward = fwd;
        if(spd > 1)
            spd = 1;
        else if (spd <=0)
            spd = 0.1;
        speed = spd;
    }

    @Override
    public void initialize() {
        super.initialize();
        driveBaseSubsystem.drive(0.0, speed, 0.0);
    }

   @Override
   public void execute(){
        super.execute();
        driveBaseSubsystem.drive(0.0, speed, 0.0);
   }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        driveBaseSubsystem.drive(0.0,0.0,0.0);
    }

    @Override
    public boolean isFinished() {
        return (driveBaseSubsystem.getOdometryDistance() >= forward);
    }
}
