package frc.robot.Other;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OtherSystem;


/**
 *
 */
public class ManualOther extends CommandBase {

    private final OtherSystem m_otherSystem;

    public ManualOther(OtherSystem subsystem) {

        m_otherSystem = subsystem;
        addRequirements(m_otherSystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double leftStick = RobotContainer.getInstance().getassistController().getLeftY();
        m_otherSystem.setServo(leftStick);
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

