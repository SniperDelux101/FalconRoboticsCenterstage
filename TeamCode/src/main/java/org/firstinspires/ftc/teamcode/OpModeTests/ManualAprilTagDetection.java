package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@TeleOp
@Config
public class ManualAprilTagDetection extends OpMode {
    public static double SPEED_GAIN = 0.02;   //  Forward Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    public static double STRAFE_GAIN = 0.015;   //  Strafe Speed Control "Gain".  eg: Ramp up to 25% power at a 25 degree Yaw error.   (0.25 / 25.0)
    public static double TURN_GAIN = 0.01;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)
    public static double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    public static double MAX_AUTO_STRAFE = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    public static double MAX_AUTO_TURN = 0.3;   //  Clip the turn speed to this max value (adjust for your robot)

    private VisionSubsystem visionSubsystem;
    public static Alliance alliance = Alliance.Blue;
    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        MatchConfig.telemetry = telemetry;
        MatchConfig.Alliance = alliance;
        visionSubsystem = new VisionSubsystem(hardwareMap, telemetry, true, true);
        visionSubsystem.stopTensorFlowProcessing();
    }

    @Override
    public void loop() {
        List<AprilTagDetection> detections = visionSubsystem.getFreshAprilTags();
        for(AprilTagDetection detection : detections) {
           visionSubsystem.outputDetectionToTelemetry(detection);
           MatchConfig.telemetry.update();

           if(detection.id == getTargetId()){
               double rangeError = (detection.ftcPose.range - Configuration.BACKDROP_DISTANCE);
               double headingError = detection.ftcPose.bearing;
               double yawError = detection.ftcPose.yaw;

               MatchConfig.telemetry.addData("RangeError: ", rangeError);
               MatchConfig.telemetry.addData("HeadingError: ", headingError);
               MatchConfig.telemetry.addData("YawError: ", yawError);
               MatchConfig.telemetry.update();

               MatchConfig.telemetry.addData("Drive: ", Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED));
               MatchConfig.telemetry.addData("Turn: ", Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN));
               MatchConfig.telemetry.addData("Strafe: ", -Range.clip(yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE));
               MatchConfig.telemetry.update();
           }
        }
        sleep(50);
    }

    private int getTargetId(){
        if(MatchConfig.Alliance == Alliance.Red) {
            return 5;
        }
        else {
            return 2;
        }
    }
    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
