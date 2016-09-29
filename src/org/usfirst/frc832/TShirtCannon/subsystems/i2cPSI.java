package org.usfirst.frc832.TShirtCannon.subsystems;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.I2C;
public class i2cPSI extends Subsystem{
    
    private static final I2C I2C = DigitalModule.getInstance(1).getI2C(80); // Sets I2C Address to 80 ( which is 40, bit-shifted left by 1)
    public static byte dataReceived[] = {0, 0, 0, 0};    // Holds all of the data from yout sensor
    public static final int minRaw = 0; // Min value of your sensor
    public static final int maxRaw = 16383; // Max value of your sensor
    public static final int minPSI = 0; // Min PSI of your sensor
    public static final int maxPSI = 150; // Max PSI of your sensor
    public double psiValue = PSIData();
    public double tempValue = TempData();
    public String status = status();
    
    public static void getData() { // Read data from sensor and put in array 'dataReceived'
        I2C.setCompatabilityMode(true); // Set clock skew for I2C (ensures compatability with all I2C devices
        I2C.read(0, 4, dataReceived);   // Read 4 bytes from address 40 and put in array 'dataReceived'
    }
    public void initDefaultCommand() {
        //setDefaultCommand(new getI2CData()); // Sets getI2CData ro run when robot is initialized
    }

    public static double PSIData() { // Get PSI from Honeywell HSC Sensor. Returns a 14 bit PSI value
        getData(); // Fill data array with data from HSC Sensor
        double psiActual; 
        int psiCounts; // Raw sensor valuve between 0 and 16383
        String bits = Integer.toBinaryString(((dataReceived[0]+256)%256)); // sets bits to the value of byte 1 in bits
        bits = bits.substring(2); // Cuts off the Status Bits
        bits = bits.concat(Integer.toBinaryString(((dataReceived[1]+256)%256))); // Adds the value of byte 2 in bits
        psiCounts = Integer.parseInt(bits, 2); // Takes our string of bits and makes it an 'int'
        psiActual = (1.0 * (((psiCounts - minRaw) * (maxPSI - minPSI)) / (maxRaw - minRaw)) + minPSI); // Calculates PSI based on equation in datasheet
        return psiActual; // Final PSI value
    }
    public static double TempData() { // Get Temperature from Honeywell HSC Sensor. Returns an 11 bit temperature value in degC
        getData();
        double tempActual;
        int tempCounts;
        String bits = Integer.toBinaryString(((dataReceived[2]+256)%256)); // sets bits to the value of byte 1 in bits
        bits = bits.concat(Integer.toBinaryString(((dataReceived[3]+256)%256))); // Adds the value of byte 2 in bits
        bits = bits.substring(0, 11); // Cuts off the 5 empty bits at end
        tempCounts = Integer.parseInt(bits, 2); // Takes our string of bits and make it an 'int'
        tempActual = (((tempCounts/2047) * 200) - 50); // Calculates Temperature based on equation in datasheet
        return tempActual; // Final Temp Value
    }
    public static String status() { // Check status of Honeywell HSC Sensor. Returns a String based on status.
        getData(); // Fill data array with data from HSC Sensor
        String bits = Integer.toBinaryString(((dataReceived[0]+256)%256)); // sets bits to the value of byte 1 in bits
        bits = bits.substring(0, 2); // Only keep first two bits of byte 1 (the status bits)
        String s2 = null;
        if(bits.equals("00")) { // status bits = 00
            s2 = "Data is current, Status OK"; // Sensor is in normal operation
        }
        else if(bits.equals("01") || bits.equals("11")) { // status bits = 01 or 11
           s2 = "WARNING: FAULT DETECTED. REBOOT cRIO NOW"; // Sensor is in Command Mode or Diagnostic State; Sensor should NEVER be in Command Mode         
        }
        else if(bits.equals("10")) { // status bits = 11
            s2 = "Data is stale, Status OK"; // Sensor data is stale; This means the sensor is being polled faster than data is being read
        }
        return s2;
    }
}
