package org.firstinspires.ftc.teamcode.Utilities;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.TeamPropPosition;

/**
 * This class holds the configuration values for the match.  These values are static so they can be
 * accessed by any object in the application
 */
@Config
public class MatchConfig {
    public static Alliance Alliance;
    public static AutonomousStartLocation AutonomousStartLocation;
    public static TeamPropPosition TeamPropPosition;
}
