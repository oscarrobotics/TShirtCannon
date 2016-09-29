package org.usfirst.frc832.TShirtCannon.subsystems;
import org.usfirst.frc832.TShirtCannon.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class anglePiston extends Subsystem {
    DoubleSolenoid lift = RobotMap.anglePiston;
    Solenoid release = RobotMap.anglePistonRelease;

    public void initDefaultCommand() {
    }
}
