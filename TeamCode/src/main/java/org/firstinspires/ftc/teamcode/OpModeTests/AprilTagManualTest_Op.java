package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Commands.DriveToAprilTagCommand;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;

@Autonomous
@Config
public class AprilTagManualTest_Op extends LinearOpMode {

    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private VisionSubsystem visionSubsystem;
    public static Alliance alliance = Alliance.Blue;
    public static TeamPropPosition teamPropPosition = TeamPropPosition.Center;

    @Override public void runOpMode(){
        CommandScheduler.getInstance().reset();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        MatchConfig.Alliance = alliance;
        MatchConfig.TeamPropPosition = teamPropPosition;
        MatchConfig.telemetry = telemetry;

        mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        visionSubsystem = new VisionSubsystem(hardwareMap, telemetry, true, true);
        DriveToAprilTagCommand cmd = new DriveToAprilTagCommand(visionSubsystem, mecanumDriveSubsystem);
        waitForStart();

        while (opModeIsActive())
        {
            CommandScheduler.getInstance().run();
            cmd.execute();
            if(cmd.isFinished()) {
                cmd.end(false);
                requestOpModeStop();
            }
        }
    }
}
