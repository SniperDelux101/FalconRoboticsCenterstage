package org.firstinspires.ftc.teamcode.OpModeTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;

@TeleOp(group = "subsystems test")
public class LinearSlideCommandTest extends OpMode {

    private LinearSlideSubsystem linearSlideSubsystem;
    @Override
    public void init() {
        MatchConfig.telemetry = telemetry;
        linearSlideSubsystem = new LinearSlideSubsystem(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        if(gamepad1.right_stick_y > 0) {
            linearSlideSubsystem.runPlusPosition(Configuration.LINEAR_SLIDE_TELEOP_MULTIPLIER, false);
        }
        else if(gamepad1.right_stick_y < 0) {
            linearSlideSubsystem.runPlusPosition(-Configuration.LINEAR_SLIDE_TELEOP_MULTIPLIER, false);
        }
        else if(gamepad1.x) {
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_LO);
        }
        else if(gamepad1.y) {
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_MED);
        }
        else if(gamepad1.b) {
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_HI);
        }

        telemetry.addData("Current Linear Slide Position ", linearSlideSubsystem.getCurrentPosition());
        telemetry.update();
    }

}
