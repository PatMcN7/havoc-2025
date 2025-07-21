// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.straightenator;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Straightenator extends SubsystemBase {
  private StraightenatorState currentState = StraightenatorState.IDLE;
  private StraightenatorState wantedState = StraightenatorState.IDLE;

  public enum StraightenatorState {
    IDLE,
    COLLECTING,
    EJECTING
  }

  public Straightenator() {}

  @Override
  public void periodic() {
    currentState = handleStateTransitions();
    applyStates();
  }

  private StraightenatorState handleStateTransitions() {
    return null;
  }

  private void applyStates() {}

  public void setWantedState(StraightenatorState state) {
    wantedState = state;
  }
}
