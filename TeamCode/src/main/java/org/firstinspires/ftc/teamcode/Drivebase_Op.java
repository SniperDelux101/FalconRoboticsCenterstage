package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.Alliance;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutonomousStartLocation;
import org.firstinspires.ftc.teamcode.Robots.Callisto;
import org.firstinspires.ftc.teamcode.Robots.RobotMode;
import org.firstinspires.ftc.teamcode.Robots.SimpleRobot;
import org.firstinspires.ftc.teamcode.Utilities.MatchConfig;

@TeleOp
public class Drivebase_Op extends OpMode {

    private Callisto robot;
    private SimpleRobot simpleRobot;

    public Drivebase_Op() {

    }
    @Override
    public void init() {
        MatchConfig.Alliance = Alliance.Blue;
        MatchConfig.AutonomousStartLocation = AutonomousStartLocation.Far;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        robot = new Callisto(RobotMode.TELEOP, hardwareMap, gamepad1, gamepad2, telemetry);
        //simpleRobot = new SimpleRobot(RobotMode.TELEOP, hardwareMap, gamepad1, gamepad2, telemetry);
    }

    @Override
    public void loop() {
        try {
            robot.run();
        }
        catch (Exception ex){
            telemetry.addData("Exception: ", ex.getMessage());
            robot.reset();
            robot.init(gamepad1, gamepad2);
        }
    }

    //TODO// UNCOMMENT CODE ONCE VALUES HAVE BEEN DETERMINED

//    @Override
//    public void stop() {
//        robot.cleanUp();
//    }

}
