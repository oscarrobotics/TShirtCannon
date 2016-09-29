package org.usfirst.frc832.TShirtCannon.subsystems;
import org.usfirst.frc832.TShirtCannon.RobotMap;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class cannonFire extends Subsystem {
    Relay fire = RobotMap.cannonFire;
    
    public void initDefaultCommand() {  
    }
}
