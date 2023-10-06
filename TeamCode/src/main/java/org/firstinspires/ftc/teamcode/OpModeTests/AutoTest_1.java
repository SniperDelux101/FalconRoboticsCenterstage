package org.firstinspires.ftc.teamcode.OpModeTests;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

//import com.acmerobotics.dashboard.FtcDashboard;
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

        drive = new FalconMecanumDrive(hardwareMap);
        TrajectorySequence Test_1 = drive.trajectorySequenceBuilder(new Pose2d(-0.28, -69.05, Math.toRadians(90.00)))
                .splineTo(new Vector2d(-0.28, 0.14), Math.toRadians(90.00))
                .splineTo(new Vector2d(0.00, 23.20), Math.toRadians(89.30))
                .splineTo(new Vector2d(-30.37, 16.31), Math.toRadians(192.78))
                .splineTo(new Vector2d(-58.08, 15.47), Math.toRadians(181.34))
                .build();
        drive.setPoseEstimate(Test_1.start());

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(Test_1);
    }
}