// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.gripper;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gripper extends SubsystemBase {
  private GripperState currentState = GripperState.IDLE;
  private GripperState wantedState = GripperState.IDLE;

  public enum GripperState {
    IDLE,
    INTAKING,
    HAS_CORAL,
    HAS_ALGAE,
    SCORING
  }

  public Gripper() {}

  @Override
  public void periodic() {
    currentState = handleStateTransitions();
    applyStates();
  }

  private GripperState handleStateTransitions() {
    return null;
  }

  private void applyStates() {}

  public void setWantedState(GripperState state) {
    wantedState = state;
  }
}
