
package frc.robot.commands.Vision;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.VisionSystem;


/**
 *
 */
public class UpdateTargets extends CommandBase {

    // private final VisionSystem m_visionSystem;

    // NetworkTable table;
    // NetworkTableEntry tx;
    // NetworkTableEntry ty;
    // NetworkTableEntry ta;
 
    public UpdateTargets(VisionSystem subsystem) {
        // m_visionSystem = subsystem;
        // addRequirements(m_visionSystem);
        // table = NetworkTableInstance.getDefault().getTable("limelight");
        // tx = table.getEntry("tx");
        // ty = table.getEntry("ty");
        // ta = table.getEntry("ta");
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
       
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // // read values periodically
        // double x = tx.getDouble(0.0);
        // double y = ty.getDouble(0.0);
        // // double area = ta.getDouble(0.0);

        // post to smart dashboard periodically
        // SmartDashboard.putNumber("LimelightX", x);
        // SmartDashboard.putNumber("LimelightY", y);
        // SmartDashboard.putNumber("LimelightArea", area);

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
