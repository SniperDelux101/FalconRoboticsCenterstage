package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.DriveForwardToObjectCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.FindAprilTagCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.BuildFarPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.BuildNearPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Commands.GyroSquareCommand;
import org.firstinspires.ftc.teamcode.Commands.MovePixelBoxArmToPositionCommand;
import org.firstinspires.ftc.teamcode.Commands.PixelBoxArmPosition;
import org.firstinspires.ftc.teamcode.Commands.PlacePixelOnSpikeCommand;
import org.firstinspires.ftc.teamcode.Commands.RunLinearSlideAndCenterPixelBoxCommand;
import org.firstinspires.ftc.teamcode.Commands.StopPixelBoxReset;
import org.firstinspires.ftc.teamcode.Commands.StrafeToFindAprilTagCommand;
import org.firstinspires.ftc.teamcode.Commands.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.Commands.TrajectorySequenceFollowerCommand;
import org.firstinspires.ftc.teamcode.Subsystems.AirplaneLauncherSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.GyroSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OdometryControlSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.opencv.core.Mat;

import java.util.HashMap;

@Autonomous(preselectTeleOp = "Drivebase_Op")
@Config
public class Autonomous_V3 extends CommandOpMode {
    ///region Subsystems
    private MecanumDriveSubsystem driveBaseSubsystem;
    private AirplaneLauncherSubsystem airplaneLauncherSubsystem;
    private ClimbSubsystem climbSubsystem;
    private ExtakeSubsystem extakeSubsystem;
    private LinearSlideSubsystem linearSlideSubsystem;
    private OdometryControlSubsystem odometryControlSubsystem;
    private IntakeMotorSubsystem intakeMotorSubsystem;
    private VisionSubsystem visionSubsystem;
    private GyroSubsystem gyroSubsystem;
    private DistanceSensorSubsystem distanceSensorSubsystem;
    ///endregion

    public static Alliance alliance = Alliance.Blue;
    public static AutonomousStartLocation startLocation = AutonomousStartLocation.Near;
    public static TeamPropPosition teamPropPosition = TeamPropPosition.Center;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();

        gyroSubsystem = GyroSubsystem.getInstance(hardwareMap, telemetry);
        driveBaseSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        odometryControlSubsystem = new OdometryControlSubsystem(hardwareMap, telemetry);
        airplaneLauncherSubsystem = new AirplaneLauncherSubsystem(hardwareMap, telemetry);
        climbSubsystem = new ClimbSubsystem(hardwareMap, telemetry);
        extakeSubsystem = new ExtakeSubsystem(hardwareMap, telemetry);
        linearSlideSubsystem = new LinearSlideSubsystem(hardwareMap, telemetry);
        intakeMotorSubsystem = new IntakeMotorSubsystem(hardwareMap, telemetry);
        visionSubsystem = new VisionSubsystem(hardwareMap, telemetry, true);
        distanceSensorSubsystem = new DistanceSensorSubsystem(hardwareMap, telemetry);

        register(driveBaseSubsystem, airplaneLauncherSubsystem, climbSubsystem, extakeSubsystem, linearSlideSubsystem, odometryControlSubsystem,
                intakeMotorSubsystem, visionSubsystem, distanceSensorSubsystem);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        while(this.opModeInInit()) {
            telemetry.addLine("GamePad1 D-PadUp = Start Location Far");
            telemetry.addLine("GamePad1 D-PadDown = Start Location Near");
            telemetry.addLine("GamePad1 D-PadLeft = Alliance Red");
            telemetry.addLine("GamePad1 D-PadRight = Alliance Blue");

            if (gamepad1.dpad_left)
                alliance = Alliance.Red;
            else if (gamepad1.dpad_right)
                alliance = Alliance.Blue;
            else if (gamepad1.dpad_up)
                startLocation = AutonomousStartLocation.Far;
            else if (gamepad1.dpad_down)
                startLocation = AutonomousStartLocation.Near;

            teamPropPosition = visionSubsystem.getTeamPropPosition();

            MatchConfig.Alliance = alliance;
            MatchConfig.AutonomousStartLocation = startLocation;
            MatchConfig.TeamPropPosition = teamPropPosition;
            MatchConfig.telemetry = telemetry;

            telemetry.addData("Team Prop Position: ", teamPropPosition);
            telemetry.addData("Alliance: ", alliance);
            telemetry.addData("Auto Start Location: ", startLocation);
            telemetry.update();
        }

