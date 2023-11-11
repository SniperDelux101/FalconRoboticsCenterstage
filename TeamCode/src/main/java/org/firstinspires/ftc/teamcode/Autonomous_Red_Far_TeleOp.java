package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Robots.Callisto;
import org.firstinspires.ftc.teamcode.Robots.RobotMode;

@Autonomous(preselectTeleOp = "Drivebase_Op")
public class Autonomous_Red_Far_TeleOp extends OpMode {
    private Callisto robot;

    public Autonomous_Red_Far_TeleOp() {

    }
    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        robot = new Callisto(RobotMode.AUTO, hardwareMap, gamepad1, gamepad2, telemetry, Alliance.Red, AutonomousStartLocation.Far);
    }

    @Override
    public void loop() {
        robot.run();
    }
}
