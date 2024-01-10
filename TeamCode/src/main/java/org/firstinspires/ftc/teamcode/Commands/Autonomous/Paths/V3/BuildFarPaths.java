package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3;

import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.Blue_Far_Start_Pose;
import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.*;
import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.Red_Far_Start_Pose;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.profile.VelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class BuildFarPaths {
    public static TrajectorySequence Phase1, Phase2, Phase3, Park;

    private static FalconMecanumDrive drive;

    public static void Build(FalconMecanumDrive dr, TeamPropPosition position, Alliance alliance){
        drive = dr;
        if (position == TeamPropPosition.Center) {
            if(alliance == Alliance.Blue){
                Blue.CenterPhases();
                drive.setPoseEstimate(Blue_Far_Start_Pose);
            }
            else{
                Red.CenterPhases();
                drive.setPoseEstimate(Red_Far_Start_Pose);
            }
        } else if (position == TeamPropPosition.Left || position == TeamPropPosition.Right) {
            if(alliance == Alliance.Blue){
                Blue.Left_Right_Phases(position);
                drive.setPoseEstimate(Blue_Far_Start_Pose);
            }
            else{
                Red.Left_Right_Phases(position);
                drive.setPoseEstimate(Red_Far_Start_Pose);
            }
        }
    }
    private static class Blue {
        public static void CenterPhases() {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                    .lineTo(new Vector2d(-36, 13))
                    .turn(BF_Center_Spike_H1)
                    .build();
            Center_Phase2();
        }
        public static void Center_Phase2() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, 8))
                    .splineTo(new Vector2d(-20, 8), BF_Center_Ph2_H1)
                    .lineToConstantHeading(new Vector2d(30, 8))
                    .splineToConstantHeading(new Vector2d(40,Y_Mod), BF_Center_Ph2_H2)
                    .build();
            Center_Phase3();
        }
//        public static void Center_Strafe() {
//            Phase_Strafe = drive.trajectorySequenceBuilder(Phase2.end())
//                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
//                    .strafeLeft(Strafe_Distance)
//                    .build();
//            Center_Phase3();
//        }
        public static void Center_Phase3() {
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(40, 31))
                    .build();
            Center_Park();
        }
        public static void Center_Park() {
            Park = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(40, 14))
                    .splineToConstantHeading(new Vector2d(60, 3), BF_Center_Park_H1)
                    .build();
        }
        public static void Left_Right_Phases(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .splineTo(new Vector2d(-33, 28), Math.toRadians(0))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineTo(new Vector2d(-42, 16))
                        .turn(Math.toRadians(180))
                        .build();
            }
            Left_Right_Phase2(position);
        }
        private static void Left_Right_Phase2(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineTo(new Vector2d(-40, 28))
                        .splineTo(new Vector2d(-40, 2), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(30, 2))
                        .splineToConstantHeading(new Vector2d(40, Y_Mod), Math.toRadians(0))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, 6))
                        .turn(Math.toRadians(90))
                        .lineToConstantHeading(new Vector2d(30, 6))
                        .splineToConstantHeading(new Vector2d(40,Y_Mod), Math.toRadians(0))
                        .build();
            }
            Left_Right_Phase3(position);
        }

//        public static void Left_Right_Strafe(TeamPropPosition position) {
//            Phase_Strafe = drive.trajectorySequenceBuilder(Phase2.end())
//                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
//                    .strafeLeft(Strafe_Distance)
//                    .build();
//            Left_Right_Phase3(position);
//        }
        private static void Left_Right_Phase3(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, 42))
                        .build();
            } else {
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, 28))
                        .build();
            }
            Left_Right_Park(position);
        }
        private static void Left_Right_Park(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, 14))
                        .splineToConstantHeading(new Vector2d(60, 7), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, 14))
                        .splineToConstantHeading(new Vector2d(60, 7), Math.toRadians(0))
                        .build();
            }
        }
    }

    private static class Red {
        public static void CenterPhases() {
            Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                    .lineTo(new Vector2d(-36, -17))
                    .turn(RF_Center_Spike_H1)
                    .build();
            Center_Phase2();
        }
        public static void Center_Phase2() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, -8))
                    .splineTo(new Vector2d(-20, -8), RF_Center_Ph2_H1)
                    .lineToConstantHeading(new Vector2d(30, -8))
                    .splineToConstantHeading(new Vector2d(40,-Y_Mod), RF_Center_Ph2_H2)
                    .build();
            Center_Phase3();
        }
//        public static void Center_Strafe() {
//            Phase_Strafe = drive.trajectorySequenceBuilder(Phase2.end())
//                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
//                    .strafeLeft(Strafe_Distance)
//                    .build();
//            Center_Phase3();
//        }
        public static void Center_Phase3() {
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(40, -35))
                    .build();
            Center_Park();
        }
        public static void Center_Park() {
            Park = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(40, -14))
                    .splineToConstantHeading(new Vector2d(50, -12), RF_Center_Park_H1)
                    .build();
        }
        public static void Left_Right_Phases(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineTo(new Vector2d(-47, -18))
                        .turn(Math.toRadians(180))
                        .build();
            } else {
                //RIGHT
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .splineTo(new Vector2d(-32, -30), Math.toRadians(0))
                        .build();
            }
            Left_Right_Phase2(position);
        }
        private static void Left_Right_Phase2(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineTo(new Vector2d(-47, -10))
                        .turn(Math.toRadians(-90))
                        .lineToConstantHeading(new Vector2d(-34, -10))
                        .splineToConstantHeading(new Vector2d(-20, -10), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(30, -10))
                        .splineToConstantHeading(new Vector2d(40,-Y_Mod), Math.toRadians(0))
                        .build();
            } else {
                //Right
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineTo(new Vector2d(-40, -30))
                        .splineTo(new Vector2d(-40, -12), RF_Right_Ph2_H1)
                        .lineToConstantHeading(new Vector2d(30, -12))
                        .splineToConstantHeading(new Vector2d(40,-Y_Mod), RF_Right_Ph2_H2)
                        .build();
                Left_Right_Phase3(position);
            }
        }
//        public static void Left_Right_Strafe(TeamPropPosition position) {
//            Phase_Strafe = drive.trajectorySequenceBuilder(Phase2.end())
//                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
//                    .strafeRight(Strafe_Distance)
//                    .build();
//            Left_Right_Phase3(position);
//        }
        private static void Left_Right_Phase3(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, -30))
                        .build();
            } else {
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, -40))
                        .build();
            }
            Left_Right_Park(position);
        }
        private static void Left_Right_Park(TeamPropPosition position) {
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, -14))
                        .splineToConstantHeading(new Vector2d(50, -12), Math.toRadians(0))
                        .build();
            } else {
                //Right
                Phase2 = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, -14))
                        .splineToConstantHeading(new Vector2d(50, -12), RF_Right_Park_H1)
                        .build();
            }
        }

    }
}
