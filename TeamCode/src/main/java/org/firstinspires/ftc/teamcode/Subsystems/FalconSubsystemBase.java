package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class FalconSubsystemBase extends SubsystemBase {
    protected final Telemetry telemetry ;

    public FalconSubsystemBase ( Telemetry tel ){
        telemetry = tel;
    }
}
