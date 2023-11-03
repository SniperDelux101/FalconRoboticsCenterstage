package org.firstinspires.ftc.teamcode.OpModeTests;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.RunLinerslideToPositionCommand;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

@TeleOp()
public class SampleCommandOpModeTest extends CommandOpMode {
    private LinearSlideSubsystem linearSlideSubsystem;
    private GamepadEx gamepad;
    @Override
    public void initialize(){
        linearSlideSubsystem = new LinearSlideSubsystem(hardwareMap);
        gamepad = new GamepadEx(gamepad1);

        gamepad.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(new RunLinerslideToPositionCommand(linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_HI));

        telemetry.addData("Current Position: ", linearSlideSubsystem.LinearCurPos());
        telemetry.addData("Target Postion: ", Configuration.LINEAR_SLIDE_POS_HI);
        telemetry.update();
    }
}
