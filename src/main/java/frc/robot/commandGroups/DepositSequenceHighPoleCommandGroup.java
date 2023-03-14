package frc.robot.commandGroups;

import java.time.Instant;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.collectorCommands.CollectorDepositCommand;
import frc.robot.commands.extensionCommands.ExtensionDepositSequenceCommand;
import frc.robot.commands.liftCommands.LiftDepositLowerCommand;
import frc.robot.commands.liftCommands.LiftHighPoleCommand;
import frc.robot.commands.liftCommands.RaiseHooksCommand;
import frc.robot.commands.liftCommands.LiftCarryCommand;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Extension;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Collector.CollectorState;
import frc.robot.subsystems.Collector.IntakeState;
import frc.robot.subsystems.Extension.TelescopePosition;
import frc.robot.subsystems.Lift.LiftConstants;
import frc.robot.subsystems.Lift.LiftPosition;


public class DepositSequenceHighPoleCommandGroup extends SequentialCommandGroup {
    Lift m_lift;
    DoubleSupplier m_triggerThreshold;
    Extension m_extension;
    Collector m_collector;
    TelescopePosition m_extensionPosition;
    WaitCommand m_WaitCommand;
    
    public DepositSequenceHighPoleCommandGroup(Lift p_lift, Extension p_extension, Collector p_collector) {
        this.m_lift = p_lift;
        this.m_extension = p_extension;
        this.m_collector = p_collector;
        
        addCommands(
            new LiftHighPoleCommand(p_lift),
            new RaiseHooksCommand(p_lift),
            new WaitCommand(0.05),
            new CollectorDepositCommand(m_collector),
            new ShelfCarryRetractedCommandGroup(m_extension, m_lift),
            new InstantCommand(() -> m_collector.m_intakeState = IntakeState.OFF)
        );
        
    }

}