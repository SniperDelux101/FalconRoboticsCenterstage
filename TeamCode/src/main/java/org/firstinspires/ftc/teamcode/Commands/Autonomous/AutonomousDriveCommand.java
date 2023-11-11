package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.PlacePixelOnSpikeCommand;
import org.firstinspires.ftc.teamcode.Commands.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.Commands.TrajectorySequenceFollowerCommand;
import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

public class AutonomousDriveCommand extends SequentialCommandGroup {

    private final LinearSlideSubsystem linearSlideSubsystem;
    private final IntakeMotorSubsystem intakeMotorSubsystem;
    private final ExtakeSubsystem extakeSubsystem;
    private final VisionSubsystem visionSubsystem;
    final MecanumDriveSubsystem mecanumDriveSubsystem;
    final Alliance alliance;
    final AutonomousStartLocation autonomousStartLocation;
    private TrajectorySequence trajectorySequence;
    private IdentifyTeamPropPositionCommand identifyTeamPropPositionCommand;


    public AutonomousDriveCommand(MecanumDriveSubsystem dr, LinearSlideSubsystem ls, IntakeMotorSubsystem is, ExtakeSubsystem es, VisionSubsystem vs,
                                  Alliance p_alliance , AutonomousStartLocation path ) {
        mecanumDriveSubsystem = dr;
        alliance = p_alliance;
        autonomousStartLocation = path;
        linearSlideSubsystem = ls;
        intakeMotorSubsystem = is;
        extakeSubsystem = es;
        visionSubsystem = vs;
        identifyTeamPropPositionCommand = new IdentifyTeamPropPositionCommand(visionSubsystem);

        addCommands(
                new TrajectorySequenceFollowerCommand(mecanumDriveSubsystem, AutonomousPaths.RedAllianceNearPhaseOne(mecanumDriveSubsystem.getDrive(),
                        identifyTeamPropPositionCommand.getTeamPropPosition())),
                new PlacePixelOnSpikeCommand(intakeMotorSubsystem).withTimeout(2000),
                new TrajectorySequenceFollowerCommand(mecanumDriveSubsystem, AutonomousPaths.RedAllianceNearPhaseTwo(mecanumDriveSubsystem.getDrive()))
        );

        addRequirements(mecanumDriveSubsystem, linearSlideSubsystem, intakeMotorSubsystem, extakeSubsystem, visionSubsystem);

    }

    @Override
    public void initialize()
    {
//        if ( alliance == Alliance.Blue && autonomousStartLocation == AutonomousStartLocation.Far)
//            trajectorySequence = this.blueAllianceFar();
//        else if ( alliance == Alliance.Red && autonomousStartLocation == AutonomousStartLocation.Far)
//            trajectorySequence = this.redAllianceFar();
//        else if ( alliance == Alliance.Blue && autonomousStartLocation == AutonomousStartLocation.Near)
//            trajectorySequence = this.blueAllianceNear();
//        else if ( alliance == Alliance.Red && autonomousStartLocation == AutonomousStartLocation.Near)
//            trajectorySequence = this.redAllianceNear();
    }
    @Override
    public void execute(){
        /// TODO:
       mecanumDriveSubsystem.getDrive().followTrajectorySequence(trajectorySequence);
    }
    @Override
    public boolean isFinished(){
        return !mecanumDriveSubsystem.isBusy();
    }


    // autonomous pathing from red alliance far from the back drop

}
