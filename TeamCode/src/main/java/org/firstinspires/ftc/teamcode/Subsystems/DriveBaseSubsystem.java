package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.kinematics.Odometry;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveBaseSubsystem extends SubsystemBase {

    private final MecanumDrive m_drive;
    private final Motor leftFront, rightFront, leftRear, rightRear;
//    private final Odometry odo_left, odo_right, odo_rear;

    private final double WHEEL_DIAMETER;

    public DriveBaseSubsystem(HardwareMap hMap) {
        leftFront = new Motor(hMap, "Left_Front_Wheel", Motor.GoBILDA.RPM_435);
        rightFront = new Motor(hMap, "Right_Front_Wheel", Motor.GoBILDA.RPM_435);
        leftRear =  new Motor(hMap, "Left_Rear_Wheel", Motor.GoBILDA.RPM_435);
        rightRear = new Motor(hMap, "Right_Rear_Wheel", Motor.GoBILDA.RPM_435);

        m_drive = new MecanumDrive(true, leftFront, rightFront, leftRear, rightRear);

        WHEEL_DIAMETER = 2.7;
    }

    public void drive(double strafe, double forward, double rotate) {
        m_drive.driveRobotCentric(strafe, forward, rotate);
    }
}
