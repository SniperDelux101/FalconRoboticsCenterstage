package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.DriveForwardToObjectCommand;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.GyroSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

@Config
@SuppressWarnings("unused")
public class DriveForwardbyDistanceTest extends CommandOpMode {
    private DistanceSensorSubsystem distanceSensorSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    public static double StopDistance = 5.0;
    private GamepadEx gamePad1;
    @Override
    public void initialize() {
        mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        distanceSensorSubsystem = new DistanceSensorSubsystem(hardwareMap, telemetry);

        gamePad1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new DriveForwardToObjectCommand(mecanumDriveSubsystem, distanceSensorSubsystem,
                GyroSubsystem.getInstance(hardwareMap, telemetry), StopDistance)
        );

        //gamePad1.getGamepadButton(GamepadKeys.Button.B).whenPressed();

    }
}
