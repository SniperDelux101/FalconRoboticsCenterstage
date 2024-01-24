package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.GyroSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;

@Config
@TeleOp
public class GyroTest extends OpMode {
    public static Alliance alliance = Alliance.Blue;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private DistanceSensorSubsystem distanceSensorSubsystem;
    private GyroSubsystem gyroSubsystem;
    public static double RedDegree = 90;
    public static double BlueDegree = 270;

    @Override
    public void init() {
        mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        gyroSubsystem = GyroSubsystem.getInstance(hardwareMap, telemetry);
        gyroSubsystem.ResetYaw();
        MatchConfig.Alliance = alliance;
        //distanceSensorSubsystem = new DistanceSensorSubsystem(hardwareMap, telemetry);
    }


    @Override
    public void loop() {
        mecanumDriveSubsystem.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        if(gamepad1.a){
            SquareToGyro();
        }

//        telemetry.addData("Left Sensor: ", distanceSensorSubsystem.getLeft());
//        telemetry.addData("Right Sensor: ", distanceSensorSubsystem.getRight());
//        telemetry.addData("Right Back Sensor: ", distanceSensorSubsystem.getBackRight());
//        telemetry.addData("Left Back Sensor: ", distanceSensorSubsystem.getBackLeft());
        telemetry.addData("Current Heading: ", gyroSubsystem.getHeading(AngleUnit.DEGREES));
        telemetry.update();
    }

    private void SquareToGyro(){
        double currentHeading = this.gyroSubsystem.getHeading(AngleUnit.DEGREES);
        double error;
        if( MatchConfig.Alliance == Alliance.Red){
            //red is +90
            error = RedDegree - currentHeading;

        } else {
            //blue is -90
            error = currentHeading - BlueDegree;
        }

        telemetry.addData("Error: ", error);

        mecanumDriveSubsystem.turnAsync(Math.toRadians(error));
    }
}
