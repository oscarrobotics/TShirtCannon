package org.usfirst.frc832.TShirtCannon.subsystems;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.command.Subsystem;

public class leds extends Subsystem {
    I2C i2c;
    byte[] toSend = new byte[1];
    
    /* List of i2c bytes and what LED effect they have
    30 - Off
    50 - Green
    72 - Rainbow
    74 - Bouncing red with trail
    76 - Green base with bouncing blue dot
    */
    
    public void initDefaultCommand() {
    }
    
    public void i2csend(byte i) { //Function for sending I2C commands to Arduino
        i2c = DigitalModule.getInstance(1).getI2C(168); // Sets I2C address to send to as "168", which is "84" bit-shifted by one
        toSend[0] = i; // sets toSend as paramater "byte"
        i2c.transaction(toSend, 1, null, 0);
    } 
}
