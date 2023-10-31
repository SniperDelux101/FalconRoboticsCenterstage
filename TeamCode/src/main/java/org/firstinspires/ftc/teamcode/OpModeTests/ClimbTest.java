package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Subsystems.ClimbSubsytem;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;

public class ClimbTest extends OpMode {

    private ClimbSubsytem climbSubsytem;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        climbSubsytem = new ClimbSubsytem(hardwareMap);
    }

    @Override
    public void loop() {

        if(gamepad1.left_bumper) {
            climbSubsytem.ClimbOut();
        }

        if(gamepad1.right_bumper) {
            climbSubsytem.ClimbIn();
        }

    }
}
