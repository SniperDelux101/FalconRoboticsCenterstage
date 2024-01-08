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
        visionSubsystem.resumeTensorStreaming();
    }
    @Override
    public void loop() {

        // Manage USB bandwidth of two camera streams, by turning on or off.
        if (gamepad1.dpad_down) {
            // Temporarily stop the streaming session. This can save CPU
            // resources, with the ability to resume quickly when needed.
            visionSubsystem.stopTensorStreaming();
        } else if (gamepad1.dpad_up) {
            // Resume the streaming session if previously stopped.
            visionSubsystem.resumeTensorStreaming();
        }
        if (gamepad1.dpad_left) {
            // Temporarily stop the streaming session. This can save CPU
            // resources, with the ability to resume quickly when needed.
            visionSubsystem.stopAprilStreaming();
        } else if (gamepad1.dpad_right) {
            // Resume the streaming session if previously stopped.
            visionSubsystem.resumeAprilStreaming();
        }
        else if(gamepad1.a){
            telemetry.clearAll();
        }

        teamPropPosition = visionSubsystem.getTeamPropPosition();
        visionSubsystem.findAprilTag(583);
        telemetry.addData("Team Prop Position : ", teamPropPosition);
        telemetry.update();
    }

    @Override
    public void stop(){
        visionSubsystem.stopTensorStreaming();
        //visionSubsystem.closeVisionPortal();
    }

}
