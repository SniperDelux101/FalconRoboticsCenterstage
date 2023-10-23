package org.firstinspires.ftc.teamcode.OpModeTests;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

//import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

@Config
@Autonomous(group = "drive")
public class AutoTest_1 extends LinearOpMode {

    FalconMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d StartPos = new Pose2d(-36.00, -70.00, Math.toRadians(90.00));

         drive = new FalconMecanumDrive(hardwareMap);
         drive.setPoseEstimate(StartPos);

        TrajectorySequence Test_One = drive.trajectorySequenceBuilder(new Pose2d(-36.00, -70.00, Math.toRadians(90.00)))
                .splineTo(new Vector2d(-36.00, -50.00), Math.toRadians(0.00))
                .splineTo(new Vector2d(-36.00, -24.00), Math.toRadians(0.00))
                .splineTo(new Vector2d(-36.00, -12.00), Math.toRadians(0.00))
                .splineTo(new Vector2d(-0.00, -12.00), Math.toRadians(0.00))
                .splineTo(new Vector2d(30.00, -12.00), Math.toRadians(0.00))
                .splineTo(new Vector2d(36.00, -24.00), Math.toRadians(0.00))
                .splineTo(new Vector2d(42.00, -36.00), Math.toRadians(0.00))
                .splineTo(new Vector2d(48.00, -60.00), Math.toRadians(0.00))
                .splineTo(new Vector2d(60.00, -60.00), Math.toRadians(0.00))
                .build();


        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(Test_One);
    }
}