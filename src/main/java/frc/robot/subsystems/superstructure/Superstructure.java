// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.superstructure;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.gripper.Gripper;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shoulder.Shoulder;
import frc.robot.subsystems.straightenator.Straightenator;

public class Superstructure extends SubsystemBase {
  private final Drive drive;
  private final Elevator elevator;
  private final Intake intake;
  private final Shoulder shoulder;
  private final Straightenator straightenator;
  private final Gripper gripper;

  private SuperState currentSuperState = SuperState.HOME;
  private SuperState wantedSuperState = SuperState.HOME;

  public enum SuperState {
    HOME,
    STARTING,
    STOW,
    L1_LOW,
    L1_HIGH,
    L2_PRESCORE,
    L2_SCORE,
    L3_PRESCORE,
    L3_SCORE,
    L4_PRESCORE,
    L4_SCORE,
    ALGAE_STOW,
    BARGE_FORWARDS,
    BARGE_BACKWARDS,
    PROCESSOR,
    CRADLE_INTAKE,
    ALGAE_GROUND,
    ALGAE_L2,
    ALGAE_L3
  }

  public Superstructure(
      Drive drive,
      Elevator elevator,
      Intake intake,
      Shoulder shoulder,
      Straightenator straightenator,
      Gripper gripper) {
    this.drive = drive;
    this.elevator = elevator;
    this.intake = intake;
    this.shoulder = shoulder;
    this.straightenator = straightenator;
    this.gripper = gripper;
  }

  @Override
  public void periodic() {
    currentSuperState = handleStateTransitions();
    applyStates();
  }

  private SuperState handleStateTransitions() {
    switch (wantedSuperState) {
    }
    return null;
  }

  private void applyStates() {
    switch (currentSuperState) {
    }
  }

  private void setWantedState(SuperState state){
    wantedSuperState = state;
  }

  public Command setStateCommand(SuperState state){
    return Commands.runOnce(() -> setWantedState(state));
  }
}
