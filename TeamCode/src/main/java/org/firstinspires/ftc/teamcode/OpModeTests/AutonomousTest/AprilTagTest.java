package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

@Config
public class AprilTagTest extends CommandOpMode {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private VisionSubsystem visionSubsystem;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();

        mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        visionSubsystem = new VisionSubsystem(hardwareMap, telemetry);



        register(mecanumDriveSubsystem, visionSubsystem);

    }
}
