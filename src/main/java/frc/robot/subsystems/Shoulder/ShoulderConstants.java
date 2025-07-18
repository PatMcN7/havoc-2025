// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Shoulder;

import frc.Motor_factories.Motors.CanDeviceID;
import frc.robot.Constants;

/** Add your docs here. */
public class ShoulderConstants {
  static CanDeviceID motorID =
      new CanDeviceID(0, Constants.CANAVOIR_NAME); // replace with actual motor ID
  static int encoderID = 0; // replace with actual encoder ID
}
