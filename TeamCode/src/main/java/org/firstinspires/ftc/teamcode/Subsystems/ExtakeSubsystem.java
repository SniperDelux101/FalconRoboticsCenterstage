package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class ExtakeSubsystem extends FalconSubsystemBase {

    private final SimpleServo r_Servo;
    private final CRServo pixelWheel;

    private final SimpleServo controlArm1;
    //private final  SimpleServo controlArm2;

    private final RevColorSensorV3 colorSensor1;
    private final RevColorSensorV3 colorSensor2;

    RevBlinkinLedDriver blinkinLedDriver;

    private int pixelCount;


    public ExtakeSubsystem(HardwareMap hMap, Telemetry tel) {
        super(tel);
        r_Servo = new SimpleServo(hMap, "Extake_Rotational_Servo", Configuration.R_SERVO_MIN, Configuration.R_SERVO_MAX);
        pixelWheel = hMap.get(CRServo.class, "Pixel_Wheel");
        controlArm1 = new SimpleServo(hMap, "Arm1_Servo", 0, 255);   //Configuration.E_MIN_ARM_ANGLE, Configuration.E_MAX_ARM_ANGLE);
        //controlArm2 = new SimpleServo(hMap, "Arm2_Servo", 0, 255); //Configuration.E_MIN_ARM_ANGLE, Configuration.E_MAX_ARM_ANGLE);
        colorSensor1 = hMap.get(RevColorSensorV3.class, "colorSensor1");
        colorSensor2 = hMap.get(RevColorSensorV3.class, "colorSensor2");
        blinkinLedDriver = hMap.get(RevBlinkinLedDriver.class, "blinkin");
        blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
    }

    @Override
    public void periodic() {
        pixelCount = detectPixelCount();
        detectPixel();
    }

    public int getPixelCount(){
        return pixelCount;
    }

    public void detectPixel () {
        pixelCount = detectPixelCount();

        switch (pixelCount){
            case 1:
                blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
                break;
            case 2:
                blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
                break;
            default:
                blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.WHITE);
        }
    }

    private int detectPixelCount(){
        telemetry.addData("Distance sensor1 ", "%.3f", colorSensor1.getDistance(DistanceUnit.MM));
        telemetry.addData("Sensor 2", "%.3f", colorSensor2.getDistance(DistanceUnit.MM));

        double sensor1Distance = colorSensor1.getDistance(DistanceUnit.MM);
        double sensor2Distance = colorSensor2.getDistance(DistanceUnit.MM);
        double distanceMax = 20.0;
        if (sensor1Distance > distanceMax && sensor2Distance > distanceMax) {
            return 0;
        } else if (sensor1Distance < 30.0 && sensor2Distance > 30.0) {
            return 1;
        } else if (sensor1Distance < distanceMax && sensor2Distance < distanceMax)
            return 2;
        else {
            return 0;
        }
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

    /**
    sets the positions for the arms and how far up and down it can go.
     */
    public void setControlArmPosition (double position){

        try {
            controlArm1.setPosition(position);
        }
        catch (Exception ex){
            telemetry.addData("Exception: ", ex.getMessage());
        }
        //controlArm2.setPosition(position);
        telemetry.addData(" Extake Control Arm 1 Position :", controlArm1.getPosition());
    }

    public double getControlArm1Position(){
        return controlArm1.getPosition();
    }
    //public double getControlArm2Position(){
        //return controlArm2.getPosition();
    //}

    /**
     *  tells the servos to direction of the wheels while intake and ejecting pixels.
     */
    public void pixelIntake(){
       pixelWheelDirection(DcMotorSimple.Direction.FORWARD);
       //detectPixel();
       telemetry.addData("Pixel Intake: ", pixelWheel.getPower());
       telemetry.addData("Pixel Intake: ", pixelWheel.getDirection());
    }
    public void pixelEject (){
        //detectPixel();
        pixelWheelDirection(DcMotorSimple.Direction.REVERSE);
        telemetry.addData("Pixel Eject: ", pixelWheel.getPower());
        telemetry.addData("Pixel Eject: ", pixelWheel.getDirection());
    }
    public void pixelStop(){
        pixelWheel.setPower(0.0);
    }
    private void pixelWheelDirection (DcMotorSimple.Direction direction){
        pixelWheel.setDirection(direction);
        pixelWheel.setPower(1.0);
    }

    public void ejectSinglePixel(){
        pixelCount = getPixelCount();
        if(pixelCount == 0)
            pixelStop();
        else{
            int newPixelCount = pixelCount--;
            while(newPixelCount != pixelCount){
                pixelEject();
                pixelCount = getPixelCount();
            }
            pixelStop();
        }
    }

    /**
     * Tells servos which direction to move.
     */
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
