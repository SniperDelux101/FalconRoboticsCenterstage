package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

public class AutonomousDriveCommand extends CommandBase {

    final MecanumDriveSubsystem mecanumDriveSubsystem;
    final Alliance alliance;
    final AutonomousPath autonomousPath;
    private TrajectorySequence trajectorySequence;

    public AutonomousDriveCommand(MecanumDriveSubsystem dr, Alliance p_alliance , AutonomousPath path ){
        mecanumDriveSubsystem = dr;
        alliance = p_alliance ;
        autonomousPath = path;

        addRequirements(mecanumDriveSubsystem);

    }

    @Override
    public void initialize()
    {
        if ( alliance == Alliance.Blue && autonomousPath == AutonomousPath.Far)
            trajectorySequence = this.blueAllianceFar();
        else if ( alliance == Alliance.Red && autonomousPath == AutonomousPath.Far)
            trajectorySequence = this.redAllianceFar();
        else if ( alliance == Alliance.Blue && autonomousPath == AutonomousPath.Near)
            trajectorySequence = this.blueAllianceNear();
        else if ( alliance == Alliance.Red && autonomousPath == AutonomousPath.Near)
            trajectorySequence = this.redAllianceNear();
    }
    @Override
    public void execute(){
        /// TODO:
       mecanumDriveSubsystem.getDrive().followTrajectorySequence(trajectorySequence);
    }
    @Override
    public boolean isFinished(){
        return !mecanumDriveSubsystem.isBusy();
    }


    // autonomous pathing from red alliance far from the back drop
    public TrajectorySequence redAllianceFar (){

        TrajectorySequence untitled0 = mecanumDriveSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d(-36.91, -61.93, Math.toRadians(450.00)))
                .splineTo(new Vector2d(-35.87, -35.87), Math.toRadians(447.71))
                .splineTo(new Vector2d(-37.09, -11.03), Math.toRadians(92.80))
                .splineTo(new Vector2d(-14.68, -8.08), Math.toRadians(367.51))
                .splineTo(new Vector2d(9.29, -9.99), Math.toRadians(355.44))
                .splineTo(new Vector2d(34.13, -10.34), Math.toRadians(359.20))
                .splineTo(new Vector2d(63.14, -9.81), Math.toRadians(1.15))
                .build();
        return untitled0;
    }
    // autonomous pathing from blue alliance far from the back drop
    public TrajectorySequence blueAllianceFar (){
        TrajectorySequence untitled0 = mecanumDriveSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d(-36.91, 61.93, Math.toRadians(-90.00)))
                .splineTo(new Vector2d(-35.87, 35.87), Math.toRadians(-87.71))
                .splineTo(new Vector2d(-37.09, 11.03), Math.toRadians(267.20))
                .splineTo(new Vector2d(-14.68, 8.08), Math.toRadians(-7.51))
                .splineTo(new Vector2d(9.29, 9.99), Math.toRadians(4.56))
                .splineTo(new Vector2d(34.13, 10.34), Math.toRadians(0.80))
                .splineTo(new Vector2d(57.93, 11.55), Math.toRadians(2.92))
                .build();

        return untitled0;
    }
    //  autonomous pathing from red alliance near the back drop
    public TrajectorySequence redAllianceNear (){
        TrajectorySequence untitled0 = mecanumDriveSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d(12.07, -61.23, Math.toRadians(0.00)))
                .splineTo(new Vector2d(36.39, -60.88), Math.toRadians(0.82))
                .splineTo(new Vector2d(62.10, -61.23), Math.toRadians(-0.77))
                .build();

        return untitled0;
    }
    // autonomous pathing from blue alliance near the back drop
    public TrajectorySequence blueAllianceNear (){
        TrajectorySequence untitled0 = mecanumDriveSubsystem.getDrive().trajectorySequenceBuilder(new Pose2d(11.55, 61.06, Math.toRadians(0.00)))
                .splineTo(new Vector2d(36.22, 61.23), Math.toRadians(0.40))
                .splineTo(new Vector2d(60.01, 61.23), Math.toRadians(0.00))
                .build();

        return untitled0;
    }
}
