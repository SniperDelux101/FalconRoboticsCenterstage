package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensorSubsystem extends FalconSubsystemBase {
    private final DistanceSensor backLeft, backRight, left, right;
    private final DistanceUnit distanceUnit;

    public DistanceSensorSubsystem(HardwareMap map, Telemetry tel){
        this(map, tel, DistanceUnit.INCH);
    }
    public DistanceSensorSubsystem(HardwareMap map, Telemetry tel, DistanceUnit unit){
        super(tel);

        distanceUnit = unit;
        backLeft = map.get(Rev2mDistanceSensor.class, "backLeft");
        backRight = map.get(Rev2mDistanceSensor.class, "backRight");
        left = map.get(Rev2mDistanceSensor.class, "left");
        right = map.get(Rev2mDistanceSensor.class, "right");
    }

    public double getBackLeft(){
        return backLeft.getDistance(distanceUnit);
    }
    public double getBackRight(){
        return backRight.getDistance(distanceUnit);
    }
    public double getLeft(){
        return left.getDistance(distanceUnit);
    }
    public double getRight(){
        return right.getDistance(distanceUnit);
    }
}
