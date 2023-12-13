package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

@Config
public class BuildBlueFarPaths {

    public static double TURN_RIGHT = Math.toRadians(90);
    public static double TURN_LEFT = Math.toRadians(-90);
    public static double BlueFarCenter_Phase1Forward = 50;
    public static double BlueFarCenter_Phase1Forward1 = 5;
    public static double BlueFarCenter_Phase2Back = 8;
    public static double BlueFarCenter_Phase2Forward2 = 90;
    public static double BlueFarCenter_Phase1turn = 180;
    public static double BlueFarCenter_Phase2turn = -130;


    // values for BlueNearLeft
    public static double BlueFarLeft_Phase1Forward = 29;
    public static double BlueFarLeft_Phase2Forward1 = 7;
    public static double BlueFarLeft_Phase2back = 5;
    public static double BlueFarLeft_Phase2StrafeRight = 22;
    public static double BluefarLeft_Phase2Forward2 = 90;
    public static double BlueFarLeft_Phase1turn = 130;

    // values for the near right blue
    public static double BlueFarRight_Phase1Forward = 38;

    public static double BlueFarRight_Phase2Back1 = 8.0;
    public static double BlueFarRight_Phase2StrafeLeft = 25.0;
    public static double BlueFarRight_Phase2Back2 = 93;
    public static double BlueFarRight_Phase1turn = -130;


    public static TrajectorySequence Phase1, Phase2;
    private static FalconMecanumDrive drive;

    public static void Build(FalconMecanumDrive dr, TeamPropPosition position) {
        drive = dr;
        if (position == TeamPropPosition.Center) {
            BlueFarCenter_Phase1();
            BlueFarCenter_Phase2();
        } else if (position == TeamPropPosition.Left) {
            BlueFarLeft_Phase1();
            BlueFarLeft_Phase2();
        } else {
            BlueFarRight_Phase1();
            BlueFarRight_Phase2();
        }
    }

    public static void BlueFarCenter_Phase1() {
        Phase1 = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(BlueFarCenter_Phase1Forward)
                .turn(TURN_RIGHT*2)
                .forward(BlueFarCenter_Phase1Forward1)
                .build();

    }

    public static void BlueFarCenter_Phase2() {
        Phase2 = drive.trajectorySequenceBuilder(new Pose2d())
                .back(BlueFarCenter_Phase2Back)
                .turn(TURN_RIGHT)
                .forward(BlueFarCenter_Phase2Forward2)
                .build();
    }

    public static void BlueFarLeft_Phase1() {
        Phase1 = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(29.0)
                .turn(TURN_LEFT)
                .forward(7.0)
                .build();
//       Phase1 = drive.trajectorySequenceBuilder(new Pose2d())
//                .forward(BlueFarCenter_Phase1Forward)
//                .turn(BlueFarLeft_Phase1turn + 1e-6)
//                .forward(BlueFarLeft_Phase2Forward1)
        //.forward(29.0)
        //                                .turn(Math.toRadians(90) + 1e-6)
        //                                .forward(7.0)
        //                                .back(9.0)
        //                                .turn(Math.toRadians(-90) - 1e-6)
        //                                .forward(22.0)
        //                                .turn(Math.toRadians(90)+ 1e-6)
        //                                .forward (90.0 )
        //                                .build());

    }

    public static void BlueFarLeft_Phase2() {
        Phase2 = drive.trajectorySequenceBuilder(new Pose2d())
//                .back(BlueFarLeft_Phase2back)
//                .strafeRight(BlueFarLeft_Phase2StrafeRight)
//                .forward(BluefarLeft_Phase2Forward2)
                .back(9.0)
                .turn(TURN_RIGHT)
                .forward(22.0)
                .turn(TURN_LEFT)
                .forward(90.0)
                .build();
    }

    public static void BlueFarRight_Phase1() {
        Phase1 = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(29.0)
                .turn(TURN_RIGHT)
                .forward(9.0)
//                .forward(BlueFarRight_Phase1Forward)
//                .turn(BlueFarRight_Phase1turn - 1e-6)
                .build();
        //.forward(29.0)
        //                                .turn(Math.toRadians(-90) - 1e-6)
        //                                .forward(9.0)
        //                                .back(12.0)
        //                                .turn(Math.toRadians(90) + 1e-6)
        //                                .forward(22.0)
        //                                .turn(Math.toRadians(90) + 1e-6)
        //                                .forward(90.0)
        //                                .build());

    }

    public static void BlueFarRight_Phase2() {
        Phase2 = drive.trajectorySequenceBuilder(new Pose2d())
                .back(12.0)
                .turn(TURN_LEFT)
                .forward(22.0)
                .turn(TURN_LEFT)
                .forward(90.0)
//                .back(BlueFarRight_Phase2Back1)
//                .strafeLeft(BlueFarRight_Phase2StrafeLeft)
//                .back(BlueFarRight_Phase2Back2)
                .build();
    }

}
