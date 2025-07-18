// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Shoulder;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.signals.NeutralModeValue;
import java.util.function.DoubleSupplier;
import org.littletonrobotics.junction.AutoLog;

/** Add your docs here. */
public interface ShoulderIO {
  @AutoLog
  public class ShoulderIOInputs {
    public double voltsApplied;
    public double tCurrent;
    public double scurrent;
    public double tempC;
    public double velocityRPM;
    public double posDeg;
    public double absolutePos;
  }

  public default void updateInputs(ShoulderIOInputs inputs) {}

  public default void setAngle(double angle) {}

  public default void setVoltage(double voltage) {}

  public default void STOP() {}

  public default void setPID(Slot0Configs PIDconfigs) {}

  public default void setMM(MotionMagicConfigs mmConfigs) {}

  public default void resetAngle(double angle) {}

  // public default void runAngleDynamic(double angle, double velo, double accel, double jerk) {}

  public default void setMode(NeutralModeValue mode) {}

  public default DoubleSupplier getShoulderSupplier() {
    return null;
  }
}
