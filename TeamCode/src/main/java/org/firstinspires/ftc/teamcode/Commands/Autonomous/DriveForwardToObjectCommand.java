package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;

public class DriveForwardToObjectCommand extends CommandBase {
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final DistanceSensorSubsystem distanceSensorSubsystem;
    private final double stopDistance;

    public DriveForwardToObjectCommand(MecanumDriveSubsystem drive, DistanceSensorSubsystem dist, double stopDistance){
        mecanumDriveSubsystem = drive;
        distanceSensorSubsystem = dist;
        this.stopDistance = stopDistance;

        addRequirements(mecanumDriveSubsystem, distanceSensorSubsystem);
    }

    @Override
    public void initialize(){
        this.squareUpRobot();
    }

    @Override
    public void execute(){
        this.mecanumDriveSubsystem.drive(-.5,0,0,.3);
    }

    @Override
    public boolean isFinished(){
        double avgDistance = (distanceSensorSubsystem.getBackLeft() + distanceSensorSubsystem.getBackRight())/2.0;
        return avgDistance <= stopDistance;
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
