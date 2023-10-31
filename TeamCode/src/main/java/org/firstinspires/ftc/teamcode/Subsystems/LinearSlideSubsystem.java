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
        linearSlideMotor.setTargetPosition(Configuration.LINEAR_SLIDE_POS_HOME);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideMotor.setPower(Configuration.LINEAR_SLIDE_POWER);
    }
    public void LinearPosLo() {
        linearSlideMotor.setTargetPosition(Configuration.LINEAR_SLIDE_POS_LO);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideMotor.setPower(Configuration.LINEAR_SLIDE_POWER);
    }
    public void LinearPosMed() {
        linearSlideMotor.setTargetPosition(Configuration.LINEAR_SLIDE_POS_MED);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideMotor.setPower(Configuration.LINEAR_SLIDE_POWER);
    }
    public void LinearPosHi() {
        linearSlideMotor.setTargetPosition(Configuration.LINEAR_SLIDE_POS_HI);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideMotor.setPower(Configuration.LINEAR_SLIDE_POWER);
    }
    public void LinearPosTransfer() {
        linearSlideMotor.setTargetPosition(Configuration.LINEAR_SLIDE_POS_TRANSFER);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideMotor.setPower(Configuration.LINEAR_SLIDE_POWER);
    }

}