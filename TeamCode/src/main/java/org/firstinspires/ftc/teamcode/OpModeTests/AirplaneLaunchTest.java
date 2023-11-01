package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.AirplaneLauncherSubsystem;

@Config
@TeleOp(group = "subsystems test")
public class AirplaneLaunchTest extends OpMode {

    private AirplaneLauncherSubsystem airplaneLauncherSubsystem;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        airplaneLauncherSubsystem = new AirplaneLauncherSubsystem(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            airplaneLauncherSubsystem.release();
        }

        else if(gamepad1.b) {
            airplaneLauncherSubsystem.retract();
        }
        telemetry.addData("current servo position",airplaneLauncherSubsystem.getLanucherServoPosition());
        telemetry.update();
    }

}
