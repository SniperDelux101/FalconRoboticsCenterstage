package org.firstinspires.ftc.teamcode.Utilities;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class Configuration {

    public static int APRIL_TAG_CAMERA_GAIN = 15;
    public static int APRIL_TAG_CAMERA_EXPOSURE = 5;
    // TRACKWIDTH is the distance between mecanum wheels
    public static double TRACKWIDTH = DistanceUnit.INCH.fromMm(403.352); //15.88 inches
    public static double WHEELBASE = DistanceUnit.INCH.fromMm(312);
    public static double FORWARD_OFFSET = DistanceUnit.INCH.fromMm(-151.2); //-5.00; //-5.983669448819
    public static double FORWARD_OFFSET_Y = DistanceUnit.INCH.fromMm(15.429);
    public static double LATERAL_DISTANCE = DistanceUnit.INCH.fromMm(268.7336781);   //10.32263261; 10.580066066788557
    public static double ODO_PARALLEL_WHEELS_OFFSET = DistanceUnit.INCH.fromMm(-23.091);
    public static double LATERAL_MULTIPLIER = 3.57142857;
    public static double WHEEL_RADIUS = 0.686;
    public static double X_MULTIPLIER = 0.3732700064;
    public static double Y_MULTIPLIER = 0.3687530368;
    public static double TICKS_PER_REV = 8192;
    //public static double DISTANCE_PER_PULSE = Math.PI * Configuration.WHEEL_RADIUS / Configuration.TICKS_PER_REV;
    public static double GEAR_RATIO = 3.0; // output (wheel) speed / input (motor) speed [Should be 4.5 or 0.2222222]
    public static double MAX_RPM = 1620;

    public static double LATERAL_LIMITER = .3;

    public static double DrivePower = .75;

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
    public static double kV = 0.01;   //1.0 / rpmToVelocity(MAX_RPM);
    public static double kA = 0.0055;
    public static double kStatic = 0.008;

    /*
     * These values are used to generate the trajectories for you robot. To ensure proper operation,
     * the constraints should never exceed ~80% of the robot's actual capabilities. While Road
     * Runner is designed to enable faster autonomous motion, it is a good idea for testing to start
     * small and gradually increase them later after everything is working. All distance units are
     * inches.
     */
    public static double MAX_VEL = 66.3358551600406; //0.07703451419276926
    public static double MAX_ACCEL = 60;
    public static double MAX_ANG_VEL = Math.toRadians(314.0718057893606); // Or the value should be 5.4815870987085304
    public static double MAX_ANG_ACCEL = Math.toRadians(54.98425989336563);   //sh


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

    public static double REAR_ODO_DROP_POSITION = 0.7400000000000005;
    public static double REAR_ODO_RETRACT_POSITION = 7.289999999999889;

    public static double RIGHT_ODO_DROP_POSITION = 0.46000000000000004;
    public static double RIGHT_ODO_RETRACT_POSITION = 0.7200000000000005;

    public static double ODO_SERVO_MULTIPLIER = 0.01;

    //****************************************************************************************************************//
    //Intake Motor Values / Percentages
    public static double INTAKE_MOTOR_POWER = 1.0;

    //****************************************************************************************************************//
    //Linear Slide Motor Values / Percentages

    //public static double LINEAR_SLIDE_POWER = 0.75;


    public static int LINEAR_SLIDE_POS_HOME = 70;
    public static int LINEAR_SLIDE_POS_LO = -1900;
    public static int LINEAR_SLIDE_POS_MED = -3700;
    public static int LINEAR_SLIDE_POS_HI = -4200;
    public static int LINEAR_SLIDE_POS_TRANSFER = -150;
    public static int LINEAR_SLIDE_POS_AUTO = -2300;
    public static int LINEAR_SLIDE_MULTIPLIER = 10;
    public static int TICK_RANGE = 50;

    //****************************************************************************************************************//
    // Tuning values for odometry servos

    public static boolean ODO_SERVO_TRUE = true;
    public static boolean ODO_SERVO_FALSE = false;

    //****************************************************************************************************************//
    // Tuning Values for BackAndForth

    public static double OMEGA_WEIGHT = 0.1;
    public static double VX_WEIGHT = 0.1;
    public static double VY_WEIGHT = 0.1;

    //****************************************************************************************************************//
    // Values for Climb Mechanism

    public static int CLIMB_OUT = 9500;
    public static int CLIMB_IN = 0;
    public static int CLIMB_MULTIPLIER = 10;


    //****************************************************************************************************************//
    // Values for horizontal extake servo

    public static double R_SERVO_MIN = 0;
    public static double R_SERVO_MAX = 1;
    public static double R_SERVO_LEFT = .09;
    public static double R_SERVO_CENTER = .3349;
    public static double R_SERVO_RIGHT = .57;
    public static double R_SERVO_MULTIPLIER = .005;

    //****************************************************************************************************************//
    // Values for horizontal extake servo

    //public static double H_SERVO_MIN = 0;
    //public static double H_SERVO_MAX = 90;

    public static double E_MIN_ARM_ANGLE = 0.0;
    public static double E_MAX_ARM_ANGLE = 255.0;

    public static double BOARD_HIGH_POSITION= .2;
    public static double BOARD_MID_POSITION= .2;
    public static double BOARD_LOW_POSITION=.2;
    public static double PIXELBOXARM_PREPEXCHANGE = 1.0;
    public static double PIXELBOXARM_EXCHANGE = .85;
    public static double PIXELBOXARM_EXTAKE = 0.0;

    //****************************************************************************************************************//
    // Values for launching servo

    public static double LAUNCHING_MIN = 0;
    public static double LAUNCHING_MAX = 90;
    public static double LAUNCH_RELEASE = -.5;
    public static double LAUNCH_RETRACT = 0;

    //************************************************************
    // Confidence configuration for the vision
    public static double CONFIDENCE_SCORE = .55;
    public static int LEFT_UPPER_BOUND_1 = 550;
    public static int RIGHT_LOWER_BOUND_1 = 1050;
    public static double TEAM_PROP_WIDTH = 3.182;
    public static double FOCAL_LENGTH = 543.45;

    public static String TENSOR_FILE = "Green_1062024.tflite";

    //*****************************************************************
    //Camera Calibration

    public static int CAMERA_WIDTH = 1600;
    public static int CAMERA_HEIGHT = 896;
    //public static int CAMERA_ZOOM = 1;


    //*****************************************************************
    public static int AUTO_VEL = 30;
    public static int VISION_VEL = 10;
    public static int STRAFE_TO_APRIL_TAG_VEL = 7;

    //*****************************************************************
    public static double BACKDROP_ERROR_DISTANCE = 1.5; //inches
    public static double BACKDROP_DISTANCE = 5.0;

    public static double SHAKE_DEGREES = 5;
    public static double VISION_STRAFE_DIS = 20;

    public static double APRIL_TAG_BEARING = 7.0;
    public static double APRIL_TAG_YAW = 2.0;
    public static double MAX_DISTANCE = 36;
    public static double DRIVE_FORWARD_SPEED = .05;
    public static double DRIVE_FORWARD_POWER = 5;
    public static double AUTO_STRAFE_DISTANCE = 3.0;
    public static double AUTO_APRILTAG_STRAFE_OFFSET = 0.0;
}
