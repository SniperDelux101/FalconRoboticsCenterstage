package org.firstinspires.ftc.teamcode.OpModeTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.RunLinerslideToPositionCommand;
import org.firstinspires.ftc.teamcode.Subsystems.LinearSlideSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Configuration;


/*
This testing program allows for the configuration of the linear slide positioning
    ** All of this testing only utilizes controller 1 **

    left D-Pad & Y Button ------->  Low position Up
    left D-Pad & A Button ------->  Low position Down

    up D-Pad & Y Button -------> Medium position Up
    up D-Pad & A Button -------> Medium position Down

    right D-Pad & Y Button -------> High position Up
    right D-Pad & A Button -------> High position Down

    down D-Pad & Y Button -------> Transfer position Up
    down D-Pad & A Button -------> Transfer position Down

    right bumper & left D-Pad -------> Low Position
    right bumper & up D-Pad -------> Medium Position
    right bumper & right D-Pad -------> High Position
    right bumper & down D-Pad -------> Transfer Position

 */

@TeleOp(group = "subsystems test")
public class LinearSlideTest extends OpMode {


    private LinearSlideSubsystem linearSlideSubsystem;
    private double pos;
    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        linearSlideSubsystem = new LinearSlideSubsystem(hardwareMap);
    }
    @Override
    public void loop() {


        if(gamepad1.dpad_left && gamepad1.y) {
            Configuration.LINEAR_SLIDE_POS_LO += Configuration.LINEAR_SLIDE_MULTIPLIER;
            //linearSlideSubsystem.LinearPosLo();
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_LO, true);
        }
        else if(gamepad1.dpad_left && gamepad1.a) {
            Configuration.LINEAR_SLIDE_POS_LO -= Configuration.LINEAR_SLIDE_MULTIPLIER;
            //linearSlideSubsystem.LinearPosLo();
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_LO, true);
        }
        else if(gamepad1.dpad_up && gamepad1.y) {
            Configuration.LINEAR_SLIDE_POS_MED += Configuration.LINEAR_SLIDE_MULTIPLIER;
            //linearSlideSubsystem.LinearPosMed();
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_MED, true);
        }
        else if(gamepad1.dpad_up && gamepad1.a) {
            Configuration.LINEAR_SLIDE_POS_MED -= Configuration.LINEAR_SLIDE_MULTIPLIER;
            //linearSlideSubsystem.LinearPosMed();
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_MED, true);
        }
        else if(gamepad1.dpad_right && gamepad1.y) {
            Configuration.LINEAR_SLIDE_POS_HI += Configuration.LINEAR_SLIDE_MULTIPLIER;
            //linearSlideSubsystem.LinearPosHi();
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_HI, true);
        }
        else if(gamepad1.dpad_right && gamepad1.a) {
            Configuration.LINEAR_SLIDE_POS_HI -= Configuration.LINEAR_SLIDE_MULTIPLIER;
            //linearSlideSubsystem.LinearPosHi();
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_HI, true);
        }
        else if(gamepad1.dpad_down && gamepad1.y) {
            Configuration.LINEAR_SLIDE_POS_TRANSFER += Configuration.LINEAR_SLIDE_MULTIPLIER;
            //linearSlideSubsystem.LinearPosTransfer();
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_TRANSFER, true);
        }
        else if(gamepad1.dpad_down && gamepad1.a) {
            Configuration.LINEAR_SLIDE_POS_TRANSFER -= Configuration.LINEAR_SLIDE_MULTIPLIER;
            //linearSlideSubsystem.LinearPosTransfer();
            linearSlideSubsystem.runToPosition(Configuration.LINEAR_SLIDE_POS_TRANSFER, true);
        }
        else if(gamepad1.dpad_left && gamepad1.right_bumper) {
            //linearSlideSubsystem.LinearPosLo();
            RunLinerslideToPositionCommand command = new RunLinerslideToPositionCommand(linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_LO);
            command.execute();
            while(!command.isFinished() && linearSlideSubsystem.LinearCurPos() < Configuration.LINEAR_SLIDE_POS_LO) {
                telemetry.addData("running to low position, target ", Configuration.LINEAR_SLIDE_POS_LO);
                telemetry.addData("Current Motor Position : ", linearSlideSubsystem.LinearCurPos());
                telemetry.addData("Command isFinished: ", command.isFinished() || linearSlideSubsystem.LinearCurPos() < Configuration.LINEAR_SLIDE_POS_LO);
                telemetry.update();
            }
            command.end(true);
        }
        else if(gamepad1.dpad_up && gamepad1.right_bumper) {
            //linearSlideSubsystem.LinearPosMed();
            new RunLinerslideToPositionCommand(linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_MED)
                    .execute();
            while(linearSlideSubsystem.LinearCurPos() < Configuration.LINEAR_SLIDE_POS_MED)
            {}
            linearSlideSubsystem.stop();
            telemetry.addData("Running to Medium Position, Target ", Configuration.LINEAR_SLIDE_POS_MED);
        }
        else if(gamepad1.dpad_right && gamepad1.right_bumper) {
            //linearSlideSubsystem.LinearPosHi();
            new RunLinerslideToPositionCommand(linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_HI)
                    .execute();
            while(linearSlideSubsystem.LinearCurPos() < Configuration.LINEAR_SLIDE_POS_HI)
            {}
            linearSlideSubsystem.stop();
            telemetry.addData("running to high position, Target", Configuration.LINEAR_SLIDE_POS_HI);
        }
        else if(gamepad1.dpad_down && gamepad1.right_bumper) {
            //linearSlideSubsystem.LinearPosTransfer();
            new RunLinerslideToPositionCommand(linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_TRANSFER)
                    .execute();
            while(linearSlideSubsystem.LinearCurPos() < Configuration.LINEAR_SLIDE_POS_TRANSFER)
            {}
            linearSlideSubsystem.stop();
            telemetry.addData("running to Transfer, target ", Configuration.LINEAR_SLIDE_POS_TRANSFER );
        }
        else if (gamepad1.dpad_down && gamepad1.left_bumper){
            //linearSlideSubsystem.LinearPosHome();
            new RunLinerslideToPositionCommand(linearSlideSubsystem, Configuration.LINEAR_SLIDE_POS_HOME)
                    .execute();
            while(linearSlideSubsystem.LinearCurPos() < Configuration.LINEAR_SLIDE_POS_HOME)
            {}
            linearSlideSubsystem.stop();
            telemetry.addData(" Running to Home Position , target ", Configuration.LINEAR_SLIDE_POS_HOME);
        }
        else if(gamepad1.left_trigger > 0 && gamepad1.right_trigger > 0){
            new RunLinerslideToPositionCommand(linearSlideSubsystem, 0)
                    .execute();
            while(linearSlideSubsystem.LinearCurPos() > 0 )
            {}
            linearSlideSubsystem.stop();
        }

        pos = linearSlideSubsystem.LinearCurPos();
        telemetry.addData("Current Motor Position : ", pos);
        telemetry.addData("Linear Pos HI: ", Configuration.LINEAR_SLIDE_POS_HI);
        telemetry.addData("Linear Pos Med: ", Configuration.LINEAR_SLIDE_POS_MED);
        telemetry.addData("Linear Pos Low: ", Configuration.LINEAR_SLIDE_POS_LO);
        telemetry.addData("Linear Pos Transfer: ", Configuration.LINEAR_SLIDE_POS_TRANSFER);
        telemetry.addData("Linera Pos Home: ", Configuration.LINEAR_SLIDE_POS_HOME);
        telemetry.update();


    }

}