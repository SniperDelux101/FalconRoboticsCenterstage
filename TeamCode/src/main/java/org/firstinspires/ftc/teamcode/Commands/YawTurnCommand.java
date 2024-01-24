package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public class YawTurnCommand extends CommandBase {

    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final VisionSubsystem visionSubsystem;

    private boolean isFinished = false;

    public YawTurnCommand(MecanumDriveSubsystem mDrive, VisionSubsystem vSystem) {
        mecanumDriveSubsystem = mDrive;
        visionSubsystem = vSystem;

        addRequirements(mecanumDriveSubsystem, visionSubsystem);
    }

    @Override
    public void initialize() {
        visionSubsystem.startAprilTagProcessing();

        AprilTagDetection detection = this.visionSubsystem.findAprilTag();
        if(detection != null){
            mecanumDriveSubsystem.turn(Math.toRadians(detection.ftcPose.yaw));
        }

        MatchConfig.telemetry.addData("Yaw: ", detection.ftcPose.yaw);
        MatchConfig.telemetry.update();
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        visionSubsystem.stopAprilTagProcessing();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
