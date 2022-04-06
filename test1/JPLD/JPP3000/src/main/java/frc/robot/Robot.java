// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {

  private final PWMSparkMax m_leftMotor = new PWMSparkMax(0);
  private final PWMSparkMax m_rightMotor = new PWMSparkMax(1);
  private DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private final Joystick m_stick = new Joystick(kJoystickChannel);
  private static final int kRamasseurChannel = 5;
  private static final int kLenceurChannel = 6;
  private static final int kJoystickChannel = 0;

  Spark m_ramasseur = new Spark(kRamasseurChannel);
  Spark m_Lenceur = new Spark(kLenceurChannel);

  private final Timer m_timer = new Timer();
  private String m_autoName;

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotor.setInverted(true);
    m_leftMotor.setInverted(true);

    // Creates UsbCamera and MjpegServer [1] and connects them
    CameraServer.startAutomaticCapture();
    SmartDashboard.putStringArray("Auto List", new String[] { "safe", "fanci", "nothing", "fanci cote echelle", "fanci cotep terminal" });
  }
/** This function is run once each time the robot enters autonomous mode. */
@Override
public void autonomousInit() {
  m_autoName = SmartDashboard.getString("Auto Selector", ""); // This would make "Drive Forwards the default auto
  
  m_timer.reset();
  m_timer.start();
}

