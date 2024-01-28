package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class LinearSlideSubsystem extends FalconSubsystemBase {
    /**
     * sets the linear slide to fo to its diffirent positions
     *
     */

    private final MotorEx linearSlideMotor;
    public LinearSlideSubsystem(HardwareMap hMap, Telemetry tel) {
        super(tel);
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
        linearSlideMotor.set(1.0);
        telemetry.addData("Linear Slide Position ; ", linearSlidePos);
        if(runSynchronous){
            while(LinearCurPos() < linearSlidePos){}
            stop();
        }
    }

    public void runPlusPosition(int slideAddition, boolean runSynchronous) {
        int newPos = getCurrentPosition() + slideAddition;
        linearSlideMotor.setRunMode(Motor.RunMode.PositionControl);
        linearSlideMotor.setTargetPosition(newPos);
        linearSlideMotor.set(1.0);
        if(runSynchronous) {
            while(LinearCurPos() < newPos){}
            stop();
        }
    }

    public void moveSlideUp() {
        int currentPos = getCurrentPosition();
        int targetPos = currentPos + Configuration.LINEAR_SLIDE_TELEOP_MULTIPLIER;
        runToTargetPosition(targetPos);
    }

    public void moveSlideDown() {
        int currentPos = getCurrentPosition();
        int targetPos = currentPos - Configuration.LINEAR_SLIDE_TELEOP_MULTIPLIER;
        runToTargetPosition(targetPos);
    }

    private int checkTargetPosition(int targetPosition) {
        if(targetPosition < Configuration.LINEAR_SLIDE_POS_HI) {
            targetPosition = Configuration.LINEAR_SLIDE_POS_HI;
        } else if (targetPosition > Configuration.LINEAR_SLIDE_POS_LO) {
            targetPosition = Configuration.LINEAR_SLIDE_POS_HOME;
        }
        return targetPosition;
    }

    private double getMultiplier(int targetPosition) {
        double dPos = 0.0;
        if(targetPosition > getCurrentPosition()) {
            dPos = Math.abs(getCurrentPosition()) / Math.abs(targetPosition);
        } else {
            dPos = Math.abs(getCurrentPosition()) / Math.abs(targetPosition);
        }
        dPos = Range.clip(dPos, .1, 1.0);
        telemetry.addData("Multiplier: ", dPos);
        return dPos;
    }

    private double getTolerance(int targetPosition) {
        telemetry.addData("Tolerance: ", Math.abs(targetPosition-getCurrentPosition()) * 0.1);
        return Math.abs(targetPosition-getCurrentPosition()) * 0.1;
    }

    public void runToTargetPosition(int targetPosition) {
        targetPosition = checkTargetPosition(targetPosition);
        linearSlideMotor.setRunMode(Motor.RunMode.PositionControl);
        linearSlideMotor.setPositionCoefficient(0.05);
        linearSlideMotor.setTargetPosition(targetPosition);
        linearSlideMotor.set(0);
        linearSlideMotor.setPositionTolerance(getTolerance(targetPosition));
        int loopCount = 0;
        while(!linearSlideMotor.atTargetPosition() && loopCount < 100) {
            linearSlideMotor.set(getMultiplier(targetPosition));
            telemetry.addData("Running to Target Pos : ", targetPosition);
            telemetry.addData("Current Pos : ", getCurrentPosition());
            telemetry.addData("Multiplier : ", getMultiplier(targetPosition));
            telemetry.update();
            loopCount++;
        }
        linearSlideMotor.stopMotor();
    }

    public void runByPower(double yValue) {
        if (yValue < .05 && yValue > -0.05) {
            yValue = 0;
        }
        linearSlideMotor.setRunMode(Motor.RunMode.RawPower);
        linearSlideMotor.set(yValue);
    }



    public boolean isSlideAtTargetPosition(){

        boolean flag = false;
        try{
            flag = linearSlideMotor.atTargetPosition();
        }
        catch (Exception ex){
            telemetry.addData("Exception: ", ex.getMessage());
        }

        return flag;
    }

    public int getCurrentPosition(){
        return linearSlideMotor.getCurrentPosition();
    }

    public void stop() {

        linearSlideMotor.stopMotor();
    }

    public void resetEncoder() {
        linearSlideMotor.resetEncoder();
    }
}