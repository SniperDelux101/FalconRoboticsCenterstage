package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V2;

import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V2.CommonPathSettings.Back_Dist;
import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V2.CommonPathSettings.Blue_Near_Park_Vector;
import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V2.CommonPathSettings.Red_Near_Park_Vector;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

public class BuildNearPaths {
    public static TrajectorySequence Phase1, Phase2;
    private static FalconMecanumDrive drive;

    public static void Build(FalconMecanumDrive dr, TeamPropPosition position, Alliance alliance){
        drive = dr;
        if (position == TeamPropPosition.Center) {
            if(alliance == Alliance.Blue){
                Blue.CenterPhases();
            }
            else{
                Red.CenterPhases();
            }
        } else if (position == TeamPropPosition.Left || position == TeamPropPosition.Right) {
            if(alliance == Alliance.Blue){
                Blue.Left_Right_Phases(position);
            }
            else{
                Red.Left_Right_Phases(position);
            }
        }
    }
    private static class Blue{
        public static void CenterPhases() {
            Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                    .lineTo(new Vector2d(12, 31))
                    .build();
            Center_Phase2();
        }

        public static void Center_Phase2() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .back(Back_Dist)
                    .splineTo(Blue_Near_Park_Vector, Math.toRadians(0))
                    .build();
        }

        public static void Left_Right_Phases(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .splineTo(new Vector2d(17, 32), Math.toRadians(0))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .splineTo(new Vector2d(6, 32), Math.toRadians(-180))
                        .build();
            }
            Left_Right_Phase2(position);
        }

        private static void Left_Right_Phase2(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .back(Back_Dist)
                        .strafeTo(new Vector2d(Phase1.end().getX()-10, Phase1.end().getY()+10))
                        .splineToConstantHeading(Blue_Near_Park_Vector, Phase1.end().getHeading())
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .back(Back_Dist)
                        .lineToConstantHeading(Blue_Near_Park_Vector)
                        .build();
            }
        }
    }

    private static class Red{
        public static void CenterPhases() {
            Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                    .lineTo(new Vector2d(12, -31))
                    .build();
            Center_Phase2();
        }

        public static void Center_Phase2() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .back(Back_Dist)
                    .splineTo(Red_Near_Park_Vector, Math.toRadians(0))
                    .build();
        }

        public static void Left_Right_Phases(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .splineTo(new Vector2d(6, -32), Math.toRadians(-180))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .splineTo(new Vector2d(17, -32), Math.toRadians(0))
                        .build();
            }
            Left_Right_Phase2(position);
        }

        private static void Left_Right_Phase2(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .back(Back_Dist)
                        .lineToConstantHeading(Red_Near_Park_Vector)
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .back(Back_Dist)
                        .strafeTo(new Vector2d(Phase1.end().getX()-Back_Dist, Phase1.end().getY()-15))
                        .lineToConstantHeading(Red_Near_Park_Vector)
                        .build();
            }
        }
    }
}
