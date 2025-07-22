package frc.robot.subsystems.intake;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
  @AutoLog
  public static class IntakeIOInputs {
    public double DeployTemperature;
    public double DeployRPM;
    public double DeployAppliedVolts;
    public double DeployCurrent;
    public double DeployVelocity;
    public Rotation2d DeployPosition;
    public double RollersTemperature;
    public double RollersRPM;
    public double RollersAppliedVolts;
    public double RollersCurrent;
    public double RollersVelocity;
    public double RollersPosition;
  }

  public default void updateInputs(IntakeIOInputs inputs) {}

  public default void stopIntaking() {}

  public default void stopDeploy() {}

  public default void resetAngle() {}

  public default void runIntakeVolts(double volts) {}

  public default void setPivotAngle(Rotation2d angle) {}

  public default void setModeIntake(NeutralModeValue mode) {}

  public default void setModeIntakeDeploy(NeutralModeValue mode) {}
}
