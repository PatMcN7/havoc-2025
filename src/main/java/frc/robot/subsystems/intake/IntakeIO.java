package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

public class IntakeIO {
  @AutoLog
  public class CoralIOInputs {
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
