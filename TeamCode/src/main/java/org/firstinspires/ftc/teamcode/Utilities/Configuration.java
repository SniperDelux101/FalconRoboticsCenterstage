package org.firstinspires.ftc.teamcode.Utilities;

import com.acmerobotics.dashboard.config.Config;
@Config
public class Configuration {
    public static double TRACKWIDTH = 11.61695216;
    public static double CENTER_WHEEL_OFSET= 5.983669448819;

    //public static double LATERAL_DISTANCE = 11.61695216; // in; distance between the left and right wheels
    //public static double FORWARD_OFFSET = 5.983669448819; // in; offset of the lateral wheel (original : 6.25)
    public static double WHEEL_DIAMETER =  1.49606;
    public static double TICKS_PER_REV = 8192;
    public static double DISTANCE_PER_PULSE = Math.PI * Configuration.WHEEL_DIAMETER / Configuration.TICKS_PER_REV;
    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed
}
