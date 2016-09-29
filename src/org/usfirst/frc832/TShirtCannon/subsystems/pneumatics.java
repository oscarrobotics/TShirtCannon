package org.usfirst.frc832.TShirtCannon.subsystems;
import org.usfirst.frc832.TShirtCannon.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import org.usfirst.frc832.TShirtCannon.Robot;

public class pneumatics extends Subsystem {
    Compressor compressors = RobotMap.pneumaticscompressors;
    Solenoid fillCannon = RobotMap.fillCannon;
    public static boolean isDone;
    public void initDefaultCommand() {
    }
    
    public static double throttleToPSI() {
        double input_start = 1.0; // Bottom of throttle range
        double input_end = -1.0; // Top of throttle range
        double output_start = 60; // Lowest PSI we want to set to
        double output_end = 120; // Highest PSI we want to set to
        double input = Robot.oi.controlStick.getThrottle();
        double slope = 1.0 * (output_end - output_start) / (input_end - input_start);
        double val = output_start + slope * (input - input_start);
        val = (int)Math.floor(val + 0.5f); // Round it to the nearest single PSI
        return val;
    }
    public void runToPSI() {
        isDone = false; // May not be needed
        double setpoint = throttleToPSI(); // gets setpoint
        final double heldsetpoint = setpoint; // holds setpoint
        double actualPSI = i2cPSI.PSIData();
        while(!((Math.abs(actualPSI - heldsetpoint) <=2) || actualPSI >= heldsetpoint)) { // while not at (setpoint +- 2 or above setpoint)
            RobotMap.fillCannon.set(true); // Open fill solenoid 
           if((Math.abs(actualPSI - heldsetpoint) <=2) || actualPSI >= heldsetpoint) { // Once at (setpoint +- 2 or above setpoint)
                RobotMap.fillCannon.set(false); // Close fill solenoid
                isDone = true; // May not be needed
            }
        }
    }
}
