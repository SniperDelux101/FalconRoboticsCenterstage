package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.FindAprilTagCommand;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

@Config
@SuppressWarnings("unused")
public class AprilTagTest extends CommandOpMode {

    public static FindAprilTagCommand.Direction Direction = FindAprilTagCommand.Direction.Left;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();

        MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        VisionSubsystem visionSubsystem = new VisionSubsystem(hardwareMap, telemetry);

        FindAprilTagCommand command = new FindAprilTagCommand(mecanumDriveSubsystem, visionSubsystem, Direction);
        schedule(command);

        register(mecanumDriveSubsystem, visionSubsystem);

    }
}
