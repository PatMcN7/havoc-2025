package frc.robot.subsystems.gripper;

import com.ctre.phoenix6.signals.NeutralModeValue;
import org.littletonrobotics.junction.AutoLog;

public interface GripperIO {
  @AutoLog
  public class GripperIOInput {
    public boolean beambreak;

    public double velocityRPM;
    public double appliedVolts;
    public double tourqueCurrent;
    public double supplyCurrent;
    public double temperature;
  }

  public default void updateInputs(GripperIOInput inputs) {}

  public default void runVolts(double volts) {}

  public default void stop() {}

  public default void setMode(NeutralModeValue mode) {}
}
