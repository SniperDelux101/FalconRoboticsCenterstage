package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

import java.util.List;

@Config
public class FindTeamPropCommand extends CommandBase {

    private final VisionSubsystem  visionSubsystem;
    private final MecanumDriveSubsystem drive;
    private boolean isFinished;
    public static double Strafe_Speed = .5;
    public static int LowBound = 300;
    public static int HighBound = 330;

    public FindTeamPropCommand(VisionSubsystem vision, MecanumDriveSubsystem falcon){
        visionSubsystem = vision;
        drive = falcon;
        isFinished = false;

        addRequirements(visionSubsystem, drive);
    }

    @Override
    public void initialize(){
        try {
            visionSubsystem.initTfod(true);
        }
        catch(Exception ex){

        }
    }

    @Override
    public void execute() {
        drive.drive(0,0,Strafe_Speed);
        List<Recognition> recognitions = visionSubsystem.getRecognitions();
        for (Recognition recognition : recognitions){

            double x = (recognition.getLeft()+ recognition.getRight()) /2 ;
            double y = (recognition.getTop()+ recognition.getBottom()) / 2;
            if(x > LowBound && x < HighBound) {
                AutonomousState.getInstance().setCurrentRecognition(recognition);
                drive.stop();
                isFinished = true;
                break;
            }
        }
    }

    @Override
    public boolean isFinished(){
        return isFinished;
    }

    @Override
    public void end(boolean interrupted) {
        visionSubsystem.stopTensorStreaming();
        drive.stop();
    }
}
