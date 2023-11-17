package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

@Autonomous
@Config
public class AutonomousPathTest extends OpMode {
    private MecanumDriveSubsystem mecanumDriveSubsystem ;

    public static Alliance alliance = Alliance.Blue;
    public static AutonomousStartLocation startLocation = AutonomousStartLocation.Near ;
    public static TeamPropPosition teamPropPosition = TeamPropPosition.Center;
    private boolean runOnece = true;


    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false) ;
    }
    @Override
    public void loop() {
        if (runOnece) {
            mecanumDriveSubsystem.followTrajectorySequenceAsync(AutonomousPaths.PhaseOne(alliance, mecanumDriveSubsystem.getDrive(), teamPropPosition, startLocation));
            runOnece = false;
        }
    }
}
