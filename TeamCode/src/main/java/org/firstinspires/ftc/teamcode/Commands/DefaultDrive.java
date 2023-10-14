package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;

public class DefaultDrive extends CommandBase {
    private final MecanumDriveSubsystem m_drive;
    private DoubleSupplier m_LeftX;
    private DoubleSupplier m_LeftY;

    private DoubleSupplier m_RightX;

    public DefaultDrive(MecanumDriveSubsystem subsystem, DoubleSupplier leftX, DoubleSupplier leftY, DoubleSupplier rightX) {
        m_drive = subsystem;
        m_LeftX = leftX;
        m_LeftY = leftY;
        m_RightX = rightX;
        addRequirements(m_drive);
    }

    @Override
    public void execute() {
        m_drive.drive(m_LeftY.getAsDouble(), m_LeftX.getAsDouble(), m_RightX.getAsDouble());
        m_drive.update();
    }

}
