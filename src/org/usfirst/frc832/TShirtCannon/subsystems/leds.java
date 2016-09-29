package org.usfirst.frc832.TShirtCannon.subsystems;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.command.Subsystem;

public class leds extends Subsystem {
    I2C i2c;
    byte[] toSend = new byte[1];
     
    public void initDefaultCommand() {
    }
    
    public void i2csend(byte i) { //Function for sending I2C commands to Arduino
        i2c = DigitalModule.getInstance(1).getI2C(168); // Sets I2C address to send to as "168", which is "84" bit-shifted by one
        toSend[0] = i; // sets toSend as paramater "byte"
        i2c.transaction(toSend, 1, null, 0);
    } 
}
