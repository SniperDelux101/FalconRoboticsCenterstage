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

    private final SimpleServo controlArm1;
    private final  SimpleServo controlArm2;


    public ExtakeSubsystem(HardwareMap hMap) {
        r_Servo = new SimpleServo(hMap, "Extake_Rotational_Servo", Configuration.R_SERVO_MIN, Configuration.R_SERVO_MAX);
        pixelWheel = hMap.get(CRServo.class, "Pixel_Wheel");
        controlArm1 = new SimpleServo(hMap, "Arm1_Servo", 0, 255);   //Configuration.E_MIN_ARM_ANGLE, Configuration.E_MAX_ARM_ANGLE);
        controlArm2 = new SimpleServo(hMap, "Arm2_Servo", 0, 255); //Configuration.E_MIN_ARM_ANGLE, Configuration.E_MAX_ARM_ANGLE);
    }

    public void setControlArmToHighBoard (){
       setControlArmPosition(Configuration.BOARD_HIGH_POSITION);
    }
    public void setControlArmToMidBoard (){
        setControlArmPosition(Configuration.BOARD_MID_POSITION);
    }
    public void setControlArmToLowBoard (){
        setControlArmPosition(Configuration.BOARD_LOW_POSITION);
    }
    public void setControlArmToExchange(){
        setControlArmPosition(Configuration.PIXELBOXARM_EXCHANGE);
    }
    public void setControlArmToPrepExchange(){
        setControlArmPosition(Configuration.PIXELBOXARM_PREPEXCHANGE);
    }
    public void setControlArmToExtake(){ setControlArmPosition(Configuration.PIXELBOXARM_EXTAKE);}

    /*
    sets the positions for the arms and how far up and down it can go.
     */
    public void setControlArmPosition (double position){
        controlArm1.setPosition(position);
        controlArm2.setPosition(position);
    }

    public double getControlArm1Position(){
        return controlArm1.getPosition();
    }
    public double getControlArm2Position(){
        return controlArm2.getPosition();
    }

    /// tells the servos to direction of the wheels while intake and ejecting pixels.
    public void pixelIntake(){
       pixelWheelDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void pixelEject (){
        pixelWheelDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void pixelStop(){
        pixelWheel.setPower(0.0);
    }
    private void pixelWheelDirection (DcMotorSimple.Direction direction){
        pixelWheel.setDirection(direction);
        pixelWheel.setPower(1.0);

    }

    ///Tells servos which direction to move.
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
