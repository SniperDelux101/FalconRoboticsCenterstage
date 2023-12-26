package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3;



import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.Blue_Near_Start_Pose;

import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.Red_Near_Start_Pose;

import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

public class BuildNearPaths {
    public static TrajectorySequence Phase1, Phase2, Park;
    private static FalconMecanumDrive drive;

    public static void Build(FalconMecanumDrive dr, TeamPropPosition position, Alliance alliance){
        drive = dr;
        if (position == TeamPropPosition.Center) {
            if(alliance == Alliance.Blue){
                Blue.CenterPhases();
                drive.setPoseEstimate(Blue_Near_Start_Pose);
            }
            else{
                Red.CenterPhases();
                drive.setPoseEstimate(Red_Near_Start_Pose);
            }
        } else if (position == TeamPropPosition.Left || position == TeamPropPosition.Right) {
            if(alliance == Alliance.Blue){
                Blue.Left_Right_Phases(position);
                drive.setPoseEstimate(Blue_Near_Start_Pose);
            }
            else{
                Red.Left_Right_Phases(position);
                drive.setPoseEstimate(Red_Near_Start_Pose);
            }
        }
    }
    private static class Blue{
        public static void CenterPhases() {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)
                    .lineTo(new Vector2d(12, 30))
                    .build();
            Center_Phase2();
        }

        public static void Center_Phase2() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .lineTo(new Vector2d(12, 36))
                    .splineTo(new Vector2d(30, 36), Math.toRadians(0))
                    .lineTo(new Vector2d(45, 36))
                    .build();
            Center_Park();
        }

        public static void Center_Park() {
            Park = drive.trajectorySequenceBuilder(Phase2.end())
                    .lineToConstantHeading(new Vector2d(45, 50))
                    .splineToConstantHeading(new Vector2d(60, 60), Math.toRadians(0))
                    .build();
        }

        public static void Left_Right_Phases(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)
                        .splineTo(new Vector2d(36,38),Math.toRadians(270))
                        .splineTo(new Vector2d(32,28), Math.toRadians(180))
                        .lineTo(new Vector2d(30,28))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)
                        .splineTo(new Vector2d(9, 30), Math.toRadians(180))
                        .build();
            }
            Left_Right_Phase2(position);
        }

        private static void Left_Right_Phase2(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .lineToConstantHeading(new Vector2d(45, 42))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .lineToConstantHeading(new Vector2d(45,30))
                        .build();
            }
            Left_Right_Park(position);
        }

        private static void Left_Right_Park(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .splineToConstantHeading(new Vector2d(50,60), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .lineToConstantHeading(new Vector2d(45, 50))
                        .splineToConstantHeading(new Vector2d(60, 60), Math.toRadians(0))
                        .build();
            }
        }

    }

    private static class Red{
        public static void CenterPhases() {
            Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                    .lineTo(new Vector2d(12, -30))
                    .build();
            Center_Phase2();
        }

        public static void Center_Phase2() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .lineTo(new Vector2d(12, -36))
                    .splineTo(new Vector2d(30, -36), Math.toRadians(0))
                    .lineTo(new Vector2d(45, -36))
                    .build();
            Center_Park();
        }
        public static void Center_Park() {
            Park = drive.trajectorySequenceBuilder(Phase2.end())
                    .lineToConstantHeading(new Vector2d(45, -50))
                    .splineToConstantHeading(new Vector2d(60, -60), Math.toRadians(0))
                    .build();
        }

        public static void Left_Right_Phases(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                        .splineTo(new Vector2d(9, -30), Math.toRadians(180))
                        .build();
            } else {
                //RIGHT
                Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                        .splineTo(new Vector2d(36,-38),Math.toRadians(90))
                        .splineTo(new Vector2d(32,-28), Math.toRadians(180))
                        .lineTo(new Vector2d(30,-28))
                        .build();
            }
            Left_Right_Phase2(position);
        }

        private static void Left_Right_Phase2(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .lineToConstantHeading(new Vector2d(45, -30))
                        .build();
            } else {
                //Right
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .lineToConstantHeading(new Vector2d(45, -42))
                        .build();
            }
            Left_Right_Park(position);
        }

        private static void Left_Right_Park(TeamPropPosition position) {
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .lineToConstantHeading(new Vector2d(45, -50))
                        .splineToConstantHeading(new Vector2d(60, -60), Math.toRadians(0))
                        .build();
            } else {
                //Right
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .splineToConstantHeading(new Vector2d(50,-60), Math.toRadians(0))
                        .build();
            }
        }

    }
}
