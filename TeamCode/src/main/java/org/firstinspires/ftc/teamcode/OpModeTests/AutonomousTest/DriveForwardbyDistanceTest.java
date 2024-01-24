package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.DriveForwardToObjectCommand;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.GyroSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;

@Config
@SuppressWarnings("unused")
@Autonomous
public class DriveForwardbyDistanceTest extends CommandOpMode {
    private DistanceSensorSubsystem distanceSensorSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    public static double StopDistance = 5.0;
    private GamepadEx gamePad1;
    @Override
    public void initialize() {

        MatchConfig.telemetry = telemetry;

        mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        distanceSensorSubsystem = new DistanceSensorSubsystem(hardwareMap, telemetry);

        schedule(new DriveForwardToObjectCommand(mecanumDriveSubsystem, distanceSensorSubsystem,
                GyroSubsystem.getInstance(hardwareMap, telemetry), StopDistance));

        //gamePad1.getGamepadButton(GamepadKeys.Button.B).whenPressed();

    }
}
