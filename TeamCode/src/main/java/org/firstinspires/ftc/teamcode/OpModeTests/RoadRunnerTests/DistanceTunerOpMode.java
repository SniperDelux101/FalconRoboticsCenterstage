package org.firstinspires.ftc.teamcode.OpModeTests.RoadRunnerTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

@TeleOp(name = "DistanceTuner", group="Testing")
public class DistanceTunerOpMode extends OpMode {

    FalconMecanumDrive driveBaseSubsystem;
    //Calis
    @Override
    public void init() {
        driveBaseSubsystem = new FalconMecanumDrive(hardwareMap);
        driveBaseSubsystem.resetEncoders();
    }

    @Override
    public void loop() {
        telemetry.addData("Odometry X", driveBaseSubsystem.getPoseEstimate().getX());
        telemetry.addData("Odometry Y", driveBaseSubsystem.getPoseEstimate().getY());
        telemetry.addData("Odometry Heading", driveBaseSubsystem.getPoseEstimate().getHeading());
        //telemetry.addData("Odometry Distance", driveBaseSubsystem.getLocalizer().

        telemetry.update();
    }
}
