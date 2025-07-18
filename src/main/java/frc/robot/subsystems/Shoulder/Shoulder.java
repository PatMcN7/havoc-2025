// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Shoulder;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shoulder extends SubsystemBase {
  /** Creates a new Shoulder. */
  private ShoulderIOInputsAutoLogged inputs = new ShoulderIOInputsAutoLogged();
  private ShoulderIO io;
  private DoubleSupplier shoulderAngleSupplier;

  private static Shoulder instance;
  public static Shoulder getInstance() {
    if (instance == null) {
    return instance = new Shoulder();
    }
    return instance;
  }
   

  public Shoulder() {
    switch (Constants.currentMode) {
      case REAL:
        io = new ShoulderIOReal();
        io.setMM(new MotionMagicConfigs().withMotionMagicAcceleration(9.).withMotionMagicCruiseVelocity(2));
        io.setPID(new Slot0Configs()
                    .withKP(65)//change these accoring to the shoulder motor
                    .withKG(0.45)
                    .withKS(0.2375)
                    .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseVelocitySign)); //this is used for a trapazoidal profile
        shoulderAngleSupplier = io.getShoulderSupplier();
        break;
    
      case SIM:
        io = new ShoulderIOSim();
        io.setPID(new Slot0Configs().withKP(1.5));
        shoulderAngleSupplier = io.getShoulderSupplier();
        break;
      case REPLAY:
        io = new ShoulderIO() {};
        break;
      default:
        System.out.println("What the sigma?");
        break;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    io.updateInputs(inputs);
    Logger.processInputs("Shoulder", inputs);
  }

  public void YOUSHALLNOTPASS() {
    io.STOP();
  }

  public void runAngle(double angle) {
    io.setAngle(angle);
  }

  public void setVoltage(double volts) {
    io.setVoltage(volts);
  }

  public void setPID(Slot0Configs configs) {
    io.setPID(configs);
  }

  public void setMM(MotionMagicConfigs mmConfigs) {
    io.setMM(mmConfigs);
  }

  public void resetAngle(double angle) {
    io.resetAngle(angle);
  }

  public void setMode(com.ctre.phoenix6.signals.NeutralModeValue mode) {
    io.setMode(mode);
  }

  public DoubleSupplier getShoulderSupplier() {
    return shoulderAngleSupplier;
  }

  public Command setAngle(DoubleSupplier angle) {
    return Commands.run(
        () -> {
          runAngle(angle.getAsDouble());
        },
        instance);
  }

  public double getVelocitydegpersec() {
    return Units.rotationsToDegrees(inputs.velocityRPM)/60;
  }

}
