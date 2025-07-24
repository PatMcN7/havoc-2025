package frc.robot.subsystems.shoulder;

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
            ShoulderConstants.MotorToArmRatio,
            10.0,
            0.5,
            Units.degreesToRadians(ShoulderConstants.MinAngle),
            Units.degreesToRadians(ShoulderConstants.MaxAngle),
            true,
            Units.degreesToRadians(ShoulderConstants.MinAngle),
            null);

    pid = new PIDController(1, 0, 0);
  }

  @Override
  public void updateInputs(ShoulderIOInputs inputs) {
    appliedVoltage = isClosedLoop ? pid.calculate(Units.degreesToRadians(inputs.angleDeg)) : appliedVoltage;
    inputs.voltage = appliedVoltage;
    sim.setInputVoltage(appliedVoltage);
    sim.update(0.02);
    inputs.angleDeg = Units.radiansToDegrees(sim.getAngleRads());
    inputs.velocityRPM = Units.radiansPerSecondToRotationsPerMinute(sim.getVelocityRadPerSec());
    inputs.statorCurrent = sim.getCurrentDrawAmps();
    inputs.torqueCurrent = sim.getCurrentDrawAmps();
  }

  @Override
  public void setVoltage(double voltage) {
    appliedVoltage = voltage;
    isClosedLoop = false;
  }

  @Override
  public void setAngle(double angleDeg) {
    pid.setSetpoint(Units.degreesToRadians(angleDeg));
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
  public void resetAngle(double angleDeg) {
    sim.setState(Units.degreesToRadians(angleDeg), sim.getVelocityRadPerSec());
  }

  @Override
  public void setMode(NeutralModeValue mode) {}
}
