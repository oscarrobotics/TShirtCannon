package org.usfirst.frc832.TShirtCannon.subsystems;
import org.usfirst.frc832.TShirtCannon.RobotMap;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class cannonControl extends Subsystem {
    Relay fire = RobotMap.cannonFire;
    Relay light = RobotMap.cannonLight;
    
    public void initDefaultCommand() {  
    }
}
