package org.usfirst.frc832.TShirtCannon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc832.TShirtCannon.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
    Command autonomousCommand;
    public static OI oi;
    public static cannonControl cannonControl;
    public static pneumatics pneumatics;
    public static anglePiston anglePiston;
    public static leds leds;
    public static driveTrain driveTrain;
    public static i2cPSI i2cPSI;

    public void robotInit() {
	RobotMap.init();
        
        cannonControl = new cannonControl();
        pneumatics = new pneumatics();
        anglePiston = new anglePiston();
        leds = new leds();
        driveTrain = new driveTrain();
        i2cPSI = new i2cPSI();
        oi = new OI();       
    }
    
    public void sendData() {
        SmartDashboard.putNumber("PSI:", Robot.i2cPSI.psiValue);
        SmartDashboard.putNumber("AirTemp degC:", Robot.i2cPSI.tempValue);
        SmartDashboard.putString("Sensor Status:", Robot.i2cPSI.status);
        SmartDashboard.putNumber("Joystick Twist", Robot.oi.controlStick.getTwist());
        SmartDashboard.putNumber("Joystick Throttle", Robot.oi.controlStick.getThrottle());
        SmartDashboard.putNumber("PSI Setpoint", Robot.pneumatics.throttleToPSI());
    }
    
    public void teleopInit() {
        Robot.leds.i2csend((byte)50); // Sets LEDs to Green once when enabled
    }
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        sendData();
    }
    public void disabledInit() {
        Robot.leds.i2csend((byte)30); // Turns off LEDs once when disabled
    }
}