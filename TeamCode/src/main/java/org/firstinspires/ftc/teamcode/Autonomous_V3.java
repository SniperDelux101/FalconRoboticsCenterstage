package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.ParallelRaceGroup;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.AprilTagStartStopCommand;
import org.firstinspires.ftc.teamcode.Commands.AprilTagStrafeCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.DriveForwardToObjectCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.FindAprilTagCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.ParkEnding;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.BuildFarPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.BuildNearPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TravelDirection;
import org.firstinspires.ftc.teamcode.Commands.DriveToAprilTagCommand;
import org.firstinspires.ftc.teamcode.Commands.GyroSquareCommand;
import org.firstinspires.ftc.teamcode.Commands.MovePixelBoxArmToPositionCommand;
import org.firstinspires.ftc.teamcode.Commands.OneCycle;
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
    public static TravelDirection direction = TravelDirection.In;
    public static ParkEnding parkEnding = ParkEnding.In;
    public static OneCycle oneCycle = OneCycle.False;

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
        visionSubsystem = new VisionSubsystem(hardwareMap, telemetry, true, true);
        distanceSensorSubsystem = new DistanceSensorSubsystem(hardwareMap, telemetry);

        register(driveBaseSubsystem, airplaneLauncherSubsystem, climbSubsystem, extakeSubsystem, linearSlideSubsystem, odometryControlSubsystem,
                intakeMotorSubsystem, visionSubsystem, distanceSensorSubsystem);
        this.visionSubsystem.startTensorFlowProcessing();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        MatchConfig.Alliance = Alliance.Blue;
        MatchConfig.AutonomousStartLocation = AutonomousStartLocation.Far;
        MatchConfig.TeamPropPosition = TeamPropPosition.NoDetection;
        MatchConfig.TravelDirection = TravelDirection.In;
        MatchConfig.ParkEnding = ParkEnding.In;
        MatchConfig.OneCycle = OneCycle.False;
        MatchConfig.telemetry = telemetry;
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
            else if(gamepad1.a) {
                parkEnding = ParkEnding.In;
            }
            else if(gamepad1.y) {
                parkEnding = ParkEnding.Out;
            }
            else if(gamepad1.x) {
                direction = TravelDirection.In;
            }
            else if(gamepad1.b) {
                direction = TravelDirection.Out;
            }
            else if(gamepad1.left_bumper) {
                oneCycle = OneCycle.True;
            }
            else if(gamepad1.right_bumper) {
                oneCycle = OneCycle.False;
            }

            teamPropPosition = visionSubsystem.getTeamPropPosition();

            MatchConfig.Alliance = alliance;
            MatchConfig.AutonomousStartLocation = startLocation;
            MatchConfig.TeamPropPosition = teamPropPosition;
            MatchConfig.TravelDirection = direction;
            MatchConfig.ParkEnding = parkEnding;
            MatchConfig.OneCycle = oneCycle;
            MatchConfig.telemetry = telemetry;

            telemetry.addData("Team Prop Position: ", teamPropPosition);
            telemetry.addData("Alliance: ", alliance);
            telemetry.addData("Auto Start Location: ", startLocation);
            telemetry.addData("Auto Travel Direction : ", direction);
            telemetry.addData("Auto Park Location : ", parkEnding);
            telemetry.addData("Auto Cycle : ", oneCycle);
            telemetry.update();
        }

        waitForStart();

        visionSubsystem.stopTensorFlowProcessing();
        visionSubsystem.shutDownTensorFlowProcessor();

        TrajectorySequence phase1, phase2, phase3, phase1_cycle, phaseF_cycle, phase2_cycle, phase3_cycle, park;

        if(startLocation == AutonomousStartLocation.Near) {
            BuildNearPaths.Build(driveBaseSubsystem.getDrive(), teamPropPosition, alliance, parkEnding, oneCycle);
            phase1 = BuildNearPaths.Phase1;
            phase2 = BuildNearPaths.Phase2;
            phase3 = BuildNearPaths.Phase3;
            phase1_cycle = BuildNearPaths.Phase1_Cycle;
            phaseF_cycle = BuildNearPaths.PhaseF_Cycle;
            phase2_cycle = BuildNearPaths.Phase2_Cycle;
            phase3_cycle = BuildNearPaths.Phase3_Cycle;
            park = BuildNearPaths.Park;
        } else{
            BuildFarPaths.Build(driveBaseSubsystem.getDrive(), teamPropPosition, alliance, direction, parkEnding, oneCycle);
            phase1 = BuildFarPaths.Phase1;
            phase2 = BuildFarPaths.Phase2;
            phase3 = BuildFarPaths.Phase3;
            phase1_cycle = BuildFarPaths.Phase1_Cycle;
            phaseF_cycle = BuildFarPaths.PhaseF_Cycle;
            phase2_cycle = BuildFarPaths.Phase2_Cycle;
            phase3_cycle = BuildFarPaths.Phase3_Cycle;
            park = BuildFarPaths.Park;
        }

        schedule(
                new SequentialCommandGroup(
                        new AprilTagStartStopCommand(visionSubsystem, AprilTagStartStopCommand.State.Start),
                        new TrajectorySequenceFollowerCommand(driveBaseSubsystem, phase1),
                        new ParallelCommandGroup(
                                new PlacePixelOnSpikeCommand(intakeMotorSubsystem).withTimeout(2000),
                                new TrajectorySequenceFollowerCommand(driveBaseSubsystem, phase2)
                        ),
                        //new StrafeToFindAprilTagCommand(driveBaseSubsystem, visionSubsystem),
                        new DriveToAprilTagCommand(visionSubsystem, driveBaseSubsystem),
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






                        //region Cycle
                        new TrajectorySequenceFollowerCommand(driveBaseSubsystem, phase1_cycle),

                        new ParallelCommandGroup(
                                new TrajectorySequenceFollowerCommand(driveBaseSubsystem, phaseF_cycle),
                                new InstantCommand(intakeMotorSubsystem::intake, intakeMotorSubsystem)
                                ),

                        new WaitCommand(1000),
                        new InstantCommand(intakeMotorSubsystem::stop, intakeMotorSubsystem),
                        new TrajectorySequenceFollowerCommand(driveBaseSubsystem, phase2_cycle),

                        new DriveToAprilTagCommand(visionSubsystem, driveBaseSubsystem),
                        new DriveForwardToObjectCommand(driveBaseSubsystem, distanceSensorSubsystem, GyroSubsystem.getInstance(hardwareMap, telemetry), Configuration.BACKDROP_DISTANCE),


                        new SequentialCommandGroup(
                                new RunLinearSlideAndCenterPixelBoxCommand(extakeSubsystem,linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_AUTO),
                                new MovePixelBoxArmToPositionCommand(extakeSubsystem, PixelBoxArmPosition.Extake)
                        ).withTimeout(1500),
                        new WaitCommand(1000),
                        new SelectCommand(
                                new HashMap<Object, Command>(){{
                                    put(TeamPropPosition.Left, new InstantCommand(extakeSubsystem::rightRotation, extakeSubsystem));
                                    put(teamPropPosition.Center, new InstantCommand(extakeSubsystem::leftRotation, extakeSubsystem));
                                    put(teamPropPosition.Right, new InstantCommand(extakeSubsystem::leftRotation, extakeSubsystem));
                                }},
                                this::getTeamPropPosition
                        ),
                        new InstantCommand(extakeSubsystem::pixelEject, extakeSubsystem),
                        new WaitCommand(1000),
                        //endregion







                        new TrajectorySequenceFollowerCommand(driveBaseSubsystem, phase3_cycle),
                        new StopPixelBoxReset(extakeSubsystem, linearSlideSubsystem),

                        new TrajectorySequenceFollowerCommand(driveBaseSubsystem, park),
                        new InstantCommand(extakeSubsystem::pixelStop, extakeSubsystem),
                        new AprilTagStartStopCommand(visionSubsystem, AprilTagStartStopCommand.State.Stop)
                ));

        // run the scheduler
        while (!isStopRequested() && opModeIsActive()) {
            try {
                run();
            }
            catch(Exception ex){
                telemetry.addData("Error in run method ", ex.getMessage());
            }
            //telemetry.addData("Gyro Reading(Degrees): ", gyroSubsystem.getHeading(AngleUnit.DEGREES));
            telemetry.update();
        }

        reset();
    }


    //region Gyro
    private double getSquareDegree() {
        if(MatchConfig.Alliance == Alliance.Blue) {
            return 270;
        }
        else {
            return 90;
        }
    }
    //endregion
    private TeamPropPosition getTeamPropPosition(){
        return teamPropPosition;
    }
}

