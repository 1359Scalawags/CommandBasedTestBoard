package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Utilities;
import frc.robot.subsystems.DriveSystem;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class TurnByAngle extends CommandBase {

    private final DriveSystem m_driveSystem;
    private double turnAngle;
    private double originalAngle;
    private Timer correctTimer;

    public TurnByAngle(DriveSystem subsystem, double turnAngle) {

        this.m_driveSystem = subsystem;
        addRequirements(m_driveSystem);
        this.turnAngle = turnAngle;

        correctTimer = new Timer();
        correctTimer.reset();

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        originalAngle = m_driveSystem.getAngle();
        correctTimer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_driveSystem.arcadeDrive(0, Constants.Drive.maxTurnSpeed, turnAngle+originalAngle);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(Utilities.IsCloseTo(m_driveSystem.getAngle(), turnAngle+originalAngle, 3)) {
            if(correctTimer.get() > 1) {
                m_driveSystem.stop();
                return true;
            }
            return false;
        }
        correctTimer.reset();    
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
