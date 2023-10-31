package org.firstinspires.ftc.teamcode.OpModeTests;

import android.graphics.Bitmap;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsytem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
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


public class ClimbTest extends OpMode {

    private ClimbSubsytem climbSubsytem;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        climbSubsytem = new ClimbSubsytem(hardwareMap);
    }

    @Override
    public void loop() {

        if(gamepad1.left_bumper && gamepad1.y) {
            Configuration.CLIMB_IN += Configuration.CLIMB_MULTIPLIER;
            climbSubsytem.ClimbIn();
        }
        if(gamepad1.left_bumper && gamepad1.a) {
            Configuration.CLIMB_IN -= Configuration.CLIMB_MULTIPLIER;
            climbSubsytem.ClimbIn();
        }
        if(gamepad1.right_bumper && gamepad1.y) {
            Configuration.CLIMB_OUT += Configuration.CLIMB_MULTIPLIER;
            climbSubsytem.ClimbOut();
        }
        if(gamepad1.right_bumper && gamepad1.a) {
            Configuration.CLIMB_OUT -= Configuration.CLIMB_MULTIPLIER;
            climbSubsytem.ClimbOut();
        }
        if(gamepad1.dpad_up) {
            climbSubsytem.ClimbOut();
        }
        if(gamepad1.dpad_down) {
            climbSubsytem.ClimbIn();
        }
    }
}
