package org.firstinspires.ftc.teamcode.OpModeTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ExtakeSubsystem;

@TeleOp(group = "subsystems test")
public class PixelBoxIntakeTest_Op extends OpMode {

    private ExtakeSubsystem extakeSubsystem;
    @Override
    public void init() {
        extakeSubsystem = new ExtakeSubsystem(hardwareMap, telemetry);
    }

    @Override
    public void loop() {

        if(gamepad1.a)
        {
            telemetry.addLine("Intake");
            extakeSubsystem.pixelIntake();
        }else if(gamepad1.b){
            telemetry.addLine("Eject");
            extakeSubsystem.pixelEject();
        }
        else if (gamepad1.x){
            telemetry.addLine("Stop");
            extakeSubsystem.pixelStop();
        }
        extakeSubsystem.detectPixel();
        telemetry.update();
    }
}
