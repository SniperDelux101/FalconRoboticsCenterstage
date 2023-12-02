package org.firstinspires.ftc.teamcode.OpModeTests.AutonomousTest;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.BuildBlueFarPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.BuildBlueNearPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.BuildRedFarPaths;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.Paths.BuildRedNearPaths;
import org.firstinspires.ftc.teamcode.Commands.PlacePixelOnSpikeCommand;
import org.firstinspires.ftc.teamcode.Commands.TrajectorySequenceFollowerCommand;
import org.firstinspires.ftc.teamcode.Subsystems.drive.TrajectorySequence.TrajectorySequence;

@Autonomous
public class SimpleGameSetUpTest extends CommandOpMode {
    public static Alliance alliance = Alliance.Blue;
    public static AutonomousStartLocation startLocation = AutonomousStartLocation.Near;

    @Override
    public void initialize() {


    }
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        int counter = 0;


        while (this.opModeInInit()) {
            telemetry.addData("Counter" , counter++);
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


        // run the scheduler
        while (!isStopRequested() && opModeIsActive()) {
            run();
        }
        reset();
    }
}
