package org.usfirst.frc832.TShirtCannon.subsystems;
import org.usfirst.frc832.TShirtCannon.RobotMap;
import org.usfirst.frc832.TShirtCannon.Robot;
import org.usfirst.frc832.TShirtCannon.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class driveTrain extends Subsystem {
    
    public static SpeedController left1 = RobotMap.driveTrainleft1;  
    public static SpeedController right1 = RobotMap.driveTrainright1;
    public static RobotDrive tank = RobotMap.driveTraintank;
    
    public void initDefaultCommand() {
	setDefaultCommand(new driveWithJoysticks());
    }
    public void takeJoystickInputs(double left, double right) {
    	tank.tankDrive(left, right);
    }
    public void stop() {
    	tank.drive(0, 0);
    }
    
    public void twistToDrive(double twistVal) {
        double outputLeft, outputRight;
        if(Math.abs(twistVal) >= 0.05) { // Makes sure the stick is being twisted before running
            if (Math.abs(twistVal) > 0.05) { // Set a deadzone to ignore input between -0.09 and 0.09
               outputLeft = -Math.abs(twistVal); // Force left to be negative
               outputRight = Math.abs(twistVal); // Force right to be positive
            } 
            else { // If we aren't outside of the deadzone, do nothing
                outputLeft = 0.0; // Do nothing
                outputRight = 0.0; //  Do nothing
            }
            tank.drive(outputLeft, outputRight); // Send outputs to drive system
        }    
    }
}
