package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

import java.util.List;

import javax.annotation.Nullable;

public class IdentifyTeamPropPositionCommand  {
    private final VisionSubsystem visionSubsystem;

    public IdentifyTeamPropPositionCommand (VisionSubsystem v_subsystem){
        visionSubsystem = v_subsystem;

    }


    public TeamPropPosition getTeamPropPosition(){
        List<Recognition> currentRecognitions = visionSubsystem.getRecognitions();
        Recognition teamProp = null;
        for (Recognition recognition : currentRecognitions){

            if (teamProp == null){
                teamProp = recognition;
            }
            else if (recognition.getConfidence()> teamProp.getConfidence()){
                teamProp = recognition;
            }
        }
        TeamPropPosition position = TeamPropPosition.Center;
        if (teamProp != null ) {
            double x= (teamProp.getLeft()+ teamProp.getRight()) /2 ;
            double y = (teamProp.getTop()+ teamProp.getBottom()) / 2;
            if (x< 213 ){
                position = TeamPropPosition.Left;
            }
            else if ( x > 426)
                position = TeamPropPosition.Right;
        }

      return position;
    }

}
