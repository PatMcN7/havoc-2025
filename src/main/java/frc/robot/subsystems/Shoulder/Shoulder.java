// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shoulder;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shoulder extends SubsystemBase {
  private double wantedAngleDeg;
  private ShoulderIOInputsAutoLogged inputs = new ShoulderIOInputsAutoLogged();
  private ShoulderIO io;
  private static Shoulder instance;

  public enum ShoulderState {
    HOME,
    HOLDING,
    MOVING
  }

  private ShoulderState currentState = ShoulderState.HOLDING;
  private ShoulderState wantedState = ShoulderState.HOLDING;

  /** Creates a new Shoulder. */
  public Shoulder() {
    switch (Constants.currentMode) {
      case REAL:
        io = new ShoulderIOReal();
        io.setPID(ShoulderConstants.PIDConfigs);
        io.setMotionMagic(ShoulderConstants.MMConfigs);
        break;
      case SIM:
        io = new ShoulderIOSim();
        io.setPID(ShoulderConstants.PIDConfigs);
        break;
      case REPLAY:
        io = new ShoulderIO() {};
        break;
      default:
        System.out.println("AHHHHHHHH!! Shoulder need help being existing!!!!");
    }
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Shoulder", inputs);
    currentState = handleStateTransitions();
    applyStates();
  }

  private ShoulderState handleStateTransitions() {
    switch (wantedState) {
      case HOME:
        return ShoulderState.HOME;
      case HOLDING:
        if (MathUtil.isNear(inputs.angleDeg, ShoulderConstants.HomeAngle, 0.1)) {
          return ShoulderState.HOME;
        }
        return ShoulderState.HOLDING;
      case MOVING:
        if (MathUtil.isNear(inputs.angleDeg, wantedAngleDeg, 0.1)) {
          wantedState = ShoulderState.HOLDING;
          return ShoulderState.HOLDING;
        }
        return ShoulderState.MOVING;
      default:
        System.out.println("EEEEEK onknuwn sHuolDer StAte: " + wantedState);
        return currentState;
    }
  }

  private void applyStates() {
    switch (wantedState) {
      case HOME:
        io.setVoltage(8);
        if (inputs.torqueCurrent > 100 || inputs.velocityRPM < 1) {
          io.resetAngle(ShoulderConstants.MinAngle);
          wantedState = ShoulderState.MOVING;
          wantedAngleDeg = ShoulderConstants.HomeAngle;
        }
      case HOLDING:
        io.setAngle(inputs.angleDeg);
      case MOVING:
        io.setAngle(wantedAngleDeg);
      default:
        System.out.println("EEEEEK onknuwn sHuolDer StAte: " + wantedState);
        // return currentState;
    }
  }

  /**
   * @param state Wanted Shoulder State
   */
  public void setWantedState(ShoulderState state) {
    wantedState = state;
  }
  /**
   * @param state Wanted Shoulder State
   * @param angle Wanted Shoulder Angle
   */
  public void setWantedState(ShoulderState state, double angleDeg) {
    wantedState = state;
    wantedAngleDeg = angleDeg;
  }

  public Shoulder getInstance() {
    if (instance == null) {
      instance = new Shoulder();
    }
    return instance;
  }
}
