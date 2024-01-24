package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

import java.io.File;

public class ClimbSubsystem extends FalconSubsystemBase {
    /**
     * This controls the left and right motors to climb in and climb out
     */

    public final DcMotorEx climbMotorLeft;
    public final DcMotorEx climbMotorRight;
    private final HardwareMap hardwareMap;
    private final File soundEffect = new File("/sdcard/FIRST/blocks/sound/the-eagle-has-landed-101681.mp3");

    public ClimbSubsystem(HardwareMap hMap, Telemetry tel) {
        super(tel);
        hardwareMap = hMap;
        climbMotorLeft = hMap.get(DcMotorEx.class,"Climb_Motor_Left");
        climbMotorRight = hMap.get(DcMotorEx.class, "Climb_Motor_Right");

        climbMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        climbMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        climbMotorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        climbMotorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /** tells the Climb motors to extend out
     *
     */
    public void ClimbOut() {
        if(soundEffect.exists()){
            SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, soundEffect);
        }
        runMotorsToPosition(Configuration.CLIMB_OUT);
    }

    /** tells the climb motors to retrack in
     *
     */
    public void ClimbIn() {
        runMotorsToPosition(Configuration.CLIMB_IN);
    }

    /**
     * calculates the position of left and right motors and updates it to telemetry and sends the climb to its target position
     * @return the position of left and right motors
     */

    public int[] getCurrentMotorPositions(){
        telemetry.addData(" Climb subsystem Left motor Position", climbMotorLeft.getCurrentPosition());
        telemetry.addData(" Climb subsystem Right motor position", climbMotorRight.getCurrentPosition());
        return new int[] {climbMotorLeft.getCurrentPosition(), climbMotorRight.getCurrentPosition()};
    }

    private void runMotorsToPosition(int position){
        climbMotorLeft.setTargetPosition(position);
        climbMotorRight.setTargetPosition(position);

        telemetry.addData("climb target position ;", position);
        climbMotorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        climbMotorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        climbMotorRight.setVelocity(1000);
        climbMotorLeft.setVelocity(1000);

//        climbMotorRight.setPower(1.0);
//        climbMotorLeft.setPower(1.0);
    }

}
