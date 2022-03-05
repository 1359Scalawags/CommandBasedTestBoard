package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Utilities;
import frc.robot.extensions.SendableCANSparkMax;


/**
 *
 */
public class DriveSystem extends SubsystemBase {
    private PIDController gyroControl;
    private SendableCANSparkMax leftMotor;
    private SendableCANSparkMax rightMotor;
    private DifferentialDrive differentialDrive;
    private ADXRS450_Gyro driveGyro;
    
    private RelativeEncoder leftEncoder;
    
    private RelativeEncoder rightEncoder;
    boolean reverse = false;

    public DriveSystem() {
        leftMotor =new SendableCANSparkMax(Constants.Drive.kLeftFrontPort, MotorType.kBrushless);

        leftMotor.restoreFactoryDefaults();
        leftMotor.setInverted(false);
        leftMotor.setIdleMode(IdleMode.kCoast);

        rightMotor =new SendableCANSparkMax(Constants.Drive.kRightFrontPort, MotorType.kBrushless);

        rightMotor.restoreFactoryDefaults();
        rightMotor.setInverted(true);
        rightMotor.setIdleMode(IdleMode.kCoast);

        differentialDrive = new DifferentialDrive(leftMotor, rightMotor);
        addChild("Differential Drive", differentialDrive);
        differentialDrive.setSafetyEnabled(true);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);

        driveGyro = new ADXRS450_Gyro();
        addChild("Gyro", driveGyro);
        // driveGyro.setSensitivity(0.007);
        driveGyro.calibrate();
       
        leftEncoder = leftMotor.getEncoder();
        rightEncoder = rightMotor.getEncoder();

        gyroControl = new PIDController(Constants.Drive.gyrokP, Constants.Drive.gyrokI, Constants.Drive.gyrokD);
        gyroControl.setIntegratorRange(-Constants.Drive.MaxIntegralRange, Constants.Drive.MaxIntegralRange);
        // SmartDashboard.putNumberArray("Gyro PID", new double[]{gyroControl.getP(), gyroControl.getI(), gyroControl.getD()});
        // SmartDashboard.putData("Gyro Control", gyroControl);
        SmartDashboard.putNumber("Gyro P", gyroControl.getP()*1000);
        SmartDashboard.putNumber("Gyro I", gyroControl.getI()*1000);
        SmartDashboard.putNumber("Gyro D", gyroControl.getD()*1000);
    }

    //CED even more gyro stuff
    public void ResetGyro(){
        driveGyro.reset();
      }
      public double getAngle() {
        return driveGyro.getAngle();
      }
      public double getGyroRate() {
        return driveGyro.getRate();
      }
    
      public void arcadeDrive(double moveSpeed, double maxTurnSpeed, double targetAngle) {
        double angleInput = driveGyro.getAngle();
        gyroControl.setSetpoint(targetAngle);
        double angleOutput = Utilities.Clamp(gyroControl.calculate(angleInput), -maxTurnSpeed, maxTurnSpeed);
        differentialDrive.arcadeDrive(moveSpeed, angleOutput);
       
      }
      public void reverseDirection() {
        if (reverse) {
            reverse = false;
        } else {
            reverse = true;
        }
    }
    public void tankDrive(double leftSpeed, double rightSpeed) {
      if (reverse) {
          differentialDrive.tankDrive(rightSpeed, leftSpeed);
      } else {
          differentialDrive.tankDrive(-leftSpeed, -rightSpeed);
      }
  }
  public void driveForward(double speed, double targetHeading) {
    final double scale = .01;
    double leftSpeed;
    double rightSpeed;
    double headingError = getAngle() - targetHeading;

    leftSpeed = Utilities.Clamp(Math.abs(speed) - headingError * scale,
    -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
    rightSpeed = Utilities.Clamp(Math.abs(speed) + headingError * scale,
    -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
    tankDrive(leftSpeed, rightSpeed);
}
public void stop() {
  tankDrive(0, 0);
}
public void driveBackward(double speed, double targetHeading) {
  final double scale = .01;
  double leftSpeed;
  double rightSpeed;
  double headingError = getAngle() - targetHeading;

  leftSpeed =Utilities.Clamp(-(speed) - headingError * scale,
  -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
  rightSpeed = Utilities.Clamp(-(speed) + headingError * scale,
  -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
  tankDrive(leftSpeed, rightSpeed);
}

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // double[] newvalues = SmartDashboard.getNumberArray("Gyro PID", new double[]{0,0,0});
        gyroControl.setP(SmartDashboard.getNumber("Gyro P", 0)/1000);
        gyroControl.setI(SmartDashboard.getNumber("Gyro I", 0)/1000);
        gyroControl.setD(SmartDashboard.getNumber("Gyro D", 0)/1000);

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }
    // public void move(double leftSpeed, double rightSpeed) {
    //     differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
    // }
  
    public double getDistanceLeft() {
      return leftEncoder.getPosition(); //TODO wich one of these  is a reversed value?
  }
  public double getDistanceRight() {
      return rightEncoder.getPosition();

  }
  
    public double getAverageDistance() {
      return (Math.abs(getDistanceRight()) + Math.abs(getDistanceLeft())) / 2;
  }

}
