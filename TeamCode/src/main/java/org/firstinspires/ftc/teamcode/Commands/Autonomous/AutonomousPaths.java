package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

import java.lang.reflect.Field;
import java.util.Stack;

public class AutonomousPaths {

    public static TrajectorySequence PhaseOne(Alliance alliance, FalconMecanumDrive drive, TeamPropPosition teamPropPosition, AutonomousStartLocation startLocation) {
        if(alliance == Alliance.Red) {
            return RedAlliancePhaseOne(drive, teamPropPosition, startLocation);
        }
        else {
            return BlueAlliancePhaseOne(drive, teamPropPosition, startLocation);
        }
    }

    public static TrajectorySequence PhaseTwo(Alliance alliance, FalconMecanumDrive drive, TeamPropPosition teamPropPosition, AutonomousStartLocation startLocation) {
        if(alliance == Alliance.Red) {
            return RedAlliancePhaseTwo(drive, teamPropPosition, startLocation);
        }
        else {
            return BlueAlliancePhaseTwo(drive, teamPropPosition, startLocation);
        }
    }
    public static TrajectorySequence Park (Alliance alliance, FalconMecanumDrive drive, AutonomousStartLocation startLocation){

        TrajectorySequence path = null;

        if ( alliance== Alliance.Red && startLocation == AutonomousStartLocation.Near){

            path =  drive.trajectorySequenceBuilder(new Pose2d(16.00, -63.00, Math.toRadians(90.00)))
                    .splineTo(new Vector2d(12.00, -34.00), Math.toRadians(90.00))
                    .lineTo(new Vector2d(44.00, -36.00))
                    .splineTo(new Vector2d(44.00, -48.00), Math.toRadians(90.00))
                    .splineTo(new Vector2d(63.00, -60.00), Math.toRadians(90.00))
                    .build();

            return path;
        }
        else if (alliance == Alliance.Red && startLocation == AutonomousStartLocation.Far){
          return path; //TODO; Impelement parking for red alliance far
        }
        else if (alliance == Alliance.Blue && startLocation == AutonomousStartLocation.Near){
            return path; //TODO; Impelement parking for blue alliance near
        }else {
            return path; //TODO; Impelement parking for blue alliance far
        }
    }


    public static TrajectorySequence RedAlliancePhaseOne(FalconMecanumDrive drive, TeamPropPosition teamPropPosition, AutonomousStartLocation startLocation){

        TrajectorySequence path = null;
        if(startLocation == AutonomousStartLocation.Near) {
            if (teamPropPosition == TeamPropPosition.Left) {

                path = drive.trajectorySequenceBuilder(new Pose2d(16.00, -63.00, Math.toRadians(90.00)))
                        .splineTo(new Vector2d(12.00, -34.00), Math.toRadians(90.00))
                        .build();

            } else if (teamPropPosition == TeamPropPosition.Center) {

                //TODO: Implement path for center position

            } else {

                //TODO: Implement path for right position

            }
        }
        else {

            //TODO: Implement pathing for red far

        }
        return path;
    }

    public static TrajectorySequence RedAlliancePhaseTwo(FalconMecanumDrive drive, TeamPropPosition teamPropPosition, AutonomousStartLocation startLocation){

        TrajectorySequence path = null;

        if(teamPropPosition == TeamPropPosition.Left && startLocation == AutonomousStartLocation.Near) {

            path = drive.trajectorySequenceBuilder(new Pose2d(16.00, -63.00, Math.toRadians(90.00)))
                    .splineTo(new Vector2d(12.00, -34.00), Math.toRadians(90.00))
                    .lineTo(new Vector2d(44.00, -36.00))
                    .build();
        }

        else if(teamPropPosition == TeamPropPosition.Left && startLocation == AutonomousStartLocation.Far) {
            //TODO: Implement a left for the far side on red alliance
        }

        else if(teamPropPosition == TeamPropPosition.Center && startLocation == AutonomousStartLocation.Near) {

        }

        else if(teamPropPosition == TeamPropPosition.Center && startLocation == AutonomousStartLocation.Far) {
            //TODO: Implement a center for the far side on red alliance
        }

        else if(teamPropPosition == TeamPropPosition.Right && startLocation == AutonomousStartLocation.Near) {
            //TODO: Implement a right for the near side on red alliance
        }

        else {
            //TODO: Implement a right for the far side on the red alliance
        }

        return path;
    }

    public static TrajectorySequence BlueAlliancePhaseOne(FalconMecanumDrive drive, TeamPropPosition teamPropPosition, AutonomousStartLocation startLocation){

        TrajectorySequence path = null;
        if(startLocation == AutonomousStartLocation.Near) {
            if (teamPropPosition == TeamPropPosition.Left) {
                path = drive.trajectorySequenceBuilder(new Pose2d(16.00, -63.00, Math.toRadians(90.00)))
                        .splineTo(new Vector2d(12.00, -34.00), Math.toRadians(90.00))
                        .build();

            } else if (teamPropPosition == TeamPropPosition.Center) {

                //TODO: Implement a path for the center position

            } else {

                //TODO: Implement path for right position

            }
        }
        else {

            //TODO: Implement pathing for red far

        }
        return path;
    }

    public static TrajectorySequence BlueAlliancePhaseTwo(FalconMecanumDrive drive, TeamPropPosition teamPropPosition, AutonomousStartLocation startLocation){

        TrajectorySequence path = null;

        if(teamPropPosition == TeamPropPosition.Center && startLocation == AutonomousStartLocation.Near) {
            //TODO: Implement a center for the near side on blue alliance
            path = drive.trajectorySequenceBuilder(new Pose2d())
                    .forward(40)
                    .waitSeconds(2)
                    .back(34)
                    .waitSeconds(2)
                    .turn(Math.toRadians(90) + 1e-6)
                    .waitSeconds(2)
                    .forward(20)
                    .build();
        }
        else if(teamPropPosition == TeamPropPosition.Center && startLocation == AutonomousStartLocation.Far) {
            //TODO: Implement a center for the far side on blue alliance
        }
        else if(teamPropPosition == TeamPropPosition.Right && startLocation == AutonomousStartLocation.Near) {
            //TODO: Implement a right for the near side on blue alliance
        }
        else if(teamPropPosition == TeamPropPosition.Right && startLocation == AutonomousStartLocation.Far) {
            //TODO: Implement a right for the far side on blue alliance
        }
        else if(teamPropPosition == TeamPropPosition.Left && startLocation == AutonomousStartLocation.Near) {
            //TODO: Implement a left for the near side on blue alliance
        }
        else {
            //TODO: Implement a left for the far side on the blue alliance
        }
        return path;
    }


}
