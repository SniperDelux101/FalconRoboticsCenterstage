package org.firstinspires.ftc.teamcode.Robots;

import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.Subsystems.DriveBaseSubsystem;

import java.util.function.DoubleSupplier;

public class Callisto extends Robot {
HardwareMap hMap;

    GamepadEx driverGamepad, utilityGamepad;
    DriveBaseSubsystem driveBaseSubsystem;
    DefaultDrive driveCommand;

public Callisto(RobotMode mode, HardwareMap map, Gamepad gamepad1, Gamepad gamepad2) {
    hMap = map;
    driveBaseSubsystem = new DriveBaseSubsystem(hMap);

    if(mode == RobotMode.AUTO) {
        initAuto();
    }
    else  {
        initTeleOp(gamepad1, gamepad2);
    }

}
private void initTeleOp(Gamepad gamepad1, Gamepad gamepad2) {
    driverGamepad = new GamepadEx(gamepad1);
    utilityGamepad = new GamepadEx(gamepad2);

    driveCommand = new DefaultDrive(driveBaseSubsystem, driverGamepad::getLeftX, driverGamepad::getLeftY, driverGamepad::getRightX);
    register(driveBaseSubsystem);
    driveBaseSubsystem.setDefaultCommand(driveCommand);
}

private void initAuto() {

    }
}
