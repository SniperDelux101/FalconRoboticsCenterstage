package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class ClimbSubsytem extends SubsystemBase {

    public final DcMotor climbMotor;

    public ClimbSubsytem(HardwareMap hMap) {
        climbMotor = hMap.dcMotor.get("Climb_Motor");
    }

    public void ClimbOut() {
        climbMotor.setTargetPosition(Configuration.CLIMB_OUT);
    }

    public void ClimbIn() {
        climbMotor.setTargetPosition(Configuration.CLIMB_IN);
    }

}
