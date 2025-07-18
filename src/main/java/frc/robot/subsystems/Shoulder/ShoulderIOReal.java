// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Shoulder;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;
import frc.Motor_factories.Motors.TalonFXFactory;
import java.util.function.DoubleSupplier;

/** Add your docs here. */
public class ShoulderIOReal implements ShoulderIO {
  TalonFX shoulder;
  CANcoder encoder;
  private StatusSignal<Angle> shoulderAngle;

  ShoulderIOReal() {
    shoulder = TalonFXFactory.createDefaultTalon(ShoulderConstants.motorID);
    encoder = new CANcoder(ShoulderConstants.encoderID, "CANAVOIR 3");
    MotorOutputConfigs configs = new MotorOutputConfigs();
    configs.DutyCycleNeutralDeadband = 0.0;
    configs.Inverted = InvertedValue.Clockwise_Positive;
    configs.NeutralMode = NeutralModeValue.Brake;
    shoulder.getConfigurator().apply(configs);

    CANcoderConfiguration econfig = new CANcoderConfiguration();
    econfig.MagnetSensor.withAbsoluteSensorDiscontinuityPoint(0.5);
    econfig.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;
    econfig.MagnetSensor.withMagnetOffset(0.415039); // todo change this
    encoder.getConfigurator().apply(econfig);

    shoulderAngle = shoulder.getPosition();
    shoulderAngle.setUpdateFrequency(500);
  }

  @Override
  public void updateInputs(ShoulderIOInputs inputs) {
    inputs.voltsApplied = shoulder.getMotorVoltage().getValueAsDouble();
    inputs.tCurrent = shoulder.getTorqueCurrent().getValueAsDouble();
    inputs.scurrent = shoulder.getStatorCurrent().getValueAsDouble();
    inputs.tempC = shoulder.getDeviceTemp().getValueAsDouble();
    inputs.velocityRPM = shoulder.getVelocity().getValueAsDouble();
    inputs.posDeg = (Units.rotationsToDegrees(shoulder.getPosition().getValueAsDouble()));
    inputs.absolutePos = encoder.getAbsolutePosition().getValueAsDouble();
  }

  @Override
  public void STOP() {
    shoulder.stopMotor();
  }

  @Override
  public void setAngle(double angle) {
    shoulder.setControl(
        new MotionMagicVoltage(Units.radiansToRotations(angle)).withEnableFOC(true));
  }

  @Override
  public void setVoltage(double volts) {
    shoulder.setVoltage(volts);
  }

  @Override
  public void setPID(Slot0Configs configs) {
    configs.GravityType = GravityTypeValue.Arm_Cosine;
    shoulder.getConfigurator().apply(configs);
  }

  @Override
  public void setMM(MotionMagicConfigs mmconfigs) {
    shoulder.getConfigurator().apply(mmconfigs);
  }

  @Override
  public void resetAngle(double angle) {
    shoulder.setPosition(Units.radiansToRotations(angle));
  }

  @Override
  public void setMode(NeutralModeValue mode) {
    shoulder.setNeutralMode(mode);
  }

  @Override
  public DoubleSupplier getShoulderSupplier() {
    return () -> (Units.rotationsToDegrees(shoulder.getPosition().getValueAsDouble()) - 90);
  }
}
