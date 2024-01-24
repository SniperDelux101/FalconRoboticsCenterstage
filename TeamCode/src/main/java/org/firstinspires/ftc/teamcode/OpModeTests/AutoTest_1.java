package org.firstinspires.ftc.teamcode.OpModeTests;

import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.BN_Center_Park_H1;
import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.BN_Center_Ph2_H1;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

@Config
@Autonomous(group = "drive")
public class AutoTest_1 extends LinearOpMode {

    FalconMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d StartPos = new Pose2d(12, 60, Math.toRadians(270));

         drive = new FalconMecanumDrive(hardwareMap);
         drive.setPoseEstimate(StartPos);

        TrajectorySequence NewTest = drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(270.00)))
                .lineTo(new Vector2d(12, 30))
                .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                .lineTo(new Vector2d(12, 32))
                .splineTo((new Vector2d(30, 32)), BN_Center_Ph2_H1)
                .lineTo(new Vector2d(40, 32))

                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(NewTest);
    }
}