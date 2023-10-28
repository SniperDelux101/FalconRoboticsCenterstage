package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;

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

        if(gamepad1.y) {
            linearSlideSubsystem.LinearPosLo();
        }


        if(gamepad1.x) {
            linearSlideSubsystem.LinearPosMed();
        }

        if(gamepad1.a) {
            linearSlideSubsystem.LinearPosHi();
        }

        if(gamepad1.b) {
            linearSlideSubsystem.LinearStop();
        }


        telemetry.addData("Current Motor Position : ", pos);
        telemetry.update();

    }

}