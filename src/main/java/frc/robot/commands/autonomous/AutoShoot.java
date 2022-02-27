
package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.BallHandlingSystem;

/**
 *
 */
public class AutoShoot extends CommandBase {

    private final BallHandlingSystem m_ballHandlingSystem;

    public AutoShoot(BallHandlingSystem subsystem) {

        m_ballHandlingSystem = subsystem;
        addRequirements(m_ballHandlingSystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
