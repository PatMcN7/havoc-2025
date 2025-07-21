package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

import com.ctre.phoenix6.signals.NeutralModeValue;

public interface IntakeIO {
  @AutoLog
  public class IntakeIOInputs {
    public double intakeTemperature;
    public double intakeRPM;
    public double intakeAppliedVolts;
    public double intakeCurrent;
    public double intakeVelocity;
    public double intakePosition;
  }

  public default void updateInputs(IntakeIOInputs inputs) {}

  public default void stopIntake() {}

  public default void resetAngle() {}

  public default void runIntakeVolts(double volts) {}

  public default void setPivotAngle(double angle) {}

  public default void setMode(NeutralModeValue mode) {}
}
