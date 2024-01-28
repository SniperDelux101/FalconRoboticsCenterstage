package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3;


import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.*;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.ParkEnding;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Commands.OneCycle;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class BuildNearPaths {
    public static TrajectorySequence Phase1, Phase2, Phase3, Phase_Cycle, Park;
    private static FalconMecanumDrive drive;

    public static void Build(FalconMecanumDrive dr, TeamPropPosition position, Alliance alliance, ParkEnding parkEnding, OneCycle oneCycle){
        drive = dr;

        if(alliance == Alliance.Blue) {
            if(position == TeamPropPosition.Center) {
                if (parkEnding == ParkEnding.Out) {
                    Blue.CenterPhase1_IO();
                }
                if (parkEnding == ParkEnding.In) {
                    Blue.CenterPhase1_II();
                }
            }
            if(position == TeamPropPosition.Left) {
                if (parkEnding == ParkEnding.Out) {
                    Blue.Left_Right_Phase1_IO(position);
                }
                if (parkEnding == ParkEnding.In) {
                    Blue.Left_Right_Phase1_II(position);
                }
            }
            if(position == TeamPropPosition.Right) {
                if(parkEnding == ParkEnding.Out) {
                    Blue.Left_Right_Phase1_IO(position);
                }
                if(parkEnding == ParkEnding.In) {
                    Blue.Left_Right_Phase1_II(position);
                }
            }

        }
        if(alliance == Alliance.Red) {
            if(position == TeamPropPosition.Center) {
                if (parkEnding == ParkEnding.Out) {
                    Red.CenterPhase1_IO();
                }
                if (parkEnding == ParkEnding.In) {
                    Red.CenterPhase1_II();
                }
            }
            if(position == TeamPropPosition.Left) {
                if (parkEnding == ParkEnding.Out) {
                    Red.Left_Right_Phase1_IO(position);
                }
                if (parkEnding == ParkEnding.In) {
                    Red.Left_Right_Phase1_II(position);
                }
            }
            if(position == TeamPropPosition.Right) {
                if(parkEnding == ParkEnding.Out) {
                    Red.Left_Right_Phase1_IO(position);
                }
                if(parkEnding == ParkEnding.In) {
                    Red.Left_Right_Phase1_II(position);
                }
            }

        }
    }
    private static class Blue{

        //region IN - OUT
        public static void CenterPhase1_IO() {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)
                    .lineToConstantHeading(new Vector2d(12, 30))
                    .build();
            Center_Phase2_IO();
        }
        public static void Center_Phase2_IO() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(12, 35))
                    .splineTo(new Vector2d(20, 40), Math.toRadians(0))
                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                    .build();
            Center_Phase3_IO();
        }
        public static void Center_Phase3_IO(){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();
            Center_Park_IO();
        }
        public static void Center_Park_IO() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(50,60), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_IO(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)
                        .lineToLinearHeading(new Pose2d(30, 30, Math.toRadians(180)))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)
                        .lineToLinearHeading(new Pose2d(12, 40, Math.toRadians(270)))
                        .splineTo(new Vector2d(8, 30), Math.toRadians(180))
                        .build();
            }
            Left_Right_Phase2_IO(position);
        }
        private static void Left_Right_Phase2_IO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(35, 30))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(30, 30))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                        .build();
            }
            Left_Right_Phase3_IO(position);
        }
        private static void Left_Right_Phase3_IO(TeamPropPosition position) {
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .forward(Configuration.BACKDROP_DISTANCE)
                        .build();
            } else {
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .forward(Configuration.BACKDROP_DISTANCE)
                        .build();
            }
            Left_Right_Park_IO(position);
        }
        private static void Left_Right_Park_IO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

        //region IN - IN

        public static void CenterPhase1_II() {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)
                    .lineToConstantHeading(new Vector2d(12, 30))
                    .build();
            Center_Phase2_II();
        }
        public static void Center_Phase2_II() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(12, 35))
                    .splineTo(new Vector2d(20, 40), Math.toRadians(0))
                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                    .build();
            Center_Phase3_II();
        }
        public static void Center_Phase3_II(){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();
            Center_Park_II();
        }
        public static void Center_Park_II() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(50, 8), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_II(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)
                        .lineToLinearHeading(new Pose2d(30, 30, Math.toRadians(180)))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)
                        .lineToLinearHeading(new Pose2d(12, 40, Math.toRadians(270)))
                        .splineTo(new Vector2d(8, 30), Math.toRadians(180))
                        .build();
            }
            Left_Right_Phase2_II(position);
        }
        private static void Left_Right_Phase2_II(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(35, 30))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(30, 30))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                        .build();
            }
            Left_Right_Phase3_II(position);
        }
        private static void Left_Right_Phase3_II(TeamPropPosition position) {
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .forward(Configuration.BACKDROP_DISTANCE)
                        .build();
            } else {
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .forward(Configuration.BACKDROP_DISTANCE)
                        .build();
            }
            Left_Right_Park_II(position);
        }
        private static void Left_Right_Park_II(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(50, 8), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(50, 8), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

    }

    private static class Red{

        //region IN - OUT
        public static void CenterPhase1_IO() {
            Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                    .lineToConstantHeading(new Vector2d(12, -30))
                    .build();
            Center_Phase2_IO();
        }
        public static void Center_Phase2_IO() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(12, -35))
                    .splineTo(new Vector2d(20, -40), Math.toRadians(0))
                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(0))
                    .build();
            Center_Phase3_IO();
        }
        public static void Center_Phase3_IO() {
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();
            Center_Park_IO();
        }
        public static void Center_Park_IO() {
            Park = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(50,-60), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_IO(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                        .lineToLinearHeading(new Pose2d(12, -40, Math.toRadians(90)))
                        .splineTo(new Vector2d(8, -30), Math.toRadians(180))
                        .build();
            } else {
                //RIGHT
                Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                        .lineToLinearHeading(new Pose2d(30, -30, Math.toRadians(180)))
                        .build();
            }
            Left_Right_Phase2_IO(position);
        }
        private static void Left_Right_Phase2_IO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(30, -30))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))
                        .build();
            } else {
                //Right
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(35, -30))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))
                        .build();
            }
            Left_Right_Phase3_IO(position);
        }
        private static void Left_Right_Phase3_IO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .forward(Configuration.BACKDROP_DISTANCE)
                        .build();
            } else {
                //Right
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .forward(Configuration.BACKDROP_DISTANCE)
                        .build();
            }
            Left_Right_Park_IO(position);
        }
        private static void Left_Right_Park_IO(TeamPropPosition position) {
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(50, -60), Math.toRadians(0))
                        .build();
            } else {
                //Right
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(50, -60), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

        //region IN - IN
        public static void CenterPhase1_II() {
            Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                    .lineToConstantHeading(new Vector2d(12, -30))
                    .build();
            Center_Phase2_II();
        }
        public static void Center_Phase2_II() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(12, -35))
                    .splineTo(new Vector2d(20, -40), Math.toRadians(0))
                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(0))
                    .build();
            Center_Phase3_II();
        }
        public static void Center_Phase3_II(){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();
            Center_Park_II();
        }
        public static void Center_Park_II() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(50, -8), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_II(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                        .lineToLinearHeading(new Pose2d(12, -40, Math.toRadians(90)))
                        .splineTo(new Vector2d(8, -30), Math.toRadians(180))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                        .lineToLinearHeading(new Pose2d(30, -30, Math.toRadians(180)))
                        .build();
            }
            Left_Right_Phase2_II(position);
        }
        private static void Left_Right_Phase2_II(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(30, -30))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(35, -30))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))
                        .build();
            }
            Left_Right_Phase3_II(position);
        }
        private static void Left_Right_Phase3_II(TeamPropPosition position) {
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .forward(Configuration.BACKDROP_DISTANCE)
                        .build();
            } else {
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .forward(Configuration.BACKDROP_DISTANCE)
                        .build();
            }
            Left_Right_Park_II(position);
        }
        private static void Left_Right_Park_II(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(50, -8), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(50, -8), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

    }
}