/** This function is called periodically during autonomous. */
@Override
public void autonomousPeriodic() {
  if (m_autoName.equals("safe")) { // Mode selected
    if (m_timer.get() > 1.0 && m_timer.get() < 3.0) { // Condition for the robot to drive
      m_robotDrive.arcadeDrive(-3.0, 0.0); // drive forwards half speed
    } else {                                             // if the timer doesn't fulfill the condition
      m_robotDrive.stopMotor(); // stop robot
    }
    if (m_timer.get() < 1.0) {
      m_Lenceur.set(0.7); // drive forwards half speed
    } else {
      m_Lenceur.stopMotor(); // stop robot
    }
  }
  if (m_autoName.equals("fanci")) {
    if (m_timer.get() < 1.0) {
      m_Lenceur.set(0.7);
    } else if (m_timer.get() > 9.0 && m_timer.get() < 10.0) {
      m_Lenceur.set(0.7);
    } else {
      m_Lenceur.stopMotor();
    }

    if (m_timer.get() > 4.5 && m_timer.get() < 5.5) {
      m_ramasseur.set(0.7);
    } else {
      m_ramasseur.stopMotor();
    }

    if (m_timer.get() > 1.0 && m_timer.get() < 3.0) {
      m_robotDrive.arcadeDrive( -0.3, 0.0); // drive forwards half speed
    } else if (m_timer.get() > 3.0 && m_timer.get() < 4.0) {
      m_robotDrive.arcadeDrive( 0.0, -0.3); // drive forwards half speed
    } else if (m_timer.get() > 4.0 && m_timer.get() < 5.0) {
      m_robotDrive.arcadeDrive( 0.3, 0.0); // drive forwards half speed
    } else if (m_timer.get() > 5.0 && m_timer.get() < 6.0) {
      m_robotDrive.arcadeDrive( -0.3, 0.0); // drive forwards half speed
    } else if (m_timer.get() > 6.0 && m_timer.get() < 7.0) {
      m_robotDrive.arcadeDrive( 0.0, 0.3); // drive forwards half speed
    } else if (m_timer.get() > 7.0 && m_timer.get() < 9.0) {
      m_robotDrive.arcadeDrive( 0.3, 0.0); // drive forwards half speed
    } else {
      m_robotDrive.stopMotor(); // stop robot
    }
  }
  if (m_autoName.equals("fanci cote echelle")) {
    if (m_timer.get() > 1.0 && m_timer.get() < 4.1) {
      // drive backwards 100 inches at half speed
      m_robotDrive.arcadeDrive( -0.3, 0.0); 
    } else if (m_timer.get() > 4.2 && m_timer.get() < 4.85) {
       // turn left 70 degrees
      m_robotDrive.arcadeDrive( 0.0, -0.3); 
    } else if (m_timer.get() > 5.0 && m_timer.get() < 6.5) {
      // drive forwards 30 inches at half speed
      m_robotDrive.arcadeDrive( 0.3, 0.0); 
    } else if (m_timer.get() > 6.6 && m_timer.get() < 8.1) {
      // drive backwards 30 inches at half speed
      m_robotDrive.arcadeDrive( -0.3, 0.0); 
    } else if (m_timer.get() > 8.2 && m_timer.get() < 8.9) {
       // turn right 70 degrees
      m_robotDrive.arcadeDrive( 0.0, 0.3); 
    } else if (m_timer.get() > 8.95 && m_timer.get() < 12.05) {
      // drive forwards 100 inches at half speed
      m_robotDrive.arcadeDrive( 0.3, 0.0); 
    } else {
      m_robotDrive.stopMotor(); // stop robot
    } 
    if (m_timer.get() < 1.0) {
      m_Lenceur.set(0.7);
    } else if (m_timer.get() > 12.15 && m_timer.get() < 13.0) {
      m_Lenceur.set(0.7);
    } else {
      m_Lenceur.stopMotor();
    }
    if (m_timer.get() > 6.0 && m_timer.get() < 6.5) {
      m_ramasseur.set(0.7);
    } else {
      m_ramasseur.stopMotor();
    }
  }
  if (m_autoName.equals("fanci cote terminal")) {
    if (m_timer.get() > 1.0 && m_timer.get() < 4.1) {
      // drive backwards 100 inches at half speed
      m_robotDrive.arcadeDrive( -0.3, 0.0); 
    } else if (m_timer.get() > 4.2 && m_timer.get() < 4.85) {
       // turn right 70 degrees
      m_robotDrive.arcadeDrive( 0.0, 0.3); 
    } else if (m_timer.get() > 5.0 && m_timer.get() < 6.5) {
      // drive forwards 30 inches at half speed
      m_robotDrive.arcadeDrive( 0.3, 0.0); 
    } else if (m_timer.get() > 6.6 && m_timer.get() < 8.1) {
      // drive backwards 30 inches at half speed
      m_robotDrive.arcadeDrive( -0.3, 0.0); 
    } else if (m_timer.get() > 8.2 && m_timer.get() < 8.9) {
       // turn left 70 degrees
      m_robotDrive.arcadeDrive( 0.0, -0.3); 
    } else if (m_timer.get() > 8.95 && m_timer.get() < 12.05) {
      // drive forwards 100 inches at half speed
      m_robotDrive.arcadeDrive( 0.3, 0.0); 
    } else {
      m_robotDrive.stopMotor(); // stop robot
    } 
    if (m_timer.get() < 1.0) {
      m_Lenceur.set(0.7);
    } else if (m_timer.get() > 12.15 && m_timer.get() < 13.0) {
      m_Lenceur.set(0.7);
    } else {
      m_Lenceur.stopMotor();
    }
    if (m_timer.get() > 6.0 && m_timer.get() < 6.5) {
      m_ramasseur.set(0.7);
    } else {
      m_ramasseur.stopMotor();
    }
  }
}
  @Override
  public void teleopPeriodic() {
    double vitesseRamasseur = 0;
    double vitesseLenceur = 0;
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(-m_stick.getY(), m_stick.getX());
    if (m_stick.getRawButton(1)) {
      vitesseLenceur = 1;
    }
    if (m_stick.getRawButton(2)) {
      vitesseRamasseur = -1;
    }
    if (m_stick.getRawButton(3)) {
      vitesseRamasseur += 1;
    }
    // ajuster constante selon le montage final
    m_ramasseur.set(vitesseRamasseur * 0.553379);
    m_Lenceur.set(vitesseLenceur * 0.703379);
  }
  
}
