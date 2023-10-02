package org.firstinspires.ftc.teamcode.Utilities;

public class Utility {
    public static double encoderTicksToInches(double ticks) {
        return Configuration.WHEEL_DIAMETER * Math.PI * Configuration.GEAR_RATIO * ticks / Configuration.TICKS_PER_REV;
    }
}
