package org.firstinspires.ftc.teamcode.OpModeTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DriveBaseSubsystem;

@TeleOp(name = "DistanceTuner", group="Testing")
public class DistanceTunerOpMode extends OpMode {

    DriveBaseSubsystem driveBaseSubsystem;
    //Calis
    @Override
    public void init() {
        driveBaseSubsystem = new DriveBaseSubsystem(hardwareMap);
        driveBaseSubsystem.resetEncoders();
    }

    @Override
    public void loop() {
        telemetry.addData("Odometry X", driveBaseSubsystem.getPosition().getX());
        telemetry.addData("Odometry Y", driveBaseSubsystem.getPosition().getY());
        telemetry.addData("Odometry Rotation", driveBaseSubsystem.getPosition().getRotation());
        telemetry.addData("Odometry Heading", driveBaseSubsystem.getPosition().getHeading());
        telemetry.addData("Odometry Distance", driveBaseSubsystem.getAverageEncoderDistance());
        telemetry.addData("Left Odo ", driveBaseSubsystem.getLeftEncoderDistance());
        telemetry.addData("Right Odo ", driveBaseSubsystem.getRightEncoderDistance());
        telemetry.addData("Rear Odo", driveBaseSubsystem.getRearEncoderDistance());

        telemetry.update();
    }
}
