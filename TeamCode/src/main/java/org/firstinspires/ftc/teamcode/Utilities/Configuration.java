package org.firstinspires.ftc.teamcode.Utilities;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Config
public class Configuration {

    // TRACKWIDTH is the distance between mecanum wheels
    public static double TRACKWIDTH = 13.03142345;

    public static double FORWARD_OFFSET = -5.983669448819;
    public static double CENTER_WHEEL_OFSET= 5.983669448819;

    //public static double FORWARD_OFFSET = 5.983669448819; // in; offset of the lateral wheel (original : 6.25)

//    public static double WHEEL_DIAMETER =  1.49606;

//    public static double WHEEL_RADIUS = 1.49606;
    public static double WHEEL_RADIUS = 1.8898;
    public static double X_MULTIPLIER = 0.1359763488;
    public static double Y_MULTIPLIER = 0.136020665;
    public static double TICKS_PER_REV = 8192;
    public static double DISTANCE_PER_PULSE = Math.PI * Configuration.WHEEL_RADIUS / Configuration.TICKS_PER_REV;
    public static double GEAR_RATIO = 3.7; // output (wheel) speed / input (motor) speed [Should be 4.5 or 0.2222222]
    public static final double MAX_RPM = 1620;

    public static double LATERAL_LIMITER = 0.3;

    public static final double DrivePower = .75;

    /*
     * Set RUN_USING_ENCODER to true to enable built-in hub velocity control using drive encoders.
     * Set this flag to false if drive encoders are not present and an alternative localization
     * method is in use (e.g., tracking wheels).
     *
     * If using the built-in motor velocity PID, update MOTOR_VELO_PID with the tuned coefficients
     * from DriveVelocityPIDTuner.
     */
    public static final boolean RUN_USING_ENCODER = false;
    public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(25.29, 0, 5,
            getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));

    /*
     * These are the feedforward parameters used to model the drive motor behavior. If you are using
     * the built-in velocity PID, *these values are fine as is*. However, if you do not have drive
     * motor encoders or have elected not to use them for velocity control, these values should be
     * empirically tuned.
     */
    public static double kV = 0.0115;   //1.0 / rpmToVelocity(MAX_RPM);
    public static double kA = 0.005;
    public static double kStatic = 0.015;

    /*
     * These values are used to generate the trajectories for you robot. To ensure proper operation,
     * the constraints should never exceed ~80% of the robot's actual capabilities. While Road
     * Runner is designed to enable faster autonomous motion, it is a good idea for testing to start
     * small and gradually increase them later after everything is working. All distance units are
     * inches.
     */
    public static double MAX_VEL = 60;
    public static double MAX_ACCEL = 60;
    public static double MAX_ANG_VEL = Math.toRadians(240);
    public static double MAX_ANG_ACCEL = Math.toRadians(240);


    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    public static double rpmToVelocity(double rpm) {
        return rpm * GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS / 60.0;
    }

    public static double getMotorVelocityF(double ticksPerSecond) {
        // see https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx
        return 32767 / ticksPerSecond;
    }

    public static RevHubOrientationOnRobot.LogoFacingDirection LOGO_FACING_DIR = RevHubOrientationOnRobot.LogoFacingDirection.DOWN;
    public static RevHubOrientationOnRobot.UsbFacingDirection USB_FACING_DIR = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;

    //****************************************************************************************************************//
    //Servo Config Values For Odometry

    public static double ODO_MIN_ANGLE = 0;
    public static double ODO_MAX_ANGLE = 180;
    public static double LEFT_ODO_DROP_POSITION = 0.019999999999999858;
    public static double LEFT_ODO_RETRACT_POSITION = 0.3800000000000001;

    public static double REAR_ODO_DROP_POSITION = 0.6700000000000005;
    public static double REAR_ODO_RETRACT_POSITION = 7.289999999999889;

    public static double RIGHT_ODO_DROP_POSITION = 0.4600000000000004;
    public static double RIGHT_ODO_RETRACT_POSITION = 0.7200000000000005;

    public static double ODO_SERVO_MULTIPLIER = 0.01;

    //****************************************************************************************************************//
    //Intake Motor Values / Percentages

    public static double INTAKE_MOTOR_POWER = 0.10;

    public static boolean ODO_SERVO_TRUE = true;
    public static boolean ODO_SERVO_FALSE = false;
}
