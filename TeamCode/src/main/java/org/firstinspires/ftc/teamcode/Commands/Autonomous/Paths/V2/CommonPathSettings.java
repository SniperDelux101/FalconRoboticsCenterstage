package org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.V2;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

@Config
public class CommonPathSettings {
    public static Pose2d Blue_Near_Start_Pose = new Pose2d(23 / 2, 10, Math.toRadians(270));
    public static Pose2d Red_Near_Start_Pose = new Pose2d(23 / 2, -60, Math.toRadians(90));

    public static Pose2d Blue_Far_Start_Pose = new Pose2d(-23  + -23/ 2, 60, Math.toRadians(270));
    public static Pose2d Red_Far_Start_Pose = new Pose2d(-23  + -23/ 2, -60, Math.toRadians(90));

    public static Vector2d Blue_Near_Park_Vector = new Vector2d(44,50);
    public static Vector2d Red_Near_Park_Vector = new Vector2d(44,-50);
    public static Vector2d Blue_Far_Park_Vector = new Vector2d(44,10);
    public static Vector2d Red_Far_Park_Vector = new Vector2d(44,-10);
    public static double Back_Dist = 5;


}
