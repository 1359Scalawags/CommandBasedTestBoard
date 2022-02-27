package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BallHandlingSystem;

public class StartShooter extends CommandBase {

    private final BallHandlingSystem m_ballHandlingSystem;

    public StartShooter(BallHandlingSystem subsystem) {
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
        m_ballHandlingSystem.setShootMotorRPM(Constants.BallHandling.kShootMotorSpeed);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_ballHandlingSystem.getShooterMotorRPM() >= Constants.BallHandling.kMinShootMotorSpeed * Constants.BallHandling.kShootMotorSpeed) {
            return true;
        }
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
