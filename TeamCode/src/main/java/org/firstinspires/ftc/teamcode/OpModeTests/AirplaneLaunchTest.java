package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.AirplaneLauncherSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;

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
        String command = "";
        if(gamepad1.a) {
            airplaneLauncherSubsystem.release();
            command = "Release";
        }
        else if(gamepad1.b) {
            airplaneLauncherSubsystem.retract();
            command = "Retract";
        }
        else if(gamepad1.dpad_left && gamepad1.a){  //configure release up
            Configuration.LAUNCH_RELEASE++;
        }
        else if(gamepad1.dpad_left && gamepad1.b){  //configure release down
            Configuration.LAUNCH_RELEASE--;
        }
        else if(gamepad1.dpad_right && gamepad1.a){  //configure retract up
            Configuration.LAUNCH_RETRACT++;
        }
        else if(gamepad1.dpad_right && gamepad1.b){  //configure retract down
            Configuration.LAUNCH_RETRACT--;
        }
        telemetry.addData("current servo position",airplaneLauncherSubsystem.getLanucherServoPosition());
        telemetry.addData("Current command: ", command);
        telemetry.addData("Launch Release", Configuration.LAUNCH_RELEASE);
        telemetry.addData("Launch Retract", Configuration.LAUNCH_RETRACT);
        telemetry.update();
    }

}
