package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class IntakeMotorSubsystem extends SubsystemBase {

    private final Motor intakeMotor;

    public IntakeMotorSubsystem(HardwareMap hMap) {
        intakeMotor = new Motor(hMap, "Intake_Motor");
    }

    public void forward() {
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
        intakeMotor.set(Configuration.INTAKE_MOTOR_POWER);
    }
    public void reverse() {
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
        intakeMotor.set(Configuration.INTAKE_MOTOR_POWER);
        intakeMotor.setInverted(true);
    }
    public void stop() {
        intakeMotor.stopMotor();
    }

}
