package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.MovePixelBoxArmToPositionCommand;
import org.firstinspires.ftc.teamcode.Commands.MoveToPixelBoxPosition;
import org.firstinspires.ftc.teamcode.Commands.PixelBoxArmPosition;
import org.firstinspires.ftc.teamcode.Commands.PixelBoxPosition;
import org.firstinspires.ftc.teamcode.Commands.PlacePixelOnSpikeCommand;
import org.firstinspires.ftc.teamcode.Commands.RunLinearSlideAndCenterPixelBoxCommand;
import org.firstinspires.ftc.teamcode.Commands.StopPixelBoxReset;
import org.firstinspires.ftc.teamcode.Commands.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.Commands.TrajectorySequenceFollowerCommand;
import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

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

        TeamPropPosition teamPropPosition = identifyTeamPropPositionCommand.getTeamPropPosition();

        //TODO:
        addCommands(
                // This moves the robot to its center position
                new TrajectorySequenceFollowerCommand(mecanumDriveSubsystem, AutonomousPaths.PhaseOne(alliance, mecanumDriveSubsystem.getDrive(),
                        teamPropPosition, path)),
                // This places a pixel on the spike
                new PlacePixelOnSpikeCommand(intakeMotorSubsystem).withTimeout(2000),
                // This moves the robot to a scoring position near the backdrop
                new TrajectorySequenceFollowerCommand(mecanumDriveSubsystem, AutonomousPaths.PhaseTwo(alliance, mecanumDriveSubsystem.getDrive(), teamPropPosition, path)),
                // This will score a pixel on the backdrop
                new RunLinearSlideAndCenterPixelBoxCommand(extakeSubsystem,linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_LO),
                // This moves the arm into a scoring position
                new MovePixelBoxArmToPositionCommand(extakeSubsystem, PixelBoxArmPosition.Extake),
                // This moves the pixel box to the correct location based off of which spike location the team prop is on
                new MoveToPixelBoxPosition(extakeSubsystem, getPixelBoxPositionFromPropPosition(teamPropPosition)),
                // This drops the pixel to the correct position
                new InstantCommand( extakeSubsystem::pixelEject, extakeSubsystem),
                // This retracts the linear slide
                new StopPixelBoxReset(extakeSubsystem, linearSlideSubsystem),
                // this will park
                new TrajectorySequenceFollowerCommand(mecanumDriveSubsystem, AutonomousPaths.Park(alliance, mecanumDriveSubsystem.getDrive(), autonomousStartLocation))
        );

        addRequirements(mecanumDriveSubsystem, linearSlideSubsystem, intakeMotorSubsystem, extakeSubsystem, visionSubsystem);

    }

    private PixelBoxPosition getPixelBoxPositionFromPropPosition(TeamPropPosition teamPropPosition) {
        if(teamPropPosition == TeamPropPosition.Left) {
            return PixelBoxPosition.Left;
        } else if (teamPropPosition == TeamPropPosition.Center) {
            return PixelBoxPosition.Center;
        }
        else {
            return PixelBoxPosition.Right;
        }
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
