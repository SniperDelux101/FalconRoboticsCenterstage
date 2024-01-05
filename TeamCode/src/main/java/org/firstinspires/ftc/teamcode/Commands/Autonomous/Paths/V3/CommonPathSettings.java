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
    public static double BN_Center_X_12 = 12;
    public static double BN_Center_Y_30 = 30;
    public static double BN_Center_Y_32 = 32;
    public static double BN_Center_X_30 = 30;
    public static double BN_Center_Ph2_H1 = Math.toRadians(0);
    public static double BN_Center_X_54 = 54;
    public static double BN_Center_X_45 = 45;
    public static double BN_Center_Y_50 = 50;
    public static double BN_Center_X_60 = 60;
    public static double BN_Center_Y_60 = 60;
    public static double BN_Center_Park_H1 = Math.toRadians(0);
    public static double BN_Left_X_30 = 30;
    public static double BN_Left_Y_28 = 28;
    public static double BN_Left_Spike_Turn1 = Math.toRadians(-90);
    public static double BN_Left_X_40 = 40;
    //public static double BN_Left_Y_28 = 28;
    public static double BN_Left_X_54 = 54;
    public static double BN_Left_Y_39 = 39;
    //public static double BN_Left_X_40 = 40;
    public static double BN_Left_Y_40 = 40;
    public static double BN_Left_X_50 = 50;
    public static double BN_Left_Y_60 = 60;
    public static double BN_Left_Park_H1 = Math.toRadians(0);
    public static double BN_Right_X_9 = 9;
    public static double BN_Right_Y_30 = 30;
    public static double BN_Right_Spike_H1 = Math.toRadians(180);
    public static double BN_Right_X_54 = 54;
    public static double BN_Right_Y_32 = 32;
    public static double BN_Right_X_40 = 40;
    //public static double BN_Right_Y_32 = 32;
    //public static double BN_Right_X_40 = 40;
    public static double BN_Right_Park_C2 = 50;
    public static double BN_Right_Park_C3 = 60;
    public static double BN_Right_Park_C4 = 60;
    public static double BN_Right_Park_H1 = Math.toRadians(0);
//  ************************************************************************************************

    //  Red Near Positions

    //  Center
    public static double RN_Center_Spike_C1 = 12;
    public static double RN_Center_Spike_C2 = -29;

    //  ************************************************************
    public static double RN_Center_Ph2_C1 = 12;
    public static double RN_Center_Ph2_C2 = -36;
    public static double RN_Center_Ph2_C3 = 30;
    public static double RN_Center_Ph2_C4 = -36;
    public static double RN_Center_Ph2_C5 = 54;
    public static double RN_Center_Ph2_C6 = -34;

    //  ************************************************************
    public static double RN_Center_Ph3_C1 = 40;
    public static double RN_Center_Ph3_C2 = -36;

    //  ************************************************************
    public static double RN_Center_Park_C1 = 40;
    public static double RN_Center_Park_C2 = -50;
    public static double RN_Center_Park_C3 = 60;
    public static double RN_Center_Park_C4 = -60;
    public static double RN_Center_Park_H1 = Math.toRadians(0);


    //  Left
    public static double RN_Left_Spike_C1 = 9;
    public static double RN_Left_Spike_C2 = -30;
    public static double RN_Left_Spike_Turn1 = Math.toRadians(180);

    //  ************************************************************
    public static double RN_Left_Ph2_C1 = 55;
    public static double RN_Left_Ph2_C2 = -33;

    //  ************************************************************
    public static double RN_Left_Ph3_C1 = 40;
    public static double RN_Left_Ph3_C2 = -30;

    //  ************************************************************
    public static double RN_Left_Park_C1 = 40;
    public static double RN_Left_Park_C2 = -50;
    public static double RN_Left_Park_C3 = 60;
    public static double RN_Left_Park_C4 = -60;
    public static double RN_Left_Park_H1 = Math.toRadians(0);


    //  Right
    public static double RN_Right_Spike_C1 = 30;
    public static double RN_Right_Spike_C2 = -28;
    public static double RN_Right_Spike_H1 = Math.toRadians(180);

    //  ************************************************************
    public static double RN_Right_Ph2_C1 = 40;
    public static double RN_Right_Ph2_C2 = -26;
    public static double RN_Right_Ph2_C3 = 55;
    public static double RN_Right_Ph2_C4 = -33;

    //  ************************************************************
    public static double RN_Right_Ph3_C1 = 40;
    public static double RN_Right_Ph3_C2 = -42;

    //  ************************************************************
    public static double RN_Right_Park_C1 = 40;
    public static double RN_Right_Park_C2 = -50;
    public static double RN_Right_Park_C3 = 60;
    public static double RN_Right_Park_C4 = -60;
    public static double RN_Right_Park_H1 = Math.toRadians(0);

