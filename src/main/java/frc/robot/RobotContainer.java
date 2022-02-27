package frc.robot;

import frc.robot.Other.ManualOther;
import frc.robot.commands.*;
import frc.robot.commands.Ball.LoadBall;
import frc.robot.commands.Ball.ShootBall;
import frc.robot.commands.Climb.LockClimber;
import frc.robot.commands.Climb.LowerClimber;
import frc.robot.commands.Climb.ManuelClimber;
import frc.robot.commands.Climb.UnlockClimber;
// import frc.robot.commands.Climb.UnlockTraverse;
import frc.robot.commands.Drive.ManualDrive;
import frc.robot.commands.Drive.ReverseDrive;
import frc.robot.commands.Drive.TurnByAngle;
import frc.robot.commands.Drive.moveFoward;
import frc.robot.commands.Vision.UpdateTargets;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;


import frc.robot.commands.autonomous.AutoDrive;
import frc.robot.commands.autonomous.AutoShoot;
import frc.robot.commands.autonomous.Auto;
/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot
 * (including subsystems, commands, and button mappings) should be declared
 * here.
 */
public class RobotContainer {

  public static enum TestModes {
    Drive,
    Climb,
    BallHandle
  }
  public final static TestModes TEST_MODE = TestModes.BallHandle;

  private static RobotContainer m_robotContainer = new RobotContainer();

  // The robot's subsystems
  //public final OtherSystem m_otherSystem = new OtherSystem();
  public DriveSystem m_driveSystem;
  public final VisionSystem m_visionSystem = new VisionSystem();
  public ClimbSystem m_climbSystem;
  public BallHandlingSystem m_ballHandlingSystem;

  // Joysticks
  private final XboxController assistController = new XboxController(1);
  private final XboxController driverController = new XboxController(0);

  // A chooser for autonomous commands
 
  SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();
  SendableChooser<Integer> m_autoAngleChooser = new SendableChooser<Integer>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  private RobotContainer() {

    if (TEST_MODE == TestModes.Climb) {
      m_climbSystem = new ClimbSystem();
    } else if(TEST_MODE == TestModes.Drive) {
      m_driveSystem = new DriveSystem();
    } else {
      m_ballHandlingSystem = new BallHandlingSystem();
    }
    // Smartdashboard Subsystems

    // SmartDashboard Buttons
    if (TEST_MODE == TestModes.Climb) {
      SmartDashboard.putData("LockClimber", new LockClimber(m_climbSystem));
      SmartDashboard.putData("RaiseClimber", new ManuelClimber(m_climbSystem));
      SmartDashboard.putData("LowerClimber", new LowerClimber(m_climbSystem));
    }

    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    if (TEST_MODE == TestModes.Drive) {
      SmartDashboard.putData("ManualDrive", new ManualDrive(m_driveSystem));
    }

    SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();
    SendableChooser<Integer> m_autoAngleChooser = new SendableChooser<Integer>();


    m_autoChooser.addOption("AutoShoot", new AutoShoot(m_ballHandlingSystem));
    m_autoChooser.addOption("AutoDrive", new AutoDrive(m_driveSystem));
    m_autoChooser.addOption("AutoShootAndDrive", new Auto(m_driveSystem, m_ballHandlingSystem, m_visionSystem));

    m_autoAngleChooser.addOption("0", 0);
    m_autoAngleChooser.addOption("90", 90);
    m_autoAngleChooser.addOption("180", 180);
    m_autoAngleChooser.setDefaultOption("0", 0);
    // m_chooser.setDefaultOption("$command.getName()", new ${name.replace(' ',
    // '')}( m_${name.substring(0,1).toLowerCase()}${name.substring(1).replace(' ',
    // '')} ));

    SmartDashboard.putData("Auto Mode", m_autoChooser);
    SmartDashboard.putData("Auto Angle", m_autoAngleChooser);

    //SmartDashboard.putData("ManualServo", new ManualServo(m_otherSystem));
    //SmartDashboard.putData("ManualSparkMotor", new ManualSparkMotor(m_otherSystem));

    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    if (TEST_MODE == TestModes.Drive) {
      m_driveSystem.setDefaultCommand(new ManualDrive(m_driveSystem));
    }
   
    //m_otherSystem.setDefaultCommand(new ManualOther(m_otherSystem));
    //m_visionSystem.setDefaultCommand(new UpdateTargets(m_visionSystem));
    // Configure autonomous sendable chooser


  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  public XboxController getdriverController() {
    return driverController;
  }

  public XboxController getassistController() {
    return assistController;
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
    // Create some buttons

    


    final JoystickButton templateBtn = new JoystickButton(driverController, XboxController.Button.kA.value);
    templateBtn.whenPressed(new AutonomousCommand(), true);
    SmartDashboard.putData("TemplateBtn", new AutonomousCommand());

    final JoystickButton moveForwardBtn = new JoystickButton(assistController, XboxController.Button.kB.value);
    if (TEST_MODE == TestModes.Drive) {
      moveForwardBtn.whenPressed(new moveFoward(m_driveSystem, 100, 0.25), true);
      SmartDashboard.putData("moveForwardBtn", new moveFoward(m_driveSystem, 100, 0.25));
    }

    final JoystickButton turnByAngleBtn = new JoystickButton(driverController, XboxController.Button.kY.value);
    turnByAngleBtn.whenPressed(new TurnByAngle(m_driveSystem, 30));
    SmartDashboard.putData("turnByAngleBtn", new TurnByAngle(m_driveSystem, 30));

    final JoystickButton reverseDriveBtn = new JoystickButton(driverController, XboxController.Button.kBack.value);
    if (TEST_MODE == TestModes.Drive) {
      reverseDriveBtn.whenPressed(new ReverseDrive(m_driveSystem), true);
      SmartDashboard.putData("reverseDriveBtn", new ReverseDrive(m_driveSystem));
    }

    final JoystickButton lockClimberBtn = new JoystickButton(driverController, XboxController.Button.kX.value);
    if (TEST_MODE == TestModes.Climb) {
      lockClimberBtn.whenPressed(new LockClimber(m_climbSystem), true);
      SmartDashboard.putData("lockClimberBtn", new LockClimber(m_climbSystem));
    }

    final JoystickButton unlockClimberBtn = new JoystickButton(driverController, XboxController.Button.kA.value);
    if (TEST_MODE == TestModes.Climb) {
      unlockClimberBtn.whenPressed(new UnlockClimber(m_climbSystem), true);
      SmartDashboard.putData("unlockClimberBtn", new UnlockClimber(m_climbSystem));
    }

    final JoystickButton raiseClimberBtn = new JoystickButton(assistController, XboxController.Button.kA.value);
    if (TEST_MODE == TestModes.Climb) {
      raiseClimberBtn.whenPressed(new ManuelClimber(m_climbSystem), true);
      SmartDashboard.putData("raiseClimberBtn", new ManuelClimber(m_climbSystem));
    }

    final JoystickButton lowerClimberBtn = new JoystickButton(assistController, XboxController.Button.kA.value);
    if (TEST_MODE == TestModes.Climb) {
      lowerClimberBtn.whenPressed(new LowerClimber(m_climbSystem), true);
      SmartDashboard.putData("lowerClimberBtn", new LowerClimber(m_climbSystem));
    }
    
    final JoystickButton shootBallBtn = new JoystickButton(assistController, XboxController.Button.kA.value);
    shootBallBtn.whenPressed(new ShootBall(m_ballHandlingSystem), true);               
    SmartDashboard.putData("lowerClimberBtn", new ShootBall(m_ballHandlingSystem));

    final JoystickButton loadBallBtn = new JoystickButton(assistController, XboxController.Button.kB.value);
    if(TEST_MODE == TestModes.BallHandle) {
      loadBallBtn.whenPressed(new LoadBall(m_ballHandlingSystem), true);
      SmartDashboard.putData("loadBallBtn", new LoadBall(m_ballHandlingSystem));
    }

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_autoChooser.getSelected();
}

public Integer getAutonomousAngle() {
    return m_autoAngleChooser.getSelected();
}


}