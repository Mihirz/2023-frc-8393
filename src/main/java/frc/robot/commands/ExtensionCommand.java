package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Extension;
import frc.robot.subsystems.Extension.RatchetPosition;
import frc.robot.subsystems.Extension.TelescopePosition;

public class ExtensionCommand extends CommandBase {
    
    private final Extension m_extension;
    private final TelescopePosition m_telescopePosition;
    private boolean stop = false;

    public ExtensionCommand(Extension extension, TelescopePosition telescopePosition) {
        m_extension = extension;
        this.m_telescopePosition = telescopePosition;
        addRequirements(m_extension);
    }

    @Override
    public void initialize() {
        m_extension.telescopeState = m_telescopePosition;
    }

    @Override
    public boolean isFinished() {
        SmartDashboard.putNumber("Telescope Position", m_extension.getTelescopeMotorPosition());
        if (m_extension.getTelescopeState() == m_telescopePosition) {
            return true;
        } else {
            return false;
        }
    }

    
}