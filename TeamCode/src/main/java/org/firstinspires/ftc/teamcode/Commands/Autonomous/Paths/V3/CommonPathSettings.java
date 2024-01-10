package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;

@Config
public class CommonPathSettings {
    public static Pose2d Blue_Near_Start_Pose = new Pose2d(12, 60, Math.toRadians(270));
    public static Pose2d Red_Near_Start_Pose = new Pose2d(12, -60, Math.toRadians(90));
    public static Pose2d Blue_Far_Start_Pose = new Pose2d(-36,60, Math.toRadians(270));
    public static Pose2d Red_Far_Start_Pose = new Pose2d(-36, -60, Math.toRadians(90));

    //  Blue Near Positions


    //  Center
    public static double BN_Center_Ph2_H1 = Math.toRadians(0);
    public static double BN_Center_Park_H1 = Math.toRadians(0);
    public static double BN_Left_Spike_Turn1 = Math.toRadians(-90);
    public static double BN_Left_Park_H1 = Math.toRadians(0);
    public static double BN_Right_Spike_H1 = Math.toRadians(180);
    public static double BN_Right_Park_H1 = Math.toRadians(0);

    //  ************************************************************
    public static double RN_Center_Park_H1 = Math.toRadians(0);


    //  Left
    public static double RN_Left_Spike_Turn1 = Math.toRadians(180);

    //  ************************************************************
    public static double RN_Left_Park_H1 = Math.toRadians(0);


    //  Right
    public static double RN_Right_Spike_H1 = Math.toRadians(180);

    //  ************************************************************
    public static double RN_Right_Park_H1 = Math.toRadians(0);

//  ************************************************************************************************

    //  Blue Far Positions


    //  Center
    public static double BF_Center_Spike_H1 = Math.toRadians(180);

    //  ************************************************************
    public static double BF_Center_Ph2_H1 = Math.toRadians(0);
    public static double BF_Center_Ph2_H2 = Math.toRadians(0);

    //  ************************************************************
    public static double BF_Center_Park_H1 = Math.toRadians(0);



    //  Red Far Positions

    //  Center
    public static double RF_Center_Spike_H1 = Math.toRadians(180);

    //  ************************************************************
    public static double RF_Center_Ph2_H1 = Math.toRadians(0);
    public static double RF_Center_Ph2_H2 = Math.toRadians(0);

    //  ************************************************************
    public static double RF_Center_Park_H1 = Math.toRadians(0);


    //  Left
    public static double RF_Left_Spike_Turn1 = Math.toRadians(180);

    //  ************************************************************
    public static double RF_Left_Ph2_H1 = Math.toRadians(-90);
    public static double RF_Left_Ph2_H2 = Math.toRadians(0);
    public static double RF_Left_Ph2_H3 = Math.toRadians(0);

    //  ************************************************************
    public static double RF_Left_Park_H1 = Math.toRadians(0);


    //  Right
    public static double RF_Right_Spike_H1 = Math.toRadians(0);

    //  ************************************************************
    public static double RF_Right_Ph2_H1 = Math.toRadians(0);
    public static double RF_Right_Ph2_H2 = Math.toRadians(0);

    //  ************************************************************
    public static double RF_Right_Park_H1 = Math.toRadians(0);

    //  ************************************************************
    public static double Strafe_Distance = 22;

    public static double Y_Mod = 16;

}
