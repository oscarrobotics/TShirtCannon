package org.usfirst.frc832.TShirtCannon.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc832.TShirtCannon.Robot;
import org.usfirst.frc832.TShirtCannon.RobotMap;

public class cannonControl extends Command {

    public cannonControl() {
        requires(Robot.cannonControl);
    }
    public boolean triggerButton = Robot.oi.controlStick.getRawButton(1);
    public boolean safetyButton = Robot.oi.controlStick.getRawButton(2);
    public boolean isDone;

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.leds.i2csend((byte) 74);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        isDone = false;
        if (safetyButton) { // If we start with safety pressed, check trigger
            RobotMap.cannonLight.set(Relay.Value.kForward);
            if (triggerButton) { // If trigger is pressed, fire
                RobotMap.cannonFire.set(Relay.Value.kForward);
                Timer.delay(0.01); // Make sure we get the shirt out (This may not be needed)
                isDone = true;
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.leds.i2csend((byte) 50);
        RobotMap.cannonLight.set(Relay.Value.kOff);
        RobotMap.cannonFire.set(Relay.Value.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
