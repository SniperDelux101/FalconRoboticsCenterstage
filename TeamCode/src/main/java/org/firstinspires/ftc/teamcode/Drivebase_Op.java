package org.firstinspires.ftc.teamcode;


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
        robot = new Callisto(RobotMode.TELEOP, hardwareMap, gamepad1, gamepad2);
    }

    @Override
    public void loop() {
        robot.run();

    }

}
