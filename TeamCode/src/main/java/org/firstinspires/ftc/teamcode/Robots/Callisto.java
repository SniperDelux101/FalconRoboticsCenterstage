package org.firstinspires.ftc.teamcode.Robots;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.Commands.ExtendClimbArmsCommand;
import org.firstinspires.ftc.teamcode.Commands.LaunchDrone;
import org.firstinspires.ftc.teamcode.Commands.RetractClimbArmsCommand;
import org.firstinspires.ftc.teamcode.Subsystems.AirplaneLauncherSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OdometryControlSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

public class Callisto extends Robot {
    //region Subsystems
    private final MecanumDriveSubsystem driveBaseSubsystem;
    private final AirplaneLauncherSubsystem airplaneLauncherSubsystem;
    private final ClimbSubsystem climbSubsystem;
    private final ExtakeSubsystem extakeSubsystem;
    private final LinearSlideSubsystem linearSlideSubsystem;
    private final OdometryControlSubsystem odometryControlSubsystem;
    //endregion

    //region Commands
//    DefaultDrive driveCommand;
    //endregion

    //region Utility Classes
    Telemetry telemetry;
    HardwareMap hMap;
    GamepadEx driverGamepad, utilityGamepad;
    //endregion
    public Callisto(RobotMode mode, HardwareMap map, Gamepad gamepad1, Gamepad gamepad2, Telemetry tel) {
        hMap = map;
        telemetry = tel;

        //region Initialize Subsystems
        driveBaseSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(map),false);
        odometryControlSubsystem = new OdometryControlSubsystem(hMap);
        airplaneLauncherSubsystem = new AirplaneLauncherSubsystem(hMap);
        climbSubsystem = new ClimbSubsystem(hMap);
        extakeSubsystem = new ExtakeSubsystem(hMap);
        linearSlideSubsystem = new LinearSlideSubsystem(hMap);
        //endregion

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

//        odometryControlSubsystem.retract();

//        driveCommand = new DefaultDrive(driveBaseSubsystem, driverGamepad::getLeftX, driverGamepad::getLeftY, driverGamepad::getRightX);
//        register(driveBaseSubsystem);
//        driveBaseSubsystem.setDefaultCommand(driveCommand);

        utilityGamepad.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new ExtendClimbArmsCommand(climbSubsystem));
        utilityGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new SequentialCommandGroup(
                        new LaunchDrone(airplaneLauncherSubsystem),
                        new RetractClimbArmsCommand(climbSubsystem)
                ));


        utilityGamepad.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(
                        new InstantCommand(()->{
                            extakeSubsystem.rightRotation();
                        })
                );
    }

    private void initAuto() {
        //TODO: Add code for autonomous driving

        odometryControlSubsystem.retract();
        odometryControlSubsystem.drop();
    }

    @Override
    public void run() {

        telemetry.addData("x", driveBaseSubsystem.getPoseEstimate().getX());
        telemetry.addData("y", driveBaseSubsystem.getPoseEstimate().getY());
        telemetry.addData("heading", driveBaseSubsystem.getPoseEstimate().getHeading());
        telemetry.update();
        super.run();

        driveBaseSubsystem.drive(driverGamepad.getLeftY(), driverGamepad.getLeftX(), driverGamepad.getRightX());
    }

//    public void cleanUp() {
//        odometryControlSubsystem.retract();
//    }

}

