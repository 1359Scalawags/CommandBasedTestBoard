package frc.robot.extensions;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;

public class SendableCANSparkMax extends CANSparkMax implements Sendable {

    private RelativeEncoder encoder;

    public SendableCANSparkMax(int deviceId, MotorType motorType) {
        super(deviceId, motorType);
        String moduleName = "SparkMax-" + deviceId;
        SendableRegistry.addLW(this, moduleName, deviceId);
        encoder = this.getEncoder();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Motor Controller");
        builder.setActuator(true);
        builder.setSafeState(this::stopMotor);
        builder.addDoubleProperty("Value", this::get, this::set);
        builder.addDoubleProperty("RPM(ReadOnly)", this.encoder::getVelocity, null);
    }
    
}
