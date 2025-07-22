package frc.robot.subsystems.Shoulder;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class ShoulderIOSim implements ShoulderIO {

  SingleJointedArmSim sim;
  private boolean isClosedLoop = false;
  private PIDController pid;
  private double appliedVoltage;

  public ShoulderIOSim() {
    sim =
        new SingleJointedArmSim(
            DCMotor.getFalcon500(1),
            ShoulderConstants.GearRatio,
            10.0,
            0.5,
            ShoulderConstants.MinAngle,
            ShoulderConstants.MaxAngle,
            true,
            ShoulderConstants.MinAngle,
            null);

    pid = new PIDController(1, 0, 0);
  }

  @Override
  public void updateInputs(ShoulderIOInputs inputs) {
    sim.update(0.02);
    inputs.angle = sim.getAngleRads();
    inputs.velocity = sim.getVelocityRadPerSec();
    appliedVoltage = isClosedLoop ? pid.calculate(inputs.angle) : appliedVoltage;
    inputs.voltage = appliedVoltage;
    inputs.statorCurrent = sim.getCurrentDrawAmps();
    inputs.torqueCurrent = sim.getCurrentDrawAmps();
  }

  @Override
  public void setVoltage(double voltage) {
    appliedVoltage = voltage;
    isClosedLoop = false;
  }

  @Override
  public void setAngle(double angle) {
    pid.setSetpoint(angle);
    isClosedLoop = true;
  }

  @Override
  public void setPID(Slot0Configs pidConfig) {
    pid.setP(pidConfig.kP);
    pid.setI(pidConfig.kI);
    pid.setD(pidConfig.kD);
  }

  @Override
  public void setMotionMagic(MotionMagicConfigs motionMagic) {}

  @Override
  public void resetAngle(double angle) {
    sim.setState(Units.degreesToRadians(angle), sim.getVelocityRadPerSec());
  }

  @Override
  public void setMode(NeutralModeValue mode) {}
}
