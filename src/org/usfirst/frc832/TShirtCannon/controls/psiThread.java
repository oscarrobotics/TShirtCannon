/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc832.TShirtCannon.controls;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import java.lang.Thread;
import org.usfirst.frc832.TShirtCannon.Robot;

/**
 *
 * @author Banks
 */
public class psiThread extends Thread {

    double sec;

    public psiThread(double sec) {
        this.sec = sec;
    }
    byte dataReceived[] = {0};
    I2C i2c = DigitalModule.getInstance(1).getI2C(40);
    //psiThread p = new psiThread(0.5);
    public void run() {
        System.out.println("runningThread");
        //Robot.i2cPSI.getFromArduino();
        i2c.setCompatabilityMode(true);
        System.out.println("debug1");
        i2c.read(0, 1, dataReceived);
        System.out.println("PSI: " + (int) dataReceived[0]);
        int psiActual = dataReceived[0];

        System.out.println("gotData");
        Timer.delay(1);
        System.out.println("doneWaiting");
        //p.start();
    }
}
