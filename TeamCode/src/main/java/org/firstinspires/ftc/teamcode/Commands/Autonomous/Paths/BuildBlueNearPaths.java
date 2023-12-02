package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths;

import android.print.PageRange;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

@Config
public class BuildBlueNearPaths {
        public static double BlueNearCenter_Phase1Strafe = 5 ;
        public static double BlueNearCenter_Phase1Forward= 32;
        public static double BlueNearCenter_Phase2Back= 28;
        public static double BlueNearCenter_Phase2Forward = 35;
        public static double BlueNearCenter_Phase2turn= 130;
        // values for BlueNearLeft
        public static double BlueNearLeft_Phase1Strafe = 9;
        public static double BlueNearLeft_Phase1Forward = 28;
        public static double BlueNearLeft_Phase2back = 23 ;
        public static double BlueNearLeft_Phase2Forward = 29;
        public static double BlueNearLeft_Phase2turn = 130 ;
        // values for the near right blue
        public static double BlueNearRight_Phase1Forward = 29;
        public static double BlueNearRight_Phase2Forward = 9 ;
        public static double BlueNearRight_Phase2Back1= 5.0;
        public static double BlueNearRight_Phase2StrafeRight = 25;
        public static double BlueNearRight_Phase2Back2 = 35 ;
        public static double BlueNearRight_Phase1turn= -130 ;


    public static TrajectorySequence Phase1 , Phase2 ;
    private static FalconMecanumDrive drive;
    public static void Build (FalconMecanumDrive dr , TeamPropPosition position){
        drive = dr;
        if ( position == TeamPropPosition.Center){
            BlueNearCenter_Phase1();
            BlueNearCenter_Phase2();
        }
        else if (position == TeamPropPosition.Left ){
            BlueNearLeft_Phase1();
            BlueNearLeft_Phase2();
        }
        else {
            BlueNearRight_Phase1();
            BlueNearRight_Phase2();
        }
    }

    public static void BlueNearCenter_Phase1(){
        Phase1= drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(BlueNearCenter_Phase1Strafe)
                .forward(BlueNearCenter_Phase1Forward)
                .build();
    }
    public static void BlueNearCenter_Phase2(){
        Phase2= drive.trajectorySequenceBuilder(new Pose2d())
                .back(BlueNearCenter_Phase2Back)
                //.waitSeconds(1)
                .turn(BlueNearCenter_Phase2turn+ 1e-6)
               // .waitSeconds(1)
                .forward(BlueNearCenter_Phase2Forward)
                .build();
    }
    public static void BlueNearLeft_Phase1 (){
        Phase1= drive.trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(BlueNearLeft_Phase1Strafe)
                .forward(BlueNearLeft_Phase1Forward)
                .build();
    }
    public static void BlueNearLeft_Phase2 (){
        Phase2= drive.trajectorySequenceBuilder(new Pose2d())
                .back(BlueNearLeft_Phase2back)
                .turn(BlueNearLeft_Phase2turn + 1e-6)
                .forward(BlueNearLeft_Phase2Forward)
                .build();
    }
    public static void BlueNearRight_Phase1 (){
        Phase1= drive.trajectorySequenceBuilder(new Pose2d())
                .forward(BlueNearRight_Phase1Forward)
                .turn(BlueNearRight_Phase1turn- 1e-6)
                .forward(BlueNearRight_Phase2Forward)
                .build();
    }
    public static void BlueNearRight_Phase2 () {
        Phase2 = drive.trajectorySequenceBuilder(new Pose2d())
                .back(BlueNearRight_Phase2Back1)
                .strafeRight(BlueNearRight_Phase2StrafeRight)
                .back(BlueNearRight_Phase2Back2)
                .build();
    }
}
