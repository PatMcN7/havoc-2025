package frc.robot.subsystems.straightenator;

import org.littletonrobotics.junction.AutoLog;

public interface StraightenatorIO {
  @AutoLog
  public static class StraightenatorIOInputs {
    public boolean backBeamBreak;
    public boolean frontBeamBreak;

    public double leftVelocityRPM;
    public double leftAppliedVolts;
    public double leftTorqueCurrent;
    public double leftSupplyCurrent;
    public double leftTemperatureCelsius;

    public double rightVelocityRPM;
    public double rightAppliedVolts;
    public double rightTorqueCurrent;
    public double rightSupplyCurrent;
    public double rightTemperatureCelsius;
  }

  public default void updateInputs(StraightenatorIOInputs inputs) {}

  public default void runVolts(double leftVolts, double rightVolts) {}

  public default void stop() {}
}
