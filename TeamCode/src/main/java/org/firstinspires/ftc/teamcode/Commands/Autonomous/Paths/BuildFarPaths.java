package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

public class BuildFarPaths {
    public static TrajectorySequence Phase1, Phase2;
    private static FalconMecanumDrive drive;

    public static void Build(FalconMecanumDrive dr, TeamPropPosition position, Alliance alliance){
        drive = dr;
        if (position == TeamPropPosition.Center) {
            if(alliance == Alliance.Blue){
                BuildFarPaths.Blue.CenterPhases();
            }
            else{
                BuildFarPaths.Red.CenterPhases();
            }
        } else if (position == TeamPropPosition.Left || position == TeamPropPosition.Right) {
            if(alliance == Alliance.Blue){
                BuildFarPaths.Blue.Left_Right_Phases(position);
            }
            else{
                BuildFarPaths.Red.Left_Right_Phases(position);
            }
        }
    }
    private static class Blue{
        public static void CenterPhases() {
            Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                    .forward(CommonPathSettings.Center_SpikeMark_Distance + CommonPathSettings.Far_Center_Offset)
                    .turn(CommonPathSettings.TURN_RIGHT*2)
                    .build();
            Center_Phase2();
        }

        public static void Center_Phase2() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .back(CommonPathSettings.Tile_Width/2)
                    .turn(CommonPathSettings.TURN_RIGHT)
                    .forward(CommonPathSettings.Far_Park_Distance)
                    .build();
        }

        public static void Left_Right_Phases(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(CommonPathSettings.Center_SpikeMark_Distance - CommonPathSettings.Spike_Offset)
                        .turn(CommonPathSettings.TURN_LEFT)
                        .forward(CommonPathSettings.Distance_To_SpikeMark)
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(CommonPathSettings.Center_SpikeMark_Distance - CommonPathSettings.Spike_Offset)
                        .turn(CommonPathSettings.TURN_RIGHT)
                        .forward(CommonPathSettings.Distance_To_SpikeMark)
                        .build();
            }
            Left_Right_Phase2(position);
        }

        private static void Left_Right_Phase2(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end().plus(new Pose2d(0,0,CommonPathSettings.TURN_LEFT)))
                        .back(CommonPathSettings.Distance_To_SpikeMark)
                        .strafeRight(CommonPathSettings.Tile_Width)
                        .forward(CommonPathSettings.Far_Park_Distance)
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end().plus(new Pose2d(0,0,CommonPathSettings.TURN_RIGHT)))
                        .back(CommonPathSettings.Distance_To_SpikeMark)
                        .strafeLeft(CommonPathSettings.Tile_Width)
                        .forward(CommonPathSettings.Far_Park_Distance)
                        .build();
            }
        }
    }

    private static class Red{
        public static void CenterPhases() {
            Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                    .forward(CommonPathSettings.Center_SpikeMark_Distance)
                    .build();
            Center_Phase2();
        }

        public static void Center_Phase2() {
            Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                    .back(CommonPathSettings.Center_SpikeMark_Distance - CommonPathSettings.Park_Offset)
                    .turn(CommonPathSettings.TURN_LEFT)
                    .forward(CommonPathSettings.Near_Park_Distance)
                    .build();
        }

        public static void Left_Right_Phases(TeamPropPosition position) {
            if (position == TeamPropPosition.Left) {
                Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(CommonPathSettings.Center_SpikeMark_Distance - CommonPathSettings.Spike_Offset)
                        .turn(CommonPathSettings.TURN_LEFT)
                        .forward(CommonPathSettings.Distance_To_SpikeMark)
                        .build();
            } else {
                Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(CommonPathSettings.Center_SpikeMark_Distance - CommonPathSettings.Spike_Offset)
                        .turn(CommonPathSettings.TURN_RIGHT)
                        .forward(CommonPathSettings.Distance_To_SpikeMark)
                        .build();
            }
            Left_Right_Phase2(position);
        }

        private static void Left_Right_Phase2(TeamPropPosition position){
            if(position == TeamPropPosition.Left){
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end().plus(new Pose2d(0,0,CommonPathSettings.TURN_LEFT)))
                        .back(CommonPathSettings.Distance_To_SpikeMark)
                        .strafeLeft(CommonPathSettings.Center_SpikeMark_Distance - CommonPathSettings.Spike_Offset - CommonPathSettings.Park_Offset)
                        .forward(CommonPathSettings.Near_Park_Distance)
                        .build();
            } else {
                Phase2 = drive.trajectorySequenceBuilder(Phase1.end().plus(new Pose2d(0,0,CommonPathSettings.TURN_RIGHT)))
                        .back(CommonPathSettings.Distance_To_SpikeMark)
                        .strafeRight(CommonPathSettings.Center_SpikeMark_Distance - CommonPathSettings.Spike_Offset - CommonPathSettings.Park_Offset)
                        .forward(CommonPathSettings.Near_Park_Distance)
                        .build();
            }
        }
    }
}
