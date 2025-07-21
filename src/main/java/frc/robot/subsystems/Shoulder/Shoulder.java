// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shoulder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shoulder extends SubsystemBase {
  private Rotation2d wantedAngle;

  public enum ShoulderState {
    HOME,
    HOLDING,
    MOVING
  }

  private ShoulderState currentState = ShoulderState.HOLDING;
  private ShoulderState wantedState = ShoulderState.HOLDING;

  /** Creates a new Shoulder. */
  public Shoulder() {}

  @Override
  public void periodic() {
    currentState = handleStateTransitions();
    applyStates();
  }

  private ShoulderState handleStateTransitions() {
    return null;
  }

  private void applyStates() {}

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
}
