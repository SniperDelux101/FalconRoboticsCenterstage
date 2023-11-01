package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class LinearSlideSubsystem extends SubsystemBase {

    private final DcMotor linearSlideMotor;
    public LinearSlideSubsystem(HardwareMap hMap) {
        linearSlideMotor = hMap.dcMotor.get("TestMotor");
    }


    public void LinearStop() {
        linearSlideMotor.setPower(0);
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

    public void runToPosition(int linearSlidePosTransfer) {
        linearSlideMotor.setTargetPosition(linearSlidePosTransfer);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideMotor.setPower(Configuration.LINEAR_SLIDE_POWER);
    }

}