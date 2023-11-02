package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class AirplaneLauncherSubsystem extends SubsystemBase {

    private final SimpleServo launchServo;

    public AirplaneLauncherSubsystem(HardwareMap hMap) {

        launchServo = new SimpleServo(hMap, "Launching_Servo", Configuration.LAUNCHING_MIN, Configuration.LAUNCHING_MAX);

        // TODO Change this value if the servo needs to change its direction
        // launchServo.setInverted(true);
    }

    public void stop(){
        launchServo.disable();
    }

    public void release() {
        launchServo.setPosition(Configuration.LAUNCH_RELEASE);
    }
    public double getLanucherServoPosition(){
        return launchServo.getPosition();
    }

    public void retract() {
        launchServo.setPosition(Configuration.LAUNCH_RETRACT);
    }


}
