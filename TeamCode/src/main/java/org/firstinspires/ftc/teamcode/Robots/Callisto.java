package org.firstinspires.ftc.teamcode.Robots;

import com.arcrobotics.ftclib.command.PurePursuitCommand;
import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.purepursuit.waypoints.EndWaypoint;
import com.arcrobotics.ftclib.purepursuit.waypoints.GeneralWaypoint;
import com.arcrobotics.ftclib.purepursuit.waypoints.StartWaypoint;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.AutonomouseDriveForward;
import org.firstinspires.ftc.teamcode.Commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.Subsystems.DriveBaseSubsystem;

public class Callisto extends Robot {
    HardwareMap hMap;

    GamepadEx driverGamepad, utilityGamepad;
    DriveBaseSubsystem driveBaseSubsystem;



    DefaultDrive driveCommand;
    Telemetry telemetry;

    public Callisto(RobotMode mode, HardwareMap map, Gamepad gamepad1, Gamepad gamepad2, Telemetry tel) {
        hMap = map;
        driveBaseSubsystem = new DriveBaseSubsystem(hMap);
        telemetry = tel;

        if (mode == RobotMode.AUTO) {
            initAuto();
        } else {
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
        //schedule(new AutonomouseDriveForward(driveBaseSubsystem, 24.0, 0.25));
        PurePursuitCommand pp = new PurePursuitCommand(driveBaseSubsystem.getMecanumDrive(), driveBaseSubsystem.getOdometrySubsystem(),
                new StartWaypoint(0,0),
                new GeneralWaypoint(10,10,.25,.25,30),
                new EndWaypoint(0,0,0,.25,.25,30,.8,.1)
                );
        pp.schedule();
    }

    @Override
    public void run() {
        telemetry.addData("Odometry X", driveBaseSubsystem.getPosition().getX());
        telemetry.addData("Odometry Y", driveBaseSubsystem.getPosition().getY());
        telemetry.addData("Odometry Rotation", driveBaseSubsystem.getPosition().getRotation());
        telemetry.addData("Odometry Heading", driveBaseSubsystem.getPosition().getHeading());
        telemetry.addData("Odometry Distance", driveBaseSubsystem.getAverageEncoderDistance());
        telemetry.addData("Left Odo ", driveBaseSubsystem.getLeftEncoderDistance());
        telemetry.addData("Right Odo ", driveBaseSubsystem.getRightEncoderDistance());
        telemetry.update();
        super.run();
    }
}

