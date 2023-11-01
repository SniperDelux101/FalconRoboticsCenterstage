package org.firstinspires.ftc.teamcode.OpModeTests;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.OdometryControlSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

/*
This class is created to test the servos that control the odometry wheels in order to either give them contact with the ground
or to suspend them above the ground.

The buttons to do this are all on GAMEPAD 1 which are :
    Left odometry wheel retract tuning is : D_Pad left + [ Y button or A button ]
    Left odometry wheel drop tuning is : D_Pad left + [ X button + B button ]

    Rear odometry wheel retract tuning is : D_Pad center + [ Y button or A button ]
    Rear odometry wheel drop tuning is : D_Pad center + [ X button + B button ]

    Right odometry wheel retract tuning is : D_Pad right + [ Y button or A button ]
    Right odometry wheel drop tuning is : D_Pad + [ X button + B button ]

    To retract all odometry wheels press left bumper
    To drop all odometry wheels press right bumper

 */

@SuppressWarnings("unused")
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


// For left odo servo
        if(gamepad1.y && gamepad1.dpad_left) {
            Configuration.LEFT_ODO_RETRACT_POSITION += Configuration.ODO_SERVO_MULTIPLIER;
            odometryControlSubsystem.retractLeft();
        }

        else if(gamepad1.a && gamepad1.dpad_left) {
            Configuration.LEFT_ODO_RETRACT_POSITION -= Configuration.ODO_SERVO_MULTIPLIER;
            odometryControlSubsystem.retractLeft();
        }

        else if(gamepad1.x && gamepad1.dpad_left) {
            Configuration.LEFT_ODO_DROP_POSITION += Configuration.ODO_SERVO_MULTIPLIER;
            odometryControlSubsystem.dropLeft();
        }

        else if(gamepad1.b && gamepad1.dpad_left) {
            Configuration.LEFT_ODO_DROP_POSITION -= Configuration.ODO_SERVO_MULTIPLIER;
            odometryControlSubsystem.dropLeft();

        }

// For rear odo servo
            if (gamepad1.y && gamepad1.dpad_up) {
                Configuration.REAR_ODO_RETRACT_POSITION += Configuration.ODO_SERVO_MULTIPLIER;
                odometryControlSubsystem.retractRear();
            }

            else if (gamepad1.a && gamepad1.dpad_up) {
                Configuration.REAR_ODO_RETRACT_POSITION -= Configuration.ODO_SERVO_MULTIPLIER;
                odometryControlSubsystem.retractRear();
            }

            else if (gamepad1.x && gamepad1.dpad_up) {
                Configuration.REAR_ODO_DROP_POSITION += Configuration.ODO_SERVO_MULTIPLIER;
                odometryControlSubsystem.dropRear();
            }

            else if (gamepad1.b && gamepad1.dpad_up) {
                Configuration.REAR_ODO_DROP_POSITION -= Configuration.ODO_SERVO_MULTIPLIER;
                odometryControlSubsystem.dropRear();
            }

// For right odo servo
            else if (gamepad1.y && gamepad1.dpad_right) {
                Configuration.RIGHT_ODO_RETRACT_POSITION += Configuration.ODO_SERVO_MULTIPLIER;
                odometryControlSubsystem.retractRight();
            }

            else if (gamepad1.a && gamepad1.dpad_right) {
                Configuration.RIGHT_ODO_RETRACT_POSITION -= Configuration.ODO_SERVO_MULTIPLIER;
                odometryControlSubsystem.retractRight();
            }

            else if (gamepad1.x && gamepad1.dpad_right) {
                Configuration.RIGHT_ODO_DROP_POSITION += Configuration.ODO_SERVO_MULTIPLIER;
                odometryControlSubsystem.dropRight();
            }

            else if (gamepad1.b && gamepad1.dpad_right) {
                Configuration.RIGHT_ODO_DROP_POSITION -= Configuration.ODO_SERVO_MULTIPLIER;
                odometryControlSubsystem.dropRight();
            }



        if(gamepad1.left_bumper) {
            odometryControlSubsystem.retract();
        }

        else if(gamepad1.right_bumper) {
            odometryControlSubsystem.drop();
        }

        telemetry.addData("Left Odometry Servo Retract Position : ", Configuration.LEFT_ODO_RETRACT_POSITION);
        telemetry.addData("Left Odometry Servo Drop Position : ", Configuration.LEFT_ODO_DROP_POSITION);
        telemetry.addData("Rear Odometry Servo Retract Position : ", Configuration.REAR_ODO_RETRACT_POSITION);
        telemetry.addData("Rear Odometry Servo Drop Position : ", Configuration.REAR_ODO_DROP_POSITION);
        telemetry.addData("Right Odometry Servo Retract Position : ", Configuration.RIGHT_ODO_RETRACT_POSITION);
        telemetry.addData("Right Odometry Servo Drop Position : ", Configuration.RIGHT_ODO_DROP_POSITION);
        telemetry.update();

    }

}
