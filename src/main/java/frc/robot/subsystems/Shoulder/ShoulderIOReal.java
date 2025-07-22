package frc.robot.subsystems.Shoulder;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ShoulderIOReal implements ShoulderIO {
  private TalonFX shoulderMotor;

  public ShoulderIOReal() {
    shoulderMotor = new TalonFX(ShoulderConstants.MotorID);
    TalonFXConfiguration config = new TalonFXConfiguration();
    SoftwareLimitSwitchConfigs limitConfig = new SoftwareLimitSwitchConfigs();
    limitConfig.withForwardSoftLimitEnable(true);
    limitConfig.withReverseSoftLimitEnable(true);
    limitConfig.withForwardSoftLimitThreshold(
        ShoulderConstants.MaxAngle * ShoulderConstants.GearRatio);
    limitConfig.withReverseSoftLimitThreshold(
        ShoulderConstants.MinAngle * ShoulderConstants.GearRatio);
    config.withSoftwareLimitSwitch(limitConfig);
  }

  @Override
  public void updateInputs(ShoulderIOInputs inputs) {
    inputs.angle =
        shoulderMotor.getPosition().getValueAsDouble()
            / ShoulderConstants.GearRatio; // Convert to radians
    inputs.velocity = shoulderMotor.getVelocity().getValueAsDouble();
    inputs.voltage = shoulderMotor.getMotorVoltage().getValueAsDouble();
  }

  @Override
  public void setVoltage(double voltage) {
    shoulderMotor.setVoltage(voltage);
  }

  @Override
  public void setAngle(double angle) {
    shoulderMotor.setControl(new MotionMagicVoltage(angle * ShoulderConstants.GearRatio));
  }

  @Override
  public void setPID(Slot0Configs pidConfig) {
    shoulderMotor.getConfigurator().apply(pidConfig);
  }

  @Override
  public void setMotionMagic(MotionMagicConfigs motionMagic) {
    shoulderMotor.getConfigurator().apply(motionMagic);
  }

  @Override
  public void resetAngle(double angle) {
    shoulderMotor.setPosition(angle * ShoulderConstants.GearRatio);
  }

  @Override
  public void setMode(NeutralModeValue mode) {
    shoulderMotor.setNeutralMode(mode);
  }
}
