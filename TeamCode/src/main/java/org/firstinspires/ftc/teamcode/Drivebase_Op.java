package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.Callisto;
import org.firstinspires.ftc.teamcode.Robots.RobotMode;

@TeleOp
public class Drivebase_Op extends OpMode {

    private Callisto robot;

    public Drivebase_Op() {

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
