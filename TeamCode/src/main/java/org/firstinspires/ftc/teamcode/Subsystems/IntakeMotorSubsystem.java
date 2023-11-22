package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class IntakeMotorSubsystem extends FalconSubsystemBase {

    private final Motor intakeMotor;

    public IntakeMotorSubsystem(HardwareMap hMap, Telemetry tel) {
        super(tel);
        intakeMotor = new Motor(hMap, "Intake_Motor");
    }

    /**
     * it tells the intake motor to run forward and eject the pixel
     */
    public void eject() {
      eject(Configuration.INTAKE_MOTOR_POWER);
    }
    public void eject(double power) {
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
        intakeMotor.set(power);
        telemetry.addData("intake motor power (ejection)", power);
    }

    /**
     * it tells the intake motor to run in reverse and intakes the pixel
     */
    public void intake() {
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
        intakeMotor.set(-Configuration.INTAKE_MOTOR_POWER);
    }
    public void stop() {
        intakeMotor.stopMotor();
    }

}
