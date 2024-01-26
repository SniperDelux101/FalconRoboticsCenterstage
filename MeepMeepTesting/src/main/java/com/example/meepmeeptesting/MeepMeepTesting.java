package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.example.meepmeeptesting.Paths.Configuration;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.entity.TrajectorySequenceEntity;


public class MeepMeepTesting {

    public static Pose2d Blue_Near_Start_Pos = new Pose2d(12, 60, Math.toRadians(270));
    public static Pose2d Red_Near_Start_Pos = new Pose2d(12, -60, Math.toRadians(90));
    public static Pose2d Blue_Far_Start_Pos = new Pose2d(-36,60, Math.toRadians(270));
    public static Pose2d Red_Far_Start_Pos = new Pose2d(-36, -60, Math.toRadians(90));
    private static int x = 4;
    private static int forward = 10;



    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity myBot = null;


//  ------------------------------------------------------------------------------------------------

        //region Blue Near [In - Out]

        // Finished

        // Left
        if (x == 1) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(30, 30, Math.toRadians(180)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(35, 30))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 2) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(12, 30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(12, 35))
                                    .splineTo(new Vector2d(20, 40), Math.toRadians(0))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50,60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 3) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(12, 40, Math.toRadians(270)))
                                    .splineTo(new Vector2d(8, 30), Math.toRadians(180))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(30, 30))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Blue Near [In - In]

        // Left
        if (x == 4) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(30, 30, Math.toRadians(180)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(35, 30))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 8), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 5) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(12, 30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(12, 35))
                                    .splineTo(new Vector2d(20, 40), Math.toRadians(0))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 8), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 6) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(12, 40, Math.toRadians(270)))
                                    .splineTo(new Vector2d(8, 30), Math.toRadians(180))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(30, 30))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 8), Math.toRadians(0))

                                    .build());
        }

        //endregion

//  ------------------------------------------------------------------------------------------------

        //region Red Near [In - Out]

        // Left
        if (x == 7) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Center
        if (x == 8) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Right
        if (x == 9) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        //endregion

        //region Red Near [In - In]

        // Left
        if (x == 10) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Center
        if (x == 11) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Right
        if (x == 12) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        //endregion

//  ------------------------------------------------------------------------------------------------

        //region Blue Far [In - In]

        // Left
        if (x == 13) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Center
        if (x == 14) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Right
        if (x == 15) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        //endregion

        //region Blue Far [In - Out]

        // Left
        if (x == 16) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Center
        if (x == 17) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Right
        if (x == 18) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        //endregion

        //region Blue Far [Out - In]

        // Left
        if (x == 19) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Center
        if (x == 20) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Right
        if (x == 21) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        //endregion

        //region Blue Far [Out - Out]

        // Left
        if (x == 22) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Center
        if (x == 23) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Right
        if (x == 24) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        //endregion

//  ------------------------------------------------------------------------------------------------

        //region Red Far [In - In]

        // Left
        if (x == 25) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Center
        if (x == 26) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Right
        if (x == 27) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        //endregion

        //region Red Far [In - Out]

        // Left
        if (x == 28) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Center
        if (x == 29) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Right
        if (x == 30) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        //endregion

        //region Red Far [Out - In]

        // Left
        if (x == 31) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Center
        if (x == 32) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Right
        if (x == 33) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        //endregion

        //region Red Far [Out - Out]

        // Left
        if (x == 34) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Center
        if (x == 35) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        // Right
        if (x == 36) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1


                                    //  Phase 2


                                    //  Phase 3


                                    //  Park


                                    .build());
        }

        //endregion

//  ------------------------------------------------------------------------------------------------



        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.94f)
                .addEntity(myBot)
                .start();
    }
}