package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;


/*
This testing program allows for the configuration of the linear slide positioning
    ** All of this testing only utilizes controller 1 **

    left D-Pad & Y Button ------->  Low position Up
    left D-Pad & A Button ------->  Low position Down

    up D-Pad & Y Button -------> Medium position Up
    up D-Pad & A Button -------> Medium position Down

    right D-Pad & Y Button -------> High position Up
    right D-Pad & A Button -------> High position Down

    down D-Pad & Y Button -------> Transfer position Up
    down D-Pad & A Button -------> Transfer position Down

    right bumper & left D-Pad -------> Low Position
    right bumper & up D-Pad -------> Medium Position
    right bumper & right D-Pad -------> High Position
    right bumper & down D-Pad -------> Transfer Position

 */

@TeleOp(group = "subsystems test")
public class LinearSlideTest extends OpMode {


    private LinearSlideSubsystem linearSlideSubsystem;
    private double pos;
    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        linearSlideSubsystem = new LinearSlideSubsystem(hardwareMap);
    }
    @Override
    public void loop() {


        if(gamepad1.dpad_left && gamepad1.y) {
            Configuration.LINEAR_SLIDE_POS_LO += Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosLo();
        }
        else if(gamepad1.dpad_left && gamepad1.a) {
            Configuration.LINEAR_SLIDE_POS_LO -= Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosLo();
        }
        else if(gamepad1.dpad_up && gamepad1.y) {
            Configuration.LINEAR_SLIDE_POS_MED += Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosMed();
        }
        else if(gamepad1.dpad_up && gamepad1.a) {
            Configuration.LINEAR_SLIDE_POS_MED -= Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosMed();
        }
        else if(gamepad1.dpad_right && gamepad1.y) {
            Configuration.LINEAR_SLIDE_POS_HI += Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosHi();
        }
        else if(gamepad1.dpad_right && gamepad1.a) {
            Configuration.LINEAR_SLIDE_POS_HI -= Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosHi();
        }
        else if(gamepad1.dpad_down && gamepad1.y) {
            Configuration.LINEAR_SLIDE_POS_TRANSFER += Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosTransfer();
        }
        else if(gamepad1.dpad_down && gamepad1.a) {
            Configuration.LINEAR_SLIDE_POS_TRANSFER -= Configuration.LINEAR_SLIDE_MULTIPLIER;
            linearSlideSubsystem.LinearPosTransfer();
        }
        else if(gamepad1.dpad_left && gamepad1.right_bumper) {
            linearSlideSubsystem.LinearPosLo();
        }
        else if(gamepad1.dpad_up && gamepad1.right_bumper) {
            linearSlideSubsystem.LinearPosMed();
        }
        else if(gamepad1.dpad_right && gamepad1.right_bumper) {
            linearSlideSubsystem.LinearPosHi();
        }
        else if(gamepad1.dpad_down && gamepad1.right_bumper) {
            linearSlideSubsystem.LinearPosTransfer();
        }
        else if (gamepad1.dpad_down && gamepad1.left_bumper){
            linearSlideSubsystem.LinearPosHome();
        }
        pos = linearSlideSubsystem.LinearCurPos();
        telemetry.addData("Current Motor Position : ", pos);
        telemetry.update();

    }

}