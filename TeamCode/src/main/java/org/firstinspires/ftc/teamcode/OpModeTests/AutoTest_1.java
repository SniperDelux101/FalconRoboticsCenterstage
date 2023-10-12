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

        Pose2d StartPos = new Pose2d(71.72, 24.05, Math.toRadians(180.00));

        drive = new FalconMecanumDrive(hardwareMap);

         drive.setPoseEstimate(StartPos);

        TrajectorySequence Test_1 = drive.trajectorySequenceBuilder(new Pose2d(71.72, 24.05, Math.toRadians(180.00)))
                .UNSTABLE_addTemporalMarkerOffset(1.29,() -> {})
                .splineTo(new Vector2d(24.47, 24.05), Math.toRadians(180.00))
                .splineTo(new Vector2d(0.00, -48.09), Math.toRadians(-90))
                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(Test_1);
    }
}