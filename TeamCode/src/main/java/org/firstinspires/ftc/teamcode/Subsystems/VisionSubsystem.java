package org.firstinspires.ftc.teamcode.Subsystems;

import android.util.Size;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

public class VisionSubsystem extends SubsystemBase {
    private static  final String TFOD_MODEL_FILE = "";
    private static final String[] LABELS ={
            "TeamProp"
    };
    private final HardwareMap hardwareMap;

    private TfodProcessor tfod;

    ///the variable to store our
    private VisionPortal visionPortal;

    public VisionSubsystem (HardwareMap hm ){
        //visionPortal = vp;
        hardwareMap = hm;
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
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcame 1"));
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
        return tfod.getRecognitions();
    }

}

