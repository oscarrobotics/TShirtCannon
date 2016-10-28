package org.usfirst.frc832.TShirtCannon.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc832.TShirtCannon.Robot;

public class  getSensorData extends Command {
    public getSensorData() {
        requires(Robot.i2cPSI);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Robot.i2cPSI.getFromArduino();
        //Robot.i2cPSI.getData();
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
