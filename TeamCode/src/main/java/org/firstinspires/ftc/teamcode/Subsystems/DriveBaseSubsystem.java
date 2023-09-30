package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public class DriveBaseSubsystem extends SubsystemBase {
    private final OdometrySubsystem odometrySubsystem;
    HolonomicOdometry holonomicOdometry;
    private final MecanumDrive m_drive;
    private final Motor leftFront, rightFront, leftRear, rightRear;
    //private final MotorEx odo_left, odo_right, odo_rear;
    private Motor.Encoder odo_left, odo_right, odo_rear;


    public DriveBaseSubsystem(HardwareMap hMap) {
        leftFront = new Motor(hMap, "Left_Front_Wheel", Motor.GoBILDA.RPM_435);
        rightFront = new Motor(hMap, "Right_Front_Wheel", Motor.GoBILDA.RPM_435);
        leftRear =  new Motor(hMap, "Left_Rear_Wheel", Motor.GoBILDA.RPM_435);
        rightRear = new Motor(hMap, "Right_Rear_Wheel", Motor.GoBILDA.RPM_435);

        odo_left = leftFront.encoder.setDistancePerPulse(Configuration.DISTANCE_PER_PULSE);
        odo_right = rightFront.encoder.setDistancePerPulse(Configuration.DISTANCE_PER_PULSE);
        odo_rear = leftRear.encoder.setDistancePerPulse(Configuration.DISTANCE_PER_PULSE);

        odo_right.setDirection(Motor.Direction.REVERSE);

        holonomicOdometry = new HolonomicOdometry(
                odo_left::getDistance,
                odo_right::getDistance,
                odo_rear::getDistance,
                Configuration.TRACKWIDTH,
                Configuration.CENTER_WHEEL_OFSET);

        odometrySubsystem= new OdometrySubsystem(holonomicOdometry);

        m_drive = new MecanumDrive(true, leftFront, rightFront, leftRear, rightRear);
    }

    public void drive(double strafe, double forward, double rotate) {
        m_drive.driveRobotCentric(strafe, forward, rotate);
    }
    public Pose2d getPosition (){
        return odometrySubsystem.getPose();
    }
    public void updatePosition(){
        odometrySubsystem.update();;
    }
    public void resetEncoders(){
        odo_right.reset();
        odo_left.reset();
        odo_rear.reset();
    }
    public double getOdometryDistance(){
           return (odo_left.getDistance()+odo_right.getDistance())/2;
    }

    public double getAverageEncoderDistance() {
        return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0;
    }

    public double getLeftEncoderDistance() {
        return odo_left.getDistance();
    }

    public double getRightEncoderDistance() {
        return odo_right.getDistance();
    }

}
