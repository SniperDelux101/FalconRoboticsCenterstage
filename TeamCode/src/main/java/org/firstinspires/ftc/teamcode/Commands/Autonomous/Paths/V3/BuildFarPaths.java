package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3;

import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.Blue_Far_Start_Pose;
import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.*;
import static org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3.CommonPathSettings.Red_Far_Start_Pose;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.profile.VelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.ParkEnding;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TravelDirection;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class BuildFarPaths {
    public static TrajectorySequence Phase1, Phase2, Phase3, Park;
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

        //region IN - OUT
        public static void CenterPhase1_IO() {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)

                    .build();
            Center_Phase2_IO();
        }
        public static void Center_Phase2_IO() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                    .build();
            Center_Phase3_IO();
        }
        public static void Center_Phase3_IO(){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                    .build();
            Center_Park_IO();
        }
        public static void Center_Park_IO() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                    .build();
        }
        public static void Left_Right_Phase1_IO(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)

                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)

                        .build();
            }
            Left_Right_Phase2_IO(position);
        }
        private static void Left_Right_Phase2_IO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            }
            Left_Right_Phase3_IO(position);
        }
        private static void Left_Right_Phase3_IO(TeamPropPosition position) {
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            } else {
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            }
            Left_Right_Park_IO(position);
        }
        private static void Left_Right_Park_IO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            }
        }
        //endregion

        //region IN - IN

        public static void CenterPhase1_II() {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)

                    .build();
            Center_Phase2_II();
        }
        public static void Center_Phase2_II() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                    .build();
            Center_Phase3_II();
        }
        public static void Center_Phase3_II(){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                    .build();
            Center_Park_II();
        }
        public static void Center_Park_II() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                    .build();
        }
        public static void Left_Right_Phase1_II(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)

                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Near_Start_Pose)

                        .build();
            }
            Left_Right_Phase2_II(position);
        }
        private static void Left_Right_Phase2_II(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            }
            Left_Right_Phase3_II(position);
        }
        private static void Left_Right_Phase3_II(TeamPropPosition position) {
            if(position == TeamPropPosition.Left){
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            } else {
                Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            }
            Left_Right_Park_II(position);
        }
        private static void Left_Right_Park_II(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            } else {
                Park = drive.trajectorySequenceBuilder(Phase3.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))

                        .build();
            }
        }
        //endregion

    }

    private static class Red{

        //region IN - OUT
        public static void CenterPhase1_IO() {
            Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                    .lineTo(new Vector2d(12, -29))
                    .build();
            Center_Phase2_IO();
        }
        public static void Center_Phase2_IO() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineTo(new Vector2d(12, -36))
                    .splineTo(new Vector2d(30, -36), Math.toRadians(0))
                    .lineTo(new Vector2d(40, -Y_Mod))
                    .build();
            Center_Phase3_IO();
        }
        public static void Center_Phase3_IO() {
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(40, -36))
                    .build();
            Center_Park_IO();
        }
        public static void Center_Park_IO() {
            Park = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(40, -50))
                    .splineToConstantHeading(new Vector2d(60, -60), RN_Center_Park_H1)
                    .build();
        }
        public static void Left_Right_Phases_IO(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                        .splineTo(new Vector2d(9, -30), Math.toRadians(180))
                        .build();
            } else {
                //RIGHT
                Phase1 = drive.trajectorySequenceBuilder(Red_Near_Start_Pose)
                        .lineToConstantHeading(new Vector2d(30,-28))
                        .turn(RN_Right_Spike_H1)
                        .build();
            }
            Left_Right_Phase2_IO(position);
        }
        private static void Left_Right_Phase2_IO(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, -Y_Mod))
                        .build();
            } else {
                //Right
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(40, -26))
                        .splineToConstantHeading(new Vector2d(40, -Y_Mod), Math.toRadians(0))
                        .build();
            }
            Left_Right_Phase3_IO(position);
        }
        private static void Left_Right_Phase3_IO(TeamPropPosition position){
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
            Left_Right_Park_IO(position);
        }
        private static void Left_Right_Park_IO(TeamPropPosition position) {
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
                        .splineToConstantHeading(new Vector2d(60,-60), RN_Right_Park_H1)
                        .build();
            }
        }
        //endregion

        //region IN - IN



        //endregion

    }
}
