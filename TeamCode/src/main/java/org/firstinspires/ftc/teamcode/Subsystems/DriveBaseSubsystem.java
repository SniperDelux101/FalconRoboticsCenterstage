package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.arcrobotics.ftclib.kinematics.Odometry;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveBaseSubsystem extends SubsystemBase {
    private final OdometrySubsystem odometrySubsystem;
    HolonomicOdometry holonomicOdometry;
    private final MecanumDrive m_drive;
    private final Motor leftFront, rightFront, leftRear, rightRear;
    private final MotorEx odo_left, odo_right, odo_rear;
    private final double WHEEL_DIAMETER;

    static final double TRACKWIDTH = 13.7;
    static final double TICKS_TO_INCHES = 15.3;
    static final double CENTER_WHEEL_OFSET= 2.4;


    public DriveBaseSubsystem(HardwareMap hMap) {
        leftFront = new Motor(hMap, "Left_Front_Wheel", Motor.GoBILDA.RPM_435);
        rightFront = new Motor(hMap, "Right_Front_Wheel", Motor.GoBILDA.RPM_435);
        leftRear =  new Motor(hMap, "Left_Rear_Wheel", Motor.GoBILDA.RPM_435);
        rightRear = new Motor(hMap, "Right_Rear_Wheel", Motor.GoBILDA.RPM_435);

        odo_left = new MotorEx(hMap, "Left_Front_Wheel");
        odo_right = new MotorEx(hMap, "Right_Front_Wheel");
        odo_rear = new MotorEx(hMap, "Left_Rear_Wheel");


        odo_left.setDistancePerPulse(TICKS_TO_INCHES);

        odo_right.setDistancePerPulse(TICKS_TO_INCHES);
        odo_rear.setDistancePerPulse(TICKS_TO_INCHES);

        holonomicOdometry = new HolonomicOdometry(odo_left::getDistance, odo_right::getDistance, odo_rear::getDistance, TRACKWIDTH, CENTER_WHEEL_OFSET);

        odometrySubsystem= new OdometrySubsystem(holonomicOdometry);


        m_drive = new MecanumDrive(true, leftFront, rightFront, leftRear, rightRear);

        WHEEL_DIAMETER = 2.7;
    }

    public void drive(double strafe, double forward, double rotate) {
        m_drive.driveRobotCentric(strafe, forward, rotate);
    }
    public Pose2d getPosition (){
        return odometrySubsystem.getPose();
    }

    public OdometrySubsystem getOdometrySubsystem() {
        return odometrySubsystem;
    }
}
