package frc.robot.subsystems.shoulder;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;

public class ShoulderConstants {
  public static final double MotorToArmRatio = 45.0; // Invented
  public static final double MaxAngle = 180; // Invented
  public static final double MinAngle = 0; // Invented
  public static final int MotorID = 0; // Invented
  public static final double HomeAngle = 40;
  public static final Slot0Configs PIDConfigs =
      new Slot0Configs().withKP(10.0).withKI(0.0).withKD(0.0);
  public static final MotionMagicConfigs MMConfigs =
      new MotionMagicConfigs()
          .withMotionMagicCruiseVelocity(2)
          .withMotionMagicAcceleration(1)
          .withMotionMagicJerk(0);
}
