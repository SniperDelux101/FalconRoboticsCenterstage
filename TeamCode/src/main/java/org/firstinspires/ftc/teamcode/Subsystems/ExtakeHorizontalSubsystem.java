package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class ExtakeHorizontalSubsystem extends SubsystemBase {

    private final ServoEx horizontalServo = new SimpleServo(
            hardwareMap, "Horizontal_Servo", Configuration.H_SERVO_MIN,Configuration.H_SERVO_MAX);

    

}
