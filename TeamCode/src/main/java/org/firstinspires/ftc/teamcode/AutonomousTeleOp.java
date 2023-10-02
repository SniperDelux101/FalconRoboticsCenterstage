package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robots.Callisto;
import org.firstinspires.ftc.teamcode.Robots.RobotMode;

@Autonomous
public class AutonomousTeleOp extends OpMode {
    private Callisto robot;

    public AutonomousTeleOp() {

    }
    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        robot = new Callisto(RobotMode.AUTO, hardwareMap, gamepad1, gamepad2, telemetry);
    }

    @Override
    public void loop() {
        robot.run();
    }
}
