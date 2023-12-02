package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
@Config
public class BuildRedNearPaths {
    public static class Params {
        public static double RedNearCenter_Phase1Strafe = 6 ;
        public static double RedNearCenter_Phase1Forward= 32;
        public static double RedNearCenter_Phase2Back= 27;
        public static double RedNearCenter_Phase2Forward = 32;
        // values for BlueNearLeft
        public static double RedNearLeft_Phase1Strafe = 6;
        public static double RedNearLeft_Phase1Forward1 = 32;
        public static double RedNearLeft_Phase1Forward2 = 4 ;
        public static double RedNearLeft_Phase2Back1 = 6;
        public static double RedNearLeft_Phase2Back2 = 25;
        public static double RedNearLeft_Phase2Forward = 32;
        // values for the near right blue
        public static double RedNearRight_Phase1Strafe = 5;
        public static double RedNearRight_Phase1Forward = 24 ;
        public static double RedNearRight_Phase2Back = 18 ;
        public static double RedNearRight_Phase2Forward= 23;

    }
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
                .strafeLeft(Params.RedNearCenter_Phase1Strafe)
                .forward(Params.RedNearCenter_Phase1Forward)
                .build();
    }
    public static void RedNearCenter_Phase2 (){
       Phase2 = drive.trajectorySequenceBuilder(new Pose2d())
                .back(Params.RedNearCenter_Phase2Back)
                .turn(Math.toRadians(-90) - 1e-6)
                .forward(Params.RedNearCenter_Phase2Forward)
                .build();
    }
    public static void RedNearRight_Phase1(){
        Phase1=drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(Params.RedNearRight_Phase1Strafe)
                .forward(Params.RedNearRight_Phase1Forward)
                .build();
    }
    public static void RedNearRight_Phase2(){
        Phase2 = drive.trajectorySequenceBuilder(new Pose2d())
                .back(Params.RedNearRight_Phase2Back)
                .waitSeconds(1)
                .turn(Math.toRadians(-90) - 1e-6)
                .waitSeconds(1)
                .forward(Params.RedNearRight_Phase2Forward)
                .build();
    }
    public static void RedNearLeft_Phase1 (){
       Phase1= drive.trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(Params.RedNearLeft_Phase1Strafe)
                .forward(Params.RedNearLeft_Phase1Forward1)
                .turn(Math.toRadians(90) + 1e-6)
                .forward(Params.RedNearLeft_Phase1Forward2)
                .build();
    }
    public static void RedNearLeft_Phase2 (){
        Phase2= drive.trajectorySequenceBuilder(new Pose2d())
                .back(Params.RedNearLeft_Phase2Back1)
                .turn(Math.toRadians(-90) - 1e-6)
                .back(Params.RedNearLeft_Phase2Back2)
                .turn(Math.toRadians(-90) - 1e-6)
                .forward(Params.RedNearLeft_Phase2Forward)
                .build();
    }
}
