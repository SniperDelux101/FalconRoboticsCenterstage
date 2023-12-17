package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

@Config
public class BuildBlueNearPaths {
    ///region Values
//    public static double TURN_RIGHT = Math.toRadians(-90);
//    public static double TURN_LEFT = Math.toRadians(90);
//    public static double BlueNearCenter_Phase1Forward = 32;
//    public static double BlueNearCenter_Phase2Back = 28;
//    public static double Near_Park_Distance = 35;
//
//    //common values for setting pixel on left & right spike marks
//    public static double Spike_Offset = 8;
//    public static double Distance_To_SpikeMark = 5;
//    public static double Park_Offset = 10;
//    public static double BlueNearLeft_Phase1Strafe = 9;
//    public static double BlueNearLeft_Phase1Forward = 28;
//    public static double BlueNearLeft_Phase2back = 23;
//    public static double BlueNearLeft_Phase2Forward = 29;
//    public static double BlueNearLeft_Phase2turn = 130;
//    // values for the near right blue
//    public static double BlueNearRight_Phase1Forward = 29;
//    public static double BlueNearRight_Phase2Forward = 9;
//    public static double BlueNearRight_Phase2Back1 = 5.0;
//    public static double BlueNearRight_Phase2StrafeRight = 25;
//    public static double BlueNearRight_Phase2Back2 = 35;
//    public static double BlueNearRight_Phase1turn = -130;


    public static TrajectorySequence Phase1, Phase2;
    private static FalconMecanumDrive drive;
    ///endregion
    public static void Build(FalconMecanumDrive dr, TeamPropPosition position) {
        drive = dr;
        if (position == TeamPropPosition.Center) {
            BlueNearCenter_Phase1();
            BlueNearCenter_Phase2();
        } else if (position == TeamPropPosition.Left || position == TeamPropPosition.Right) {
            BlueNear_Left_Right_Phase1(position);
            BlueNear_Left_Right_Phase2(position);
        }
    }

    public static void BlueNearCenter_Phase1() {
        Phase1 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .forward(CommonPathSettings.Center_SpikeMark_Distance)
                .build();
    }

    public static void BlueNearCenter_Phase2() {
        Phase2 = drive.trajectorySequenceBuilder(Phase1.end())
                .back(CommonPathSettings.Center_SpikeMark_Distance - CommonPathSettings.Park_Offset)
                .turn(CommonPathSettings.TURN_LEFT)
                .forward(CommonPathSettings.Near_Park_Distance)
                .build();
    }

    public static void BlueNear_Left_Right_Phase1(TeamPropPosition position) {
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
    }

    public static void BlueNear_Left_Right_Phase2(TeamPropPosition position){
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
