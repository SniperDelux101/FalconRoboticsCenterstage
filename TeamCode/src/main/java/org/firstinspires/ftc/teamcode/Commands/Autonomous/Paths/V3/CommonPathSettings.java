package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V3;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

@Config
public class CommonPathSettings {
    public static Pose2d Blue_Near_Start_Pose = new Pose2d(12, 60, Math.toRadians(270));
    public static Pose2d Red_Near_Start_Pose = new Pose2d(12, -60, Math.toRadians(90));

    public static Pose2d Blue_Far_Start_Pose = new Pose2d(-36,60, Math.toRadians(270));
    public static Pose2d Red_Far_Start_Pose = new Pose2d(-36, -60, Math.toRadians(90));




}
