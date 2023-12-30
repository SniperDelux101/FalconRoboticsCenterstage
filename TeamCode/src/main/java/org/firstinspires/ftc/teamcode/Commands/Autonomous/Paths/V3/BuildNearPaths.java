package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3;



import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.Blue_Near_Start_Pose;

import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.Red_Near_Start_Pose;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class BuildNearPaths {
    public static TrajectorySequence Phase1, Phase2, Phase3, Park;
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
                    .lineTo(new Vector2d(12, 28))
                    .build();
            Center_Phase2();
        }
        public static void Center_Phase2() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineTo(new Vector2d(12, 36))
                    .splineTo(new Vector2d(30, 32), Math.toRadians(0))
                    .lineTo(new Vector2d(54, 32))
                    .build();
            Center_Phase3();
        }
        public static void Center_Phase3(){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(45, 32))
                    .build();
            Center_Park();
        }
        public static void Center_Park() {
            Park = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(45, 50))
                    .splineToConstantHeading(new Vector2d(60, 60), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phases(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)
                        .lineToConstantHeading(new Vector2d(30,28))
                        .turn(Math.toRadians(-90))
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
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, 28))
                        .lineToConstantHeading(new Vector2d(54, 39))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .lineToConstantHeading(new Vector2d(54,28))
                        .build();
            }
            Left_Right_Phase3(position);
        }
        private static void Left_Right_Phase3(TeamPropPosition position) {
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, 40))
                        .build();
            } else {
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .lineToConstantHeading(new Vector2d(40, 28))
                        .build();
            }
            Left_Right_Park(position);
        }
        private static void Left_Right_Park(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(50,60), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, 50))
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
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineTo(new Vector2d(12, -36))
                    .splineTo(new Vector2d(30, -36), Math.toRadians(0))
                    .lineTo(new Vector2d(53, -34))
                    .build();
            Center_Phase3();
        }
        public static void Center_Phase3() {
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(40, -36))
                    .build();
            Center_Park();
        }
        public static void Center_Park() {
            Park = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(40, -50))
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
                        .lineToConstantHeading(new Vector2d(30,-28))
                        .turn(Math.toRadians(90))
                        .build();
            }
            Left_Right_Phase2(position);
        }
        private static void Left_Right_Phase2(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(53, -30))
                        .build();
            } else {
                //Right
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, -28))
                        .splineToConstantHeading(new Vector2d(51.5, -39), Math.toRadians(0))
                        .build();
            }
            Left_Right_Phase3(position);
        }
        private static void Left_Right_Phase3(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, -30))
                        .build();
            } else {
                //Right
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, -42))
                        .build();
            }
            Left_Right_Park(position);
        }
        private static void Left_Right_Park(TeamPropPosition position) {
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, -50))
                        .splineToConstantHeading(new Vector2d(60, -60), Math.toRadians(0))
                        .build();
            } else {
                //Right
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, -50))
                        .splineToConstantHeading(new Vector2d(60,-60), Math.toRadians(0))
                        .build();
            }
        }

    }
}
