package org.firstinspires.ftc.teamcode.Subsystems;

import android.util.Size;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Subsystems.vision.TeamPropProcessor;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VisionSubsystem extends FalconSubsystemBase {
    /**
     * this subsystem uses a Tensor flow model file to be able to recognize the team props
     * then it sets the camera's resulotion
     */
    private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/" + Configuration.TENSOR_FILE;

    private static final String[] LABELS = {
            "TeamProp"
    };
    private final HardwareMap hardwareMap;

    private TfodProcessor tfod;

    private AprilTagProcessor aprilTag;

    private TeamPropProcessor teamPropProcessor;

    ///the variable to store our vision portals
    private VisionPortal tensorVisionPortal;
    private VisionPortal aprilTagVisionPortal;
    private int tensorflowVisionPortal_ID;
    private int apriltagVisionPortal_ID;

    public int GetAprilTagID()
    {
        if(MatchConfig.Alliance == Alliance.Red) {
            return 5;
//            if(MatchConfig.TeamPropPosition == TeamPropPosition.Left)
//                return 4;
//            else if( MatchConfig.TeamPropPosition == TeamPropPosition.Center)
//                return 5;
//            else
//                return 6;
        }
        else {
            return 2;
//            if(MatchConfig.TeamPropPosition == TeamPropPosition.Left)
//                return 1;
//            else if( MatchConfig.TeamPropPosition == TeamPropPosition.Center)
//                return 2;
//            else
//                return 3;
        }
    }

    public enum AprilTagID {
        BLUE_BACKDROP_LEFT_TAG(1),
        BLUE_BACKDROP_CENTER_TAG(2),
        BLUE_BACKDROP_RIGHT_TAG(3),
        RED_BACKDROP_LEFT_TAG(4),
        RED_BACKDROP_CENTER_TAG(5),
        RED_BACKDROP_RIGHT_TAG(6),
        RED_AUDIENCE_WALL_LARGE_TAG(7),
        RED_AUDIENCE_WALL_SMALL_TAG(8),
        BLUE_AUDIENCE_WALL_SMALL_TAG(9),
        BLUE_AUDIENCE_WALL_LARGE_TAG(10);

        private final int id;
        private boolean isDetected;
        private double timestamp;
        private AprilTagDetection detection;
        AprilTagID(int id) {
            this.id = id;
            this.isDetected = false;
            this.detection = null;
            this.timestamp = 0;
        }

        public static AprilTagID getByID(int id) {
            for (AprilTagID tag : values()) {
                if (tag.id == id)
                    return tag;
            }
            return null;
        }

        public static void setAllUnDetected() {
            for (AprilTagID tag : values()) {
                tag.isDetected = false;
            }
        }
    }

    public VisionSubsystem(HardwareMap hm, Telemetry tel) {
        this(hm, tel, false, false);
    }

    public VisionSubsystem(HardwareMap hm, Telemetry tel, boolean initVisionPortals, boolean allowStreaming) {
        super(tel);
        hardwareMap = hm;
        if (initVisionPortals) {
            initMultiPortals();
            initAprilTagProcessor(allowStreaming);
            initTfod(allowStreaming);
        }
    }

    private void initMultiPortals(){
        List portalList;

        portalList = JavaUtil.makeIntegerList(VisionPortal.makeMultiPortalView(2, VisionPortal.MultiPortalLayout.VERTICAL));
        tensorflowVisionPortal_ID = ((Integer) JavaUtil.inListGet(portalList, JavaUtil.AtMode.FROM_START, 0, false)).intValue();
        apriltagVisionPortal_ID = ((Integer) JavaUtil.inListGet(portalList, JavaUtil.AtMode.FROM_START, 1, false)).intValue();
    }

    private void initTfod(boolean allowStreaming) {
        //teamPropProcessor = new TeamPropProcessor(MatchConfig.Alliance);
        File f = new File(TFOD_MODEL_FILE);
        if(!f.exists())
            telemetry.addLine("Tensor file " + TFOD_MODEL_FILE + " does not exist");

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
        builder.setCameraResolution(new Size(Configuration.CAMERA_WIDTH, Configuration.CAMERA_HEIGHT));
        builder.enableLiveView(allowStreaming);
        builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);
        builder.addProcessor(tfod);
        builder.setLiveViewContainerId(tensorflowVisionPortal_ID);

        tensorVisionPortal = builder.build();
        tfod.setMinResultConfidence((float) Configuration.CONFIDENCE_SCORE);

        this.enableTensorFlowProcessing();
    }

    private void initAprilTagProcessor(boolean allowStreaming) {
        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()
                // The following default settings are available to un-comment and edit as needed.
                .setDrawAxes(allowStreaming)
                .setDrawCubeProjection(allowStreaming)
                .setDrawTagID(allowStreaming)
                .setDrawTagOutline(allowStreaming)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                // == CAMERA CALIBRATION ==
                // If you do not manually specify calibration parameters, the SDK will attempt
                // to load a predefined calibration for your camera.
                //.setLensIntrinsics(578.272, 578.272, 402.145, 221.506)
                // ... these parameters are fx, fy, cx, cy.
                .build();

        // Adjust Image Decimation to trade-off detection-range for detection-rate.
        // eg: Some typical detection data using a Logitech C920 WebCam
        // Decimation = 1 ..  Detect 2" Tag from 10 feet away at 10 Frames per second
        // Decimation = 2 ..  Detect 2" Tag from 6  feet away at 22 Frames per second
        // Decimation = 3 ..  Detect 2" Tag from 4  feet away at 30 Frames Per Second (default)
        // Decimation = 3 ..  Detect 5" Tag from 10 feet away at 30 Frames Per Second (default)
        // Note: Decimation can be changed on-the-fly to adapt during a match.
        //aprilTag.setDecimation(5);

        if(aprilTagVisionPortal != null)
            aprilTagVisionPortal = null;

        // Create the vision portal by using a builder.
//        aprilTagVisionPortal = new VisionPortal.Builder()
//                .addProcessor(aprilTag)
//                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 2"))
//                .setCameraResolution(new Size(640, 480))
//                .enableLiveView(allowStreaming)
//                .setStreamFormat(VisionPortal.StreamFormat.YUY2)
//                .setAutoStopLiveView(allowStreaming)
//                .setLiveViewContainerId(apriltagVisionPortal_ID)
//                .build();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 2"));
        builder.setCameraResolution(new Size(640, 480));
        builder.enableLiveView(allowStreaming);
        // Set the stream format; MJPEG uses less bandwidth than default YUY2./builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);
        builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);
        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        builder.setAutoStopLiveView(allowStreaming);

        // Set and enable the processor.
        builder.addProcessor(aprilTag);

        builder.setLiveViewContainerId(apriltagVisionPortal_ID);

        // Build the Vision Portal, using the above settings.
        aprilTagVisionPortal = builder.build();
        enableAprilTagProcessing();

        this.setAprilTagExposure();
    }

    public boolean setAprilTagExposure() {
        if(aprilTagVisionPortal == null)
            return false;

        // Make sure camera is streaming before we try to set the exposure controls
        if (aprilTagVisionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
            telemetry.addData("Camera", "Waiting");
            telemetry.update();
            waitForCameraToInit(aprilTagVisionPortal);
            telemetry.addData("Camera", "Ready");
            telemetry.update();
        }

        if(aprilTagVisionPortal.getCameraState() == VisionPortal.CameraState.STREAMING) {
            ExposureControl exposureControl = aprilTagVisionPortal.getCameraControl(ExposureControl.class);
            if (exposureControl.isExposureSupported()) {
                exposureControl.setMode(ExposureControl.Mode.Manual);
                exposureControl.setExposure(Configuration.APRIL_TAG_CAMERA_EXPOSURE, TimeUnit.MILLISECONDS);

                GainControl gainControl = aprilTagVisionPortal.getCameraControl(GainControl.class);
                gainControl.setGain(Configuration.APRIL_TAG_CAMERA_GAIN);
            }
        }
        return true;
    }

    public void startAprilTagProcessing(){
        if(aprilTagVisionPortal != null && aprilTagVisionPortal.getCameraState() != VisionPortal.CameraState.STREAMING)
        {
            this.enableAprilTagProcessing();
            aprilTagVisionPortal.resumeStreaming();
            waitForCameraToInit(aprilTagVisionPortal);
        }
    }

    public void stopAprilTagProcessing(){
        aprilTagVisionPortal.stopStreaming();
        this.disableAprilTagProcessing();
    }

    private void enableAprilTagProcessing(){
        aprilTagVisionPortal.setProcessorEnabled(aprilTag, true);
    }
    private void disableAprilTagProcessing(){
        aprilTagVisionPortal.setProcessorEnabled(aprilTag, false);
    }

    public void startTensorFlowProcessing(){
        if(tensorVisionPortal != null && tensorVisionPortal.getCameraState() != VisionPortal.CameraState.STREAMING)
        {
            this.enableTensorFlowProcessing();
            tensorVisionPortal.resumeStreaming();
            waitForCameraToInit(tensorVisionPortal);
        }
    }

    public void stopTensorFlowProcessing(){
        if(tensorVisionPortal.getCameraState() == VisionPortal.CameraState.STREAMING || tensorVisionPortal.getCameraState() == VisionPortal.CameraState.CAMERA_DEVICE_READY
            || tensorVisionPortal.getCameraState() == VisionPortal.CameraState.STARTING_STREAM) {
            tensorVisionPortal.stopStreaming();
            this.disableTensorFlowProcessing();
        }
    }

    private void enableTensorFlowProcessing(){
        tensorVisionPortal.setProcessorEnabled(tfod, true);
    }
    private void disableTensorFlowProcessing(){
        tensorVisionPortal.setProcessorEnabled(tfod, false);
    }

    public void shutDownTensorFlowProcessor(){
        if(tfod != null)
            tfod.shutdown();
    }


    private void waitForCameraToInit(VisionPortal visionPortal){
        int counter = 0;
        while(visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING & counter < 10){
            try{Thread.sleep(500);}
            catch(Exception ex) {}
            counter++;
        }
    }

    public List<Recognition> getTeamPropRecognitions() {
        return tfod.getRecognitions();
    }

    public TeamPropPosition getTeamPropPosition() {
        List<Recognition> currentRecognitions = getTeamPropRecognitions();
        TeamPropPosition position = TeamPropPosition.Left;
        Recognition teamProp = null;
        if (currentRecognitions != null && currentRecognitions.size() > 0) {
            telemetry.addData("Number of Team prop recognitions", currentRecognitions.size());
            for (Recognition recognition : currentRecognitions) {

                if (teamProp == null) {
                    teamProp = recognition;
                } else if (recognition.getConfidence() > teamProp.getConfidence()) {
                    teamProp = recognition;
                }
                telemetry.addData("Confidence score", teamProp.getConfidence());
            }

            if (teamProp != null) {
                double x = (teamProp.getLeft() + teamProp.getRight()) / 2;
                double y = (teamProp.getTop() + teamProp.getBottom()) / 2;
                telemetry.addData("X position : ", x);
                telemetry.addData("Y positions :", y);


                if (x < Configuration.LEFT_UPPER_BOUND_1) {
                        position = TeamPropPosition.Left;
                   } else if (x > Configuration.LEFT_UPPER_BOUND_1 && x < Configuration.RIGHT_LOWER_BOUND_1) {
                    position = TeamPropPosition.Center;
                    } else if (x > Configuration.RIGHT_LOWER_BOUND_1)
                        position = TeamPropPosition.Right;
                else
                    position = TeamPropPosition.NoDetection;

            }
        }
        else if(teamPropProcessor != null && teamPropProcessor.objectDetected && teamPropProcessor.detectedPosition != TeamPropPosition.NoDetection){
            position = teamPropProcessor.detectedPosition;
        }
        else if (MatchConfig.Alliance == Alliance.Red && MatchConfig.AutonomousStartLocation == AutonomousStartLocation.Near)
            position = TeamPropPosition.Left;
        else if (MatchConfig.Alliance == Alliance.Red && MatchConfig.AutonomousStartLocation == AutonomousStartLocation.Far)
            position = TeamPropPosition.Right;
        else if (MatchConfig.Alliance == Alliance.Blue && MatchConfig.AutonomousStartLocation == AutonomousStartLocation.Far)
            position = TeamPropPosition.Left;
        else if (MatchConfig.Alliance == Alliance.Blue && MatchConfig.AutonomousStartLocation == AutonomousStartLocation.Near)
            position = TeamPropPosition.Right;


        telemetry.addData("Team prop position :", position);
        return position;
    }

    public AprilTagDetection findAprilTag() {
        return findAprilTag(GetAprilTagID());
    }

    public VisionPortal.CameraState getAprilTagCameraState(){
        return aprilTagVisionPortal.getCameraState();
    }

    public List<AprilTagDetection> getAprilTags(){
        return aprilTag.getDetections();
    }

    public AprilTagDetection findAprilTag(int tagID) {
        AprilTagDetection currentDetection = null;
        List<AprilTagDetection> currentDetections = getAprilTags();

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if(detection.id == tagID){
                outputDetectionToTelemetry(detection);
                currentDetection = detection;
               break;
            }
        }
        // end for() loop

        return currentDetection;
    }

    private void outputDetectionToTelemetry(AprilTagDetection detection) {
        try {
            telemetry.addLine(String.format("\n==== (ID %d)", detection.id));
            telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
            telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
            telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
        } catch (Exception ex) {}
    }

    private Pose2d DeterminePoseFromAprilTag(AprilTagID tag) {

        //get the field position of the AprilTag on the field and store it in a float vector (VectorF)
        VectorF tagVector = tag.detection.metadata.fieldPosition;

        //store the X value of the tag in tagPosXOnField access the individual values of the vector using .get(0)
        double tagPosXOnField = tagVector.get(0);

        //store the Y value of the tag - use .get(1) for this one
        double tagPosYOnField = tagVector.get(1);

        //the +90 rotates this tagheading to be facing the backdrop (it comes in at -90 for the backdrop)
        double tagHeading = QuaternionToHeading(tag.detection.metadata.fieldOrientation) + 90;

        //Look at Fig. 2 at https://ftc-docs.firstinspires.org/en/latest/apriltag/understanding_apriltag_detection_values/understanding-apriltag-detection-values.html
        //save tag.detection.ftPose.x into a distanceY variable;
        //save tag.detection.ftcPose.y into a distanceX variable;
        //This is confusing because the ftCpose X and Y are not the X and Y coordinates of the field, but of the camera image
        //Ftcpose.x represents left and right on the camera image, which for the backdrop corresponds to the Y value.
        //Ftcpose.y represents distance to the robot, which for the backdrop is our X value

        double distanceY = tag.detection.ftcPose.x;
        double distanceX = tag.detection.ftcPose.y;

        //save the tag.detection.ftPose.yaw as the cameraYaw
        double cameraYaw = tag.detection.ftcPose.yaw;

        Pose2d newPose = new Pose2d(tagPosXOnField - distanceX, tagPosYOnField + distanceY, GyroSubsystem.getInstance(hardwareMap, telemetry).getHeading(AngleUnit.RADIANS));

        telemetry.addLine();
        telemetry.addData("Tag", tag.detection.metadata.name);
//        telemetry.addData("Tag Pose", "X %5.2f, Y %5.2f, heading %5.2f ", tagPosXOnField, tagPosYOnField, tagHeading);
//        telemetry.addData("DistToCamera", "X %5.2f, , Y %5.2f, yaw %5.2f,", distanceX, distanceY, cameraYaw);

        telemetry.addData("New Pose", "X %5.2f, Y %5.2f, heading %5.2f ", newPose.getX(), newPose.getY(), Math.toDegrees(newPose.getHeading()));

        return newPose;
    }

    private double QuaternionToHeading(Quaternion quaternion) {

        // Calculate yaw (heading) from quaternion
        double t0 = 2.0 * (quaternion.w * quaternion.z + quaternion.x * quaternion.y);
        double t1 = 1.0 - 2.0 * (quaternion.y * quaternion.y + quaternion.z * quaternion.z);
        double yawRadians = Math.atan2(t0, t1);

        // Convert yaw angle from radians to degrees
        double yawDegrees = Math.toDegrees(yawRadians);

        // Ensure the yaw angle is within the range [-180, 180] degrees
        if (yawDegrees > 180) {
            yawDegrees -= 360;
        } else if (yawDegrees < -180) {
            yawDegrees += 360;
        }

        return yawDegrees;
    }

    public float getAprilFPS(){
        return aprilTagVisionPortal.getFps();
    }
}