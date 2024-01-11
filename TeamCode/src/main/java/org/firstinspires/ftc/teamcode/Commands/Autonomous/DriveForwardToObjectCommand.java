package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.GyroSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;

@Config
public class DriveForwardToObjectCommand extends CommandBase {
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final DistanceSensorSubsystem distanceSensorSubsystem;
    private final GyroSubsystem gyroSubsystem;
    private final double stopDistance;

    public DriveForwardToObjectCommand(MecanumDriveSubsystem drive, DistanceSensorSubsystem dist, GyroSubsystem gSubsystem, double stopDistance){
        mecanumDriveSubsystem = drive;
        distanceSensorSubsystem = dist;
        gyroSubsystem = gSubsystem;
        this.stopDistance = stopDistance;

        addRequirements(mecanumDriveSubsystem, distanceSensorSubsystem);
    }

    @Override
    public void execute(){
        MatchConfig.telemetry.addLine("Executing " + this.getClass().getName());
        MatchConfig.telemetry.addData("Average Distance to Object: ", distanceSensorSubsystem.getBackAverageDistance(Configuration.MAX_DISTANCE));
        mecanumDriveSubsystem.drive(Configuration.DRIVE_FORWARD_SPEED,0,0,Configuration.DRIVE_FORWARD_POWER);
    }

    @Override
    public boolean isFinished(){
        double distance = distanceSensorSubsystem.getBackAverageDistance(Configuration.MAX_DISTANCE);
        double delta = Math.abs( distance - stopDistance);
        MatchConfig.telemetry.addData("Average Distance to Object: ", distance);
        MatchConfig.telemetry.addData("Delta: ", delta);
        MatchConfig.telemetry.update();
        return delta < Configuration.DISTANCE_ERROR_DISTANCE;
    }

    @Override
    public void end(boolean interrupted){
        this.mecanumDriveSubsystem.stop();
    }

    private void squareUpRobot(){
        double diff = Math.abs(distanceSensorSubsystem.getBackLeft() - distanceSensorSubsystem.getBackRight());
        double threshold = Math.min(distanceSensorSubsystem.getBackLeft(), distanceSensorSubsystem.getBackRight()) * 0.01;

        double leftX, rightX;
        if(distanceSensorSubsystem.getBackLeft() > distanceSensorSubsystem.getBackRight()){
            leftX = 0;
            rightX = .5;
        }
        else {
            leftX = .5;
            rightX=0;
        }

        double a,b;

        while (diff > threshold) {
            a = distanceSensorSubsystem.getBackLeft();
            b = distanceSensorSubsystem.getBackRight();
            this.mecanumDriveSubsystem.drive(0,leftX,rightX,.3);
            a *= 0.99;
            b *= 1.01;

            diff = Math.abs(a - b);
        }
        mecanumDriveSubsystem.stop();
    }

}
