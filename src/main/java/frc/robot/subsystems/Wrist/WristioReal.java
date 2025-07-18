package frc.robot.subsystems.Wrist;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Angle;
import frc.Motor_factories.Motors.CanDeviceID;
import frc.Motor_factories.Motors.TalonFXFactory;


public class WristioReal implements WristIO {
    TalonFX wrist;
    CANcoder encoder;
    private StatusSignal<Angle> wristAngle;
    public WristIOReal() {
        wrist = TalonFXFactory.createDefaultTalon(new CanDeviceID(0)); //change

    }
}
