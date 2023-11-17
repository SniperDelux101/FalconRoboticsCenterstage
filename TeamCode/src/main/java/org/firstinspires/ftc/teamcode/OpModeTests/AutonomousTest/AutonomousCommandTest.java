package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousDriveCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Commands.AutonomouseDriveForward;
import org.firstinspires.ftc.teamcode.Subsystems.AirplaneLauncherSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OdometryControlSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

public class AutonomousCommandTest extends CommandOpMode {
    private final MecanumDriveSubsystem driveBaseSubsystem;
    private final AirplaneLauncherSubsystem airplaneLauncherSubsystem;
    private final ClimbSubsystem climbSubsystem;
    private final ExtakeSubsystem extakeSubsystem;
    private final LinearSlideSubsystem linearSlideSubsystem;
    private final OdometryControlSubsystem odometryControlSubsystem;
    private final IntakeMotorSubsystem intakeMotorSubsystem;
    private final VisionSubsystem visionSubsystem;
    public static Alliance alliance = Alliance.Blue;
    public static AutonomousStartLocation startLocation = AutonomousStartLocation.Near ;
    public static TeamPropPosition teamPropPosition = TeamPropPosition.Center;

    public AutonomousCommandTest(){
        driveBaseSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap),false);
        odometryControlSubsystem = new OdometryControlSubsystem(hardwareMap);
        airplaneLauncherSubsystem = new AirplaneLauncherSubsystem(hardwareMap);
        climbSubsystem = new ClimbSubsystem(hardwareMap);
        extakeSubsystem = new ExtakeSubsystem(hardwareMap);
        linearSlideSubsystem = new LinearSlideSubsystem(hardwareMap);
        intakeMotorSubsystem = new IntakeMotorSubsystem(hardwareMap);
        //TODO: Fix the vision portal
        visionSubsystem = new VisionSubsystem(hardwareMap);
    }
    @Override
    public void initialize (){
        schedule(
                new WaitUntilCommand(this::isStarted).andThen(
                        new AutonomousDriveCommand (driveBaseSubsystem, linearSlideSubsystem, intakeMotorSubsystem, extakeSubsystem, visionSubsystem, alliance, startLocation)
                )
        );

    }
}
