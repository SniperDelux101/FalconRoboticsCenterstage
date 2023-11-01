package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class ClimbSubsytem extends SubsystemBase {

    public final DcMotor climbMotorLeft;
    public final DcMotor climbMotorRight;

    public ClimbSubsytem(HardwareMap hMap) {
        climbMotorLeft = hMap.dcMotor.get("Climb_Motor_Left");
        climbMotorRight = hMap.dcMotor.get("Climb_Motor_Right");
    }

    /// tells the Climb motors to extend out
    public void ClimbOut() {
        climbMotorLeft.setTargetPosition(Configuration.CLIMB_OUT);
        climbMotorRight.setTargetPosition(Configuration.CLIMB_OUT);
    }

    /// tells the climb motors to retrack in
    public void ClimbIn() {
        climbMotorLeft.setTargetPosition(Configuration.CLIMB_IN);
        climbMotorRight.setTargetPosition(Configuration.CLIMB_IN);
    }

}
