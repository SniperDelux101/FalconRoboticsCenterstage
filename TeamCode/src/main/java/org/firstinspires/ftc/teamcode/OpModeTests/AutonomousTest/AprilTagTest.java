package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import android.service.autofill.FieldClassification;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.AprilTagStartStopCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.DriveForwardToObjectCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.FindAprilTagCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Commands.DriveToAprilTagCommand;
import org.firstinspires.ftc.teamcode.Commands.StrafeToFindAprilTagCommand;
import org.firstinspires.ftc.teamcode.Commands.YawTurnCommand;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.GyroSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.opencv.core.Mat;

@Config
@SuppressWarnings("unused")
@Autonomous
public class AprilTagTest extends CommandOpMode {
    public static Alliance alliance = Alliance.Blue;
    public static TeamPropPosition teamPropPosition = TeamPropPosition.Center;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        MatchConfig.Alliance = alliance;
        MatchConfig.TeamPropPosition = teamPropPosition;
        MatchConfig.telemetry = telemetry;

        MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        VisionSubsystem visionSubsystem = new VisionSubsystem(hardwareMap, telemetry, true, true);
        DistanceSensorSubsystem distanceSensorSubsystem = new DistanceSensorSubsystem(hardwareMap, telemetry);

        schedule(new SequentialCommandGroup(
                //new AprilTagStartStopCommand(visionSubsystem, AprilTagStartStopCommand.State.Start),
                //new StrafeToFindAprilTagCommand(mecanumDriveSubsystem, visionSubsystem),
                new DriveToAprilTagCommand(visionSubsystem, mecanumDriveSubsystem)
                //new DriveForwardToObjectCommand(mecanumDriveSubsystem, distanceSensorSubsystem, GyroSubsystem.getInstance(hardwareMap, telemetry), Configuration.BACKDROP_DISTANCE),
                //new AprilTagStartStopCommand(visionSubsystem, AprilTagStartStopCommand.State.ClosePortal)
                ));

        register(mecanumDriveSubsystem, visionSubsystem, distanceSensorSubsystem, GyroSubsystem.getInstance(hardwareMap, telemetry));
    }

//    @Override
//    public void runOpMode() throws InterruptedException{
//        try {
//            super.runOpMode();
//        }
//        catch(Exception ex){
//            MatchConfig.telemetry.addLine(ex.getMessage());
//        }
//        MatchConfig.telemetry.update();
//    }
}
