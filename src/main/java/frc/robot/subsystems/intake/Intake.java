// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private IntakeState currentState = IntakeState.HOME;
  private IntakeState wantedState = IntakeState.HOME;

  public enum IntakeState {
    COLLECTING,
    STOW,
    HOME,
    EJECTING
  }

  public Intake() {}

  @Override
  public void periodic() {
    currentState = handleStateTransitions();
    applyStates();
  }

  private IntakeState handleStateTransitions() {
    return null;
  }

  private void applyStates() {}

  public void setWantedState(IntakeState state) {
    wantedState = state;
  }
}
