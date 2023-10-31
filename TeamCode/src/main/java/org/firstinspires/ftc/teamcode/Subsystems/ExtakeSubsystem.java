package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class ExtakeSubsystem extends SubsystemBase {

    private final SimpleServo r_Servo;
    private final CRServo pixelWheel;

    public ExtakeSubsystem(HardwareMap hMap) {
        r_Servo = new SimpleServo(hMap, "Extake_Rotational_Servo", Configuration.R_SERVO_MIN, Configuration.R_SERVO_MAX);
        pixelWheel = hMap.get(CRServo.class, "pixelwheel");
    }

    public void pixelIntake(){
       pixelWheelDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void pixelEject (){
        pixelWheelDirection(DcMotorSimple.Direction.FORWARD);
    }
    private void pixelWheelDirection (DcMotorSimple.Direction direction){
        pixelWheel.setDirection(direction);
        pixelWheel.setPower(1.0);

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
