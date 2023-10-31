package org.firstinspires.ftc.teamcode.Subsystems;

import android.graphics.Bitmap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class RotationalExtakeSubsystem extends SubsystemBase {

    private final SimpleServo r_Servo;

    public RotationalExtakeSubsystem(HardwareMap hMap) {
        r_Servo = new SimpleServo(hMap, "Extake_Rotational_Servo", Configuration.R_SERVO_MIN, Configuration.R_SERVO_MAX);
    }

    public void leftRotation() {
        r_Servo.setPosition(Configuration.R_SERVO_LEFT);
    }

    public void centerRotation() {
        r_Servo.setPosition(Configuration.R_SERVO_CENTER);
    }

    public void rightRotation() {
        r_Servo.setPosition(Configuration.R_SERVO_RIGHT);
    }

}
