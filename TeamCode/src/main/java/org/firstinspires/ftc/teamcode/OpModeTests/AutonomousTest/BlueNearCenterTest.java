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
public class BlueNearCenterTest extends LinearOpMode {

    FalconMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d StartPos = new Pose2d(12, 60, Math.toRadians(270));

        drive = new FalconMecanumDrive(hardwareMap);
        drive.setPoseEstimate(StartPos);

        TrajectorySequence NewTest = drive.trajectorySequenceBuilder(StartPos)

                .lineTo(new Vector2d(12, 30))



                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(NewTest);
    }
}
