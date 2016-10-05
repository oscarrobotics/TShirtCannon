package org.usfirst.frc832.TShirtCannon.subsystems;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.I2C;
import org.usfirst.frc832.TShirtCannon.commands.getSensorData;
public class i2cPSI extends Subsystem{
    
    private static final I2C I2C = DigitalModule.getInstance(1).getI2C(4); // Sets I2C Address to 4 ( which is 2, bit-shifted left by 1)
    public static byte dataReceived[] = {0, 0};    // Holds all of the data from your Arduino
    public static final boolean isCompatMode = false;
    public static double psiActual, tempActual;
    public void initDefaultCommand() {
        //setDefaultCommand(new getI2CData()); // Sets getI2CData ro run when robot is initialized
        setDefaultCommand(new getSensorData());
    }
    
    public static void getFromArduino() {
        System.out.println("I2C Compatability Mode = " + isCompatMode);
        I2C.setCompatabilityMode(isCompatMode);
        System.out.println("I2C Read Start");
        I2C.read(0, 3, dataReceived);
        System.out.println("I2C Read Finish");
        System.out.println("I2C Data printing");
        System.out.println("PSI: " + (int)dataReceived[0]);
        System.out.print("\t");      
        System.out.println("Temp: " + (int)dataReceived[1]);  
        psiActual = dataReceived[0];
        tempActual = dataReceived[1];
    }
    
    /* Mega block of all old I2C code
    
    
    public static final int minRaw = 0; // Min value of your sensor
    public static final int maxRaw = 16383; // Max value of your sensor
    public static final int minPSI = 0; // Min PSI of your sensor
    public static final int maxPSI = 150; // Max PSI of your sensor
    public static double psiValue, tempValue;
    public static String status;
    
    public static void getData() { // Read data from sensor and put in array 'dataReceived'
        I2C.setCompatabilityMode(true); // Set clock skew for I2C (ensures compatability with all I2C devices
        I2C.read(0, 4, dataReceived);   // Read 4 bytes from address 40 and put in array 'dataReceived'
        psiValue = PSIData();
        tempValue = TempData();
        status = status();
    }
    
    
    

    public static double PSIData() { // Get PSI from Honeywell HSC Sensor. Returns a 14 bit PSI value
        //getData(); // Fill data array with data from HSC Sensor
        double psiActual; 
        int psiCounts; // Raw sensor valuve between 0 and 16383
        String bs0 = Integer.toBinaryString(((byte)dataReceived[0] & 0xFF) + 0x100).substring(1); // sets bs0 to the value of byte 1 in bits
        String bs1 = Integer.toBinaryString(((byte)dataReceived[1] & 0xFF) + 0x100).substring(1); // sets bs1 to the value of byte 2 in bits
        //System.out.println("byte 0: " + bs0);
        //System.out.println("byte 1: " + bs1);
        bs0 = bs0.substring(2,8); // Cuts off the Status Bits
        //System.out.println("substring: " + bs0);
        String bits = bs0.concat(bs1);
        //System.out.println("concat: " + bits);
        psiCounts = Integer.parseInt(bits, 2); // Takes our string of bits and makes it an 'int'
        System.out.println("parseInt: " + psiCounts);
        psiActual = (1.0 * (((psiCounts - minRaw) * (maxPSI - minPSI)) / (maxRaw - minRaw)) + minPSI); // Calculates PSI based on equation in datasheet
        System.out.println("outputValue" + psiActual);
        psiActual = psiActual - 26;
        return psiActual; // Final PSI value
    }
    public static double TempData() { // Get Temperature from Honeywell HSC Sensor. Returns an 11 bit temperature value in degC
        //getData();
        double tempActual;
        int tempCounts;
        String bs2 = Integer.toBinaryString(((byte)dataReceived[2] & 0xFF) + 0x100).substring(1); // sets bs2 to the value of byte 3 in bits
        String bs3 = Integer.toBinaryString(((byte)dataReceived[3] & 0xFF) + 0x100).substring(1); // sets bs3 to the value of byte 4 in bits
        bs2 = bs2.concat(bs3);
        String bits = bs2.substring(0, 11); // Cuts off the 5 empty bits at end
        tempCounts = Integer.parseInt(bits, 2); // Takes our string of bits and make it an 'int'
        tempActual = (((tempCounts/2047) * 200) - 50); // Calculates Temperature based on equation in datasheet
        return tempActual; // Final Temp Value
    }
    public static String status() { // Check status of Honeywell HSC Sensor. Returns a String based on status.
        //getData(); // Fill data array with data from HSC Sensor
        String bs0 = Integer.toBinaryString(((byte)dataReceived[0] & 0xFF) + 0x100).substring(1);  // sets bits to the value of byte 1 in bits
        bs0 = bs0.substring(0, 2); // Only keep first two bits of byte 1 (the status bits)
        String s2 = null;
        if(bs0.equals("00")) { // status bs0 = 00
            s2 = "Data is current, Status OK"; // Sensor is in normal operation
        }
        else if(bs0.equals("01") || bs0.equals("11")) { // status bs0 = 01 or 11
           s2 = "WARNING: FAULT DETECTED. REBOOT cRIO NOW"; // Sensor is in Command Mode or Diagnostic State; Sensor should NEVER be in Command Mode         
        }
        else if(bs0.equals("10")) { // status bs0 = 11
            s2 = "Data is stale, Status OK"; // Sensor data is stale; This means the sensor is being polled faster than data is being read
        }
        return s2;
    }

    */
}
