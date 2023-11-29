package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousDriveCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.AirplaneLauncherSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OdometryControlSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

@Autonomous
public class AutonomousCommandTest extends CommandOpMode {
    ///region Subsystems
    private final MecanumDriveSubsystem driveBaseSubsystem;
    private final AirplaneLauncherSubsystem airplaneLauncherSubsystem;
    private final ClimbSubsystem climbSubsystem;
    private final ExtakeSubsystem extakeSubsystem;
    private final LinearSlideSubsystem linearSlideSubsystem;
    private final OdometryControlSubsystem odometryControlSubsystem;
    private final IntakeMotorSubsystem intakeMotorSubsystem;
    ///endregion
    private final VisionSubsystem visionSubsystem;
    public static Alliance alliance = Alliance.Red;
    public static AutonomousStartLocation startLocation = AutonomousStartLocation.Near;
    public static TeamPropPosition teamPropPosition = TeamPropPosition.Center;

    public AutonomousCommandTest(Telemetry telemetry) {
        driveBaseSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        odometryControlSubsystem = new OdometryControlSubsystem(hardwareMap, telemetry);
        airplaneLauncherSubsystem = new AirplaneLauncherSubsystem(hardwareMap, telemetry);
        climbSubsystem = new ClimbSubsystem(hardwareMap, telemetry);
        extakeSubsystem = new ExtakeSubsystem(hardwareMap, telemetry);
        linearSlideSubsystem = new LinearSlideSubsystem(hardwareMap, telemetry);
        intakeMotorSubsystem = new IntakeMotorSubsystem(hardwareMap, telemetry);
        //TODO: Fix the vision portal
        visionSubsystem = new VisionSubsystem(hardwareMap, telemetry);
    }

    @Override
    public void initialize() {

        telemetry.addLine("Before Schedule");
        telemetry.update();
       /* schedule(
                new WaitUntilCommand(this::isStarted).andThen(
                        new AutonomousDriveCommand(driveBaseSubsystem, linearSlideSubsystem, intakeMotorSubsystem, extakeSubsystem, visionSubsystem, alliance, startLocation)
                )
        );

        */

        schedule(
                new InstantCommand(intakeMotorSubsystem::eject, intakeMotorSubsystem)
        );

        register(driveBaseSubsystem, airplaneLauncherSubsystem, climbSubsystem, extakeSubsystem, linearSlideSubsystem, odometryControlSubsystem, intakeMotorSubsystem, visionSubsystem);

        telemetry.addLine("After Schedule");
        telemetry.update();
    }
}
