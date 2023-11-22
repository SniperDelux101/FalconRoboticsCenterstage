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

    private ExtakeSubsystem extakeSubsystem;
    private double controlArmPosition = 0.0;
    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        extakeSubsystem = new ExtakeSubsystem(hardwareMap , telemetry);
    }
    @Override
    public void loop() {
        if(gamepad1.dpad_left && gamepad1.x) {
            Configuration.R_SERVO_LEFT -= Configuration.R_SERVO_MULTIPLIER;
            extakeSubsystem.leftRotation();
        }
        else if(gamepad1.dpad_left && gamepad1.b) {
            Configuration.R_SERVO_LEFT += Configuration.R_SERVO_MULTIPLIER;
            extakeSubsystem.leftRotation();
        }
        else if(gamepad1.dpad_up && gamepad1.x) {
            Configuration.R_SERVO_CENTER -= Configuration.R_SERVO_MULTIPLIER;
            extakeSubsystem.centerRotation();
        }
        else if(gamepad1.dpad_up && gamepad1.b) {
            Configuration.R_SERVO_CENTER += Configuration.R_SERVO_MULTIPLIER;
            extakeSubsystem.centerRotation();
        }
        else if(gamepad1.dpad_right && gamepad1.x) {
            Configuration.R_SERVO_RIGHT -= Configuration.R_SERVO_MULTIPLIER;
            extakeSubsystem.rightRotation();
        }
        else if(gamepad1.dpad_right && gamepad1.b) {
            Configuration.R_SERVO_RIGHT += Configuration.R_SERVO_MULTIPLIER;
            extakeSubsystem.rightRotation();
        }
        else if (gamepad1.right_bumper){
            extakeSubsystem.pixelEject();
        }
        else if (gamepad1.left_bumper){
            extakeSubsystem.pixelIntake();
        }
        else if (gamepad1.left_trigger > 0){
            extakeSubsystem.pixelStop();
        }
        else if (gamepad1.dpad_up){
            controlArmPosition+=.005;
            if (controlArmPosition > Configuration.E_MAX_ARM_ANGLE){
                controlArmPosition = Configuration.E_MAX_ARM_ANGLE;
            }
            telemetry.addData("Moving Arm to: ", controlArmPosition);
            extakeSubsystem.setControlArmPosition(controlArmPosition);
        }
        else if (gamepad1.dpad_down) {
            controlArmPosition-=.005;
            if (controlArmPosition< Configuration.E_MIN_ARM_ANGLE){
                controlArmPosition = Configuration.E_MIN_ARM_ANGLE;
            }
            telemetry.addData("Moving Arm to: ", controlArmPosition);
            extakeSubsystem.setControlArmPosition(controlArmPosition);
        }
        else if(gamepad2.a){
            extakeSubsystem.leftRotation();
        }
        else if(gamepad2.b){
            extakeSubsystem.centerRotation();
        }
        else if(gamepad2.y){
            extakeSubsystem.rightRotation();
        }
        else if(gamepad2.dpad_left){
            extakeSubsystem.setControlArmToPrepExchange();
        }
        else if(gamepad2.dpad_up){
            extakeSubsystem.setControlArmToHighBoard();
        } else if (gamepad2.dpad_right) {
            extakeSubsystem.setControlArmToMidBoard();
        }
        else if (gamepad2.dpad_down){
            extakeSubsystem.setControlArmToExchange();
        }
        else if(gamepad2.left_bumper){
            extakeSubsystem.setControlArmToLowBoard();
        }


        telemetry.addData("Control Arm1 Position: ", extakeSubsystem.getControlArm1Position());
        //telemetry.addData("Control Arm2 Position: ", extakeSubsystem.getControlArm2Position());
        telemetry.addData("Pixel Box Left Position : ",Configuration.R_SERVO_LEFT);
        telemetry.addData("Pixel Box Right Position: ", Configuration.R_SERVO_RIGHT);
        telemetry.addData("Pixel Box Center Position:", Configuration.R_SERVO_CENTER);

        telemetry.update();
    }
}