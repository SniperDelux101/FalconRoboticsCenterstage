package org.firstinspires.ftc.teamcode.OpModeTests.RoadRunnerTests;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator;
import org.firstinspires.ftc.teamcode.Utilities.Encoder;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

import java.util.Arrays;
import java.util.List;

/*
 * Sample tracking wheel localizer implementation assuming the standard configuration:
 *
 *    /--------------\
 *    |     ____     |
 *    |     ----     |
 *    | ||        || |
 *    | ||        || |
 *    |              |
 *    |              |
 *    \--------------/
 *
 */
@Config
public class StandardTrackingWheelLocalizer extends ThreeTrackingWheelLocalizer {
//    public static double TICKS_PER_REV = 8192;
//    public static double WHEEL_RADIUS = 1.49606; // in
//    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed
//
//    public static double LATERAL_DISTANCE = 11.61695216; // in; distance between the left and right wheels
//    public static double FORWARD_OFFSET = 5.983669448819; // in; offset of the lateral wheel

    private Encoder leftEncoder, rightEncoder, rearEncoder;

    private List<Integer> lastEncPositions, lastEncVels;

    public StandardTrackingWheelLocalizer(HardwareMap hardwareMap, List<Integer> lastTrackingEncPositions, List<Integer> lastTrackingEncVels) {
        super(Arrays.asList(
                new Pose2d(Configuration.ODO_PARALLEL_WHEELS_OFFSET, Configuration.LATERAL_DISTANCE / 2, 0), // left
                new Pose2d(Configuration.ODO_PARALLEL_WHEELS_OFFSET, -Configuration.LATERAL_DISTANCE / 2, 0), // right
                new Pose2d(Configuration.FORWARD_OFFSET, Configuration.FORWARD_OFFSET_Y, Math.toRadians(90)) // rear
        ));

        lastEncPositions = lastTrackingEncPositions;
        lastEncVels = lastTrackingEncVels;

        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "Left_Front_Wheel"));
        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "Right_Front_Wheel"));
        rearEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "Left_Rear_Wheel"));

//        rearEncoder.setDirection(Encoder.Direction.REVERSE);

        // TODO: reverse any encoders using Encoder.setDirection(Encoder.Direction.REVERSE)
    }

    public static double encoderTicksToInches(double ticks) {
        return Configuration.WHEEL_RADIUS * 2 * Math.PI * Configuration.GEAR_RATIO * ticks / Configuration.TICKS_PER_REV;
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        int leftPos = leftEncoder.getCurrentPosition();
        int rightPos = rightEncoder.getCurrentPosition();
        int frontPos = rearEncoder.getCurrentPosition();

        lastEncPositions.clear();
        lastEncPositions.add(leftPos);
        lastEncPositions.add(rightPos);
        lastEncPositions.add(frontPos);

        return Arrays.asList(
                encoderTicksToInches(leftPos)*Configuration.X_MULTIPLIER,
                encoderTicksToInches(rightPos)*Configuration.X_MULTIPLIER,
                encoderTicksToInches(frontPos)*Configuration.Y_MULTIPLIER
        );
    }

    @NonNull
    @Override
    public List<Double> getWheelVelocities() {
        int leftVel = (int) leftEncoder.getCorrectedVelocity();
        int rightVel = (int) rightEncoder.getCorrectedVelocity();
        int frontVel = (int) rearEncoder.getCorrectedVelocity();

        lastEncVels.clear();
        lastEncVels.add(leftVel);
        lastEncVels.add(rightVel);
        lastEncVels.add(frontVel);

        return Arrays.asList(
                encoderTicksToInches(leftVel)*Configuration.X_MULTIPLIER,
                encoderTicksToInches(rightVel)*Configuration.X_MULTIPLIER,
                encoderTicksToInches(frontVel)*Configuration.Y_MULTIPLIER
        );
    }

}