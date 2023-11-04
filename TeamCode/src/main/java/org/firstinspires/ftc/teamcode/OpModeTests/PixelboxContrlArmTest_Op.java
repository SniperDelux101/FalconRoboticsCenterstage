package org.firstinspires.ftc.teamcode.OpModeTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

@TeleOp
public class PixelboxContrlArmTest_Op extends OpMode {

    LinearSlideSubsystem linearSlideSubsystem;
    ExtakeSubsystem extakeSubsystem;
    @Override
    public void init() {
        linearSlideSubsystem = new LinearSlideSubsystem(hardwareMap);
        extakeSubsystem = new ExtakeSubsystem(hardwareMap);
    }

    @Override
    public void loop() {

        if(gamepad1.x)
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_MED, true);
        else if (gamepad1.y)
        {
            //extakeSubsystem.
        }

    }
}
