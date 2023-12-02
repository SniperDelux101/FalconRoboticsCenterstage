package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.sql.Statement;

import jdk.javadoc.internal.tool.Start;

public class MeepMeepTesting {
    public static void main(String[] args) {
        testVisionLogic();
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 11)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(16,-63,Math.toRadians(90)))

                                .forward(29)
                                .turn(Math.toRadians(-90.0)-1e-6)
                                .forward(5)
                                .back(11)
                                .turn(Math.toRadians(-90) - 1e-6)
                                .forward(24)
                                .turn(Math.toRadians(90)+1e-6)
                                .forward(30)


                                .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

    public enum Alliance {
        Blue, Red
    }

    public enum StartLocation {
        Near, Far
    }
    public static int LEFT_UPPER_BOUND_1 = 200;
    public static int RIGHT_LOWER_BOUND_1 = 650;
    public  static int LEFT_UPPER_BOUND_2 = 100;
    public static int RIGHT_LOWER_BOUND_2 = 350;
    public static void testVisionLogic() {

        int x = 0;
        Alliance alliance = Alliance.Red;
        StartLocation location = StartLocation.Far;


        // 2
        if (alliance == Alliance.Red && location == StartLocation.Near) {
            if(x > RIGHT_LOWER_BOUND_2) {
                System.out.println("Right");
            }   else if (x > LEFT_UPPER_BOUND_2 && x < RIGHT_LOWER_BOUND_2) {
                System.out.println("Center");
            }   else
                System.out.println("Left");
        }

        // 1
        if (alliance == Alliance.Red && location == StartLocation.Far) {
            if (x < LEFT_UPPER_BOUND_1) {
                System.out.println("Left");
            } else if (x > LEFT_UPPER_BOUND_1 && x < RIGHT_LOWER_BOUND_1) {
                System.out.println("Center");
            } else
                System.out.println("Right");
        }

        // 1
        if (alliance == Alliance.Blue && location == StartLocation.Near) {
            if (x < LEFT_UPPER_BOUND_1) {
                System.out.println("Left");
            } else if (x > LEFT_UPPER_BOUND_1 && x < RIGHT_LOWER_BOUND_1) {
                System.out.println("Center");
            } else
                System.out.println("Right");
        }

        // 2
        if (alliance == Alliance.Blue && location == StartLocation.Far) {
            if (x > RIGHT_LOWER_BOUND_2) {
                System.out.println("Right");
            } else if (x > LEFT_UPPER_BOUND_2 && x < RIGHT_LOWER_BOUND_2) {
                System.out.println("Center");
            } else
                System.out.println("Left");
        }
    }
}


/*
drive.trajectorySequenceBuilder(new Pose2d(17.00, -63.00, Math.toRadians(90.00)))
    .lineToConstantHeading(new Vector2d(11, -36))
    .lineToConstantHeading(new Vector2d(24,-36))
    .lineToSplineHeading(new Pose2d(42,-36, Math.toRadians(180)))
    .build());
*/