package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

public class StrafeToFindAprilTagCommand extends CommandBase {

    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final VisionSubsystem visionSubsystem;
    private TrajectorySequence sequence;
    private boolean isFinished;
    private boolean hasExecuted;

    public StrafeToFindAprilTagCommand(MecanumDriveSubsystem mDrive, VisionSubsystem vSystem) {
        mecanumDriveSubsystem = mDrive;
        visionSubsystem = vSystem;
        isFinished = false;
        hasExecuted = false;

        addRequirements(mecanumDriveSubsystem, visionSubsystem);
    }

    @Override
    public void initialize() {

        visionSubsystem.resumeAprilStreaming();

        if(MatchConfig.Alliance == Alliance.Blue) {
            sequence = mecanumDriveSubsystem.trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.STRAFE_TO_APRIL_TAG_VEL, Configuration.TRACKWIDTH))
                    .strafeRight(Configuration.VISION_STRAFE_DIS)
                    .build();
        }
        else {
            sequence = mecanumDriveSubsystem.trajectorySequenceBuilder(mecanumDriveSubsystem.getPoseEstimate())
                    .setVelConstraint(new MecanumVelocityConstraint(Configuration.STRAFE_TO_APRIL_TAG_VEL, Configuration.TRACKWIDTH))
                    .strafeLeft(Configuration.VISION_STRAFE_DIS)
                    .build();
        }
    }

    @Override
    public void execute() {
        MatchConfig.telemetry.addLine("Executing StrafeToFindAprilTagCommand");
        MatchConfig.telemetry.addLine("Looking for Tag: " + visionSubsystem.GetAprilTagID());

        if(hasExecuted) {
            // Look for AprilTag
            AprilTagDetection detection = this.visionSubsystem.findAprilTag();
            if(detection != null) {
                mecanumDriveSubsystem.stop();
                mecanumDriveSubsystem.breakFollowing();
                MatchConfig.telemetry.addLine("FOUND Tag " + visionSubsystem.GetAprilTagID());
                isFinished = true;
            }
            else
                MatchConfig.telemetry.addLine("Didn't Find ID: " + visionSubsystem.GetAprilTagID());
        }
        else {
            // Tell System to strafe
            mecanumDriveSubsystem.followTrajectorySequenceAsync(sequence);
            MatchConfig.telemetry.addLine("Executing Trajectory in Strafe to April Tag");
            hasExecuted = true;
        }
        MatchConfig.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
    @Override
    public void end(boolean interrupted) {
        if(interrupted && mecanumDriveSubsystem.isBusy())
            mecanumDriveSubsystem.breakFollowing();
        visionSubsystem.stopAprilStreaming();
    }
}
