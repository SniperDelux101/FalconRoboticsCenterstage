package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousDriveCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.BuildBlueFarPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.BuildBlueNearPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.BuildRedFarPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.BuildRedNearPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.PlacePixelOnSpikeCommand;
import org.firstinspires.ftc.teamcode.Commands.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.Commands.TrajectorySequenceFollowerCommand;
import org.firstinspires.ftc.teamcode.Subsystems.AirplaneLauncherSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OdometryControlSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

import java.security.PublicKey;
import java.util.HashMap;

@Autonomous(preselectTeleOp = "Drivebase_Op")
@Config
public class AutonomousCommandTest extends CommandOpMode {
    ///region Subsystems
    private MecanumDriveSubsystem driveBaseSubsystem;
    private AirplaneLauncherSubsystem airplaneLauncherSubsystem;
    private ClimbSubsystem climbSubsystem;
    private ExtakeSubsystem extakeSubsystem;
    private LinearSlideSubsystem linearSlideSubsystem;
    private OdometryControlSubsystem odometryControlSubsystem;
    private IntakeMotorSubsystem intakeMotorSubsystem;
    private VisionSubsystem visionSubsystem;
    ///endregion

    public static Alliance alliance = Alliance.Blue;
    public static AutonomousStartLocation startLocation = AutonomousStartLocation.Near;
    public static TeamPropPosition teamPropPosition = TeamPropPosition.Center;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();

        driveBaseSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        odometryControlSubsystem = new OdometryControlSubsystem(hardwareMap, telemetry);
        airplaneLauncherSubsystem = new AirplaneLauncherSubsystem(hardwareMap, telemetry);
        climbSubsystem = new ClimbSubsystem(hardwareMap, telemetry);
        extakeSubsystem = new ExtakeSubsystem(hardwareMap, telemetry);
        linearSlideSubsystem = new LinearSlideSubsystem(hardwareMap, telemetry);
        intakeMotorSubsystem = new IntakeMotorSubsystem(hardwareMap, telemetry);
        visionSubsystem = new VisionSubsystem(hardwareMap, telemetry);

        register(driveBaseSubsystem, airplaneLauncherSubsystem, climbSubsystem, extakeSubsystem, linearSlideSubsystem, odometryControlSubsystem, intakeMotorSubsystem, visionSubsystem);
        visionSubsystem.initTfod(false);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();


        boolean readVision = true;

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

            if(readVision){
                teamPropPosition = visionSubsystem.getTeamPropPosition();
                readVision = false;
            }

            telemetry.addData("Team Prop Position: ", teamPropPosition);
            telemetry.addData("Alliance: ", alliance);
            telemetry.addData("Auto Start Location: ", startLocation);
            telemetry.update();
        }

        waitForStart();


        visionSubsystem.stopStreaming();
        TrajectorySequence phase1, phase2;

        if(startLocation == AutonomousStartLocation.Near && alliance == Alliance.Blue) {
            BuildBlueNearPaths.Build(driveBaseSubsystem.getDrive(), teamPropPosition);
            phase1 = BuildBlueNearPaths.Phase1;
            phase2 = BuildBlueNearPaths.Phase2;
        } else if(startLocation == AutonomousStartLocation.Far  && alliance == Alliance.Blue){
            BuildBlueFarPaths.Build(driveBaseSubsystem.getDrive(), teamPropPosition);
            phase1 = BuildBlueFarPaths.Phase1;
            phase2 = BuildBlueFarPaths.Phase2;
        }
        else if(startLocation == AutonomousStartLocation.Near && alliance == Alliance.Red){
            BuildRedNearPaths.Build(driveBaseSubsystem.getDrive(), teamPropPosition);
            phase1 = BuildRedNearPaths.Phase1;
            phase2 = BuildRedNearPaths.Phase2;
        } else{
            BuildRedFarPaths.Build(driveBaseSubsystem.getDrive(), teamPropPosition);
            phase1 = BuildRedFarPaths.Phase1;
            phase2 = BuildRedFarPaths.Phase2;
        }

        schedule(
                    new SequentialCommandGroup(
                    new TrajectorySequenceFollowerCommand(driveBaseSubsystem, phase1),
                    // This places a pixel on the spike
                            new ParallelCommandGroup(
                                    new PlacePixelOnSpikeCommand(intakeMotorSubsystem).withTimeout(2000),
                                    new TrajectorySequenceFollowerCommand(driveBaseSubsystem, phase2)
                            )
                )
        );

        // run the scheduler
        while (!isStopRequested() && opModeIsActive()) {
            run();
        }
        reset();
    }
