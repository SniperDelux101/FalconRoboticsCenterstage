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
       return visionSubsystem.getTeamPropPosition();
    }

}
