package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;

import java.util.List;

@Config
@TeleOp(group = "subsystems test")
public class AutoVisionSystemTest extends OpMode {
    private VisionSubsystem visionSubsystem ;
    public static TeamPropPosition teamPropPosition = TeamPropPosition.Center;
    private boolean getRecognition = true;

    public static Alliance alliance = Alliance.Blue;
    public static AutonomousStartLocation autonomousStartLocation = AutonomousStartLocation.Near;

    @Override
    public void init() {

        MatchConfig.Alliance = alliance;
        MatchConfig.AutonomousStartLocation = autonomousStartLocation;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
         visionSubsystem= new VisionSubsystem(hardwareMap, telemetry, true);

    }
    @Override
    public void loop() {
        if (getRecognition) {
            teamPropPosition = visionSubsystem.getTeamPropPosition();
            getRecognition = true;
        }
        telemetry.addData("Team Prop Position : ", teamPropPosition);
        telemetry.update();
    }

}
