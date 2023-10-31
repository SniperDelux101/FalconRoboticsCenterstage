package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class OdometryControlSubsystem extends SubsystemBase {


    private final SimpleServo servoLeft, servoRight, servoRear;

    public OdometryControlSubsystem(HardwareMap hMap) {

        servoLeft = new SimpleServo(hMap, "Left_Odometry_Servo", Configuration.ODO_MIN_ANGLE, Configuration.ODO_MAX_ANGLE);

        servoRight = new SimpleServo(hMap, "Right_Odometry_Servo", Configuration.ODO_MIN_ANGLE, Configuration.ODO_MAX_ANGLE);

        servoRear = new SimpleServo(hMap, "Rear_Odometry_Servo", Configuration.ODO_MIN_ANGLE, Configuration.ODO_MAX_ANGLE);

        servoLeft.setInverted(Configuration.ODO_SERVO_FALSE);
        servoRight.setInverted(Configuration.ODO_SERVO_TRUE);
        servoRear.setInverted(Configuration.ODO_SERVO_FALSE);

    }

    public void dropLeft() {
        servoLeft.setPosition(Configuration.LEFT_ODO_DROP_POSITION);
    }

    public void dropRight() {
        servoRight.setPosition(Configuration.RIGHT_ODO_DROP_POSITION);
    }

    public void dropRear() {
        servoRear.setPosition(Configuration.REAR_ODO_DROP_POSITION);
    }

    public void retractLeft() {
        servoLeft.setPosition(Configuration.LEFT_ODO_RETRACT_POSITION);
    }

    public void retractRight() {
        servoRight.setPosition(Configuration.RIGHT_ODO_RETRACT_POSITION);
    }

    public void retractRear() {
        servoRear.setPosition(Configuration.REAR_ODO_RETRACT_POSITION);
    }

    public void drop() {
        servoLeft.setPosition(Configuration.LEFT_ODO_DROP_POSITION);
        servoRight.setPosition(Configuration.RIGHT_ODO_DROP_POSITION);
        servoRear.setPosition(Configuration.REAR_ODO_DROP_POSITION);
    }

    public void retract() {
        servoLeft.setPosition(Configuration.LEFT_ODO_RETRACT_POSITION);
        servoRight.setPosition(Configuration.RIGHT_ODO_RETRACT_POSITION);
        servoRear.setPosition(Configuration.REAR_ODO_RETRACT_POSITION);
    }

}
