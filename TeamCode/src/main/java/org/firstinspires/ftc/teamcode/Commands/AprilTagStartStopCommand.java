package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;
import org.firstinspires.ftc.vision.VisionPortal;

public class AprilTagStartStopCommand extends CommandBase {

    public enum State{
        Start,
        Stop,
        ClosePortal
    }
    private VisionSubsystem visionSubsystem;
    private final State state;
    private boolean hasExecuted = false;
    private boolean isFinished = false;

    public AprilTagStartStopCommand(VisionSubsystem vSystem, State state){
        visionSubsystem = vSystem;
        this.state = state;
        addRequirements(visionSubsystem);
    }

    @Override
    public void execute() {
        if (!hasExecuted) {
            if (this.state == State.Start) {
                MatchConfig.telemetry.addLine("Enabling Camera");
                MatchConfig.telemetry.update();
                visionSubsystem.startAprilTagProcessing();
                MatchConfig.telemetry.addLine("After Enabling Camera");
                MatchConfig.telemetry.update();
                while (visionSubsystem.getAprilTagCameraState() != VisionPortal.CameraState.STREAMING) {
                    try {
                        Thread.sleep(20);
                        MatchConfig.telemetry.addLine("Waiting for camera to initialize");
                        MatchConfig.telemetry.update();
                    } catch (Exception ex) {
                    }
                }
                isFinished = true;
            } else if (this.state == State.Stop) {
                visionSubsystem.stopAprilTagProcessing();
                isFinished = true;
            }
            else if (this.state == State.ClosePortal) {
                visionSubsystem.stopAprilTagProcessing();
                visionSubsystem.stopTensorFlowProcessing();
                visionSubsystem = null;
                System.gc();
                isFinished = true;
            }
        }
        hasExecuted = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
