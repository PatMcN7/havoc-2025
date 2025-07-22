package frc.robot.subsystems.intake;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
  @AutoLog
  public static class IntakeIOInputs {
    public double deployTemperature;
    public double deployRPM;
    public double deployAppliedVolts;
    public double deployCurrent;
    public double deployVelocity;
    public Rotation2d deployPosition;
    public double rollersTemperature;
    public double rollersRPM;
    public double rollersAppliedVolts;
    public double rollersCurrent;
    public double rollersVelocity;
    public double rollersPosition;
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
