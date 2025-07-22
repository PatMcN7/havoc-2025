package frc.robot.subsystems.gripper;

import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class GripperIOSim implements GripperIO {
    private FlywheelSim gripperSim;
    
    public GripperIOSim() {
        gripperSim = new FlywheelSim(LinearSystemId.createFlywheelSystem(DCMotor.getKrakenX60(1), 0, 0), DCMotor.getKrakenX60(1));
    }

    @Override
    public void updateInputs(GripperIOInput inputs) { 

        gripperSim.update(.02);
        inputs.appliedVolts = gripperSim.getInputVoltage();
        inputs.supplyCurrent = gripperSim.getCurrentDrawAmps();
        inputs.torqueCurrent = 0;
        inputs.temperatureC = 0.0;
        inputs.velocityRPM = gripperSim.getOutput(0);
    }

    @Override 
    public void runVolts(double volts) {
        gripperSim.setInputVoltage(volts);
    }

    @Override 
    public void stop() {
        gripperSim.setInputVoltage(0);
    }
}


