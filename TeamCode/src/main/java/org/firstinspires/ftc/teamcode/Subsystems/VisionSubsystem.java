package org.firstinspires.ftc.teamcode.Subsystems;

import android.util.Size;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
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

    private AprilTagProcessor aprilTag;

    ///the variable to store our
    private VisionPortal visionPortal;
    private Alliance alliance;
    private AutonomousStartLocation startLocation;

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

    public VisionSubsystem (HardwareMap hm, Telemetry tel){
        this(hm, tel, Alliance.Red, AutonomousStartLocation.Near, false);
    }

    public VisionSubsystem (HardwareMap hm, Telemetry tel, Alliance alli, AutonomousStartLocation location, boolean initTF) {
        super(tel);
        hardwareMap = hm;
        alliance = alli;
        startLocation = location;
        if(initTF)
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

        initAprilTagProcessor();
        builder.addProcessor(aprilTag);

        visionPortal = builder.build();
        tfod.setMinResultConfidence((float) Configuration.CONFIDENCE_SCORE);
    }

    private void initAprilTagProcessor(){
        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()
                // The following default settings are available to un-comment and edit as needed.
                //.setDrawAxes(false)
                //.setDrawCubeProjection(false)
                //.setDrawTagOutline(true)
                //.setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
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
        aprilTag.setDecimation(3);
    }

    public void setEnableDisableForAprilTagPortal(boolean enable){
        aprilTagVisionPortal.setProcessorEnabled(aprilTag, enable);
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
        TeamPropPosition position = TeamPropPosition.Left;
        Recognition teamProp = null;
        if(currentRecognitions != null && currentRecognitions.size() > 0){
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

                // 1 -- Red Far && Blue Near
                if ((alliance == Alliance.Red && startLocation == AutonomousStartLocation.Far) || (alliance == Alliance.Blue && startLocation == AutonomousStartLocation.Near)) {
                if (x < Configuration.LEFT_UPPER_BOUND_1) {
                    position = TeamPropPosition.Left;
                } else if (x > Configuration.LEFT_UPPER_BOUND_1 && x < Configuration.RIGHT_LOWER_BOUND_1) {
                    position = TeamPropPosition.Center;
                } else
                    position = TeamPropPosition.Right;
            }

                // 2 -- Red Near & Blue Far
                if ((alliance == Alliance.Red && startLocation == AutonomousStartLocation.Near) || (alliance == Alliance.Blue && startLocation == AutonomousStartLocation.Far)) {
                    if (x < Configuration.LEFT_UPPER_BOUND_2) {
                        position = TeamPropPosition.Left;
                    } else if (x > Configuration.LEFT_UPPER_BOUND_2 && x < Configuration.RIGHT_LOWER_BOUND_2) {
                        position = TeamPropPosition.Center;
                    } else
                        position = TeamPropPosition.Right;
                }
            }
        } else if (alliance == Alliance.Red && startLocation == AutonomousStartLocation.Near)
            position = TeamPropPosition.Left;
        else if (alliance == Alliance.Red && startLocation == AutonomousStartLocation.Far)
            position = TeamPropPosition.Right;
        else if (alliance == Alliance.Blue && startLocation == AutonomousStartLocation.Far)
            position = TeamPropPosition.Left;
        else if (alliance == Alliance.Blue && startLocation == AutonomousStartLocation.Near)
            position = TeamPropPosition.Right;


        telemetry.addData("Team prop position :", position);
        return position;
    }

    public boolean findAprilTag(TeamPropPosition position){
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));

                //if(detection.)
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }   // end for() loop
    }

    private Pose2d DeterminePoseFromAprilTag(AprilTagID tag) {

        //get the field position of the AprilTag on the field and store it in a float vector (VectorF)
        VectorF tagVector = tag.detection.metadata.fieldPosition;

        //store the X value of the tag in tagPosXOnField access the individual values of the vector using .get(0)
        double tagPosXOnField = tagVector.get(0);

        //store the Y value of the tag - use .get(1) for this one
        double tagPosYOnField = tagVector.get(1);

        //the +90 rotates this tagheading to be facing the backdrop (it comes in at -90 for the backdrop)
        double tagHeading = QuaternionToHeading(tag.detection.metadata.fieldOrientation)+90;

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

        Pose2d newPose = new Pose2d(tagPosXOnField-distanceX, tagPosYOnField+distanceY, Robot.getInstance().getGyroSubsystem().currentRelativeYawRadians);

        telemetry.addLine();
        telemetry.addData("Tag", tag.detection.metadata.name);
//        telemetry.addData("Tag Pose", "X %5.2f, Y %5.2f, heading %5.2f ", tagPosXOnField, tagPosYOnField, tagHeading);
//        telemetry.addData("DistToCamera", "X %5.2f, , Y %5.2f, yaw %5.2f,", distanceX, distanceY, cameraYaw);

        telemetry.addData("New Pose", "X %5.2f, Y %5.2f, heading %5.2f ", newPose.position.x, newPose.position.y, Math.toDegrees(newPose.heading.log()));

        return newPose;
    }

    public double QuaternionToHeading (Quaternion quaternion) {

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

}

