package org.firstinspires.ftc.teamcode.Utilities;

import com.acmerobotics.dashboard.config.Config;
@Config
public class Configuration {
    public static double TRACKWIDTH = 10.3;
    public static double CENTER_WHEEL_OFSET= 2.4;
    public static double WHEEL_DIAMETER = 2.7;
    public static double TICKS_PER_REV = 8192;
    public static double DISTANCE_PER_PULSE = Math.PI * Configuration.WHEEL_DIAMETER / Configuration.TICKS_PER_REV;
}
