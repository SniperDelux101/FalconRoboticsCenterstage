package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

@Config
@Autonomous(group = "drive")
public class BlueNearLeftTest extends LinearOpMode {

    FalconMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d StartPos = new Pose2d(12, 60, Math.toRadians(270));

        drive = new FalconMecanumDrive(hardwareMap);
        drive.setPoseEstimate(StartPos);

        TrajectorySequence NewTest = drive.trajectorySequenceBuilder(StartPos)
                .splineToConstantHeading(new Vector2d(30, 30), Math.toRadians(270))
                .turn(Math.toRadians(-90))

                .lineTo(new Vector2d(40, 30))
                .lineTo(new Vector2d(40, 36))

                .addTemporalMarker(() -> {

                })

                .splineToConstantHeading(new Vector2d(50,60), Math.toRadians(0))

                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(NewTest);
    }
}