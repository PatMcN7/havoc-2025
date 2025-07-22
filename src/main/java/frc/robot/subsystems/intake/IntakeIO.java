package frc.robot.subsystems.intake;

import com.ctre.phoenix6.signals.NeutralModeValue;
import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
  @AutoLog
  public static class IntakeIOInputs {
    public double intakeDeployTemperature;
    public double intakeDeployRPM;
    public double intakeDeployAppliedVolts;
    public double intakeDeployCurrent;
    public double intakeDeployVelocity;
    public double intakeDeployPosition;
    public double intakeTemperature;
    public double intakeRPM;
    public double intakeAppliedVolts;
    public double intakeCurrent;
    public double intakeVelocity;
    public double intakePosition;
  }

  public default void updateInputs(IntakeIOInputs inputs) {}

  public default void stopIntaking() {}

  public default void stopDeploy() {}

  public default void resetAngle() {}

  public default void runIntakeVolts(double volts) {}

  public default void setPivotAngle(rotation2d angle) {}

  public default void setModeIntake(NeutralModeValue mode) {}

  public default void setModeIntakeDeploy(NeutralModeValue mode) {}
}
