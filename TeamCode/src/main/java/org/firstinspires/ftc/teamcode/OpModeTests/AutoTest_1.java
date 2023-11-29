package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

@Config
@Autonomous(group = "drive")
public class AutoTest_1 extends LinearOpMode {

    FalconMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d StartPos = new Pose2d(17.00, -63.00, Math.toRadians(90.00));

         drive = new FalconMecanumDrive(hardwareMap);
         drive.setPoseEstimate(StartPos);

        TrajectorySequence NewTest = drive.trajectorySequenceBuilder(new Pose2d(17.00, -63.00, Math.toRadians(90.00)))
                .splineTo(new Vector2d(14.15, -47.53), Math.toRadians(90.00))
                .splineTo(new Vector2d(13.58, -32.96), Math.toRadians(90.00))
                .lineTo(new Vector2d(44.00, -36.00))
                .splineTo(new Vector2d(44.56, -49.23), Math.toRadians(90.00))
                .lineTo(new Vector2d(43.57, -61.67))
                .splineTo(new Vector2d(63.65, -61.39), Math.toRadians(90.00))
                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(NewTest);
    }
}