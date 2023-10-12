package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OdometryControlSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

@Config
@TeleOp(group = "subsystems test")
public class IntakeMotorTest extends OpMode {

    private IntakeMotorSubsystem intakeMotorSubsystem;
    private String direction;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        intakeMotorSubsystem = new IntakeMotorSubsystem(hardwareMap);
        direction = "Not Running";
    }

    @Override
    public void loop() {

        if(gamepad1.right_bumper) {
            intakeMotorSubsystem.forward();
            direction = "Forward";
        }

        if(gamepad1.left_bumper) {
            intakeMotorSubsystem.reverse();
            direction = "Reverse";
        }

        if(gamepad1.b) {
            intakeMotorSubsystem.stop();
            direction = "Stopped";
        }

        telemetry.addData("Motor Speed Percentage : ", Configuration.INTAKE_MOTOR_POWER);
        telemetry.addData("Direction : ", direction);
        telemetry.update();

    }


}
