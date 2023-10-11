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

        servoLeft.setInverted(false);
        servoRight.setInverted(true);
        servoRear.setInverted(false);

    }

    public void retract() {
        servoLeft.setPosition(Configuration.ODO_RETRACT_POSITION);
        servoRight.setPosition(Configuration.ODO_RETRACT_POSITION);
        servoRear.setPosition(Configuration.ODO_RETRACT_POSITION);
    }

    public void drop() {
        servoLeft.setPosition(Configuration.ODO_DROP_POSITION);
        servoRight.setPosition(Configuration.ODO_DROP_POSITION);
        servoRear.setPosition(Configuration.ODO_DROP_POSITION);
    }

}
