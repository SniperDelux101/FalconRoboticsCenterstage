package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class SubsystemTelemetryBase extends SubsystemBase {
    protected final Telemetry telemetry;

    public SubsystemTelemetryBase(Telemetry tel){
        telemetry = tel;
    }

    /**
     * This method will return the telementry object
     * @return Telementry
     */
    protected Telemetry getTelemetry(){
        return telemetry;
    }
}