//  ************************************************************************************************

    //  Blue Far Positions


    //  Center
    public static double BF_Center_Spike_C1 = -36;
    public static double BF_Center_Spike_C2 = 13;
    public static double BF_Center_Spike_H1 = Math.toRadians(180);

    //  ************************************************************
    public static double BF_Center_Ph2_C1 = -36;
    public static double BF_Center_Ph2_C2 = 8;
    public static double BF_Center_Ph2_C3 = -20;
    public static double BF_Center_Ph2_C4 = 8;
    public static double BF_Center_Ph2_H1 = Math.toRadians(0);
    public static double BF_Center_Ph2_C5 = 30;
    public static double BF_Center_Ph2_C6 = 8;
    public static double BF_Center_Ph2_C7 = 56;
    public static double BF_Center_Ph2_C8 = 31;
    public static double BF_Center_Ph2_H2 = Math.toRadians(0);

    //  ************************************************************
    public static double BF_Center_Ph3_C1 = 40;
    public static double BF_Center_Ph3_C2 = 31;

    //  ************************************************************
    public static double BF_Center_Park_C1 = 40;
    public static double BF_Center_Park_C2 = 14;
    public static double BF_Center_Park_C3 = 60;
    public static double BF_Center_Park_C4 = 3;
    public static double BF_Center_Park_H1 = Math.toRadians(0);


    //  Left
    public static double BF_Left_Spike_C1 = -33;
    public static double BF_Left_Spike_C2 = 28;
    public static double BF_Left_Spike_Turn1 = Math.toRadians(0);

    //  ************************************************************
    public static double BF_Left_Ph2_C1 = -40;
    public static double BF_Left_Ph2_C2 = 28;
    public static double BF_Left_Ph2_C3 = -40;
    public static double BF_Left_Ph2_C4 = 6;
    public static double BF_Left_Ph2_H1 = Math.toRadians(0);
    public static double BF_Left_Ph2_C5 = 30;
    public static double BF_Left_Ph2_C6 = 6;
    public static double BF_Left_Ph2_C7 = 54;
    public static double BF_Left_Ph2_C8 = 39;
    public static double BF_Left_Ph2_H2 = Math.toRadians(0);

    //  ************************************************************
    public static double BF_Left_Ph3_C1 = 40;
    public static double BF_Left_Ph3_C2 = 42;

    //  ************************************************************
    public static double BF_Left_Park_C1 = 40;
    public static double BF_Left_Park_C2 = 14;
    public static double BF_Left_Park_C3 = 60;
    public static double BF_Left_Park_C4 = 7;
    public static double BF_Left_Park_H1 = Math.toRadians(0);

    //  Right
    public static double BF_Right_Spike_C1 = -42;
    public static double BF_Right_Spike_C2 = 16;
    public static double BF_Right_Spike_H1 = Math.toRadians(180);

    //  ************************************************************
    public static double BF_Right_Ph2_C1 = -46;
    public static double BF_Right_Ph2_C2 = 8;
    public static double BF_Right_Ph2_H1 = Math.toRadians(90);
    public static double BF_Right_Ph2_C3 = 30;
    public static double BF_Right_Ph2_C4 = 12;
    public static double BF_Right_Ph2_C5 = 55;
    public static double BF_Right_Ph2_C6 = 30;
    public static double BF_Right_Ph2_H2 = Math.toRadians(0);

    //  ************************************************************
    public static double BF_Right_Ph3_C1 = 40;
    public static double BF_Right_Ph3_C2 = 28;

    //  ************************************************************
    public static double BF_Right_Park_C1 = 40;
    public static double BF_Right_Park_C2 = 14;
    public static double BF_Right_Park_C3 = 60;
    public static double BF_Right_Park_C4 = 7;
    public static double BF_Right_Park_H1 = Math.toRadians(0);



    //  Red Far Positions

    //  Center
    public static double RF_Center_Spike_C1 = -36;
    public static double RF_Center_Spike_C2 = -17;
    public static double RF_Center_Spike_H1 = Math.toRadians(180);

    //  ************************************************************
    public static double RF_Center_Ph2_C1 = -36;
    public static double RF_Center_Ph2_C2 = -8;
    public static double RF_Center_Ph2_C3 = -20;
    public static double RF_Center_Ph2_C4 = -8;
    public static double RF_Center_Ph2_H1 = Math.toRadians(0);
    public static double RF_Center_Ph2_C5 = 30;
    public static double RF_Center_Ph2_C6 = -8;
    public static double RF_Center_Ph2_C7 = 55;
    public static double RF_Center_Ph2_C8 = -35;
    public static double RF_Center_Ph2_H2 = Math.toRadians(0);

    //  ************************************************************
    public static double RF_Center_Ph3_C1 = 40;
    public static double RF_Center_Ph3_C2 = -35;

    //  ************************************************************
    public static double RF_Center_Park_C1 = 40;
    public static double RF_Center_Park_C2 = -14;
    public static double RF_Center_Park_C3 = 50;
    public static double RF_Center_Park_C4 = -12;
    public static double RF_Center_Park_H1 = Math.toRadians(0);




    //  Left
    public static double RF_Left_Spike_C1 = -47;
    public static double RF_Left_Spike_C2 = -18;
    public static double RF_Left_Spike_Turn1 = Math.toRadians(180);

    //  ************************************************************
    public static double RF_Left_Ph2_C1 = -47;
    public static double RF_Left_Ph2_C2 = -10;
    public static double RF_Left_Ph2_H1 = Math.toRadians(-90);
    public static double RF_Left_Ph2_C3 = -34;
    public static double RF_Left_Ph2_C4 = -10;
    public static double RF_Left_Ph2_C5 = -20;
    public static double RF_Left_Ph2_C6 = -10;
    public static double RF_Left_Ph2_H2 = Math.toRadians(0);
    public static double RF_Left_Ph2_C7 = 30;
    public static double RF_Left_Ph2_C8 = -10;
    public static double RF_Left_Ph2_C9 = 55;
    public static double RF_Left_Ph2_C10 = -35;
    public static double RF_Left_Ph2_H3 = Math.toRadians(0);

    //  ************************************************************
    public static double RF_Left_Ph3_C1 = 40;
    public static double RF_Left_Ph3_C2 = -30;

    //  ************************************************************
    public static double RF_Left_Park_C1 = 40;
    public static double RF_Left_Park_C2 = -14;
    public static double RF_Left_Park_C3 = 50;
    public static double RF_Left_Park_C4 = -12;
    public static double RF_Left_Park_H1 = Math.toRadians(0);


    //  Right
    public static double RF_Right_Spike_C1 = -32;
    public static double RF_Right_Spike_C2 = -30;
    public static double RF_Right_Spike_H1 = Math.toRadians(0);

    //  ************************************************************
    public static double RF_Right_Ph2_C1 = -40;
    public static double RF_Right_Ph2_C2 = -30;
    public static double RF_Right_Ph2_C3 = -40;
    public static double RF_Right_Ph2_C4 = -12;
    public static double RF_Right_Ph2_H1 = Math.toRadians(0);
    public static double RF_Right_Ph2_C5 = 30;
    public static double RF_Right_Ph2_C6 = -12;
    public static double RF_Right_Ph2_C7 = 55;
    public static double RF_Right_Ph2_C8 = -35;
    public static double RF_Right_Ph2_H2 = Math.toRadians(0);

    //  ************************************************************
    public static double RF_Right_Ph3_C1 = 40;
    public static double RF_Right_Ph3_C2 = -40;

    //  ************************************************************
    public static double RF_Right_Park_C1 = 40;
    public static double RF_Right_Park_C2 = -14;
    public static double RF_Right_Park_C3 = 50;
    public static double RF_Right_Park_C4 = -12;
    public static double RF_Right_Park_H1 = Math.toRadians(0);
}
