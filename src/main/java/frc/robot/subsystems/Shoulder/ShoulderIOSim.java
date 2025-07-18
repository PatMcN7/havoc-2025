// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Shoulder;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;


/** Add your docs here. */
public class ShoulderIOSim implements ShoulderIO {
    private SingleJointedArmSim shoulder;
    private boolean isClosedLoop;
    private double applyedVoltage;
    private PIDController pid;

    public ShoulderIOSim() {
        shoulder = new SingleJointedArmSim(DCMotor.getFalcon500(1),
             10, 
             0.003, 
             0.8, 
             Units.degreesToRadians(-160), 
             Units.degreesToRadians(160), 
             false, 
             0);
        pid = new PIDController(1.0, 0, 0);
    }

    @Override
    public void updateInputs(ShoulderIOInputs inputs) {
        if (isClosedLoop) applyedVoltage = pid.calculate(shoulder.getAngleRads());
        shoulder.setInputVoltage(applyedVoltage);
        shoulder.update(0.02); // Simulate at 50Hz
        inputs.absolutePos = 0;
        inputs.voltsApplied = applyedVoltage;
        inputs.posDeg = Units.radiansToDegrees(shoulder.getAngleRads());
        inputs.scurrent = shoulder.getCurrentDrawAmps();
        inputs.tCurrent = 0;
        inputs.tempC = 0;
        inputs.velocityRPM = Units.radiansPerSecondToRotationsPerMinute(shoulder.getVelocityRadPerSec());
    }

    @Override
    public void STOP() {
        applyedVoltage = 0;
        isClosedLoop = false;
    }

    @Override
    public void setAngle(double angle) {
        isClosedLoop = true;
        pid.setSetpoint((angle));
    }

    @Override
    public void setVoltage(double volts) {
        isClosedLoop = false;
        applyedVoltage = volts;
    }

    @Override 
    public void setPID(Slot0Configs configs) {
        pid.setP(configs.kP);
        pid.setI(configs.kI);
        pid.setD(configs.kD);
    }

    @Override
    public void setMM(MotionMagicConfigs mmConfigs) {}

    @Override
    public void resetAngle(double angle) {
        shoulder.setState(angle, shoulder.getVelocityRadPerSec());
    }

    @Override
    public DoubleSupplier getShoulderSupplier() {
        return () -> Units.radiansToDegrees(shoulder.getAngleRads());
    }
}
