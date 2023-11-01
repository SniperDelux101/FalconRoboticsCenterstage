package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

@Config
@TeleOp(group = "subsystems test")
public class ExtakeArmTesting extends OpMode {

    private ExtakeSubsystem rotationalExtakeSubsystem;
    private double controlArmPosition = 0.0;
    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        rotationalExtakeSubsystem = new ExtakeSubsystem(hardwareMap);
    }
    @Override
    public void loop() {
        if(gamepad1.dpad_left && gamepad1.x) {
            Configuration.R_SERVO_LEFT -= Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.leftRotation();
        }
        else if(gamepad1.dpad_left && gamepad1.b) {
            Configuration.R_SERVO_LEFT += Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.leftRotation();
        }
        else if(gamepad1.dpad_up && gamepad1.x) {
            Configuration.R_SERVO_CENTER -= Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.centerRotation();
        }
        else if(gamepad1.dpad_up && gamepad1.b) {
            Configuration.R_SERVO_CENTER += Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.centerRotation();
        }
        else if(gamepad1.dpad_right && gamepad1.x) {
            Configuration.R_SERVO_RIGHT -= Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.rightRotation();
        }
        else if(gamepad1.dpad_right && gamepad1.b) {
            Configuration.R_SERVO_RIGHT += Configuration.R_SERVO_MULTIPLIER;
            rotationalExtakeSubsystem.rightRotation();
        }
        else if (gamepad1.right_bumper){
            rotationalExtakeSubsystem.pixelEject();
        }
        else if (gamepad1.left_bumper){
            rotationalExtakeSubsystem.pixelIntake();
        }
        else if (gamepad1.left_trigger > 0 && gamepad1.x){
            controlArmPosition++;
            if (controlArmPosition > Configuration.E_MAX_ARM_ANGLE){
                controlArmPosition = Configuration.E_MAX_ARM_ANGLE;
            }
            rotationalExtakeSubsystem.setControlArmPosition(controlArmPosition);
        }
        else if (gamepad1.left_trigger > 0 && gamepad1.b) {
            controlArmPosition--;
            if (controlArmPosition< Configuration.E_MIN_ARM_ANGLE){
                controlArmPosition = Configuration.E_MIN_ARM_ANGLE;
            }
            rotationalExtakeSubsystem.setControlArmPosition(controlArmPosition);
        }

        telemetry.addData("Control Arm Position: ", controlArmPosition);
        telemetry.addData("Pixel Box Left Position : ",Configuration.R_SERVO_LEFT);
        telemetry.addData("Pixel Box Right Position: ", Configuration.R_SERVO_RIGHT);
        telemetry.addData("Pixel Box Center Position:", Configuration.R_SERVO_CENTER);

        telemetry.update();
    }
}