package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

public class AutonomousPaths {

    public static TrajectorySequence RedAllianceNearPhaseOne (FalconMecanumDrive drive, TeamPropPosition teamPropPosition){

        TrajectorySequence path = null;
        if(teamPropPosition == TeamPropPosition.Left) {
            //TODO: Implement path for left position
        }
        else if(teamPropPosition == TeamPropPosition.Center) {
            path = drive.trajectorySequenceBuilder(new Pose2d(12.00, -64.00, Math.toRadians(90.00)))
                    .splineTo(new Vector2d(12.00, -34.00), Math.toRadians(90.00))
                    .build();
        }
        else {
            //TODO: Implement path for right position
        }
        return path;
    }

    public static TrajectorySequence RedAllianceNearPhaseTwo (FalconMecanumDrive drive){

        TrajectorySequence path = drive.trajectorySequenceBuilder(new Pose2d(12.00, -34.00, Math.toRadians(90.00)))
                .lineTo(new Vector2d(26.00, -34.00))
                .lineTo(new Vector2d(44.00, -34.00))
                .build();
        return path;
    }


}
