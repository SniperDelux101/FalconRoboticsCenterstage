package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

@Config
@TeleOp(group = "subsystems test")
public class LinearSlideTest extends OpMode {


    private LinearSlideSubsystem linearSlideSubsystem;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        linearSlideSubsystem = new LinearSlideSubsystem(hardwareMap);
    }


    @Override
    public void loop() {

        double pos = linearSlideSubsystem.LinearCurPos();

        if(gamepad1.dpad_left && gamepad1.y) {
            Configuration.LINEAR_SLIDE_POS_LO += Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosLo();
        }
        if(gamepad1.dpad_left && gamepad1.a) {
            Configuration.LINEAR_SLIDE_POS_LO -= Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosLo();
        }
        if(gamepad1.dpad_up && gamepad1.y) {
            Configuration.LINEAR_SLIDE_POS_MED += Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosMed();
        }
        if(gamepad1.dpad_up && gamepad1.a) {
            Configuration.LINEAR_SLIDE_POS_MED -= Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosMed();
        }
        if(gamepad1.dpad_right && gamepad1.y) {
            Configuration.LINEAR_SLIDE_POS_HI += Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosHi();
        }
        if(gamepad1.dpad_right && gamepad1.a) {
            Configuration.LINEAR_SLIDE_POS_HI -= Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosHi();
        }
        if(gamepad1.left_stick_button) {
            linearSlideSubsystem.LinearPosLo();
        }
        if(gamepad1.touchpad) {
            linearSlideSubsystem.LinearPosMed();
        }
        if(gamepad1.right_stick_button) {
            linearSlideSubsystem.LinearPosHi();
        }

        telemetry.addData("Current Motor Position : ", pos);
        telemetry.update();

    }

}