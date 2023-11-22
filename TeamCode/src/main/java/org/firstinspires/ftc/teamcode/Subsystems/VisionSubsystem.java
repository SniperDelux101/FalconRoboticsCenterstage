package org.firstinspires.ftc.teamcode.Subsystems;

import android.util.Size;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

public class VisionSubsystem extends FalconSubsystemBase {
    /**
     * this subsystem uses a Tensor flow model file to be able to recognize the team props
     * then it sets the camera's resulotion
     */
    private static  final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/model_20231017_212515.tflite";

    private static final String[] LABELS ={
            "TeamProp"
    };
    private final HardwareMap hardwareMap;

    private TfodProcessor tfod;

    ///the variable to store our
    private VisionPortal visionPortal;

    public VisionSubsystem (HardwareMap hm, Telemetry tel){
        super(tel);
        hardwareMap = hm;

        initTfod(false);
    }
    public void initTfod(boolean allowStreaming ){
        tfod = new TfodProcessor.Builder()
                .setModelFileName(TFOD_MODEL_FILE)
                .setModelLabels(LABELS)
                .setIsModelTensorFlow2(true)
                .setIsModelQuantized(true)
                .setModelInputSize(300)
                .setModelAspectRatio(16.0 / 9.0)
                .build();


        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        builder.setCameraResolution(new Size(640, 480));
        builder.enableLiveView(allowStreaming);
        if (allowStreaming ){
            builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);
        }
        builder.addProcessor(tfod);

        visionPortal = builder.build();
        tfod.setMinResultConfidence((float) Configuration.CONFIDENCE_SCORE);
    }

    public void enableTfod (boolean ennabled ){
        ///TODO: Come back and do type checking for null values
        visionPortal.setProcessorEnabled(tfod, ennabled);
    }
    public void stopStreaming(){
        visionPortal.stopStreaming();
    }
    public void resumeStreaming(){
        visionPortal.resumeStreaming();
    }
    public void closeVisionPortal(){
        visionPortal.close();
    }

    public List<Recognition> getRecognitions (){
        List<Recognition> recognitionList = tfod.getRecognitions();
        return recognitionList;
    }
    public TeamPropPosition getTeamPropPosition(){
        List<Recognition> currentRecognitions = getRecognitions();
        TeamPropPosition position = TeamPropPosition.Right;
        Recognition teamProp = null;
        if(currentRecognitions != null){
            telemetry.addData("Number of Team prop recognitions" , currentRecognitions.size());
            for (Recognition recognition : currentRecognitions){

                if (teamProp == null){
                    teamProp = recognition;
                }
                else if (recognition.getConfidence()> teamProp.getConfidence()){
                    teamProp = recognition;
                }
                telemetry.addData("Confidence score" , teamProp.getConfidence());
            }

            if (teamProp != null ) {
                double x= (teamProp.getLeft()+ teamProp.getRight()) /2 ;
                double y = (teamProp.getTop()+ teamProp.getBottom()) / 2;
                telemetry.addData("X position : ", x);
                telemetry.addData("Y positions :", y);
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
        telemetry.addData("Team prop position :", position);
        return position;
    }

}

