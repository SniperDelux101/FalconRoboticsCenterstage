package org.firstinspires.ftc.teamcode.Robots;

import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;

public class SimpleRobot extends Robot {
    private final LinearSlideSubsystem linearSlideSubsystem;
    TriggerReader driverRightTrigger;
    TriggerReader driverLeftTrigger;
    TriggerReader utilityRightTrigger;
    TriggerReader utilityLeftTrigger;

    Gamepad driverGamePad_FTC;
    Gamepad utilityGamePad_FTC;
    Telemetry telemetry;
    HardwareMap hMap;
    GamepadEx driverGamepad, utilityGamepad;

    public SimpleRobot(RobotMode mode, HardwareMap map, Gamepad gamepad1, Gamepad gamepad2, Telemetry tel) {
        hMap = map;
        telemetry = tel;
        driverGamePad_FTC = gamepad1;
        utilityGamePad_FTC = gamepad2;

        linearSlideSubsystem = new LinearSlideSubsystem(hMap);

        driverGamepad = new GamepadEx(driverGamePad_FTC);
        utilityGamepad = new GamepadEx(utilityGamePad_FTC);

        driverRightTrigger = new TriggerReader(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER);
        driverLeftTrigger = new TriggerReader(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER);
        utilityRightTrigger = new TriggerReader(utilityGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER);
        utilityLeftTrigger = new TriggerReader(utilityGamepad, GamepadKeys.Trigger.LEFT_TRIGGER);

    }

    @Override
    public void run() {

        if (driverLeftTrigger.isDown()) {
            double newPos = linearSlideSubsystem.LinearCurPos() +1.0;
            linearSlideSubsystem.runToPosition((int)newPos);
        }

        telemetry.update();
        super.run();
    }


}
