package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class LinearSlideSubsystem extends SubsystemBase {

    private final MotorEx linearSlideMotor;
    public LinearSlideSubsystem(HardwareMap hMap) {
        linearSlideMotor = new MotorEx(hMap,"Linear_Slide_Motor");
        linearSlideMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        linearSlideMotor.setRunMode(Motor.RunMode.PositionControl);
    }
    public void LinearStop() {
        linearSlideMotor.stopMotor();
    }

    public double LinearCurPos() {
        return linearSlideMotor.getCurrentPosition();
    }

    public void LinearPosHome() {
        runToPosition(Configuration.LINEAR_SLIDE_POS_HOME);
    }
    public void LinearPosLo() {
        runToPosition(Configuration.LINEAR_SLIDE_POS_LO);
    }
    public void LinearPosMed() {
        runToPosition(Configuration.LINEAR_SLIDE_POS_MED);
    }
    public void LinearPosHi() {
        runToPosition(Configuration.LINEAR_SLIDE_POS_HI);
    }
    public void LinearPosTransfer() {
        runToPosition(Configuration.LINEAR_SLIDE_POS_TRANSFER);
    }

    public void runToPosition(int linearSlidePosTransfer){
        runToPosition(linearSlidePosTransfer, false);
    }

    public void runToPosition(int linearSlidePos, boolean runSynchronous){
        linearSlideMotor.setRunMode(Motor.RunMode.PositionControl);
        linearSlideMotor.setTargetPosition(linearSlidePos);
        linearSlideMotor.set(.5);
        if(runSynchronous){
            while(LinearCurPos() < linearSlidePos){}
            stop();
        }
    }

    public boolean isSlideAtTargetPosition(){
        return linearSlideMotor.atTargetPosition();
    }

    public void stop() {
        linearSlideMotor.stopMotor();
    }
}