/*
    public TrajectorySequence BlueNearCenter_Phase1(){
       return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
               .strafeRight(5)
               .forward(32)
                .build();
    }
    public TrajectorySequence BlueNearCenter_Phase2(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .back(28)
                .waitSeconds(1)
                .turn(Math.toRadians(90) + 1e-6)
                .waitSeconds(1)
                .forward(35)
                .build();
    }
    public TrajectorySequence BlueNearLeft_Phase1 (){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(9.0)
                .forward(28.0)
                .build();
    }
    public TrajectorySequence BlueNearLeft_Phase2 (){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .back(23.0)
                .turn(Math.toRadians(90) + 1e-6)
                .forward(29.0)
                .build();
    }
    public TrajectorySequence BlueNearRight_Phase1 (){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
         .forward(29.0)
                .turn(Math.toRadians(-90) - 1e-6)
                .build();
    }
    public TrajectorySequence BlueNearRight_Phase2 (){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .forward(9.0)
                .back(5.0)
                .strafeRight(25.0)
                .back(35.0)
                .build();
    }
    public TrajectorySequence BlueFarCenter_Phase1(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
         .forward(50.0)
                .turn(Math.toRadians(180) + 1e-6)
                .build();

    }
    public TrajectorySequence BlueFarCenter_Phase2(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .forward(5.0)
                .back(8.0)
                .turn(Math.toRadians(-90) - 1e-6)
                .forward(90.0)
                .build();

    }
    public TrajectorySequence BlueFarLeft_Phase1(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
         .forward(29.0)
                .turn(Math.toRadians(90) + 1e-6)
                .forward(7.0)
                .back(5.0)
                .strafeRight(22.0)
                .forward(90.0)
                .build();

    }
    public TrajectorySequence BlueFarLeft_Phase2(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
        .forward(7.0)
                .back(5.0)
                .strafeRight(22.0)
                .forward(90.0)
                .build();

    }
    public TrajectorySequence BlueFarRight_Phase1 (){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .forward(29.0)
                .turn(Math.toRadians(-90) - 1e-6)
                .build();

    }
    public TrajectorySequence BlueFarRight_Phase2 (){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .forward(9.0)
                .back(8.0)
                .strafeLeft(25.0)
                .back(93.0)
                .build();
    }
    public TrajectorySequence RedFarCenter_Phase1(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .forward(50.0)
                .turn(Math.toRadians(180) + 1e-6)
                .build();

    }
    public TrajectorySequence RedFarCenter_Phase2(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .forward(5.0)
                .back(8.0)
                .turn(Math.toRadians(90) + 1e-6)
                .forward(90.0)
                .build();

    }
    public TrajectorySequence RedFarLeft_Phase1(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .forward(29.0)
                .turn(Math.toRadians(90) + 1e-6)
                .build();

    }
    public TrajectorySequence RedFarLeft_Phase2(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .forward(9.0)
                .back(8.0)
                .strafeRight(25.0)
                .back(93.0)
                .build();
    }


    public TrajectorySequence RedFarRight_Phase1(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
         .forward(29.0)
                .forward(29.0)
                .turn(Math.toRadians(-90) - 1e-6)
                .build();
    }
    public TrajectorySequence RedFarRight_Phase2 (){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .forward(5.0)
                .back(8.0)
                .strafeLeft(25.0)
                .forward(93.0)
                .build();
    }

    public TrajectorySequence RedNearCenter_Phase1 (){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(6.0)
                .forward(32.0)
                .build();
    }
    public TrajectorySequence RedNearCenter_Phase2 (){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .back(27.0)
                .turn(Math.toRadians(-90) - 1e-6)
                .forward(32.0)
                .build();
    }

    public TrajectorySequence RedNearRight_Phase1(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .strafeRight(5)
                .forward(24)
                .build();
    }
    public TrajectorySequence RedNearRight_Phase2(){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .back(18)
                .waitSeconds(1)
                .turn(Math.toRadians(-90) - 1e-6)
                .waitSeconds(1)
                .forward(23)
                .build();
    }

    public TrajectorySequence RedNearLeft_Phase1 (){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(6.0)
                .forward(32.0)
                .turn(Math.toRadians(90) + 1e-6)
                .forward(4)
                .build();
    }
    public TrajectorySequence RedNearLeft_Phase2 (){
        return driveBaseSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d())
                .back(6)
                .turn(Math.toRadians(-90) - 1e-6)
                .back(25.0)
                .turn(Math.toRadians(-90) - 1e-6)
                .forward(32.0)
                .build();
    }
 */

}

