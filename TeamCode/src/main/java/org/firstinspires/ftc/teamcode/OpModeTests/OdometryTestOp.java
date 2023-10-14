package org.firstinspires.ftc.teamcode.OpModeTests;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.OdometryControlSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;


@Config
@TeleOp(group = "subsystems test")
public class OdometryTestOp extends OpMode {

    private OdometryControlSubsystem odometryControlSubsystem;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        odometryControlSubsystem = new OdometryControlSubsystem(hardwareMap);
    }

    @Override
    public void loop() {

        if(gamepad1.dpad_up) {
            Configuration.ODO_RETRACT_POSITION += Configuration.ODO_SERVO_MULTIPLIER;
            odometryControlSubsystem.retract();
        }

        if(gamepad1.dpad_down) {
            Configuration.ODO_RETRACT_POSITION -= Configuration.ODO_SERVO_MULTIPLIER;
            odometryControlSubsystem.retract();
        }

        if(gamepad1.dpad_left) {
            Configuration.ODO_DROP_POSITION += Configuration.ODO_SERVO_MULTIPLIER;
            odometryControlSubsystem.drop();
        }

        if(gamepad1.dpad_right) {
            Configuration.ODO_DROP_POSITION -= Configuration.ODO_SERVO_MULTIPLIER;
            odometryControlSubsystem.drop();
        }

        if(gamepad1.a) {
            odometryControlSubsystem.retract();
        }

        if(gamepad1.b) {
            odometryControlSubsystem.drop();
        }

        telemetry.addData("Odometry Servo Retract Position : ", Configuration.ODO_RETRACT_POSITION);
        telemetry.addData("Odometry Servo Drop Position : ", Configuration.ODO_DROP_POSITION);
        telemetry.update();

    }

}
