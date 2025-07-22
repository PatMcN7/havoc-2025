// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Shoulder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shoulder extends SubsystemBase {
  private Rotation2d wantedAngle;
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
    switch(Constants.currentMode){
      case REAL:
        io = new ShoulderIOReal();
        break;
      case SIM:
        io = new ShoulderIOSim();
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
    currentState = handleStateTransitions();
    applyStates();
  }

  private ShoulderState handleStateTransitions() {
    switch (wantedState) {
      case HOME:
        return ShoulderState.HOME;
      case HOLDING:
        return ShoulderState.HOLDING;
      case MOVING:
        return ShoulderState.MOVING;
      default:
        System.out.println("EEEEEK onknuwn sHuolDer StAte: " + wantedState);
        return currentState;
    }
  }

  private void applyStates() {
    switch (wantedState) {
      case HOME:
        io.setAngle(ShoulderConstants.homeAngle);
      case HOLDING:
        io.setAngle(wantedAngle.getRotations() * ShoulderConstants.GearRatio);
      case MOVING:
        io.setAngle(wantedAngle.getRotations() * ShoulderConstants.GearRatio);
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
  public void setWantedState(ShoulderState state, Rotation2d angle) {
    wantedState = state;
    wantedAngle = angle;
  }

  public Shoulder getInstance() {
    if (instance == null) {
      instance = new Shoulder();
    }
    return instance;
  }
}
