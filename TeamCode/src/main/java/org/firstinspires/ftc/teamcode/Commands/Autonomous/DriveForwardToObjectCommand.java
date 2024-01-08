package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.GyroSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.opencv.core.Mat;

@Config
public class DriveForwardToObjectCommand extends CommandBase {
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final DistanceSensorSubsystem distanceSensorSubsystem;
    private final GyroSubsystem gyroSubsystem;
    private final double stopDistance;
    private boolean hasExecuted = false;
    private boolean shakeRobot = false;

    public DriveForwardToObjectCommand(MecanumDriveSubsystem drive, DistanceSensorSubsystem dist, GyroSubsystem gSubsystem, double stopDistance){
        mecanumDriveSubsystem = drive;
        distanceSensorSubsystem = dist;
        gyroSubsystem = gSubsystem;
        this.stopDistance = stopDistance;

        addRequirements(mecanumDriveSubsystem, distanceSensorSubsystem);
    }

    @Override
    public void initialize(){
        //this.squareUpRobot();
    }

    @Override
    public void execute(){
        MatchConfig.telemetry.addData("Average Distance to Object: ", distanceSensorSubsystem.getBackAverageDistance());
        MatchConfig.telemetry.update();
        if(!hasExecuted && distanceSensorSubsystem.getBackAverageDistance() < 36 && !mecanumDriveSubsystem.isBusy()) {
            TrajectorySequence t;
            if (distanceSensorSubsystem.getBackAverageDistance() < this.stopDistance) {
                //this.mecanumDriveSubsystem.drive(X, 0, 0, POWER);
                t = mecanumDriveSubsystem.getDrive().trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.VISION_VEL, Configuration.TRACKWIDTH))
                        .forward(stopDistance - distanceSensorSubsystem.getBackAverageDistance())
                        .build();
            } else {
                //this.mecanumDriveSubsystem.drive(-X, 0, 0, POWER);
                t = mecanumDriveSubsystem.getDrive().trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.VISION_VEL, Configuration.TRACKWIDTH))
                        .back(distanceSensorSubsystem.getBackAverageDistance() - stopDistance)
                        .build();
            }
            hasExecuted = true;
            mecanumDriveSubsystem.getDrive().followTrajectorySequence(t);
        }
        else{
            if(!shakeRobot && !hasExecuted)
            {
                double turnRadians = Math.toRadians(Configuration.SHAKE_DEGREES);
                TrajectorySequence ts = mecanumDriveSubsystem.getDrive().trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                        .turn(turnRadians)
                        .turn(-turnRadians*2)
                        .turn(turnRadians)
                        .build();
                mecanumDriveSubsystem.getDrive().followTrajectorySequence(ts);
                shakeRobot = true;
            }
        }
    }

    @Override
    public boolean isFinished(){
        double delta = Math.abs(distanceSensorSubsystem.getBackAverageDistance() - stopDistance);
        MatchConfig.telemetry.addData("Average Distance to Object: ", distanceSensorSubsystem.getBackAverageDistance());
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

    private void SquareToGyro(){
        double currentHeading = this.gyroSubsystem.getHeading(AngleUnit.DEGREES);
        double error;
        if( MatchConfig.Alliance == Alliance.Red){
            error = 90 - currentHeading;

        } else {
            error = currentHeading - 270;
        }

        mecanumDriveSubsystem.turn(Math.toRadians(error));
    }

}
