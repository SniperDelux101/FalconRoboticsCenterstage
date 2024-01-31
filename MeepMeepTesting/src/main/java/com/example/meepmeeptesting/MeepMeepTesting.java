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
    private static int x = 61;
    private static int forward = 2;



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

        //region Blue Near One Cycle [In - Out]

        if (x == 7) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 8) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50,60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 9) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Blue Near One Cycle [In - In]

        if (x == 10) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 11) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50,60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 12) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        //endregion

//  ------------------------------------------------------------------------------------------------

        //region Red Near [In - Out]

        // Left
        if (x == 13) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(12, -40, Math.toRadians(90)))
                                    .splineTo(new Vector2d(8, -30), Math.toRadians(180))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(30, -30))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, -60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 14) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(12, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(12, -35))
                                    .splineTo(new Vector2d(20, -40), Math.toRadians(0))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(0))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50,-60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 15) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(30, -30, Math.toRadians(180)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(35, -30))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, -60), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Red Near [In - In]

        // Left
        if (x == 16) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(12, -40, Math.toRadians(90)))
                                    .splineTo(new Vector2d(8, -30), Math.toRadians(180))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(30, -30))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, -8), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 17) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(12, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(12, -35))
                                    .splineTo(new Vector2d(20, -40), Math.toRadians(0))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(0))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, -8), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 18) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(30, -30, Math.toRadians(180)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(35, -30))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, -8), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Red Near One Cycle [In - Out]

        // Left
        if (x == 19) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(12, -40, Math.toRadians(90)))
                                    .splineTo(new Vector2d(8, -30), Math.toRadians(180))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(30, -30))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, -60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 20) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(12, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(12, -35))
                                    .splineTo(new Vector2d(20, -40), Math.toRadians(0))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(0))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50,-60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 21) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(30, -30, Math.toRadians(180)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(35, -30))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, -60), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Red Near One Cycle [In - In]

        if (x == 22) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(30, -30, Math.toRadians(180)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(35, -30))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, -60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 23) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(12, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(12, -35))
                                    .splineTo(new Vector2d(20, -40), Math.toRadians(0))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50,-60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 24) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Near_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(12, -40, Math.toRadians(90)))
                                    .splineTo(new Vector2d(8, -30), Math.toRadians(180))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(30, -30))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, -60), Math.toRadians(0))

                                    .build());
        }

        //endregion

//  ------------------------------------------------------------------------------------------------

        //region Blue Far [In - In]

        // Left
        if (x == 25) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, 40))
                                    .splineTo(new Vector2d(-30, 30), Math.toRadians(0))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-40, 30))
                                    .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 26) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, 30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, 40))
                                    .splineTo(new Vector2d(-50, 30), Math.toRadians(270))
                                    .lineToConstantHeading(new Vector2d(-50, 12))
                                    .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 27) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-46, 20, Math.toRadians(90)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, 15))
                                    .splineTo(new Vector2d(-36, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Blue Far [In - Out]

        // Left
        if (x == 28) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, 40))
                                    .splineTo(new Vector2d(-30, 30), Math.toRadians(0))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-40, 30))
                                    .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 29) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, 30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, 40))
                                    .splineTo(new Vector2d(-50, 30), Math.toRadians(270))
                                    .lineToConstantHeading(new Vector2d(-50, 12))
                                    .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 30) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-46, 20, Math.toRadians(90)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, 15))
                                    .splineTo(new Vector2d(-36, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Blue Far [Out - In]

        // Left
        if (x == 31) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-30, 30, Math.toRadians(0)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-45, 30))
                                    .splineTo(new Vector2d(-45, 58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, 58))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 32) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-36, 30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, 40))
                                    .splineTo(new Vector2d(-30, 58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, 58))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 33) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-46, 40))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, 50))
                                    .splineTo(new Vector2d(-40, 58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, 58))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Blue Far [Out - Out]

        // Left
        if (x == 34) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-30, 30, Math.toRadians(0)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-45, 30))
                                    .splineTo(new Vector2d(-45, 58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, 58))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 35) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-36, 30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, 40))
                                    .splineTo(new Vector2d(-30, 58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, 58))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 36) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-46, 40))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, 50))
                                    .splineTo(new Vector2d(-40, 58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, 58))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 60), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Blue Far One Cycle [In - In]

        if (x == 37) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, 40))
                                    .splineTo(new Vector2d(-30, 30), Math.toRadians(0))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-40, 30))
                                    .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 38) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, 30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, 40))
                                    .splineTo(new Vector2d(-50, 30), Math.toRadians(270))
                                    .lineToConstantHeading(new Vector2d(-50, 12))
                                    .splineTo(new Vector2d(-45, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 39) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-46, 20, Math.toRadians(90)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, 15))
                                    .splineTo(new Vector2d(-36, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, 8), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Far Near One Cycle [In - Out]

        if (x == 40) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 41) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(180))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50,60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 42) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Blue Far One Cycle [Out - In]

        if (x == 43) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 44) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50,60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 45) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Blue Far One Cycle [Out - Out]

        if (x == 46) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 47) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50,60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 48) {
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

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, 58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, 58))
                                    .splineToConstantHeading(new Vector2d(-57, 35), Math.toRadians(270))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, 35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, 35))
                                    .lineToConstantHeading(new Vector2d(-57, 12))
                                    .splineToConstantHeading(new Vector2d(-54, 8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, 8))
                                    .splineToConstantHeading(new Vector2d(46, 16), Math.toRadians(90))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(50, 60), Math.toRadians(0))

                                    .build());
        }

        //endregion

