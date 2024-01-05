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
                    .lineTo(new Vector2d(BF_Center_Spike_C1, BF_Center_Spike_C2))
                    .turn(BF_Center_Spike_H1)
                    .build();
            Center_Phase2();
        }
        public static void Center_Phase2() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(BF_Center_Ph2_C1, BF_Center_Ph2_C2))
                    .splineTo(new Vector2d(BF_Center_Ph2_C3, BF_Center_Ph2_C4), BF_Center_Ph2_H1)
                    .lineToConstantHeading(new Vector2d(BF_Center_Ph2_C5, BF_Center_Ph2_C6))
                    .splineToConstantHeading(new Vector2d(BF_Center_Ph2_C7,BF_Center_Ph2_C8), BF_Center_Ph2_H2)
                    .build();
            Center_Phase3();
        }
        public static void Center_Phase3() {
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(BF_Center_Ph3_C1, BF_Center_Ph3_C2))
                    .build();
            Center_Park();
        }
        public static void Center_Park() {
            Park = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(BF_Center_Park_C1, BF_Center_Park_C2))
                    .splineToConstantHeading(new Vector2d(BF_Center_Park_C3, BF_Center_Park_C4), BF_Center_Park_H1)
                    .build();
        }
        public static void Left_Right_Phases(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .splineTo(new Vector2d(BF_Left_Spike_C1, BF_Left_Spike_C2), BF_Left_Spike_Turn1)
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineTo(new Vector2d(BF_Right_Spike_C1, BF_Right_Spike_C2))
                        .turn(BF_Right_Spike_H1)
                        .build();
            }
            Left_Right_Phase2(position);
        }
        private static void Left_Right_Phase2(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineTo(new Vector2d(BF_Left_Ph2_C1, BF_Left_Ph2_C2))
                        .splineTo(new Vector2d(BF_Left_Ph2_C3, BF_Left_Ph2_C4), BF_Left_Ph2_H1)
                        .lineToConstantHeading(new Vector2d(BF_Left_Ph2_C5, BF_Left_Ph2_C6))
                        .splineToConstantHeading(new Vector2d(BF_Left_Ph2_C7, BF_Left_Ph2_C8), BF_Left_Ph2_H2)
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(BF_Right_Ph2_C1, BF_Right_Ph2_C2))
                        .turn(BF_Right_Ph2_H1)
                        .lineToConstantHeading(new Vector2d(BF_Right_Ph2_C3, BF_Right_Ph2_C4))
                        .splineToConstantHeading(new Vector2d(BF_Right_Ph2_C5,BF_Right_Ph2_C6), BF_Right_Ph2_H2)
                        .build();
            }
            Left_Right_Phase3(position);
        }
        private static void Left_Right_Phase3(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(BF_Left_Ph3_C1, BF_Left_Ph3_C2))
                        .build();
            } else {
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(BF_Right_Ph3_C1, BF_Right_Ph3_C2))
                        .build();
            }
            Left_Right_Park(position);
        }
        private static void Left_Right_Park(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(BF_Left_Park_C1, BF_Left_Park_C2))
                        .splineToConstantHeading(new Vector2d(BF_Left_Park_C3, BF_Left_Park_C4), BF_Left_Park_H1)
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(BF_Right_Park_C1, BF_Right_Park_C2))
                        .splineToConstantHeading(new Vector2d(BF_Right_Park_C3, BF_Right_Park_C4), BF_Right_Park_H1)
                        .build();
            }
        }
    }

    private static class Red {
        public static void CenterPhases() {
            Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                    .lineTo(new Vector2d(RF_Center_Spike_C1, RF_Center_Spike_C2))
                    .turn(RF_Center_Spike_H1)
                    .build();
            Center_Phase2();
        }
        public static void Center_Phase2() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(RF_Center_Ph2_C1, RF_Center_Ph2_C2))
                    .splineTo(new Vector2d(RF_Center_Ph2_C3, RF_Center_Ph2_C4), RF_Center_Ph2_H1)
                    .lineToConstantHeading(new Vector2d(RF_Center_Ph2_C5, RF_Center_Ph2_C6))
                    .splineToConstantHeading(new Vector2d(RF_Center_Ph2_C7,RF_Center_Ph2_C8), RF_Center_Ph2_H2)
                    .build();
            Center_Phase3();
        }
        public static void Center_Phase3() {
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(RF_Center_Ph3_C1, RF_Center_Ph3_C2))
                    .build();
            Center_Park();
        }
        public static void Center_Park() {
            Park = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(RF_Center_Park_C1, RF_Center_Park_C2))
                    .splineToConstantHeading(new Vector2d(RF_Center_Park_C3, RF_Center_Park_C4), RF_Center_Park_H1)
                    .build();
        }
        public static void Left_Right_Phases(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineTo(new Vector2d(RF_Left_Spike_C1, RF_Left_Spike_C2))
                        .turn(RF_Left_Spike_Turn1)
                        .build();
            } else {
                //RIGHT
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .splineTo(new Vector2d(RF_Right_Spike_C1, RF_Right_Spike_C2), RF_Right_Spike_H1)
                        .build();
            }
            Left_Right_Phase2(position);
        }
        private static void Left_Right_Phase2(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineTo(new Vector2d(RF_Left_Ph2_C1, RF_Left_Ph2_C2))
                        .turn(RF_Left_Ph2_H1)
                        .lineToConstantHeading(new Vector2d(RF_Left_Ph2_C3, RF_Left_Ph2_C4))
                        .splineToConstantHeading(new Vector2d(RF_Left_Ph2_C5, RF_Left_Ph2_C6), RF_Left_Ph2_H2)
                        .lineToConstantHeading(new Vector2d(RF_Left_Ph2_C7, RF_Left_Ph2_C8))
                        .splineToConstantHeading(new Vector2d(RF_Left_Ph2_C9,RF_Left_Ph2_C10), RF_Left_Ph2_H3)
                        .build();
            } else {
                //Right
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineTo(new Vector2d(RF_Right_Ph2_C1, RF_Right_Ph2_C2))
                        .splineTo(new Vector2d(RF_Right_Ph2_C3, RF_Right_Ph2_C4), RF_Right_Ph2_H1)
                        .lineToConstantHeading(new Vector2d(RF_Right_Ph2_C5, RF_Right_Ph2_C6))
                        .splineToConstantHeading(new Vector2d(RF_Right_Ph2_C7, RF_Right_Ph2_C8), RF_Right_Ph2_H2)
                        .build();
                Left_Right_Phase3(position);
            }
        }
        private static void Left_Right_Phase3(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(RF_Left_Ph3_C1, RF_Left_Ph3_C2))
                        .build();
            } else {
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(RF_Right_Ph3_C1, RF_Right_Ph3_C2))
                        .build();
            }
            Left_Right_Park(position);
        }
        private static void Left_Right_Park(TeamPropPosition position) {
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(RF_Left_Park_C1, RF_Left_Park_C2))
                        .splineToConstantHeading(new Vector2d(RF_Left_Park_C3, -RF_Left_Park_C4), RF_Left_Park_H1)
                        .build();
            } else {
                //Right
                Phase2 = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(RF_Right_Park_C1, RF_Right_Park_C2))
                        .splineToConstantHeading(new Vector2d(RF_Right_Park_C3, RF_Right_Park_C4), RF_Right_Park_H1)
                        .build();
            }
        }

    }
}
