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

    private static TrajectorySequence RedAlliancePhaseOne(FalconMecanumDrive drive, TeamPropPosition teamPropPosition, AutonomousStartLocation startLocation){

        TrajectorySequence path = null;
        if(startLocation == AutonomousStartLocation.Near) {
            if (teamPropPosition == TeamPropPosition.Left) {

                //TODO: Implement path for left position

            } else if (teamPropPosition == TeamPropPosition.Center) {
                path = drive.trajectorySequenceBuilder(new Pose2d(12.00, -64.00, Math.toRadians(90.00)))
                        .splineTo(new Vector2d(12.00, -34.00), Math.toRadians(90.00))
                        .build();
            } else {

                //TODO: Implement path for right position

            }
        }
        else {

            //TODO: Implement pathing for red far

        }
        return path;
    }

    private static TrajectorySequence RedAlliancePhaseTwo(FalconMecanumDrive drive, TeamPropPosition teamPropPosition, AutonomousStartLocation startLocation){

        TrajectorySequence path = null;

        if(teamPropPosition == TeamPropPosition.Center && startLocation == AutonomousStartLocation.Near) {
                path = drive.trajectorySequenceBuilder(new Pose2d(12.00, -34.00, Math.toRadians(90.00)))
                    .lineTo(new Vector2d(26.00, -34.00))
                    .lineTo(new Vector2d(44.00, -34.00))
                    .build();
        }
        else if(teamPropPosition == TeamPropPosition.Center && startLocation == AutonomousStartLocation.Far) {
            //TODO: Implement a center for the far side on red alliance
        }
        else if(teamPropPosition == TeamPropPosition.Right && startLocation == AutonomousStartLocation.Near) {
            //TODO: Implement a right for the near side on red alliance
        }
        else if(teamPropPosition == TeamPropPosition.Right && startLocation == AutonomousStartLocation.Far) {
            //TODO: Implement a right for the far side on red alliance
        }
        else if(teamPropPosition == TeamPropPosition.Left && startLocation == AutonomousStartLocation.Near) {
            //TODO: Implement a left for the near side on red alliance
        }
        else {
            //TODO: Implement a left for the far side on the red alliance
        }
        return path;
    }

    private static TrajectorySequence BlueAlliancePhaseOne(FalconMecanumDrive drive, TeamPropPosition teamPropPosition, AutonomousStartLocation startLocation){

        TrajectorySequence path = null;
        if(startLocation == AutonomousStartLocation.Near) {
            if (teamPropPosition == TeamPropPosition.Left) {

                //TODO: Implement path for left position

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

    private static TrajectorySequence BlueAlliancePhaseTwo(FalconMecanumDrive drive, TeamPropPosition teamPropPosition, AutonomousStartLocation startLocation){

        TrajectorySequence path = null;

        if(teamPropPosition == TeamPropPosition.Center && startLocation == AutonomousStartLocation.Near) {
            //TODO: Implement a center for the near side on blue alliance
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
