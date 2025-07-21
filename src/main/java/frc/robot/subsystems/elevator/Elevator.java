// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.elevator;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  private double wantedHeight;

  public enum ElevatorState {
    HOME,
    HOLDING,
    MOVING
  }

  private ElevatorState currentState = ElevatorState.HOLDING;
  private ElevatorState wantedState = ElevatorState.HOLDING;

  /** Creates a new Elevator. */
  public Elevator() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private ElevatorState handleStateTransitions() {
    return null;
  }

  private void applyStates() {}

  /**
   * @param state Wanted Elevator State
   */
  public void setWantedState(ElevatorState state) {
    wantedState = state;
  }
  /**
   * @param state Wanted Elevator State
   * @param height Wanted Elevator Height
   */
  public void setWantedState(ElevatorState state, double height) {
    wantedState = state;
    wantedHeight = height;
  }
}
