package org.firstinspires.ftc.teamcode.Robots;

import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OdometryControlSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

public class Callisto extends Robot {
    HardwareMap hMap;

    GamepadEx driverGamepad, utilityGamepad;
    MecanumDriveSubsystem driveBaseSubsystem;
    DefaultDrive driveCommand;
    Telemetry telemetry;

    private OdometryControlSubsystem odometryControlSubsystem;

    public Callisto(RobotMode mode, HardwareMap map, Gamepad gamepad1, Gamepad gamepad2, Telemetry tel) {
        hMap = map;
        driveBaseSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(map),false);
        odometryControlSubsystem = new OdometryControlSubsystem(hMap);
        telemetry = tel;

        //TODO// UNCOMMENT CODE ONCE VALUES HAVE BEEN DETERMINED
//        odometryControlSubsystem.drop();

        if (mode == RobotMode.AUTO) {
            initAuto();
        } else {
            initTeleOp(gamepad1, gamepad2);
        }
    }

    private void initTeleOp(Gamepad gamepad1, Gamepad gamepad2) {
        driverGamepad = new GamepadEx(gamepad1);
        utilityGamepad = new GamepadEx(gamepad2);

        odometryControlSubsystem.retract();

//        driveCommand = new DefaultDrive(driveBaseSubsystem, driverGamepad::getLeftX, driverGamepad::getLeftY, driverGamepad::getRightX);
//        register(driveBaseSubsystem);
//        driveBaseSubsystem.setDefaultCommand(driveCommand);
    }

    private void initAuto() {
        //TODO: Add code for autonomous driving


        odometryControlSubsystem.retract();
        odometryControlSubsystem.drop();
    }

    @Override
    public void run() {

        telemetry.addData("x", driveBaseSubsystem.getPoseEstimate().getX());
        telemetry.addData("y", driveBaseSubsystem.getPoseEstimate().getY());
        telemetry.addData("heading", driveBaseSubsystem.getPoseEstimate().getHeading());
        telemetry.update();
        super.run();

        driveBaseSubsystem.drive(driverGamepad.getLeftY(), driverGamepad.getLeftX(), driverGamepad.getRightX());
    }

//    public void cleanUp() {
//        odometryControlSubsystem.retract();
//    }

}

