
package frc.robot;

import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.Timer; //TODO: Do we need to import a timer or not?

public class Constants {
    
    public static final boolean PRESSED = true;
    public static final boolean NOTPRESSED = false;
    public static final double TOLERANCE = .1;
   
   

    public static final class Drive {


        public static final double AutoMotorSpeed = 1;
        public static final double AutoMotorDistance = 1;
        public static final double AngleTurnBy = 0;
        //Drive motor ports
        public static final int kLeftFrontPort = 21;
        public static final int kRightFrontPort = 23;
        //Drive motor speeds
        public static final float kBoostSpeed = 0.6f;
        public static final float kMaxDriveSpeed = 0.85f;
        public static final float kBaseDriveSpeed = 0.4f;
        //Motor varibles
        public static final double PID_P = 0.00001;
        public static final double PID_I = 0.000005;
        public static final double PID_D = 0.0001;
        public static final double PID_Iz = 0;
        public static final double PID_Ff = 0;
        //Gyro varibles
        public static final double gyrokP =0.05;
        public static final double gyrokI =0.0000001;
        public static final double gyrokD =0.0;
        public static final double gyrokIz =0.0;
        public static final double gyrokFf =0.0;
        
        public static final double AutoStraightSpeed = 1;

        public static final double maxTurnSpeed = 0.5;

        public static final double MaxIntegralRange = 0.25;
    }

    public static final class Climb {
        //Climb Motors and Servos (Solenoids too)
        public static final int kClimbMotor = 23;
        //public static final int kTraverseMotor = 7;
        public static final int kAntiDropClimbServo = 9;
        //public static final int kAntiDropTraverseServo = 1;
        public static final int kClimberSolenoid = 9;
        //Climb Motor Speeds
        public static final float kClimbMotorSpeed = 0.5f;
        //public static final float kTraverseMotorSpeed = 0.5f;
        //gonna need actual values here
        //public static final float transferLockedServoPosition = 0f;
        //public static final float transferUnlockedServoPosition = 1f;
        public static final float kClimbServoLockPosition = 0f;
        public static final float kClimbServoUnlockPosition = 1f;


        public static final boolean kClimbLimitSwitch = true;
        public static final int kClimbLimitSwitchPort = 7;
        //public static final boolean kTraverseLimitSwitch = true;
        //public static final int kTraverseLimitSwitchPort = 4;
        public static final double kClimbConversionFactor = 1;
        //public static final double kTraverseConversionFactor = 1;
       
        public static final double kClimbHeightlimit = 20;
        //public static final double kTraverseHeightlimit = 20;
    }

    public static final class BallHandling {
        //Ball Handling Motors
        public static final int kLoadMotor1 = 7;
        public static final int kStagingMotor = 23;
        public static final int kShootMotor = 21;
        //Ball Motor Speeds
        public static final float kLoadMotorsSpeed = 0.5f;
        public static final float kStagingMotorSpeed = 0.5f;
        public static final float kShootMotorSpeed = 0.5f;
        public static final float kMinShootMotorSpeed = 0.95f;
        public static final float kShootMotorMaxRPM = 5000.0f;
        //Ball Timers
        public static final float shootTimerLength = 3f; //TODO: Change this later if needed
        public static final float reverseballTimer = 5f;

        public static final boolean BALLPRESENT = true;

        public static final double kP = 6e-5; 
        public static final double kI = 0;
        public static final double kD = 0; 
        public static final double kIz = 0; 
        public static final double kFF = 0.000015; 
        public static final int kMaxOutput = 1; 
        public static final int kMinOutput = -1;

        public static final int kloadinput = 7;        //TODO: Find out and put the actual port numbers for the digital inputs on the robot, here!!!
        public static final int kstaginginput = 5;

    }

    /**
     * public static final class DriveConstants {
     * public static final int kLeftMotor1Port = 0;
     * public static final int kLeftMotor2Port = 1;
     * public static final int kRightMotor1Port = 2;
     * public static final int kRightMotor2Port = 3;
     * }
     */
    
}
