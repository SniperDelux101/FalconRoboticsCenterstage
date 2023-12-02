package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

@Config
public class BuildBlueFarPaths {
    public static class Params {
        public static double BlueFarCenter_Phase1Forward= 50;
        public static double BlueFarCenter_Phase1Forward1 = 5;
        public static double BlueFarCenter_Phase2Back= 8;
        public static double BlueFarCenter_Phase2Forward2 = 90;

        // values for BlueNearLeft
        public static double BlueFarLeft_Phase1Forward = 29;
        public static double BlueFarLeft_Phase2Forward1 = 7;
        public static double BlueFarLeft_Phase2back = 5 ;
        public static double BlueFarLeft_Phase2StrafeRight = 22 ;
        public static double BluefarLeft_Phase2Forward2 = 90 ;

        // values for the near right blue
        public static double BlueFarRight_Phase1Forward = 38;

        public static double BlueFarRight_Phase2Back1= 8.0;
        public static double BlueFarRight_Phase2StrafeLeft = 25.0;
        public static double BlueFarRight_Phase2Back2 = 93 ;

    }
    public static TrajectorySequence Phase1 , Phase2 ;
    private static FalconMecanumDrive drive;
    public static void Build (FalconMecanumDrive dr , TeamPropPosition position){
        drive = dr;
        if ( position == TeamPropPosition.Center){
            BlueFarCenter_Phase1();
            BlueFarCenter_Phase2();
        }
        else if (position == TeamPropPosition.Left ){
            BlueFarLeft_Phase1();
            BlueFarLeft_Phase2();
        }
        else {
            BlueFarRight_Phase1();
            BlueFarRight_Phase2();
        }
    }
    public static void BlueFarCenter_Phase1(){
        Phase1= drive.trajectorySequenceBuilder(new Pose2d())
                .forward(Params.BlueFarCenter_Phase1Forward)
                .turn(Math.toRadians(180) + 1e-6)
                .forward(Params.BlueFarCenter_Phase1Forward1)
                .build();

    }
    public static void BlueFarCenter_Phase2(){
        Phase2 = drive.trajectorySequenceBuilder(new Pose2d())
                .back(Params.BlueFarCenter_Phase2Back)
                .turn(Math.toRadians(-90) - 1e-6)
                .forward(Params.BlueFarCenter_Phase2Forward2)
                .build();
    }
    public static void BlueFarLeft_Phase1(){
       Phase1 = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(Params.BlueFarCenter_Phase1Forward)
                .turn(Math.toRadians(90) + 1e-6)
                .forward(Params.BlueFarLeft_Phase2Forward1)
                .build();
    }
    public static void BlueFarLeft_Phase2() {
        Phase2 = drive.trajectorySequenceBuilder(new Pose2d())
                .back(Params.BlueFarLeft_Phase2back)
                .strafeRight(Params.BlueFarLeft_Phase2StrafeRight)
                .forward(Params.BluefarLeft_Phase2Forward2)
                .build();
    }
    public static void BlueFarRight_Phase1 (){
        Phase1 = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(Params.BlueFarRight_Phase1Forward)
                .turn(Math.toRadians(-90) - 1e-6)
                .build();

    }
    public static void BlueFarRight_Phase2 (){
        Phase2 = drive.trajectorySequenceBuilder(new Pose2d())
                .back(Params.BlueFarRight_Phase2Back1)
                .strafeLeft(Params.BlueFarRight_Phase2StrafeLeft)
                .back(Params.BlueFarRight_Phase2Back2)
                .build();
    }

}
