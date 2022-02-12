
package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class OtherSystem extends SubsystemBase {

    private Servo servo;
    private Spark sparkMotor;
    private Relay relay;

    /**
    *
    */
    public OtherSystem() {
        servo = new Servo(0);
        addChild("Servo", servo);

        sparkMotor = new Spark(1);
        addChild("SparkMotor", sparkMotor);
        sparkMotor.setInverted(false);

        relay = new Relay(0);
        addChild("Relay", relay);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
