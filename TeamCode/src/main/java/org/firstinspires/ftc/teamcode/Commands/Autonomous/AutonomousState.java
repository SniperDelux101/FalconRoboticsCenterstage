package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

@Config
public class AutonomousState {
    private static AutonomousState autonomousState = null;

    private Recognition currentRecognition;
    private double distanceToProp;

    public static int Low_Bound = 20;
    public static int High_Bound = 30;

    private AutonomousState(){}

    public static AutonomousState getInstance()
    {
        if(autonomousState == null)
            autonomousState = new AutonomousState();

        return autonomousState;
    }

    public void setCurrentRecognition(Recognition rec)
    {
        this.currentRecognition = rec;
    }
    public Recognition getCurrentRecognition()
    {
        return this.currentRecognition;
    }

    public double determineDistanceToTeamProp(){
        if(this.currentRecognition != null && this.currentRecognition.getWidth() > 0) {
            distanceToProp = (Configuration.TEAM_PROP_WIDTH * Configuration.FOCAL_LENGTH) / this.currentRecognition.getWidth();
            return distanceToProp;
        }
        else
            return 0;
    }

    public TeamPropPosition getTeamPropPosition(AutonomousStartLocation startLocation){

        if(this.distanceToProp == 0)
            this.determineDistanceToTeamProp();

        //if Near Right will be closest, followed by center and then left
        if(startLocation == AutonomousStartLocation.Near){
            if(this.distanceToProp < Low_Bound)
                return TeamPropPosition.Right;
            else if(this.distanceToProp > Low_Bound && this.distanceToProp < High_Bound)
                return TeamPropPosition.Center;
            else
                return TeamPropPosition.Left;
        }
        //if Far: left will be closest, followed by center and then right
        else{
            if(this.distanceToProp < Low_Bound)
                return TeamPropPosition.Left;
            else if(this.distanceToProp > Low_Bound && this.distanceToProp < High_Bound)
                return TeamPropPosition.Center;
            else
                return TeamPropPosition.Right;
        }
    }
}
