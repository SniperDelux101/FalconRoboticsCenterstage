package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3;

import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.Blue_Far_Start_Pose;
import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.*;
import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.Red_Far_Start_Pose;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.profile.VelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.ParkEnding;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TravelDirection;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class BuildFarPaths {
    public static TrajectorySequence Phase1, Phase2, Phase3, Phase_Cycle, Park;
    private static FalconMecanumDrive drive;

    public static void Build(FalconMecanumDrive dr, TeamPropPosition position, Alliance alliance, TravelDirection direction, ParkEnding parkEnding){
        drive = dr;

        if(alliance == Alliance.Blue) {
            if(position == TeamPropPosition.Center) {
                if (direction == TravelDirection.In) {
                    if(parkEnding == ParkEnding.In) {

                    }
                    if(parkEnding == ParkEnding.Out) {

                    }
                }
                if (direction == TravelDirection.Out) {
                    if(parkEnding == ParkEnding.In) {

                    }
                    if(parkEnding == ParkEnding.Out) {

                    }
                }
            }
            if(position == TeamPropPosition.Left) {
                if (direction == TravelDirection.In) {
                    if(parkEnding == ParkEnding.In) {

                    }
                    if(parkEnding == ParkEnding.Out) {

                    }
                }
                if (direction == TravelDirection.Out) {
                    if(parkEnding == ParkEnding.In) {

                    }
                    if(parkEnding == ParkEnding.Out) {

                    }
                }
            }
            if(position == TeamPropPosition.Right) {
                if(direction == TravelDirection.In) {
                    if(parkEnding == ParkEnding.In) {

                    }
                    if(parkEnding == ParkEnding.Out) {

                    }
                }
                if(direction == TravelDirection.Out) {
                    if(parkEnding == ParkEnding.In) {

                    }
                    if(parkEnding == ParkEnding.Out) {

                    }
                }
            }

        }
        if(alliance == Alliance.Red) {
            if (position == TeamPropPosition.Center) {
                if (direction == TravelDirection.In) {
                    if (parkEnding == ParkEnding.In) {

                    }
                    if (parkEnding == ParkEnding.Out) {

                    }
                }
                if (direction == TravelDirection.Out) {
                    if (parkEnding == ParkEnding.In) {

                    }
                    if (parkEnding == ParkEnding.Out) {

                    }
                }
            }
            if (position == TeamPropPosition.Left) {
                if (direction == TravelDirection.In) {
                    if (parkEnding == ParkEnding.In) {

                    }
                    if (parkEnding == ParkEnding.Out) {

                    }
                }
                if (direction == TravelDirection.Out) {
                    if (parkEnding == ParkEnding.In) {

                    }
                    if (parkEnding == ParkEnding.Out) {

                    }
                }
            }
            if (position == TeamPropPosition.Right) {
                if (direction == TravelDirection.In) {
                    if (parkEnding == ParkEnding.In) {

                    }
                    if (parkEnding == ParkEnding.Out) {

                    }
                }
                if (direction == TravelDirection.Out) {
                    if (parkEnding == ParkEnding.In) {

                    }
                    if (parkEnding == ParkEnding.Out) {

                    }
                }
            }
        }
    }
    private static class Blue{

        //region IN - IN

        public static void CenterPhase1_II() {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                    .lineTo(new Vector2d(-36, 30))
                    .build();
            Center_Phase2_II();
        }
        public static void Center_Phase2_II() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, 40))
                    .splineTo(new Vector2d(-50, 30), Math.toRadians(270))
                    .lineToConstantHeading(new Vector2d(-50, 12))
                    .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(40, 8))
                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))
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
                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_II(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineTo(new Vector2d(-36, 40))
                        .splineTo(new Vector2d(-30, 30), Math.toRadians(0))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-46, 20, Math.toRadians(90)))
                        .build();
            }
            Left_Right_Phase2_II(position);
        }
        private static void Left_Right_Phase2_II(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-40, 30))
                        .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, 8))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, 15))
                        .splineTo(new Vector2d(-36, 8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, 8))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))
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
                        .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

        //region IN - OUT
        public static void CenterPhase1_IO() {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                    .lineTo(new Vector2d(-36, 30))
                    .build();
            Center_Phase2_IO();
        }
        public static void Center_Phase2_IO() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, 40))
                    .splineTo(new Vector2d(-50, 30), Math.toRadians(270))
                    .lineToConstantHeading(new Vector2d(-50, 12))
                    .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(40, 8))
                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))
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
                    .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_IO(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineTo(new Vector2d(-36, 40))
                        .splineTo(new Vector2d(-30, 30), Math.toRadians(0))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-46, 20, Math.toRadians(90)))
                        .build();
            }
            Left_Right_Phase2_IO(position);
        }
        private static void Left_Right_Phase2_IO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-40, 30))
                        .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, 8))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, 15))
                        .splineTo(new Vector2d(-36, 8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, 8))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))
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
                        .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

        //region OUT - IN
        public static void CenterPhase1_OI() {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                    .lineToConstantHeading(new Vector2d(-36, 30))
                    .build();
            Center_Phase2_OI();
        }
        public static void Center_Phase2_OI() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, 40))
                    .splineTo(new Vector2d(-30, 58), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(25, 58))
                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                    .build();
            Center_Phase3_OI();
        }
        public static void Center_Phase3_OI(){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();
            Center_Park_OI();
        }
        public static void Center_Park_OI() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_OI(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-30, 30, Math.toRadians(0)))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineToConstantHeading(new Vector2d(-46, 40))
                        .build();
            }
            Left_Right_Phase2_OI(position);
        }
        private static void Left_Right_Phase2_OI(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-45, 30))
                        .splineTo(new Vector2d(-45, 58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, 58))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, 50))
                        .splineTo(new Vector2d(-40, 58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, 58))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                        .build();
            }
            Left_Right_Phase3_OI(position);
        }
        private static void Left_Right_Phase3_OI(TeamPropPosition position) {
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
            Left_Right_Park_OI(position);
        }
        private static void Left_Right_Park_OI(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

        //region OUT - OUT
        public static void CenterPhase1_OO() {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                    .lineToConstantHeading(new Vector2d(-36, 30))
                    .build();
            Center_Phase2_OO();
        }
        public static void Center_Phase2_OO() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, 40))
                    .splineTo(new Vector2d(-30, 58), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(25, 58))
                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                    .build();
            Center_Phase3_OO();
        }
        public static void Center_Phase3_OO(){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();
            Center_Park_OO();
        }
        public static void Center_Park_OO() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_OO(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-30, 30, Math.toRadians(0)))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineToConstantHeading(new Vector2d(-46, 40))
                        .build();
            }
            Left_Right_Phase2_OO(position);
        }
        private static void Left_Right_Phase2_OO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-45, 30))
                        .splineTo(new Vector2d(-45, 58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, 58))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, 50))
                        .splineTo(new Vector2d(-40, 58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, 58))
                        .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))
                        .build();
            }
            Left_Right_Phase3_OO(position);
        }
        private static void Left_Right_Phase3_OO(TeamPropPosition position) {
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
            Left_Right_Park_OO(position);
        }
        private static void Left_Right_Park_OO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

    }

    private static class Red{

        //region IN - IN

        public static void CenterPhase1_II() {
            Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                    .lineTo(new Vector2d(-36, -30))
                    .build();
            Center_Phase2_II();
        }
        public static void Center_Phase2_II() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, -40))
                    .splineTo(new Vector2d(-50, -30), Math.toRadians(90))
                    .lineToConstantHeading(new Vector2d(-50, -12))
                    .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(40, -8))
                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))
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
                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_II(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-46, -20, Math.toRadians(270)))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineTo(new Vector2d(-36, -40))
                        .splineTo(new Vector2d(-30, -30), Math.toRadians(0))
                        .build();
            }
            Left_Right_Phase2_II(position);
        }
        private static void Left_Right_Phase2_II(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, -15))
                        .splineTo(new Vector2d(-36, -8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, -8))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-40, -30))
                        .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, -8))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))
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
                        .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

        //region IN - OUT
        public static void CenterPhase1_IO() {
            Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                    .lineTo(new Vector2d(-36, -30))
                    .build();
            Center_Phase2_IO();
        }
        public static void Center_Phase2_IO() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, -40))
                    .splineTo(new Vector2d(-50, -30), Math.toRadians(90))
                    .lineToConstantHeading(new Vector2d(-50, -12))
                    .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(40, -8))
                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))
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
                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phases_IO(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-46, -20, Math.toRadians(270)))
                        .build();
            } else {
                //RIGHT
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineTo(new Vector2d(-36, -40))
                        .splineTo(new Vector2d(-30, -30), Math.toRadians(0))
                        .build();
            }
            Left_Right_Phase2_IO(position);
        }
        private static void Left_Right_Phase2_IO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, -15))
                        .splineTo(new Vector2d(-36, -8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, -8))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))
                        .build();
            } else {
                //Right
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-40, -30))
                        .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, -8))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))
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
                        .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))
                        .build();
            } else {
                //Right
                Park = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

        //region OUT - IN
        public static void CenterPhase1_OI() {
            Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                    .lineToConstantHeading(new Vector2d(-36, -30))
                    .build();
            Center_Phase2_OI();
        }
        public static void Center_Phase2_OI() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, -40))
                    .splineTo(new Vector2d(-30, -58), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(25, -58))
                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))
                    .build();
            Center_Phase3_OI();
        }
        public static void Center_Phase3_OI(){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();
            Center_Park_OI();
        }
        public static void Center_Park_OI() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_OI(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineToConstantHeading(new Vector2d(-46, -40))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-30, -30, Math.toRadians(0)))
                        .build();
            }
            Left_Right_Phase2_OI(position);
        }
        private static void Left_Right_Phase2_OI(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, -50))
                        .splineTo(new Vector2d(-40, -58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, -58))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-45, -30))
                        .splineTo(new Vector2d(-45, -58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, -58))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))
                        .build();
            }
            Left_Right_Phase3_OI(position);
        }
        private static void Left_Right_Phase3_OI(TeamPropPosition position) {
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
            Left_Right_Park_OI(position);
        }
        private static void Left_Right_Park_OI(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

        //region OUT - OUT
        public static void CenterPhase1_OO() {
            Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                    .lineToConstantHeading(new Vector2d(-36, -30))
                    .build();
            Center_Phase2_OO();
        }
        public static void Center_Phase2_OO() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, -40))
                    .splineTo(new Vector2d(-30, -58), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(25, -58))
                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))
                    .build();
            Center_Phase3_OO();
        }
        public static void Center_Phase3_OO(){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();
            Center_Park_OO();
        }
        public static void Center_Park_OO() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_OO(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineToConstantHeading(new Vector2d(-46, -40))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-30, -30, Math.toRadians(0)))
                        .build();
            }
            Left_Right_Phase2_OO(position);
        }
        private static void Left_Right_Phase2_OO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, -50))
                        .splineTo(new Vector2d(-40, -58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, -58))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-45, -30))
                        .splineTo(new Vector2d(-45, -58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, -58))
                        .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))
                        .build();
            }
            Left_Right_Phase3_OO(position);
        }
        private static void Left_Right_Phase3_OO(TeamPropPosition position) {
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
            Left_Right_Park_OO(position);
        }
        private static void Left_Right_Park_OO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))
                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))
                        .build();
            }
        }
        //endregion

    }
}
