package frc.robot.subsystems.gripper;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;


public class GripperIOTalonFX implements GripperIO {
    private final TalonFX gripperMotor;

    public GripperIOTalonFX() {
       gripperMotor = new TalonFX(0);

       TalonFXConfiguration config = new TalonFXConfiguration();
       config.CurrentLimits.StatorCurrentLimit = 100;
       config.CurrentLimits.SupplyCurrentLimit = 100;
       config.CurrentLimits.SupplyCurrentLimitEnable = true;
       config.CurrentLimits.StatorCurrentLimitEnable = true;
       gripperMotor.getConfigurator().apply(config);
    }
    
    @Override
    public void updateInputs(GripperIOInput inputs) {
        inputs.appliedVolts = gripperMotor.getMotorVoltage().getValueAsDouble();
        inputs.supplyCurrent = gripperMotor.getSupplyCurrent().getValueAsDouble();
        inputs.torqueCurrent = gripperMotor.getStatorCurrent().getValueAsDouble();
        inputs.temperatureC = gripperMotor.getDeviceTemp().getValueAsDouble();
        inputs.velocityRPM = gripperMotor.getVelocity().getValueAsDouble() * 60;
    }

    @Override
    public void runVolts(double volts) { 
        gripperMotor.setControl(new VoltageOut(volts).withEnableFOC(true));
    }

    @Override
    public void stop() {
        gripperMotor.stopMotor();
    }
}


