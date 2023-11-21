package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class ClimbSubsystem extends FalconSubsystemBase {

    public final DcMotor climbMotorLeft;
    public final DcMotor climbMotorRight;

    public ClimbSubsystem(HardwareMap hMap, Telemetry tel) {
        super(tel);
        climbMotorLeft = hMap.dcMotor.get("Climb_Motor_Left");
        climbMotorRight = hMap.dcMotor.get("Climb_Motor_Right");

        climbMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        climbMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /// tells the Climb motors to extend out
    public void ClimbOut() {
        runMotorsToPosition(Configuration.CLIMB_OUT);
    }

    /// tells the climb motors to retrack in
    public void ClimbIn() {
        runMotorsToPosition(Configuration.CLIMB_IN);
    }

    public int[] getCurrentMotorPositions(){
        telemetry.addData(" Left motor Position", climbMotorLeft.getCurrentPosition());
        telemetry.addData("Right motor position", climbMotorRight.getCurrentPosition());
        return new int[] {climbMotorLeft.getCurrentPosition(), climbMotorRight.getCurrentPosition()};
    }

    private void runMotorsToPosition(int position){
        climbMotorLeft.setTargetPosition(position);
        climbMotorRight.setTargetPosition(position);

        telemetry.addData("climb target position ;", position);
        climbMotorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        climbMotorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        climbMotorRight.setPower(1.0);
        climbMotorLeft.setPower(1.0);
    }

}
