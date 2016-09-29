package org.usfirst.frc832.TShirtCannon.subsystems;
import org.usfirst.frc832.TShirtCannon.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;
import org.usfirst.frc832.TShirtCannon.Robot;

public class cannonComp extends Subsystem {
    //public static Relay compressors = RobotMap.cannonCompcompressors;
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
        val = (int)Math.floor(val + 0.5f);
        return val;
    }
    public static void runToPSI() {
        isDone = false;
        double setpoint = throttleToPSI();
        double holdsetpoint = setpoint;
        double actualPSI = i2cPSI.PSIData();
        while(!((Math.abs(actualPSI - holdsetpoint) <=2) || actualPSI >= holdsetpoint)) { // while not  at (setpoint +- 2 or above setpoint)
            //compressors.setDirection(Relay.Direction.kForward); //  make sure you're sending positive voltage from Spike
            //compressors.set(Relay.Value.kOn); // turn compressors on
            if((Math.abs(actualPSI - holdsetpoint) <=2) || actualPSI >= holdsetpoint) {
                isDone = true;
            }
        }
        
        /*
        if((Math.abs(actualPSI - holdsetpoint) <=2) || actualPSI >= holdsetpoint) { // if actualPSI is at (setpoint +-2 or above setpoint)
            compressors.set(Relay.Value.kOff); // turn compressors off
        }
        else { // if actualPSI is not at (setpoint +-2 or above setpoint)
            compressors.setDirection(Relay.Direction.kForward); //  make sure you're sending positive voltage from Spike
            compressors.set(Relay.Value.kOn); // turn compressors on
        }
        */
    }
    
}