//  ------------------------------------------------------------------------------------------------

        //region Red Far [In - In]

        // Left
        if (x == 49) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-46, -20, Math.toRadians(270)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, -15))
                                    .splineTo(new Vector2d(-36, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 50) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, -40))
                                    .splineTo(new Vector2d(-50, -30), Math.toRadians(90))
                                    .lineToConstantHeading(new Vector2d(-50, -12))
                                    .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 51) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, -40))
                                    .splineTo(new Vector2d(-30, -30), Math.toRadians(0))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-40, -30))
                                    .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Red Far [In - Out]

        // Left
        if (x == 52) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-46, -20, Math.toRadians(270)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, -15))
                                    .splineTo(new Vector2d(-36, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 53) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, -40))
                                    .splineTo(new Vector2d(-50, -30), Math.toRadians(90))
                                    .lineToConstantHeading(new Vector2d(-50, -12))
                                    .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 54) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, -40))
                                    .splineTo(new Vector2d(-30, -30), Math.toRadians(0))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-40, -30))
                                    .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Red Far [Out - In]

        // Left
        if (x == 55) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-46, -40))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, -50))
                                    .splineTo(new Vector2d(-40, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 56) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-36, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, -40))
                                    .splineTo(new Vector2d(-30, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 57) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-30, -30, Math.toRadians(0)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-45, -30))
                                    .splineTo(new Vector2d(-45, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Red Far [Out - Out]

        // Left
        if (x == 58) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-46, -40))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, -50))
                                    .splineTo(new Vector2d(-40, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 59) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-36, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, -40))
                                    .splineTo(new Vector2d(-30, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 60) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-30, -30, Math.toRadians(0)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-45, -30))
                                    .splineTo(new Vector2d(-45, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Red Far One Cycle [In - In]

        if (x == 61) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-46, -20, Math.toRadians(270)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, -15))
                                    .splineTo(new Vector2d(-36, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 62) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, -40))
                                    .splineTo(new Vector2d(-50, -30), Math.toRadians(90))
                                    .lineToConstantHeading(new Vector2d(-50, -12))
                                    .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 63) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineTo(new Vector2d(-36, -40))
                                    .splineTo(new Vector2d(-30, -30), Math.toRadians(0))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-40, -30))
                                    .splineTo(new Vector2d(-45, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Red Far One Cycle [In - Out]

        if (x == 64) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-46, -20, Math.toRadians(270)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, -15))
                                    .splineTo(new Vector2d(-36, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 65) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-36, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, -40))
                                    .splineTo(new Vector2d(-30, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 57) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-30, -30, Math.toRadians(0)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-45, -30))
                                    .splineTo(new Vector2d(-45, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Red Far One Cycle [Out - In]

        if (x == 67) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-46, -40))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, -50))
                                    .splineTo(new Vector2d(-40, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 68) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-36, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, -40))
                                    .splineTo(new Vector2d(-30, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 69) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-30, -30, Math.toRadians(0)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-45, -30))
                                    .splineTo(new Vector2d(-45, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -8), Math.toRadians(0))

                                    .build());
        }

        //endregion

        //region Red Far One Cycle [Out - Out]

        if (x == 70) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-46, -40))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-46, -50))
                                    .splineTo(new Vector2d(-40, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))

                                    .build());
        }

        // Center
        if (x == 71) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToConstantHeading(new Vector2d(-36, -30))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-36, -40))
                                    .splineTo(new Vector2d(-30, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))

                                    .build());
        }

        // Right
        if (x == 72) {
            myBot = new DefaultBotBuilder(meepMeep)
                    .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Red_Far_Start_Pos)

                                    //  Phase 1
                                    .lineToLinearHeading(new Pose2d(-30, -30, Math.toRadians(0)))

                                    //  Phase 2
                                    .lineToConstantHeading(new Vector2d(-45, -30))
                                    .splineTo(new Vector2d(-45, -58), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(25, -58))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(90))

                                    //  Phase 3
                                    .forward(forward)

                                    //  Cycle Phase 1
                                    .splineToConstantHeading(new Vector2d(33, -58), Math.toRadians(180))
                                    .lineToConstantHeading(new Vector2d(-40, -58))
                                    .splineToConstantHeading(new Vector2d(-57, -35), Math.toRadians(90))

                                    //  Cycle Phase 2
                                    .lineToConstantHeading(new Vector2d(-60, -35))

                                    //  Cycle Phase 3
                                    .lineToConstantHeading(new Vector2d(-57, -35))
                                    .lineToConstantHeading(new Vector2d(-57, -12))
                                    .splineToConstantHeading(new Vector2d(-54, -8), Math.toRadians(0))
                                    .lineToConstantHeading(new Vector2d(40, -8))
                                    .splineToConstantHeading(new Vector2d(46, -16), Math.toRadians(270))

                                    //  Cycle Phase 4
                                    .forward(forward)

                                    //  Park
                                    .splineToConstantHeading(new Vector2d(55, -60), Math.toRadians(0))

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