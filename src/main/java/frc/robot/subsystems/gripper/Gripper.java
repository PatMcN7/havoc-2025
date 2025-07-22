// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.gripper;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Gripper extends SubsystemBase {

  private GripperIOInputAutoLogged inputs = new GripperIOInputAutoLogged();
  private GripperIO io;
  private static Gripper instance;
  private GripperState currentState = GripperState.IDLE;
  private GripperState wantedState = GripperState.IDLE;

  public static Gripper getInstance() {
      if (instance == null) {
        return new Gripper();
      }
      else {
        return instance;
      }
    }

  public enum GripperState {
    IDLE,
    INTAKING,
    HAS_CORAL,
    HAS_ALGAE,
    SCORING
  }

  public Gripper() {
    switch (Constants.currentMode) {
      case REAL: 
        io = new GripperIOTalonFX();
        break;
      case SIM: 
        io = new GripperIOSim();
        break;
      case REPLAY: 
        io = new GripperIO() {};
        break;
        default: 
          System.out.println("Evan owes me $10");
          break;
    }
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Gripper", inputs);
    currentState = handleStateTransitions();
    applyStates();
  }

  public void stop() {
    io.stop();
  }

  public void runVolts(DoubleSupplier volts) {
    io.runVolts(volts.getAsDouble());
  }

  private GripperState handleStateTransitions() {
    return wantedState;
  }

  private void applyStates() {
    switch (currentState) {
      case IDLE: 
        io.stop();
        break;
      case INTAKING: 
        io.runVolts(GripperIOConstants.INTAKE_VOLTAGE);
        break;
      case HAS_CORAL: 
        io.runVolts(GripperIOConstants.CORAL_VOLTAGE);
        break;
      case HAS_ALGAE: 
        io.runVolts(GripperIOConstants.ALGAE_VOLTAGE); 
    }
  }

  public void setWantedState(GripperState state) {
    wantedState = state;
  }
}