        waitForStart();

        visionSubsystem.stopTensorStreaming();
        visionSubsystem.resumeAprilStreaming();
        TrajectorySequence phase1, phase2, phase3, park;

        if(startLocation == AutonomousStartLocation.Near) {
            BuildNearPaths.Build(driveBaseSubsystem.getDrive(), teamPropPosition, alliance);
            phase1 = BuildNearPaths.Phase1;
            phase2 = BuildNearPaths.Phase2;
            phase3 = BuildNearPaths.Phase3;
            park = BuildNearPaths.Park;
        } else{
            BuildFarPaths.Build(driveBaseSubsystem.getDrive(), teamPropPosition, alliance);
            phase1 = BuildFarPaths.Phase1;
            phase2 = BuildFarPaths.Phase2;
            phase3 = BuildFarPaths.Phase3;
            park = BuildFarPaths.Park;
        }

        schedule(
                new SequentialCommandGroup(
                        new TrajectorySequenceFollowerCommand(driveBaseSubsystem, phase1),
                        new ParallelCommandGroup(
                                new PlacePixelOnSpikeCommand(intakeMotorSubsystem).withTimeout(2000),
                                new TrajectorySequenceFollowerCommand(driveBaseSubsystem, phase2)
                        ),
                        //new GyroSquareCommand(gyroSubsystem, driveBaseSubsystem, getSquareDegree()).withTimeout(1000),
                        new StrafeToFindAprilTagCommand(driveBaseSubsystem, visionSubsystem),
                        new FindAprilTagCommand(driveBaseSubsystem, visionSubsystem),
                        new DriveForwardToObjectCommand(driveBaseSubsystem, distanceSensorSubsystem, GyroSubsystem.getInstance(hardwareMap, telemetry), Configuration.BACKDROP_DISTANCE),
                        new SequentialCommandGroup(
                                new RunLinearSlideAndCenterPixelBoxCommand(extakeSubsystem,linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_AUTO),
                                new MovePixelBoxArmToPositionCommand(extakeSubsystem, PixelBoxArmPosition.Extake)
                        ).withTimeout(1500),
                        new WaitCommand(1000),
                        new SelectCommand(
                                new HashMap<Object, Command>(){{
                                    put(TeamPropPosition.Left, new InstantCommand(extakeSubsystem::leftRotation, extakeSubsystem));
                                    put(teamPropPosition.Center, new InstantCommand(extakeSubsystem::centerRotation, extakeSubsystem));
                                    put(teamPropPosition.Right, new InstantCommand(extakeSubsystem::rightRotation, extakeSubsystem));
                                }},
                                this::getTeamPropPosition
                        ),
                        new InstantCommand(extakeSubsystem::pixelEject, extakeSubsystem),
                        new WaitCommand(1000),
                        new TrajectorySequenceFollowerCommand(driveBaseSubsystem, phase3),
                        new StopPixelBoxReset(extakeSubsystem, linearSlideSubsystem),
                        new TrajectorySequenceFollowerCommand(driveBaseSubsystem, park),
                        new InstantCommand(extakeSubsystem::pixelStop, extakeSubsystem)
                ));

        // run the scheduler
        while (!isStopRequested() && opModeIsActive()) {
            run();
            telemetry.addData("Gyro Reading(Degrees): ", gyroSubsystem.getHeading(AngleUnit.DEGREES));
            telemetry.update();
        }
        visionSubsystem.stopAllVisionPortalStreaming();
        reset();
    }
    private double getSquareDegree() {
        if(MatchConfig.Alliance == Alliance.Blue) {
            return 270;
        }
        else {
            return 90;
        }
    }
    private TeamPropPosition getTeamPropPosition(){
        return teamPropPosition;
    }
}

