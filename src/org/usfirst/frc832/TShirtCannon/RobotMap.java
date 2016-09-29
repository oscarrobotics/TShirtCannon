package org.usfirst.frc832.TShirtCannon;
    
import edu.wpi.first.wpilibj.*;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static Relay cannonFire;
    //public static Relay cannonCompcompressors;
    public static Relay cannonLight;
    public static Compressor pneumaticscompressors;
    public static DoubleSolenoid anglePiston;
    public static Solenoid anglePistonRelease;
    public static Solenoid fillCannon;
    public static SpeedController driveTrainleft1;
    public static SpeedController driveTrainright1;
    public static RobotDrive driveTraintank;
    public static void init() {
        cannonFire = new Relay(1, 1);        
	cannonLight = new Relay(1, 2);
	pneumaticscompressors = new Compressor(1, 1, 1, 4);
	anglePiston = new DoubleSolenoid(1, 1, 2);      
	anglePistonRelease = new Solenoid(1, 3);
        fillCannon = new Solenoid(1, 4);
	
        driveTrainleft1 = new Talon(1, 1);
        driveTrainright1 = new Talon(1, 2);
        driveTraintank = new RobotDrive(driveTrainleft1, driveTrainright1);
        driveTraintank.setSafetyEnabled(false);
        driveTraintank.setExpiration(0.1);
        driveTraintank.setSensitivity(0.5);
        driveTraintank.setMaxOutput(1.0);  
    }
}