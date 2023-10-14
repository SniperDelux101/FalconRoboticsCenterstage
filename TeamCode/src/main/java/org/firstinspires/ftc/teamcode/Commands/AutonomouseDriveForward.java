package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;


import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;

public class AutonomouseDriveForward extends CommandBase {

    private final MecanumDriveSubsystem driveBaseSubsystem;
    private final double distance, speed;
    Trajectory trajectory;

    public AutonomouseDriveForward(MecanumDriveSubsystem driveBase, double dist, double spd) {
        driveBaseSubsystem = driveBase;
        distance = dist;
        if(spd > 1)
            spd = 1;
        else if (spd <=0)
            spd = 0.1;
        speed = spd;
        addRequirements(driveBaseSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();

        trajectory = driveBaseSubsystem.trajectoryBuilder(new com.acmerobotics.roadrunner.geometry.Pose2d())
                .forward(distance)
                .build();

    }

    @Override
    public void execute(){
        driveBaseSubsystem.followTrajectoryAsync(trajectory);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        driveBaseSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return !driveBaseSubsystem.isBusy();
    }
}
