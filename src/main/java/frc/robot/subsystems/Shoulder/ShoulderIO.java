package frc.robot.subsystems.shoulder;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.signals.NeutralModeValue;
import org.littletonrobotics.junction.AutoLog;

public interface ShoulderIO {
  @AutoLog
  public static class ShoulderIOInputs {
    public double angleDeg;
    public double velocityRPM;
    public double voltage;
    public double torqueCurrent;
    public double statorCurrent;
  }

  public default void updateInputs(ShoulderIOInputs inputs) {}

  public default void setVoltage(double voltage) {}

  public default void setAngle(double angleDeg) {}

  public default void setPID(Slot0Configs pid) {}

  public default void setMode(NeutralModeValue mode) {}

  public default void setMotionMagic(MotionMagicConfigs motionMagic) {}

  public default void resetAngle(double angleDeg) {}
}
