package org.firstinspires.ftc.teamcode.OpModeTests.RoadRunnerTests;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;

/**
 * This is a simple routine to test translational drive capabilities.
 *
 * NOTE: this has been refactored to use FTCLib's command-based
 */
@Config
@Autonomous(group = "drive")
public class StrafeTest extends CommandOpMode {

    public static double DISTANCE = 10; // in

    private MecanumDriveSubsystem drive;
    private TrajectoryFollowerCommand strafeFollower;

    @Override
    public void initialize() {
        drive = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        strafeFollower = new TrajectoryFollowerCommand(drive,
                drive.trajectoryBuilder(new Pose2d())
                        .strafeRight(DISTANCE)
                        .build()
        );
        schedule(new WaitUntilCommand(this::isStarted).andThen(strafeFollower.whenFinished(() -> {
            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("finalX", poseEstimate.getX());
            telemetry.addData("finalY", poseEstimate.getY());
            telemetry.addData("finalHeading", poseEstimate.getHeading());
            telemetry.update();
        })));
    }

}
