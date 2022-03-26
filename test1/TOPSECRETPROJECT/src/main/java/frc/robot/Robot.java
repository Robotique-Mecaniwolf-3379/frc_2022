// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;



/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 2;
  private static final int kRearLeftChannel = 4;
  private static final int kFrontRightChannel = 1;
  private static final int kRearRightChannel = 3;
  private static final int kRamasseurChannel = 5;
  private static final int kLenceurChannel = 6;
  private static final int kJoystickChannel = 0;


  private MecanumDrive m_robotDrive;
  private Joystick m_stick;
 

  Spark m_ramasseur  = new Spark(kRamasseurChannel);
  Spark m_Lenceur  = new Spark(kLenceurChannel);


  @Override
  public void robotInit() {
    PWMSparkMax frontLeft = new PWMSparkMax(kFrontLeftChannel);
    PWMSparkMax rearLeft = new PWMSparkMax(kRearLeftChannel);
    PWMSparkMax frontRight = new PWMSparkMax(kFrontRightChannel);
    PWMSparkMax rearRight = new PWMSparkMax(kRearRightChannel);

    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontLeft.setInverted(true);
    rearRight.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_stick = new Joystick(kJoystickChannel);

      // Creates UsbCamera and MjpegServer [1] and connects them
    CameraServer.startAutomaticCapture();
 }

  @Override
  public void teleopPeriodic() {
    double vitesseRamasseur = 0;
    double vitesseLenceur = 0;
    // Use the joystick Z axis for lateral movement, Y axis for forward
    // movement, and X axis for rotation.
    m_robotDrive.driveCartesian(-m_stick.getY(), m_stick.getX(), m_stick.getZ(), 0.0);
    if (m_stick.getRawButton(1)) {
      vitesseLenceur = 1;
    }
    if (m_stick.getRawButton(2)) {
      vitesseRamasseur = -1;
    }
    if (m_stick.getRawButton(3)) {
      vitesseRamasseur += 1;
    }
    m_ramasseur.set(vitesseRamasseur * 0.7);
    m_Lenceur.set(vitesseLenceur * 0.4);
  }
}
