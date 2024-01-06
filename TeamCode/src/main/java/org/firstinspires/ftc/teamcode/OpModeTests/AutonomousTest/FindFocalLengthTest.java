package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

import java.util.List;

@TeleOp
@Config
public class FindFocalLengthTest extends LinearOpMode {
    private VisionSubsystem visionSubsystem;
    public static double distanceToPropInInches = 24;
    public static boolean useConfigFocalPoint = true;
    @Override
    public void runOpMode() throws InterruptedException {
        visionSubsystem = new VisionSubsystem(hardwareMap, telemetry);
        visionSubsystem.initTfod(true);

        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {
            List<Recognition> recognitions = visionSubsystem.getRecognitions();
            double focalLength;
            double pixelWidthOfProp;
            double distanceToObjectInInches;
            if(recognitions != null) {
                for (Recognition recognition : visionSubsystem.getRecognitions()) {
                    //Focal length(inches) = (Pixel Width * Distance to object(inches))/Actual width(inches)
                    pixelWidthOfProp = Double.valueOf(recognition.getWidth());
                    telemetry.addData("Pixel Width: ", pixelWidthOfProp);
                    if(useConfigFocalPoint)
                        focalLength = Configuration.FOCAL_LENGTH;
                    else
                        focalLength = ( pixelWidthOfProp * distanceToPropInInches)/ Configuration.TEAM_PROP_WIDTH;
                    telemetry.addData("Focal Length: ", focalLength);
                    distanceToObjectInInches = (Configuration.TEAM_PROP_WIDTH * focalLength)/pixelWidthOfProp;
                    telemetry.addData("Distance to TeamProp: ", distanceToObjectInInches);
                    telemetry.update();
                }
            }
        }

        visionSubsystem.stopTensorStreaming();
    }
}
