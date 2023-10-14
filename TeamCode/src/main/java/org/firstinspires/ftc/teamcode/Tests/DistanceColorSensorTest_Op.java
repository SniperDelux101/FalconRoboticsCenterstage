package org.firstinspires.ftc.teamcode.Tests;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Distance and Color Sensor Test", group = "test")
public class DistanceColorSensorTest_Op extends OpMode {

    DistanceSensor distanceSensor;
    Rev2mDistanceSensor sensorTimeOfFlight;
    ColorSensor colorSensor;

    @Override
    public void init() {
        distanceSensor = hardwareMap.get(DistanceSensor.class, "DistanceSensor");
        sensorTimeOfFlight = (Rev2mDistanceSensor) distanceSensor;

        telemetry = new MultipleTelemetry(telemetry);
        telemetry.addData(">>", "Press start to continue");
        telemetry.update();
    }

    @Override
    public void loop() {
        telemetry.addData("deviceName", distanceSensor.getDeviceName() );
        telemetry.addData("range", String.format("%.01f mm", distanceSensor.getDistance(DistanceUnit.MM)));
        telemetry.addData("range", String.format("%.01f cm", distanceSensor.getDistance(DistanceUnit.CM)));
        telemetry.addData("range", String.format("%.01f m", distanceSensor.getDistance(DistanceUnit.METER)));
        telemetry.addData("range", String.format("%.01f in", distanceSensor.getDistance(DistanceUnit.INCH)));

        // Rev2mDistanceSensor specific methods.
        telemetry.addData("ID", String.format("%x", sensorTimeOfFlight.getModelID()));
        telemetry.addData("did time out", Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()));

        telemetry.update();
    }
}
