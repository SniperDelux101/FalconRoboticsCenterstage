package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.Constraints;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveShim;
import com.noahbres.meepmeep.roadrunner.DriveTrainType;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.example.meepmeeptesting.Paths.*;
import com.example.meepmeeptesting.Paths.V2.*;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class MeepMeepTesting {

    public enum StartLocation {
        Near, Far
    }

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        StartLocation location = StartLocation.Near;
        Alliance alliance = Alliance.Blue;
        TeamPropPosition teamPropPosition = TeamPropPosition.Left;

        double maxVel = 66.3358551600406;
        double maxAccel = 60;
        double maxAngVel = Math.toRadians(54.98425989336563);
        double maxAngAccel = Math.toRadians(54.98425989336563);
        double trackWidth = 11.6003937;
        Constraints constraints = new Constraints(maxVel, maxAccel, maxAngVel, maxAngAccel, trackWidth);
        TrajectorySequence phase1, phase2;
        Pose2d startingPose;
        DriveShim shim;

        if(location == StartLocation.Near){
            if(alliance == Alliance.Blue)
                startingPose = new Pose2d(23 / 2, 60, Math.toRadians(270));
            else
                startingPose = new Pose2d(23 / 2, -60, Math.toRadians(90));
            shim = new DriveShim(DriveTrainType.MECANUM, constraints, startingPose);
            BuildNearPaths.Build(shim, teamPropPosition, alliance);
            phase1 = BuildNearPaths.Phase1;
            phase2 = BuildNearPaths.Phase2;
        }
        else {
            if(alliance == Alliance.Blue)
                startingPose = new Pose2d(-23  + -23/ 2, 60, Math.toRadians(270));
            else
                startingPose = new Pose2d(-23  + -23/ 2, -60, Math.toRadians(90));
            shim = new DriveShim(DriveTrainType.MECANUM, constraints, startingPose);
            BuildFarPaths.Build(shim, teamPropPosition, alliance);
            phase1 = BuildFarPaths.Phase1;
            phase2 = BuildFarPaths.Phase2;
        }

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(constraints)
                .build();
        RoadRunnerBotEntity bot2 = new DefaultBotBuilder(meepMeep)
                .setConstraints(constraints)
                .setColorScheme(new ColorSchemeBlueDark())
                        .build();

        myBot.followTrajectorySequence(phase1);
        bot2.followTrajectorySequence(phase2);

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .addEntity(bot2)
                .start();
    }
}