package frc.robot.subsystems.superstructure;

import edu.wpi.first.math.geometry.Rotation2d;

public class RobotPose {
  private double elevatorHeight;
  private Rotation2d shoulderAngle;

  public RobotPose(double elevatorHeight, Rotation2d shoulderAngle) {
    this.elevatorHeight = elevatorHeight;
    this.shoulderAngle = shoulderAngle;
  }

  public double getElevatorHeight() {
    return elevatorHeight;
  }

  public Rotation2d getShoulderAngle() {
    return shoulderAngle;
  }
}
