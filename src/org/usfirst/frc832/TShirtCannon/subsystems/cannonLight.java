package org.usfirst.frc832.TShirtCannon.subsystems;
import org.usfirst.frc832.TShirtCannon.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;

public class cannonLight extends Subsystem {
    Relay light = RobotMap.cannonLight;
    
    public void initDefaultCommand() {
    }
}
