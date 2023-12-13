package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

public class BuildRedFarPaths {

    public static double TURN_RIGHT = Math.toRadians(90);
    public static double TURN_LEFT = Math.toRadians(-90);
    public static double RedFarCenter_Phase1Forward= 50;
    public static double RedFarCenter_Phase2Forward1 = 5;
    public static double RedFarCenter_Phase2Back= 8;
    public static double RedFarCenter_Phase2Forward2 = 90;
    public static double RedFarCenter_Phase1turn = 180;
    public static double RedFarCenter_Phase2turn = 130;


    // values for RedNearLeft
    public static double RedFarLeft_Phase1Forward = 29;
    public static double RedFarLeft_Phase2Forward1 = 9;
    public static double RedFarLeft_Phase2back = 8 ;
    public static double RedFarLeft_Phase2StrafeRight = 25 ;
    public static double RedFarLeft_Phase2Back2 = 93 ;
    public static double RedFarLeft_Phase1Turn = 130;

    // values for the near right red
    public static double RedFarRight_Phase1Forward = 38;
    public static double RedFarRight_Phase2Forward1 = 9 ;
    public static double RedFarRight_Phase1Turn = -130;
    public static double RedFarRight_Phase2Back1= 8.0;
    public static double RedFarRight_Phase2StrafeLeft = 25.0;
    public static double RedFarRight_Phase2Forward2 = 93 ;
    public static TrajectorySequence Phase1 , Phase2 ;
    private static FalconMecanumDrive drive;
    public static void Build (FalconMecanumDrive dr , TeamPropPosition position){
        drive = dr;
        if ( position == TeamPropPosition.Center){
            RedFarCenter_Phase1();
            RedFarCenter_Phase2();
        }
        else if (position == TeamPropPosition.Left ){
            RedFarLeft_Phase1();
            RedFarLeft_Phase2();
        }
        else {
            RedFarRight_Phase1();
            RedFarRight_Phase2();
        }
    }
    public static void RedFarCenter_Phase1(){
        Phase1= drive.trajectorySequenceBuilder(new Pose2d())
                .forward(RedFarCenter_Phase1Forward)
                .turn( TURN_LEFT*2)
                .forward(RedFarCenter_Phase2Forward1)
                .build();

    }
    public static void RedFarCenter_Phase2(){
        Phase2= drive.trajectorySequenceBuilder(new Pose2d())
                .back(RedFarCenter_Phase2Back)
                .turn(  TURN_LEFT)
                .forward(RedFarCenter_Phase2Forward2)
                .build();

    }
    public static void RedFarLeft_Phase1(){
        Phase1= drive.trajectorySequenceBuilder(new Pose2d())
                .forward(29.0)
                .turn(TURN_LEFT)
                .forward(9.0)
//                .forward(RedFarLeft_Phase1Forward)
//                .turn( RedFarLeft_Phase1Turn+ 1e-6)
//                .forward(RedFarLeft_Phase2Forward1)
                // .forward(29.0)
                //                                .turn(Math.toRadians(90) + 1e-6)
                //                                .forward(9.0)
                //                                .back(12.0)
                //                                .turn(Math.toRadians(-90) -1e-6)
                //                                .forward(23)
                //                                .turn(Math.toRadians(-90)-1e-6)
                //                                .forward(93)
                .build();

    }
    public static void RedFarLeft_Phase2(){
        Phase2= drive.trajectorySequenceBuilder(new Pose2d())
                .back(12.0)
                .turn(TURN_RIGHT)
                .forward(23)
                .turn(TURN_RIGHT)
                .forward(93)
//                .back(RedFarLeft_Phase2back)
//                .strafeRight(RedFarLeft_Phase2StrafeRight)
//                .back(RedFarLeft_Phase2Back2)
                .build();
    }


    public static void RedFarRight_Phase1(){
        Phase1= drive.trajectorySequenceBuilder(new Pose2d())
                .forward(30.0)
                .turn(TURN_RIGHT)
                .forward(6)
//                .forward(RedFarRight_Phase1Forward)
//                .turn( RedFarRight_Phase1Turn  - 1e-6)
                //.forward(30.0)
                //                                .turn(Math.toRadians(90)+1e-6)
                //                                .forward(6)
                //                                .back(10)
                //                                .turn(Math.toRadians(-90)-1e-6)
                //                                .forward(22)
                //                                .turn(Math.toRadians(-90)-1e-6)
                //                                .forward(93)
                .build();
    }
    public static void RedFarRight_Phase2 (){
        Phase2= drive.trajectorySequenceBuilder(new Pose2d())
                .back(10)
                .turn(TURN_LEFT)
                .forward(22)
                .turn(TURN_RIGHT)
                .forward(90.0)
//                .back(RedFarRight_Phase2Back1)
//                .strafeLeft(RedFarRight_Phase2StrafeLeft)
//                .forward(RedFarRight_Phase2Forward2)
                .build();
    }
}
