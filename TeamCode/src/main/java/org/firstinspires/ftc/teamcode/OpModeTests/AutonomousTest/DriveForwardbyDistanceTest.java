package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.DriveForwardToObjectCommand;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.GyroSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

@Config
@SuppressWarnings("unused")
public class DriveForwardbyDistanceTest extends CommandOpMode {
    private DistanceSensorSubsystem distanceSensorSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    public static double StopDistance = 5.0;
    @Override
    public void initialize() {
        mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        distanceSensorSubsystem = new DistanceSensorSubsystem(hardwareMap, telemetry);


        schedule(new DriveForwardToObjectCommand(mecanumDriveSubsystem, distanceSensorSubsystem,
                GyroSubsystem.getInstance(hardwareMap, telemetry), StopDistance));

    }
}
