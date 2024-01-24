package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

/*
This testing OpMode just makes sure that the motion of the motors for intake is accurate
    ** This testing only utilizes gamepad one **

    Right Bumper -------> Motor Forward
    Left Bumper -------> Motor Reverse

    Button B -------> Stops motors

 */



@Config
@TeleOp(group = "subsystems test")
public class IntakeMotorTest extends OpMode {

    private IntakeMotorSubsystem intakeMotorSubsystem;
    private String direction;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        intakeMotorSubsystem = new IntakeMotorSubsystem(hardwareMap , telemetry);
        direction = "Not Running";
    }

    @Override
    public void loop() {

        if(gamepad1.right_bumper) {
            intakeMotorSubsystem.intake();
            direction = "Forward";
        }

        else if(gamepad1.left_bumper) {
            intakeMotorSubsystem.eject();
            direction = "Reverse";
        }

        else if(gamepad1.b) {
            intakeMotorSubsystem.stop();
            direction = "Stopped";
        }

        telemetry.addData("Motor Speed Percentage : ", Configuration.INTAKE_MOTOR_POWER);
        telemetry.addData("Direction : ", direction);
        telemetry.update();

    }


}
