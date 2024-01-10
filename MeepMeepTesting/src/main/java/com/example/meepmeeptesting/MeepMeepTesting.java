package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeepTesting {

    public static Pose2d Blue_Near_Start_Pos = new Pose2d(12, 60, Math.toRadians(270));
    public static Pose2d Red_Near_Start_Pos = new Pose2d(12, -60, Math.toRadians(90));
    public static Pose2d Blue_Far_Start_Pos = new Pose2d(-36,60, Math.toRadians(270));
    public static Pose2d Red_Far_Start_Pos = new Pose2d(-36, -60, Math.toRadians(90));

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(Blue_Far_Start_Pos)

                                // Blue Near Left
                                /*
                                .lineToConstantHeading(new Vector2d(30,28))
                                .turn(Math.toRadians(-90))

                                .lineToConstantHeading(new Vector2d(40, 28))
                                .lineToConstantHeading(new Vector2d(53, 39))

                                .lineToConstantHeading(new Vector2d(40, 40))

                                .splineToConstantHeading(new Vector2d(50,60), Math.toRadians(0))

                                 */

                                //Blue Near Center
                                /*
                                .lineTo(new Vector2d(12, 28))

                                .lineTo(new Vector2d(12, 36))
                                .splineTo(new Vector2d(30, 32), Math.toRadians(0))
                                .lineTo(new Vector2d(54, 32))

                                .lineToConstantHeading(new Vector2d(45, 32))

                                .lineToConstantHeading(new Vector2d(45, 50))
                                .splineToConstantHeading(new Vector2d(60, 60), Math.toRadians(0))
                                */

                                // Blue Near Right
                                /*
                                .splineTo(new Vector2d(9, 30), Math.toRadians(180))

                                .lineToConstantHeading(new Vector2d(53,28))

                                .lineToConstantHeading(new Vector2d(40, 28))

                                .lineToConstantHeading(new Vector2d(40, 50))
                                .splineToConstantHeading(new Vector2d(60, 60), Math.toRadians(0))

                                 */

//              -------------------------------------------------------------------

                                // Red Near Left
                                /*
                                .splineTo(new Vector2d(9, -30), Math.toRadians(180))

                                .lineToConstantHeading(new Vector2d(51.5, -30))

                                .lineToConstantHeading(new Vector2d(40, -30))

                                .lineToConstantHeading(new Vector2d(40, -50))
                                .splineToConstantHeading(new Vector2d(60, -60), Math.toRadians(0))

                                 */

                                //  Red Near Center
                                /*
                                .lineTo(new Vector2d(12, -30))

                                .lineTo(new Vector2d(12, -36))
                                .splineTo(new Vector2d(30, -36), Math.toRadians(0))
                                .lineTo(new Vector2d(51.5, -36))

                                .lineToConstantHeading(new Vector2d(40, -36))

                                .lineToConstantHeading(new Vector2d(40, -50))
                                .splineToConstantHeading(new Vector2d(60, -60), Math.toRadians(0))

                                 */

                                //  Red Near Right
                                /*
                                .lineToConstantHeading(new Vector2d(30,-28))
                                .turn(Math.toRadians(90))

                                .lineToConstantHeading(new Vector2d(40, -28))
                                .splineToConstantHeading(new Vector2d(51.5, -42), Math.toRadians(0))

                                .lineToConstantHeading(new Vector2d(40, -42))

                                .lineToConstantHeading(new Vector2d(40, -50))
                                .splineToConstantHeading(new Vector2d(60,-60), Math.toRadians(0))

                                 */

//              -------------------------------------------------------------------

                                // Blue Far Left 1
                                /*
                                .splineTo(new Vector2d(-32, 30), Math.toRadians(0))

                                .lineTo(new Vector2d(-40, 30))
                                .splineTo(new Vector2d(-36,58), Math.toRadians(0))
                                .lineToConstantHeading(new Vector2d(12, 58))
                                .splineToConstantHeading(new Vector2d(45,40), Math.toRadians(0))

                                .lineToConstantHeading(new Vector2d(40, 14))
                                .splineToConstantHeading(new Vector2d(60, 12), Math.toRadians(0))

                                 */

                                // Blue Far Center 1
                                /*
                                .lineTo(new Vector2d(-36, 30))

                                .lineTo(new Vector2d(-36, 36))
                                .splineTo(new Vector2d(-36,58), Math.toRadians(0))
                                .lineToConstantHeading(new Vector2d(12, 58))
                                .splineToConstantHeading(new Vector2d(45,35), Math.toRadians(0))

                                .lineToConstantHeading(new Vector2d(40, 14))
                                .splineToConstantHeading(new Vector2d(60, 12), Math.toRadians(0))

                                 */

                                // Blue Far Right 1
                                 /*
                                .splineTo(new Vector2d(-40, 30), Math.toRadians(180))

                                .lineTo(new Vector2d(-34, 30))
                                .lineToConstantHeading(new Vector2d(-34, 54))
                                .splineToConstantHeading(new Vector2d(-30, 58), Math.toRadians(0))
                                .lineToConstantHeading(new Vector2d(12, 58))
                                .splineToConstantHeading(new Vector2d(45,30), Math.toRadians(0))

                                .lineToConstantHeading(new Vector2d(40, 14))
                                .splineToConstantHeading(new Vector2d(60, 12), Math.toRadians(0))

                                */

//              -------------------------------------------------------------------

                                //  Blue Far Left 2
                                /*
                                .splineTo(new Vector2d(-33, 28), Math.toRadians(0))

                                .lineTo(new Vector2d(-40, 28))
                                .splineTo(new Vector2d(-40, 2), Math.toRadians(0))
                                .lineToConstantHeading(new Vector2d(30, 2))
                                .splineToConstantHeading(new Vector2d(40, 32), Math.toRadians(0))

//                                .lineToConstantHeading(new Vector2d(40, 42))

                                .lineToConstantHeading(new Vector2d(40, 14))
                                .splineToConstantHeading(new Vector2d(60, 7), Math.toRadians(0))

                                 */

                                // Blue Far Center 2
                                /*
                                .lineTo(new Vector2d(-36, 16))
                                .turn(Math.toRadians(180))

                                .lineToConstantHeading(new Vector2d(-36, 8))
                                .splineTo(new Vector2d(-20, 8), Math.toRadians(0))
                                .lineToConstantHeading(new Vector2d(30, 8))
                                .splineToConstantHeading(new Vector2d(54,35), Math.toRadians(0))

                                .lineToConstantHeading(new Vector2d(40, 35))

                                .lineToConstantHeading(new Vector2d(40, 14))
                                .splineToConstantHeading(new Vector2d(60, 9), Math.toRadians(0))

                                 */

                                //  Blue Far Right 2
//                                /*
                                .lineTo(new Vector2d(-42, 16))
                                .turn(Math.toRadians(180))

                                .lineToConstantHeading(new Vector2d(-46, 6))
                                .turn(Math.toRadians(90))
                                .lineToConstantHeading(new Vector2d(30, 6))
                                .splineToConstantHeading(new Vector2d(40,16), Math.toRadians(0))

                                .strafeRight(22)

                                .lineToConstantHeading(new Vector2d(40, 28))

                                .lineToConstantHeading(new Vector2d(40, 14))
                                .splineToConstantHeading(new Vector2d(60, 7), Math.toRadians(0))
//                                */

//              -------------------------------------------------------------------

                                //  Red Far Left 1
                                /*
                                .splineTo(new Vector2d(-40, -30), Math.toRadians(180))

                                .lineTo(new Vector2d(-34, -30))
                                .lineToConstantHeading(new Vector2d(-34, -54))
                                .splineToConstantHeading(new Vector2d(-30, -58), Math.toRadians(0))
                                .lineToConstantHeading(new Vector2d(12, -58))
                                .splineToConstantHeading(new Vector2d(45,-30), Math.toRadians(0))

                                .lineToConstantHeading(new Vector2d(40, -14))
                                .splineToConstantHeading(new Vector2d(60, -12), Math.toRadians(0))
                                 */

                                // Red Far Center 1
                                /*
                                .lineTo(new Vector2d(-36, -30))

                                .lineTo(new Vector2d(-36, -36))
                                .splineTo(new Vector2d(-36,-58), Math.toRadians(0))
                                .lineToConstantHeading(new Vector2d(12, -58))
                                .splineToConstantHeading(new Vector2d(45,-35), Math.toRadians(0))

                                .lineToConstantHeading(new Vector2d(40, -14))
                                .splineToConstantHeading(new Vector2d(60, -12), Math.toRadians(0))
                                 */

                                //  Red Far Right 1
                                /*
                                .splineTo(new Vector2d(-32, -30), Math.toRadians(0))

                                .lineTo(new Vector2d(-40, -30))
                                .splineTo(new Vector2d(-36,-58), Math.toRadians(0))
                                .lineToConstantHeading(new Vector2d(12, -58))
                                .splineToConstantHeading(new Vector2d(45,-40), Math.toRadians(0))

                                .lineToConstantHeading(new Vector2d(40, -14))
                                .splineToConstantHeading(new Vector2d(60, -12), Math.toRadians(0))

                                */

//              -------------------------------------------------------------------

                                // Red Far Left 2
                                /*
                                .lineTo(new Vector2d(-47, -18))
                                .turn(Math.toRadians(180))

                                .lineTo(new Vector2d(-47, -10))
                                .turn(Math.toRadians(-90))
                                .lineToConstantHeading(new Vector2d(-34, -10))
                                .splineToConstantHeading(new Vector2d(-20, -10), Math.toRadians(0))
                                .lineToConstantHeading(new Vector2d(30, -10))
                                .splineToConstantHeading(new Vector2d(40,-35), Math.toRadians(0))

                                .lineToConstantHeading(new Vector2d(40, -30))

                                .lineToConstantHeading(new Vector2d(40, -14))
                                .splineToConstantHeading(new Vector2d(50, -12), Math.toRadians(0))
                                 */

                                // Red Far Center 2
                                /*
                                .lineTo(new Vector2d(-36, -18))
                                .turn(Math.toRadians(180))

                                .lineToConstantHeading(new Vector2d(-36, -8))
                                .splineTo(new Vector2d(-20, -8), Math.toRadians(0))
                                .lineToConstantHeading(new Vector2d(30, -8))
                                .splineToConstantHeading(new Vector2d(51.5,-35), Math.toRadians(0))

                                .lineToConstantHeading(new Vector2d(40, -35))

                                .lineToConstantHeading(new Vector2d(40, -14))
                                .splineToConstantHeading(new Vector2d(50, -12), Math.toRadians(0))

                                 */

                                //  Red Far Right 2
                                /*
                                .splineTo(new Vector2d(-32, -30), Math.toRadians(0))

                                .lineTo(new Vector2d(-40, -30))
                                .splineTo(new Vector2d(-40, -12), Math.toRadians(0))
                                .lineToConstantHeading(new Vector2d(30, -12))
                                .splineToConstantHeading(new Vector2d(53, -40), Math.toRadians(0))

                                .lineToConstantHeading(new Vector2d(40, -40))

                                .lineToConstantHeading(new Vector2d(40, -14))
                                .splineToConstantHeading(new Vector2d(50, -12), Math.toRadians(0))

                                 */

                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.94f)
                .addEntity(myBot)
                .start();
    }
}