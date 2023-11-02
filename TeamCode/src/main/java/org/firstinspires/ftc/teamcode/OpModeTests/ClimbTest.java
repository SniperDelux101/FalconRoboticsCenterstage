package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

/*
This testing OpMode controls the configuration of the positioning regarding the climb mechanism
       ** All testing utilizes only gamepad one **

       Left Bumper & Y Button -------> Climb In Up
       Left Bumper & A Button -------> Climb In Down

       Right Bumper & Y Button -------> Climb Out Up
       Right Bumper & A Button -------> Climb Out Down

       D-Pad Up -------> Climb Out
       D-Pad Down -------> Climb in
 */
@TeleOp(group = "subsystems test")
public class ClimbTest extends OpMode {

    private ClimbSubsystem climbSubsystem;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        climbSubsystem = new ClimbSubsystem(hardwareMap);
    }

    @Override
    public void loop() {
        String command = "";
        if(gamepad1.left_bumper && gamepad1.y) {
            command = "Left Bumper and Y";
            Configuration.CLIMB_IN += Configuration.CLIMB_MULTIPLIER;
            //climbSubsytem.ClimbIn();
        }
        else if(gamepad1.left_bumper && gamepad1.a) {
            command = "Left Bumper and A";
            Configuration.CLIMB_IN -= Configuration.CLIMB_MULTIPLIER;
            //climbSubsytem.ClimbIn();
        }
        else if(gamepad1.right_bumper && gamepad1.y) {
            command = "Right bumper and Y";
            Configuration.CLIMB_OUT += Configuration.CLIMB_MULTIPLIER;
            //climbSubsytem.ClimbOut();
        }
        else if(gamepad1.right_bumper && gamepad1.a) {
            command = "Right bumber and A";
            Configuration.CLIMB_OUT -= Configuration.CLIMB_MULTIPLIER;
            //climbSubsytem.ClimbOut();
        }
        else if(gamepad1.dpad_up) {
            command = "Dpad Up";
            climbSubsystem.ClimbOut();
        }
        else if(gamepad1.dpad_down) {
            climbSubsystem.ClimbIn();
            command = "Dpad Down";
        }

        int[] motorPositions = climbSubsystem.getCurrentMotorPositions();
        telemetry.addData("Value for clim in", Configuration.CLIMB_IN);
        telemetry.addData("value for climb out", Configuration.CLIMB_OUT);
        telemetry.addData("Left Motor Position", motorPositions[0]);
        telemetry.addData("Right Motor Position", motorPositions[1]);
        telemetry.addData("Current Command: ", command);
        telemetry.update();
    }
}
