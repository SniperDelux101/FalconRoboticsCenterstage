package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.DriveForwardToObjectCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.FindAprilTagCommand;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.GyroSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

@Config
@SuppressWarnings("unused")
public class AprilTagTest extends CommandOpMode {

    public static FindAprilTagCommand.Direction Direction = FindAprilTagCommand.Direction.Left;
    public static double DistanceToBackDropStop = 5;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();

        MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        VisionSubsystem visionSubsystem = new VisionSubsystem(hardwareMap, telemetry);
        DistanceSensorSubsystem distanceSensorSubsystem = new DistanceSensorSubsystem(hardwareMap, telemetry);

        schedule(new SequentialCommandGroup(
                new FindAprilTagCommand(mecanumDriveSubsystem, visionSubsystem, Direction),
                new DriveForwardToObjectCommand(mecanumDriveSubsystem, distanceSensorSubsystem, GyroSubsystem.getInstance(hardwareMap, telemetry), DistanceToBackDropStop)
                ));

        register(mecanumDriveSubsystem, visionSubsystem, distanceSensorSubsystem);
    }
}
