package frc.robot.subsystems.gripper;

import org.littletonrobotics.junction.AutoLog;

public interface GripperIO {
  @AutoLog
  public static class GripperIOInput {

    public double velocityRPM;
    public double appliedVolts;
    public double torqueCurrent;
    public double supplyCurrent;
    public double temperatureC;
  }

  public default void updateInputs(GripperIOInput inputs) {}

  public default void runVolts(double volts) {}

  public default void stop() {}
}
