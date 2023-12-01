package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 11)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(18, -63.00, Math.toRadians(90)))
                                .strafeRight(5)
                                .forward(24)
                                .back(18)
                                .waitSeconds(1)
                                .turn(Math.toRadians(-90) - 1e-6)
                                .waitSeconds(1)
                                .forward(23)
                                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}

/*
drive.trajectorySequenceBuilder(new Pose2d(17.00, -63.00, Math.toRadians(90.00)))
    .lineToConstantHeading(new Vector2d(11, -36))
    .lineToConstantHeading(new Vector2d(24,-36))
    .lineToSplineHeading(new Pose2d(42,-36, Math.toRadians(180)))
    .build());
*/