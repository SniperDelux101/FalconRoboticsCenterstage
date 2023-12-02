package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OdometryControlSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

@Autonomous
@Config
public class AutonomousPathTest extends OpMode {
    private MecanumDriveSubsystem mecanumDriveSubsystem ;
    private OdometryControlSubsystem odometryControlSubsystem;

    public static Alliance alliance = Alliance.Blue;
    public static AutonomousStartLocation startLocation = AutonomousStartLocation.Near ;
    public static TeamPropPosition teamPropPosition = TeamPropPosition.Center;
    private boolean runOnece = true;

    public static double X = 17.0;
    public static double Y = 63.0;
    public static double Forward1 = 40.0;
    public static double Back = 34.0;
    public static double ForwardPark = 35;


    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false) ;
        odometryControlSubsystem = new OdometryControlSubsystem(hardwareMap, telemetry);

    }
    @Override
    public void loop() {
        if (runOnece) {
            //odometryControlSubsystem.retract();
            //odometryControlSubsystem.drop();
//            mecanumDriveSubsystem.followTrajectorySequenceAsync(AutonomousPaths.PhaseOne(alliance, mecanumDriveSubsystem.getDrive(), teamPropPosition, startLocation));
//            mecanumDriveSubsystem.followTrajectorySequenceAsync(AutonomousPaths.RedAlliancePhaseOne( mecanumDriveSubsystem.getDrive(), TeamPropPosition.Left , startLocation));
//            mecanumDriveSubsystem.followTrajectorySequenceAsync(AutonomousPaths.RedAlliancePhaseOne(mecanumDriveSubsystem.getDrive(), TeamPropPosition.Center , startLocation));
//            mecanumDriveSubsystem.followTrajectorySequenceAsync(AutonomousPaths.RedAlliancePhaseOne(mecanumDriveSubsystem.getDrive(),TeamPropPosition.Right , startLocation));
//            mecanumDriveSubsystem.followTrajectorySequenceAsync(AutonomousPaths.BlueAlliancePhaseOne(mecanumDriveSubsystem.getDrive(), TeamPropPosition.Left , startLocation));
//            mecanumDriveSubsystem.followTrajectorySequenceAsync(AutonomousPaths.BlueAlliancePhaseOne(mecanumDriveSubsystem.getDrive() , TeamPropPosition.Center , startLocation));
//            mecanumDriveSubsystem.followTrajectorySequenceAsync(AutonomousPaths.BlueAlliancePhaseOne( mecanumDriveSubsystem.getDrive(), TeamPropPosition.Right, startLocation));

            mecanumDriveSubsystem.getDrive().followTrajectorySequence(
                    mecanumDriveSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                            .forward(Forward1)
                            .waitSeconds(2)
                            .back(Back)
                            .waitSeconds(2)
                            .turn(Math.toRadians(90) + 1e-6)
                            .waitSeconds(2)
                            .forward(ForwardPark)
                            .build());
             runOnece = false;
        }
    }


}
