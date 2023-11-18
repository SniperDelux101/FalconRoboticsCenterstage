package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

import java.util.List;

@Config
@TeleOp(group = "subsystems test")
public class AutoVisionSystemTest extends OpMode {
    private VisionSubsystem visionSubsystem ;
    public static TeamPropPosition teamPropPosition = TeamPropPosition.Center;
    private boolean getRecognition = true;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
         visionSubsystem= new VisionSubsystem(hardwareMap);

    }
    @Override
    public void loop() {
        if (getRecognition) {
            teamPropPosition = this.getTeamPropPosition();
            getRecognition = true;
        }
        telemetry.addData("Team Prop Position : ", teamPropPosition);
        telemetry.update();
    }
    public TeamPropPosition getTeamPropPosition(){
        List<Recognition> currentRecognitions = visionSubsystem.getRecognitions();
        TeamPropPosition position = TeamPropPosition.Right;
        Recognition teamProp = null;
        if(currentRecognitions != null){
            for (Recognition recognition : currentRecognitions){

                if (teamProp == null){
                    teamProp = recognition;
                }
                else if (recognition.getConfidence()> teamProp.getConfidence()){
                    teamProp = recognition;
                }
            }

            if (teamProp != null ) {
                double x= (teamProp.getLeft()+ teamProp.getRight()) /2 ;
                double y = (teamProp.getTop()+ teamProp.getBottom()) / 2;
                telemetry.addData("X value :", x );
                telemetry.addData("Y Value : ",y );
                if (x< Configuration.LEFT_UPPER_BOUND){
                    position = TeamPropPosition.Left;
                }
                else if  (x > Configuration.LEFT_UPPER_BOUND && x< Configuration.RIGHT_LOWER_BOUND){
                    position = TeamPropPosition.Center;
                }
                else if ( x > Configuration.RIGHT_LOWER_BOUND)
                    position = TeamPropPosition.Right;
            }
        }
        return position;
    }

}
