package org.firstinspires.ftc.teamcode.Robots;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousDriveCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.FireDroneAndClimbCommand;
import org.firstinspires.ftc.teamcode.Commands.MovePixelBoxArmToPositionCommand;
import org.firstinspires.ftc.teamcode.Commands.MoveToPixelBoxPosition;
import org.firstinspires.ftc.teamcode.Commands.PixelBoxArmPosition;
import org.firstinspires.ftc.teamcode.Commands.PixelBoxPosition;
import org.firstinspires.ftc.teamcode.Commands.ResetAndPrepForExchangeCommand;
import org.firstinspires.ftc.teamcode.Commands.RunLinearSlideAndCenterPixelBoxCommand;
import org.firstinspires.ftc.teamcode.Commands.StopPixelBoxReset;
import org.firstinspires.ftc.teamcode.Subsystems.AirplaneLauncherSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OdometryControlSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class Callisto extends Robot {
    //region Subsystems
    private final MecanumDriveSubsystem driveBaseSubsystem;
    private final AirplaneLauncherSubsystem airplaneLauncherSubsystem;
    private final ClimbSubsystem climbSubsystem;
    private final ExtakeSubsystem extakeSubsystem;
    private final LinearSlideSubsystem linearSlideSubsystem;
    private final OdometryControlSubsystem odometryControlSubsystem;
    private final IntakeMotorSubsystem intakeMotorSubsystem;
    private final VisionSubsystem visionSubsystem;
    //endregion

    //region Commands
//    DefaultDrive driveCommand;
    //endregion

    //region Utility Classes
    Telemetry telemetry;
    HardwareMap hMap;
    GamepadEx driverGamepad, utilityGamepad;
    Gamepad FTC_driverGamepad, FTC_utilityGamepad;


    private final Alliance alliance;
    private final AutonomousStartLocation autonomousStartLocation;
    private final RobotMode robotMode;
    //endregion
    public Callisto(RobotMode mode, HardwareMap map, Gamepad gamepad1, Gamepad gamepad2, Telemetry tel , Alliance p_alliance , AutonomousStartLocation path) {
        hMap = map;
        telemetry = tel;
        robotMode = mode;
        alliance = p_alliance;
        autonomousStartLocation = path;
        FTC_driverGamepad = gamepad1;
        FTC_utilityGamepad = gamepad2;

        //region Initialize Subsystems
        driveBaseSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(map),false);
        odometryControlSubsystem = new OdometryControlSubsystem(hMap , telemetry);
        airplaneLauncherSubsystem = new AirplaneLauncherSubsystem(hMap, telemetry);
        climbSubsystem = new ClimbSubsystem(hMap, telemetry);
        extakeSubsystem = new ExtakeSubsystem(hMap, telemetry);
        linearSlideSubsystem = new LinearSlideSubsystem(hMap , telemetry);
        intakeMotorSubsystem = new IntakeMotorSubsystem(hMap, telemetry);
        //TODO: Fix the vision portal
        visionSubsystem = new VisionSubsystem(hMap, telemetry);
        //endregion

        linearSlideSubsystem.resetEncoder();

        //TODO// UNCOMMENT CODE ONCE VALUES HAVE BEEN DETERMINED
//        odometryControlSubsystem.drop();

        if (mode == RobotMode.AUTO) {
            initAuto();
        } else {
            initTeleOp(gamepad1, gamepad2);
        }
    }

    private void initTeleOp(Gamepad gamepad1, Gamepad gamepad2) {
        driverGamepad = new GamepadEx(gamepad1);
        utilityGamepad = new GamepadEx(gamepad2);

        odometryControlSubsystem.retract();

        // linear slide extends to high position
        utilityGamepad.getGamepadButton(GamepadKeys.Button.B)
                        .whenPressed(
                                new SequentialCommandGroup(
                                    new RunLinearSlideAndCenterPixelBoxCommand( extakeSubsystem,linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_HI),
                                        new MovePixelBoxArmToPositionCommand(extakeSubsystem, PixelBoxArmPosition.Extake)
                                )
                        );
        // linear slide extends to med position
        utilityGamepad.getGamepadButton(GamepadKeys.Button.Y)
                        .whenPressed(
                                new SequentialCommandGroup(
                                    new RunLinearSlideAndCenterPixelBoxCommand(extakeSubsystem,linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_MED),
                                        new MovePixelBoxArmToPositionCommand(extakeSubsystem, PixelBoxArmPosition.Extake)
                                )
                        );
        //linear slide extends to low position
        utilityGamepad.getGamepadButton(GamepadKeys.Button.X)
                        .whenPressed(
                                new SequentialCommandGroup(
                                    new RunLinearSlideAndCenterPixelBoxCommand(extakeSubsystem,linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_LO),
                                        new MovePixelBoxArmToPositionCommand(extakeSubsystem, PixelBoxArmPosition.Extake)
                                )
                        );
        //linear slide extends to transfer position
        utilityGamepad.getGamepadButton(GamepadKeys.Button.A)
                        .whenPressed(
                                new ResetAndPrepForExchangeCommand(extakeSubsystem, linearSlideSubsystem)
                        );
        // Drop the box
        utilityGamepad.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                        .whenPressed(
                                new MoveToPixelBoxPosition( extakeSubsystem, PixelBoxPosition.Center)
                        );
        utilityGamepad.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(
                        new MoveToPixelBoxPosition( extakeSubsystem, PixelBoxPosition.Left)
                );
        utilityGamepad.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(
                        new MoveToPixelBoxPosition( extakeSubsystem, PixelBoxPosition.Right)
                );
        // Dropping the pixel
        utilityGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenHeld(
                        new InstantCommand(extakeSubsystem::pixelEject, extakeSubsystem)
                )
                .whenReleased(new StopPixelBoxReset(extakeSubsystem, linearSlideSubsystem));

        // Stop the Intake motor
        driverGamepad.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(
                        new InstantCommand(intakeMotorSubsystem::stop, intakeMotorSubsystem)
                );
        // Reverses the intake motor
        driverGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(
                        new SequentialCommandGroup(
                                new InstantCommand(intakeMotorSubsystem::intake, intakeMotorSubsystem),
                                new InstantCommand(extakeSubsystem::pixelIntake, extakeSubsystem)
                        )
                );
        // Intakes motor
        driverGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(
                        new SequentialCommandGroup(
                                new InstantCommand(intakeMotorSubsystem::eject, intakeMotorSubsystem),
                                new InstantCommand(extakeSubsystem::pixelEject, extakeSubsystem)
                                ));

        //Stops the pixelbox from spinning
        driverGamepad.getGamepadButton(GamepadKeys.Button.X)
                        .whenPressed(new InstantCommand(extakeSubsystem::pixelStop, extakeSubsystem));

        //Send climb arms out command
        utilityGamepad.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .and(new GamepadButton(utilityGamepad, GamepadKeys.Button.RIGHT_BUMPER))
                .whenActive(
                        new InstantCommand(climbSubsystem::ClimbOut, climbSubsystem)
                );

        //fire the Drone and Climb
        driverGamepad.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .and(new GamepadButton(driverGamepad, GamepadKeys.Button.A))
                .whenActive(new FireDroneAndClimbCommand(airplaneLauncherSubsystem, climbSubsystem));
        /* Start the intake wheels
        TriggerReader rightTrigger = new TriggerReader(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)
                .wasJustPressed(
                        new InstantCommand(()->{
                                intakeMotorSubsystem.forward();
                })
                );
        rightTrigger.wasJustReleased(
                new InstantCommand(()-> {
                    intakeMotorSubsystem.stop();
                }));
        // Reverse the Intake Wheels
        TriggerReader leftTrigger = new TriggerReader(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)
                .wasJustPressed(
                        new InstantCommand(()-> {
                            intakeMotorSubsystem.reverse();
                        }));
        leftTrigger.wasJustReleased(
                new InstantCommand(()-> {
                    intakeMotorSubsystem.stop();
                }));
*/
    }

    private void initAuto() {
        //TODO: Add code for autonomous driving
        odometryControlSubsystem.drop();

        schedule(new AutonomousDriveCommand(driveBaseSubsystem, linearSlideSubsystem, intakeMotorSubsystem, extakeSubsystem, visionSubsystem,
                alliance, autonomousStartLocation));
    }

    @Override
    public void run() {
        if (robotMode == RobotMode.TELEOP) {
            if (FTC_driverGamepad.left_trigger>0)
                driveBaseSubsystem.drive(driverGamepad.getLeftY(), driverGamepad.getLeftX(), driverGamepad.getRightX(), Configuration.DrivePower*.3);
            else
             driveBaseSubsystem.drive(driverGamepad.getLeftY(), driverGamepad.getLeftX(), driverGamepad.getRightX());
        }

//        telemetry.addData("x", driveBaseSubsystem.getPoseEstimate().getX());
//        telemetry.addData("y", driveBaseSubsystem.getPoseEstimate().getY());
//        telemetry.addData("heading", driveBaseSubsystem.getPoseEstimate().getHeading());
        telemetry.addLine("Driver Control");
        telemetry.addLine("[LEFT BUMPER] --> [Full Eject]");
        telemetry.addLine("[RIGHT BUMPER] --> [Full Intake]");
        telemetry.addLine("[X BUTTON] --> [PixelBox Stop]");
        telemetry.addLine("[Y BUTTON] --> [-------]");
        telemetry.addLine("[B BUTTON] --> [Stop Intake Motor]");
        telemetry.addLine("[A BUTTON] --> [-------]");
        telemetry.addLine("[D-PAD DOWN + RIGHT BUMPER] --> [Launch Drone & Climb In]");

        telemetry.addLine("Utility Control");
        telemetry.addLine("[LEFT BUMPER] --> [-------]");
        telemetry.addLine("[RIGHT BUMPER] --> [Hold to Remove Pixels & Release to go back to exchange]");
        telemetry.addLine("[X BUTTON] --> [Linear Position Low]");
        telemetry.addLine("[Y BUTTON] --> [Linear Position Medium]");
        telemetry.addLine("[B BUTTON] --> [Linear Position High]");
        telemetry.addLine("[A BUTTON] --> [Return to Exchange]");
        telemetry.addLine("[D-PAD LEFT] --> [Extake Left]");
        telemetry.addLine("[D-PAD UP] --> [Extake Center]");
        telemetry.addLine("[D-PAD RIGHT] --> [Extake Right]");
        telemetry.addLine("[D-PAD DOWN + RIGHT BUMPER] --> [Climb Out]");

        telemetry.update();
        super.run();
    }

//    public void cleanUp() {
//        odometryControlSubsystem.retract();
//    }

}

