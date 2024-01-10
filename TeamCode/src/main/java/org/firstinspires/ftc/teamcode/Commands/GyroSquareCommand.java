package org.firstinspires.ftc.teamcode.Commands;

import android.service.autofill.FieldClassification;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Subsystems.GyroSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;

public class GyroSquareCommand extends CommandBase {

    private final GyroSubsystem gyroSubsystem;
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final double finalDegreeAngle;

    public GyroSquareCommand(GyroSubsystem gSystem, MecanumDriveSubsystem mSystem, double degree) {
        gyroSubsystem = gSystem;
        mecanumDriveSubsystem = mSystem;
        finalDegreeAngle = degree;

        addRequirements(gyroSubsystem, mecanumDriveSubsystem);
    }

    private void SquareToGyro(){
        double currentHeading = this.gyroSubsystem.getHeading(AngleUnit.DEGREES);
        double error;
        if( MatchConfig.Alliance == Alliance.Red){
            error = 90 - currentHeading;

        } else {
            error = currentHeading - 270;
        }
        MatchConfig.telemetry.addData("Current Heading: ", currentHeading);
        MatchConfig.telemetry.addData("Error: ", error);

        mecanumDriveSubsystem.turn(Math.toRadians(error));
        mecanumDriveSubsystem.update();
    }
    @Override
    public void execute() {
        if(!mecanumDriveSubsystem.isBusy()) {
            SquareToGyro();
        }
    }

    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return this.gyroSubsystem.getHeading(AngleUnit.DEGREES) < finalDegreeAngle + 1.5 && this.gyroSubsystem.getHeading(AngleUnit.DEGREES) > finalDegreeAngle - 1.5;
    }
}
