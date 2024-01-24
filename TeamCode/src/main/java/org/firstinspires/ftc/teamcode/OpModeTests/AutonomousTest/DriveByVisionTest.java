package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.DriveToTeamPropCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.FindTeamPropCommand;
import org.firstinspires.ftc.teamcode.Commands.PlacePixelOnSpikeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.drive.FalconMecanumDrive;

public class DriveByVisionTest extends CommandOpMode {

    private Alliance alliance;
    private AutonomousStartLocation startLocation;

    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private VisionSubsystem visionSubsystem;
    private IntakeMotorSubsystem intakeMotorSubsystem;
    @Override
    public void initialize() {
        mecanumDriveSubsystem = new MecanumDriveSubsystem(new FalconMecanumDrive(hardwareMap), false);
        visionSubsystem = new VisionSubsystem(hardwareMap, telemetry);
        intakeMotorSubsystem = new IntakeMotorSubsystem(hardwareMap, telemetry);
        register(mecanumDriveSubsystem, visionSubsystem, intakeMotorSubsystem);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        while(this.opModeInInit()) {
            telemetry.addLine("GamePad1 D-PadUp = Start Location Far");
            telemetry.addLine("GamePad1 D-PadDown = Start Location Near");
            telemetry.addLine("GamePad1 D-PadLeft = Alliance Red");
            telemetry.addLine("GamePad1 D-PadRight = Alliance Blue");
            telemetry.addLine();

            if (gamepad1.dpad_left)
                alliance = Alliance.Red;
            else if (gamepad1.dpad_right)
                alliance = Alliance.Blue;
            else if (gamepad1.dpad_up)
                startLocation = AutonomousStartLocation.Far;
            else if (gamepad1.dpad_down)
                startLocation = AutonomousStartLocation.Near;

            telemetry.addData("Alliance: ", alliance);
            telemetry.addData("Auto Start Location: ", startLocation);
            telemetry.update();
        }

        waitForStart();


        schedule(
                new SequentialCommandGroup(
                        new FindTeamPropCommand(visionSubsystem, mecanumDriveSubsystem),
                        new DriveToTeamPropCommand(mecanumDriveSubsystem),
                        new PlacePixelOnSpikeCommand(intakeMotorSubsystem)
                )
        );

        // run the scheduler
        while (!isStopRequested() && opModeIsActive()) {
            run();
        }
        reset();
    }
}
