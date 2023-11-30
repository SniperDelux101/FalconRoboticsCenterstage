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
                .lineToConstantHeading(new Vector2d(11, -36))
                .lineToConstantHeading(new Vector2d(24,-36))
                .lineToSplineHeading(new Pose2d(42,-36, Math.toRadians(180)))

                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(NewTest);
    }
}