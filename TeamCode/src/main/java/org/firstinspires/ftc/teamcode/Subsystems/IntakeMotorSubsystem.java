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

    /// it tells the intake motor to run forward
    public void forward() {
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
        intakeMotor.set(Configuration.INTAKE_MOTOR_POWER);
    }
    /// it tells the intake motor to run in reverse
    public void reverse() {
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
        intakeMotor.set(-Configuration.INTAKE_MOTOR_POWER);
    }
    public void stop() {
        intakeMotor.stopMotor();
    }

}
