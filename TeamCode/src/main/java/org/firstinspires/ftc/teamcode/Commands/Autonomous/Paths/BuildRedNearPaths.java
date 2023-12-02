package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
@Config
public class BuildRedNearPaths {
        public static double RedNearCenter_Phase1Strafe = 6 ;
        public static double RedNearCenter_Phase1Forward= 38;

        public static double RedNearCenter_Phase2Back= 27;
    public static double RedNearCenter_Phase2_Turn = -130;
        public static double RedNearCenter_Phase2Forward = 30;
        // values for BlueNearLeft
        public static double RedNearLeft_Phase1Strafe = 6;
        public static double RedNearLeft_Phase1Forward1 = 37;
        public static double RedNearLeft_Phase1Forward2 = 10;
        public static double RedNearLeft_Phase2Back1 = 6;
        public static double RedNearLeft_Phase2Back2 = 25;
    public static double RedNearLeft_Phase2Turn = -90;
    public static double RedNearLeft_Phase1Turn2 = 130;
    public static double RedNearLeft_Phase2Turn2 = -130;
        public static double RedNearLeft_Phase2Forward = 32;

        // values for the near right blue

        public static double RedNearRight_Phase1Forward = 24 ;
        public static double RedNearRight_Phase2Back = 18 ;
        public static double RedNearRight_Phase1turn = -90 ;

        public static double RedNearRight_Phase2Turn = -130;
        public static double RedNearRight_Phase2Forward= 23;


    public static TrajectorySequence Phase1 , Phase2 ;
    private static FalconMecanumDrive drive;
    public static void Build (FalconMecanumDrive dr , TeamPropPosition position){
        drive = dr;
        if ( position == TeamPropPosition.Center){
           RedNearCenter_Phase1();
            RedNearCenter_Phase2();
        }
        else if (position == TeamPropPosition.Left ){
            RedNearLeft_Phase1();
           RedNearLeft_Phase2();
        }
        else {
            RedNearRight_Phase1();
            RedNearRight_Phase2();
        }
    }

    public static void RedNearCenter_Phase1 (){
        Phase1 = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(RedNearCenter_Phase1Forward)
                .build();
    }
    public static void RedNearCenter_Phase2 (){
       Phase2 = drive.trajectorySequenceBuilder(new Pose2d())
                .back(RedNearCenter_Phase2Back)
                .turn(Math.toRadians(RedNearCenter_Phase2_Turn) - 1e-6)
                .forward(RedNearCenter_Phase2Forward)
                .build();
    }
    public static void RedNearRight_Phase1(){
        Phase1=drive.trajectorySequenceBuilder(new Pose2d())
                .forward(29)
                .turn(Math.toRadians(-90.0)-1e-6)
                .forward(5)
//                .forward(RedNearRight_Phase1Forward)
//                .turn(RedNearRight_Phase1turn - 1e-6)

//                 .forward(29)
//                                .turn(Math.toRadians(-90.0)-1e-6)
//                                .forward(5)
//                                .back(11)
//                                .turn(Math.toRadians(-90) - 1e-6)
//                                .forward(24)
//                                .turn(Math.toRadians(90)+1e-6)
//                                .forward(30)
                .build();
    }
    public static void RedNearRight_Phase2(){
        Phase2 = drive.trajectorySequenceBuilder(new Pose2d())
                .back(11)
                .turn(Math.toRadians(-90) - 1e-6)
                .forward(24)
                .turn(Math.toRadians(90)+1e-6)
                .forward(30)
//                .back(RedNearRight_Phase2Back)
//                .waitSeconds(1)
//                .turn(RedNearRight_Phase2Turn - 1e-6)
//                .waitSeconds(1)
//                .forward(RedNearRight_Phase2Forward)
                .build();
    }
    public static void RedNearLeft_Phase1 (){
       Phase1= drive.trajectorySequenceBuilder(new Pose2d())
               .forward(29.0)
               .turn(Math.toRadians(90) + 1e-6)
               .forward(10)
//                .strafeLeft(RedNearLeft_Phase1Strafe)
//                .forward(RedNearLeft_Phase1Forward1)
//                .turn(RedNearLeft_Phase1Turn2+ 1e-6)
//                .forward(RedNearLeft_Phase1Forward2)
               // .forward(29.0)
               //                                .turn(Math.toRadians(90) + 1e-6)
               //                                .forward(10)
               //                                .back(8)
               //                                .turn(Math.toRadians(-90) - 1e-6)
               //                                .back(23.0)
               //                                .turn(Math.toRadians(-90) - 1e-6)
               //                                .forward(35.0)
                .build();
    }
    public static void RedNearLeft_Phase2 (){
        Phase2= drive.trajectorySequenceBuilder(new Pose2d())
                .back(8)
                .turn(Math.toRadians(-90) - 1e-6)
                .back(23.0)
                .turn(Math.toRadians(-90) - 1e-6)
                .forward(35.0)
//                .back(RedNearLeft_Phase2Back1)
//                .turn(RedNearLeft_Phase2Turn - 1e-6)
//                .back(RedNearLeft_Phase2Back2)
//                .turn(RedNearLeft_Phase2Turn2 - 1e-6)
//                .forward(RedNearLeft_Phase2Forward)
                .build();
    }
}
