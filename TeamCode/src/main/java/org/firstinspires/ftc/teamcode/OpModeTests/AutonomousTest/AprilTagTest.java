package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import android.service.autofill.FieldClassification;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.DriveForwardToObjectCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.FindAprilTagCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.GyroSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.opencv.core.Mat;

@Config
@SuppressWarnings("unused")
public class AprilTagTest extends CommandOpMode {
    public static double DistanceToBackDropStop = .75;
    public static Alliance alliance = Alliance.Blue;
    public static TeamPropPosition teamPropPosition = TeamPropPosition.Center;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();

        MatchConfig.Alliance = alliance;
        MatchConfig.TeamPropPosition = teamPropPosition;
        MatchConfig.telemetry = telemetry;

        MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        VisionSubsystem visionSubsystem = new VisionSubsystem(hardwareMap, telemetry);
        DistanceSensorSubsystem distanceSensorSubsystem = new DistanceSensorSubsystem(hardwareMap, telemetry);

        schedule(new SequentialCommandGroup(
                new FindAprilTagCommand(mecanumDriveSubsystem, visionSubsystem),
                new DriveForwardToObjectCommand(mecanumDriveSubsystem, distanceSensorSubsystem, GyroSubsystem.getInstance(hardwareMap, telemetry), DistanceToBackDropStop)
                ));

        register(mecanumDriveSubsystem, visionSubsystem, distanceSensorSubsystem);
    }

    @Override
    public void runOpMode() throws InterruptedException{
        super.runOpMode();
        MatchConfig.telemetry.update();
    }
}
