package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

public class BuildRedFarPaths {
    public static class Params {
        public static double RedFarCenter_Phase1Forward= 50;
        public static double RedFarCenter_Phase2Forward1 = 5;
        public static double RedFarCenter_Phase2Back= 8;
        public static double RedFarCenter_Phase2Forward2 = 90;

        // values for RedNearLeft
        public static double RedFarLeft_Phase1Forward = 29;
        public static double RedFarLeft_Phase2Forward1 = 9;
        public static double RedFarLeft_Phase2back = 8 ;
        public static double RedFarLeft_Phase2StrafeRight = 25 ;
        public static double RedFarLeft_Phase2Back2 = 93 ;

        // values for the near right red
        public static double RedFarRight_Phase1Forward = 38;
        public static double RedFarRight_Phase2Forward1 = 9 ;
        public static double RedFarRight_Phase2Back1= 8.0;
        public static double RedFarRight_Phase2StrafeLeft = 25.0;
        public static double RedFarRight_Phase2Forward2 = 93 ;

    }
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
                .forward(Params.RedFarCenter_Phase1Forward)
                .turn(Math.toRadians(180) + 1e-6)
                .forward(Params.RedFarCenter_Phase2Forward1)
                .build();

    }
    public static void RedFarCenter_Phase2(){
        Phase2= drive.trajectorySequenceBuilder(new Pose2d())
                .back(Params.RedFarCenter_Phase2Back)
                .turn(Math.toRadians(90) + 1e-6)
                .forward(Params.RedFarCenter_Phase2Forward2)
                .build();

    }
    public static void RedFarLeft_Phase1(){
        Phase1= drive.trajectorySequenceBuilder(new Pose2d())
                .forward(Params.RedFarLeft_Phase1Forward)
                .turn(Math.toRadians(90) + 1e-6)
                .forward(Params.RedFarLeft_Phase2Forward1)
                .build();

    }
    public static void RedFarLeft_Phase2(){
        Phase2= drive.trajectorySequenceBuilder(new Pose2d())
                .back(Params.RedFarLeft_Phase2back)
                .strafeRight(Params.RedFarLeft_Phase2StrafeRight)
                .back(Params.RedFarLeft_Phase2Back2)
                .build();
    }


    public static void RedFarRight_Phase1(){
        Phase1= drive.trajectorySequenceBuilder(new Pose2d())
                .forward(Params.RedFarRight_Phase1Forward)
                .turn(Math.toRadians(-90) - 1e-6)
                .build();
    }
    public static void RedFarRight_Phase2 (){
        Phase2= drive.trajectorySequenceBuilder(new Pose2d())
                .back(Params.RedFarRight_Phase2Back1)
                .strafeLeft(Params.RedFarRight_Phase2StrafeLeft)
                .forward(Params.RedFarRight_Phase2Forward2)
                .build();
    }
}
