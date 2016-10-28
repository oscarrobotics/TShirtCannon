package org.usfirst.frc832.TShirtCannon.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc832.TShirtCannon.Robot;

public class fillToPSI extends Command {
    
    public fillToPSI() {
        requires(Robot.pneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.pneumatics.runToPSI();
        Robot.i2cLEDs.setLEDs((byte)76);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return Robot.pneumatics.isDone; // Do we need this? We'll see...
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.i2cLEDs.setLEDs((byte)50);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
