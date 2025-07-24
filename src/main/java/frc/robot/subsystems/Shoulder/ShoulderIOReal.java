package frc.robot.subsystems.shoulder;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.Motor_factories.Motors.CanDeviceID;
import frc.Motor_factories.Motors.TalonFXFactory;

public class ShoulderIOReal implements ShoulderIO {
  private TalonFX shoulderMotor;

  public ShoulderIOReal() {
    CanDeviceID canDevice = new CanDeviceID(ShoulderConstants.MotorID, "CANIVORE 3");
    shoulderMotor = TalonFXFactory.createDefaultTalon(canDevice);
    TalonFXConfiguration config = new TalonFXConfiguration();
    SoftwareLimitSwitchConfigs limitConfig = new SoftwareLimitSwitchConfigs();
    limitConfig.withForwardSoftLimitEnable(true);
    limitConfig.withReverseSoftLimitEnable(true);
    limitConfig.withForwardSoftLimitThreshold(
        ShoulderConstants.MinAngle / 360 * ShoulderConstants.MotorToArmRatio);
    limitConfig.withReverseSoftLimitThreshold(
        ShoulderConstants.MaxAngle / 360 * ShoulderConstants.MotorToArmRatio);
    config.withSoftwareLimitSwitch(limitConfig);
  }

  @Override
  public void updateInputs(ShoulderIOInputs inputs) {
    inputs.angleDeg =
        shoulderMotor.getPosition().getValueAsDouble()
            * 360
            / ShoulderConstants.MotorToArmRatio; // Convert to radians
    inputs.velocityRPM = shoulderMotor.getVelocity().getValueAsDouble() * 60;
    inputs.voltage = shoulderMotor.getMotorVoltage().getValueAsDouble();
    inputs.torqueCurrent = shoulderMotor.getTorqueCurrent().getValueAsDouble();
    inputs.statorCurrent = shoulderMotor.getStatorCurrent().getValueAsDouble();
  }

  @Override
  public void setVoltage(double voltage) {
    shoulderMotor.setVoltage(voltage);
  }

  @Override
  public void setAngle(double angleDeg) {
    shoulderMotor.setControl(
        new MotionMagicVoltage(angleDeg / 360 * ShoulderConstants.MotorToArmRatio)
            .withEnableFOC(true));
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
  public void resetAngle(double angleDeg) {
    shoulderMotor.setPosition(angleDeg / 360);
  }

  @Override
  public void setMode(NeutralModeValue mode) {
    shoulderMotor.setNeutralMode(mode);
  }
}
