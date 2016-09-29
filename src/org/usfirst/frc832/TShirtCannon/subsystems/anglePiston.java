package org.usfirst.frc832.TShirtCannon.subsystems;
import org.usfirst.frc832.TShirtCannon.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class anglePiston extends Subsystem {
    DoubleSolenoid lift = RobotMap.anglePiston;
    Solenoid release = RobotMap.anglePistonOpen;

    public void initDefaultCommand() {
    }
    
    /**
     * Sets mode of angle piston
     * @param mode 0,off 1,up 2,down
     */ 
    public void angleMode(int mode) {
        switch(mode) {
            case 0: RobotMap.anglePistonOpen.set(false);
                    RobotMap.anglePiston.set(DoubleSolenoid.Value.kOff);
                    
            case 1: RobotMap.anglePiston.set(DoubleSolenoid.Value.kForward);
                    RobotMap.anglePistonOpen.set(true);
                    
            case 2: RobotMap.anglePiston.set(DoubleSolenoid.Value.kReverse);
                    RobotMap.anglePistonOpen.set(true);                   
        }
    }
}
