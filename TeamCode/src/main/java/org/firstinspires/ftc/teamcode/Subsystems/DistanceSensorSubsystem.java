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
        backLeft = map.get(Rev2mDistanceSensor.class, "Left_Rear_DSensor");
        backRight = map.get(Rev2mDistanceSensor.class, "Right_Rear_DSensor");
        left = map.get(Rev2mDistanceSensor.class, "Left_Front_DSensor");
        right = map.get(Rev2mDistanceSensor.class, "Right_Front_DSensor");
    }

    public double getBackAverageDistance(){
       return (getBackLeft() + getBackRight())/2.0;
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
