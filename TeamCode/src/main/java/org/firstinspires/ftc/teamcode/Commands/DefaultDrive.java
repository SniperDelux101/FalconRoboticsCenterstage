package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.teamcode.Subsystems.DriveBaseSubsystem;

public class DefaultDrive extends CommandBase {
    private final DriveBaseSubsystem m_drive;
    private DoubleSupplier m_forward;
    private DoubleSupplier m_rotation;

    private DoubleSupplier m_strafe;

    public DefaultDrive(DriveBaseSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier rotation) {
        m_drive = subsystem;
        m_forward = forward;
        m_rotation = rotation;
        m_strafe = strafe;
        addRequirements(m_drive);
    }

    @Override
    public void execute() {
        m_drive.drive(m_strafe.getAsDouble(), -m_forward.getAsDouble(), m_rotation.getAsDouble());
    }

}
