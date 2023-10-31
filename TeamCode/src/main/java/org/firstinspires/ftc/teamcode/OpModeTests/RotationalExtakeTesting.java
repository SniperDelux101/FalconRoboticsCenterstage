package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.RotationalExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

@Config
@TeleOp(group = "subsystems test")
public class RotationalExtakeTesting extends OpMode {

    private RotationalExtakeSubsystem rotationalExtakeSubsystem;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        rotationalExtakeSubsystem = new RotationalExtakeSubsystem(hardwareMap);
    }
    @Override
    public void loop() {
        if(gamepad1.dpad_left && gamepad1.x) {
            Configuration.R_SERVO_LEFT -= Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.leftRotation();
        }
        if(gamepad1.dpad_left && gamepad1.b) {
            Configuration.R_SERVO_LEFT += Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.leftRotation();
        }
        if(gamepad1.dpad_up && gamepad1.x) {
            Configuration.R_SERVO_CENTER -= Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.centerRotation();
        }
        if(gamepad1.dpad_up && gamepad1.b) {
            Configuration.R_SERVO_CENTER += Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.centerRotation();
        }
        if(gamepad1.dpad_right && gamepad1.x) {
            Configuration.R_SERVO_RIGHT -= Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.rightRotation();
        }
        if(gamepad1.dpad_right && gamepad1.b) {
            Configuration.R_SERVO_RIGHT += Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.rightRotation();
        }
    }
}