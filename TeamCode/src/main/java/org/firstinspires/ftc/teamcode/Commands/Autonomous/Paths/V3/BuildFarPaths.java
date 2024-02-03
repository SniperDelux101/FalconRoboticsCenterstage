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
import org.firstinspires.ftc.robotcontroller.external.samples.ConceptExploringIMUOrientation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.ParkEnding;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TravelDirection;
import org.firstinspires.ftc.teamcode.Commands.OneCycle;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class BuildFarPaths {
    public static TrajectorySequence Phase1, Phase2, Phase3, Phase1_Cycle, PhaseF_Cycle, Phase2_Cycle, Phase3_Cycle, Park;
    private static FalconMecanumDrive drive;

    public static void Build(FalconMecanumDrive dr, TeamPropPosition position, Alliance alliance, TravelDirection direction, ParkEnding parkEnding, OneCycle oneCycle){
        drive = dr;

        if(alliance == Alliance.Blue) {

            //region Center
            if(position == TeamPropPosition.Center) {
                if (direction == TravelDirection.In) {
                    if(parkEnding == ParkEnding.In) {
                        Blue.CenterPhase1_II(oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                    if(parkEnding == ParkEnding.Out) {
                        Blue.CenterPhase1_IO(oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                }
                if (direction == TravelDirection.Out) {
                    if(parkEnding == ParkEnding.In) {
                        Blue.CenterPhase1_OI(oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                    if(parkEnding == ParkEnding.Out) {
                        Blue.CenterPhase1_OO(oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                }
            }
            //endregion
            //region Left
            if(position == TeamPropPosition.Left) {
                if (direction == TravelDirection.In) {
                    if(parkEnding == ParkEnding.In) {
                        Blue.Left_Right_Phase1_II(position, oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                    if(parkEnding == ParkEnding.Out) {
                        Blue.Left_Right_Phase1_IO(position, oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                }
                if (direction == TravelDirection.Out) {
                    if(parkEnding == ParkEnding.In) {
                        Blue.Left_Right_Phase1_OI(position, oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                    if(parkEnding == ParkEnding.Out) {
                        Blue.Left_Right_Phase1_OO(position, oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                }
            }
            //endregion
            //region Right
            if(position == TeamPropPosition.Right) {
                if(direction == TravelDirection.In) {
                    if(parkEnding == ParkEnding.In) {
                        Blue.Left_Right_Phase1_II(position, oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                    if(parkEnding == ParkEnding.Out) {
                        Blue.Left_Right_Phase1_IO(position, oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                }
                if(direction == TravelDirection.Out) {
                    if(parkEnding == ParkEnding.In) {
                        Blue.Left_Right_Phase1_OI(position, oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                    if(parkEnding == ParkEnding.Out) {
                        Blue.Left_Right_Phase1_OO(position, oneCycle);
                        drive.setPoseEstimate(Blue_Far_Start_Pose);
                    }
                }
            }
                //endregion

        }

        if(alliance == Alliance.Red) {

            //region Center
            if (position == TeamPropPosition.Center) {
                if (direction == TravelDirection.In) {
                    if (parkEnding == ParkEnding.In) {
                        Red.CenterPhase1_II(oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                    if (parkEnding == ParkEnding.Out) {
                        Red.CenterPhase1_IO(oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                }
                if (direction == TravelDirection.Out) {
                    if (parkEnding == ParkEnding.In) {
                        Red.CenterPhase1_OI(oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                    if (parkEnding == ParkEnding.Out) {
                        Red.CenterPhase1_OO(oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                }
            }
            //endregion
            //region Left
            if (position == TeamPropPosition.Left) {
                if (direction == TravelDirection.In) {
                    if (parkEnding == ParkEnding.In) {
                        Red.Left_Right_Phase1_II(position, oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                    if (parkEnding == ParkEnding.Out) {
                        Red.Left_Right_Phases_IO(position, oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                }
                if (direction == TravelDirection.Out) {
                    if (parkEnding == ParkEnding.In) {
                        Red.Left_Right_Phase1_OI(position, oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                    if (parkEnding == ParkEnding.Out) {
                        Red.Left_Right_Phase1_OO(position, oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                }
            }
            //endregion
            //region Right
            if (position == TeamPropPosition.Right) {
                if (direction == TravelDirection.In) {
                    if (parkEnding == ParkEnding.In) {
                        Red.Left_Right_Phase1_II(position, oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                    if (parkEnding == ParkEnding.Out) {
                        Red.Left_Right_Phases_IO(position, oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                }
                if (direction == TravelDirection.Out) {
                    if (parkEnding == ParkEnding.In) {
                        Red.Left_Right_Phase1_OI(position, oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                    if (parkEnding == ParkEnding.Out) {
                        Red.Left_Right_Phase1_OO(position, oneCycle);
                        drive.setPoseEstimate(Red_Far_Start_Pose);
                    }
                }
            }
            //endregion

        }
    }
    private static class Blue{

        //region IN - IN
        public static void CenterPhase1_II(OneCycle oneCycle) {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                    .lineTo(new Vector2d(-36, 30))
                    .build();
            Center_Phase2_II(oneCycle);
        }
        public static void Center_Phase2_II(OneCycle oneCycle) {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, 40))
                    .splineTo(new Vector2d(-50, 30), Math.toRadians(270))
                    .lineToConstantHeading(new Vector2d(-50, 12))
                    .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(40, 8))
                    .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(90))
                    .build();
            Center_Phase3_II(oneCycle);
        }
        public static void Center_Phase3_II(OneCycle oneCycle){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();

            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Center_Park_II();
            }
        }
        public static void Center_Park_II() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_II(TeamPropPosition position, OneCycle oneCycle) {
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
            Left_Right_Phase2_II(position, oneCycle);
        }
        private static void Left_Right_Phase2_II(TeamPropPosition position, OneCycle oneCycle){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-40, 30))
                        .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, 8))
                        .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(90))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, 15))
                        .splineTo(new Vector2d(-36, 8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, 8))
                        .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(90))
                        .build();
            }
            Left_Right_Phase3_II(position, oneCycle);
        }
        private static void Left_Right_Phase3_II(TeamPropPosition position, OneCycle oneCycle) {
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

            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Left_Right_Park_II(position);
            }
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
        public static void CenterPhase1_IO(OneCycle oneCycle) {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                    .lineTo(new Vector2d(-36, 30))
                    .build();
            Center_Phase2_IO(oneCycle);
        }
        public static void Center_Phase2_IO(OneCycle oneCycle) {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, 40))
                    .splineTo(new Vector2d(-50, 30), Math.toRadians(270))
                    .lineToConstantHeading(new Vector2d(-50, 12))
                    .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(40, 8))
                    .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(90))
                    .build();
            Center_Phase3_IO(oneCycle);
        }
        public static void Center_Phase3_IO(OneCycle oneCycle){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();

            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Center_Park_IO();
            }
        }
        public static void Center_Park_IO() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_IO(TeamPropPosition position, OneCycle oneCycle) {
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
            Left_Right_Phase2_IO(position, oneCycle);
        }
        private static void Left_Right_Phase2_IO(TeamPropPosition position, OneCycle oneCycle){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-40, 30))
                        .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, 8))
                        .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(90))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, 15))
                        .splineTo(new Vector2d(-36, 8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, 8))
                        .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(90))
                        .build();
            }
            Left_Right_Phase3_IO(position, oneCycle);
        }
        private static void Left_Right_Phase3_IO(TeamPropPosition position, OneCycle oneCycle) {
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

            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Left_Right_Park_IO(position);
            }
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
        public static void CenterPhase1_OI(OneCycle oneCycle) {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                    .lineToConstantHeading(new Vector2d(-36, 30))
                    .build();
            Center_Phase2_OI(oneCycle);
        }
        public static void Center_Phase2_OI(OneCycle oneCycle) {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, 40))
                    .splineTo(new Vector2d(-30, 58), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(25, 58))
                    .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(270))
                    .build();
            Center_Phase3_OI(oneCycle);
        }
        public static void Center_Phase3_OI(OneCycle oneCycle){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();

            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Center_Park_OI();
            }
        }
        public static void Center_Park_OI() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_OI(TeamPropPosition position, OneCycle oneCycle) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-30, 30, Math.toRadians(0)))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineToConstantHeading(new Vector2d(-46, 40))
                        .build();
            }
            Left_Right_Phase2_OI(position, oneCycle);
        }
        private static void Left_Right_Phase2_OI(TeamPropPosition position, OneCycle oneCycle){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-45, 30))
                        .splineTo(new Vector2d(-45, 58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, 58))
                        .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(270))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, 50))
                        .splineTo(new Vector2d(-40, 58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, 58))
                        .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(270))
                        .build();
            }
            Left_Right_Phase3_OI(position, oneCycle);
        }
        private static void Left_Right_Phase3_OI(TeamPropPosition position, OneCycle oneCycle) {
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
            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Left_Right_Park_OI(position);
            }
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
        public static void CenterPhase1_OO(OneCycle oneCycle) {
            Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                    .lineToConstantHeading(new Vector2d(-36, 30))
                    .build();
            Center_Phase2_OO(oneCycle);
        }
        public static void Center_Phase2_OO(OneCycle oneCycle) {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, 40))
                    .splineTo(new Vector2d(-30, 58), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(25, 58))
                    .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(270))
                    .build();
            Center_Phase3_OO(oneCycle);
        }
        public static void Center_Phase3_OO(OneCycle oneCycle){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();

            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Center_Park_OO();
            }
        }
        public static void Center_Park_OO() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_OO(TeamPropPosition position, OneCycle oneCycle) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-30, 30, Math.toRadians(0)))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Blue_Far_Start_Pose)
                        .lineToConstantHeading(new Vector2d(-46, 40))
                        .build();
            }
            Left_Right_Phase2_OO(position, oneCycle);
        }
        private static void Left_Right_Phase2_OO(TeamPropPosition position, OneCycle oneCycle){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-45, 30))
                        .splineTo(new Vector2d(-45, 58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, 58))
                        .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(270))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, 50))
                        .splineTo(new Vector2d(-40, 58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, 58))
                        .splineToConstantHeading(new Vector2d(46, Configuration.BACKDROP_Y), Math.toRadians(270))
                        .build();
            }
            Left_Right_Phase3_OO(position, oneCycle);
        }
        private static void Left_Right_Phase3_OO(TeamPropPosition position, OneCycle oneCycle) {
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
            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Left_Right_Park_OO(position);
            }
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

        //region Cycle One
        public static void Phase1_OneCycle() {
            Phase1_Cycle = drive.trajectorySequenceBuilder(Phase3.end())

                    //  Cycle Phase 1
                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                    .lineToConstantHeading(new Vector2d(-40, 58))
                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))
                    .build();
            PhaseF_OneCycle();
        }
        public static void PhaseF_OneCycle() {
            PhaseF_Cycle = drive.trajectorySequenceBuilder(Phase1.end())
                    //  Cycle Phase Forward
                    .lineToConstantHeading(new Vector2d(-60, 35))
                    .build();
            Phase2_OneCycle();
        }
        public static void Phase2_OneCycle() {
            Phase2_Cycle = drive.trajectorySequenceBuilder(PhaseF_Cycle.end())

                    //  Cycle Phase 2
                    .lineToConstantHeading(new Vector2d(-57, 35))
                    .lineToConstantHeading(new Vector2d(-57, 12))
                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(40, 8))
                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))
                    .build();
            Phase3_OneCycle();
        }
        public static void Phase3_OneCycle() {
            Phase3_Cycle = drive.trajectorySequenceBuilder(Phase2_Cycle.end())

                    //  Cycle Phase 3
                    .forward(Configuration.BACKDROP_DISTANCE)

                    .build();

        }
        //endregion

    }

    private static class Red{

        //region IN - IN
        public static void CenterPhase1_II(OneCycle oneCycle) {
            Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                    .lineTo(new Vector2d(-36, -30))
                    .build();
            Center_Phase2_II(oneCycle);
        }
        public static void Center_Phase2_II(OneCycle oneCycle) {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, -40))
                    .splineTo(new Vector2d(-50, -30), Math.toRadians(90))
                    .lineToConstantHeading(new Vector2d(-50, -12))
                    .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(40, -8))
                    .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(270))
                    .build();
            Center_Phase3_II(oneCycle);
        }
        public static void Center_Phase3_II(OneCycle oneCycle){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();

            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Center_Park_II();
            }
        }
        public static void Center_Park_II() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_II(TeamPropPosition position, OneCycle oneCycle) {
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
            Left_Right_Phase2_II(position, oneCycle);
        }
        private static void Left_Right_Phase2_II(TeamPropPosition position, OneCycle oneCycle){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, -15))
                        .splineTo(new Vector2d(-36, -8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, -8))
                        .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(270))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-40, -30))
                        .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, -8))
                        .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(270))
                        .build();
            }
            Left_Right_Phase3_II(position, oneCycle);
        }
        private static void Left_Right_Phase3_II(TeamPropPosition position, OneCycle oneCycle) {
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
            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Left_Right_Park_II(position);
            }
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
        public static void CenterPhase1_IO(OneCycle oneCycle) {
            Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                    .lineTo(new Vector2d(-36, -30))
                    .build();
            Center_Phase2_IO(oneCycle);
        }
        public static void Center_Phase2_IO(OneCycle oneCycle) {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, -40))
                    .splineTo(new Vector2d(-50, -30), Math.toRadians(90))
                    .lineToConstantHeading(new Vector2d(-50, -12))
                    .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(40, -8))
                    .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(270))
                    .build();
            Center_Phase3_IO(oneCycle);
        }
        public static void Center_Phase3_IO(OneCycle oneCycle) {
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();

            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Center_Park_IO();
            }
        }
        public static void Center_Park_IO() {
            Park = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phases_IO(TeamPropPosition position, OneCycle oneCycle) {
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
            Left_Right_Phase2_IO(position, oneCycle);
        }
        private static void Left_Right_Phase2_IO(TeamPropPosition position, OneCycle oneCycle){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, -15))
                        .splineTo(new Vector2d(-36, -8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, -8))
                        .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(270))
                        .build();
            } else {
                //Right
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-40, -30))
                        .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(40, -8))
                        .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(270))
                        .build();
            }
            Left_Right_Phase3_IO(position, oneCycle);
        }
        private static void Left_Right_Phase3_IO(TeamPropPosition position, OneCycle oneCycle){
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
            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Left_Right_Park_IO(position);
            }
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
        public static void CenterPhase1_OI(OneCycle oneCycle) {
            Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                    .lineToConstantHeading(new Vector2d(-36, -30))
                    .build();
            Center_Phase2_OI(oneCycle);
        }
        public static void Center_Phase2_OI(OneCycle oneCycle) {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, -40))
                    .splineTo(new Vector2d(-30, -58), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(25, -58))
                    .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(90))
                    .build();
            Center_Phase3_OI(oneCycle);
        }
        public static void Center_Phase3_OI(OneCycle oneCycle){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();

            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Center_Park_OI();
            }
        }
        public static void Center_Park_OI() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_OI(TeamPropPosition position, OneCycle oneCycle) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineToConstantHeading(new Vector2d(-46, -40))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-30, -30, Math.toRadians(0)))
                        .build();
            }
            Left_Right_Phase2_OI(position, oneCycle);
        }
        private static void Left_Right_Phase2_OI(TeamPropPosition position, OneCycle oneCycle){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, -50))
                        .splineTo(new Vector2d(-40, -58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, -58))
                        .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(90))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-45, -30))
                        .splineTo(new Vector2d(-45, -58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, -58))
                        .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(90))
                        .build();
            }
            Left_Right_Phase3_OI(position, oneCycle);
        }
        private static void Left_Right_Phase3_OI(TeamPropPosition position, OneCycle oneCycle) {
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
            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Left_Right_Park_II(position);
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
        public static void CenterPhase1_OO(OneCycle oneCycle) {
            Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                    .lineToConstantHeading(new Vector2d(-36, -30))
                    .build();
            Center_Phase2_OO(oneCycle);
        }
        public static void Center_Phase2_OO(OneCycle oneCycle) {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .lineToConstantHeading(new Vector2d(-36, -40))
                    .splineTo(new Vector2d(-30, -58), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(25, -58))
                    .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(90))
                    .build();
            Center_Phase3_OO(oneCycle);
        }
        public static void Center_Phase3_OO(OneCycle oneCycle){
            Phase3 = drive.trajectorySequenceBuilder(Phase2.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .forward(Configuration.BACKDROP_DISTANCE)
                    .build();

            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Center_Park_OO();
            }
        }
        public static void Center_Park_OO() {
            Park = drive.trajectorySequenceBuilder(Phase3.end())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))
                    .build();
        }
        public static void Left_Right_Phase1_OO(TeamPropPosition position, OneCycle oneCycle) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineToConstantHeading(new Vector2d(-46, -40))
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(Red_Far_Start_Pose)
                        .lineToLinearHeading(new Pose2d(-30, -30, Math.toRadians(0)))
                        .build();
            }
            Left_Right_Phase2_OO(position, oneCycle);
        }
        private static void Left_Right_Phase2_OO(TeamPropPosition position, OneCycle oneCycle){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-46, -50))
                        .splineTo(new Vector2d(-40, -58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, -58))
                        .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(90))
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                        .setVelConstraint(new MecanumVelocityConstraint(Configuration.AUTO_VEL, Configuration.TRACKWIDTH))
                        .lineToConstantHeading(new Vector2d(-45, -30))
                        .splineTo(new Vector2d(-45, -58), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(25, -58))
                        .splineToConstantHeading(new Vector2d(46, -Configuration.BACKDROP_Y), Math.toRadians(90))
                        .build();
            }
            Left_Right_Phase3_OO(position, oneCycle);
        }
        private static void Left_Right_Phase3_OO(TeamPropPosition position, OneCycle oneCycle) {
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
            if(oneCycle == OneCycle.True) {
                Phase1_OneCycle();
            }
            if(oneCycle == OneCycle.False) {
                Left_Right_Park_OO(position);
            }
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

        // TODO Change Coordinates
        //region Cycle One
        public static void Phase1_OneCycle() {
            Phase1_Cycle = drive.trajectorySequenceBuilder(Phase3.end())

                    //  Cycle Phase 1
                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                    .lineToConstantHeading(new Vector2d(-40, -58))
                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))
                    .build();
            PhaseF_OneCycle();
        }
        public static void PhaseF_OneCycle() {
            PhaseF_Cycle = drive.trajectorySequenceBuilder(Phase1.end())
                    //  Cycle Phase Forward
                    .lineToConstantHeading(new Vector2d(-60, -35))
                    .build();
            Phase2_OneCycle();
        }
        public static void Phase2_OneCycle() {
            Phase2_Cycle = drive.trajectorySequenceBuilder(PhaseF_Cycle.end())

                    //  Cycle Phase 2
                    .lineToConstantHeading(new Vector2d(-57, -35))
                    .lineToConstantHeading(new Vector2d(-57, -12))
                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(40, -8))
                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))
                    .build();
            Phase3_OneCycle();
        }
        public static void Phase3_OneCycle() {
            Phase3_Cycle = drive.trajectorySequenceBuilder(Phase2_Cycle.end())

                    //  Cycle Phase 3
                    .forward(Configuration.BACKDROP_DISTANCE)

                    .build();

        }
        //endregion

    }
}
