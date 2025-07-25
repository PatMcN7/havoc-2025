// Copyright (c) 2023 FRC 254
// https://github.com/Team254/FRC-2023-Public
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.Motor_factories.Motors;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.*;

/**
 * Creates FXTalons objects and configures all the parameters we care about to factory defaults.
 * Closed-loop and sensor parameters are not set, as these are expected to be set by the
 * application.
 */
public class TalonFXFactory {

  public static final NeutralModeValue NEUTRAL_MODE = NeutralModeValue.Brake;
  public static final InvertedValue INVERT_VALUE = InvertedValue.CounterClockwise_Positive;
  public static final double NEUTRAL_DEADBAND = 0.04;

  // create a TalonFX with the default (out of the box) configuration
  public static TalonFX createDefaultTalon(CanDeviceID id) {
    return createDefaultTalon(id, true);
  }

  public static TalonFX createDefaultTalon(CanDeviceID id, boolean triggerConfig) {
    var talon = createTalon(id);
    if (triggerConfig) {
      Phoenix6Util.applyAndCheckConfiguration(talon, getDefaultConfig());
    }
    return talon;
  }

  public static TalonFX createPermanentFollowerTalon(
      CanDeviceID followerId, CanDeviceID masterId, boolean opposeMasterDirection) {
    if (!followerId.getBus().equals(masterId.getBus())) {
      throw new RuntimeException("Master and Follower TalonFXs must be on the same CAN bus.");
    }
    final TalonFX talon = createTalon(followerId);
    talon.setControl(new Follower(masterId.getDeviceNumber(), opposeMasterDirection));
    return talon;
  }

  public static TalonFXConfiguration getDefaultConfig() {
    TalonFXConfiguration config = new TalonFXConfiguration();

    config.MotorOutput.NeutralMode = NEUTRAL_MODE;
    config.MotorOutput.Inverted = INVERT_VALUE;
    config.MotorOutput.DutyCycleNeutralDeadband = NEUTRAL_DEADBAND;
    config.MotorOutput.PeakForwardDutyCycle = 1.0;
    config.MotorOutput.PeakReverseDutyCycle = -1.0;

    // All TalonFx motors must use this current limiting configuration to ensure protection of the
    // motors
    config.CurrentLimits.StatorCurrentLimit = 80;
    config.CurrentLimits.SupplyCurrentLimit = 105;
    config.CurrentLimits.SupplyCurrentLimitEnable = true;
    config.CurrentLimits.StatorCurrentLimitEnable = true;

    config.SoftwareLimitSwitch.ForwardSoftLimitEnable = false;
    config.SoftwareLimitSwitch.ForwardSoftLimitThreshold = 0;
    config.SoftwareLimitSwitch.ReverseSoftLimitEnable = false;
    config.SoftwareLimitSwitch.ReverseSoftLimitThreshold = 0;

    config.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RotorSensor;
    config.Feedback.FeedbackRotorOffset = 0;
    config.Feedback.SensorToMechanismRatio = 1;

    config.HardwareLimitSwitch.ForwardLimitEnable = false;
    config.HardwareLimitSwitch.ForwardLimitAutosetPositionEnable = false;
    config.HardwareLimitSwitch.ForwardLimitSource = ForwardLimitSourceValue.LimitSwitchPin;
    config.HardwareLimitSwitch.ForwardLimitType = ForwardLimitTypeValue.NormallyOpen;
    config.HardwareLimitSwitch.ReverseLimitEnable = false;
    config.HardwareLimitSwitch.ReverseLimitAutosetPositionEnable = false;
    config.HardwareLimitSwitch.ReverseLimitSource = ReverseLimitSourceValue.LimitSwitchPin;
    config.HardwareLimitSwitch.ReverseLimitType = ReverseLimitTypeValue.NormallyOpen;

    config.Audio.BeepOnBoot = false;

    return config;
  }

  private static TalonFX createTalon(CanDeviceID id) {
    TalonFX talon = new TalonFX(id.getDeviceNumber(), id.getBus());
    talon.clearStickyFaults();
    talon.setNeutralMode(NeutralModeValue.Brake);
    return talon;
  }

  private TalonFXFactory() {}
}
