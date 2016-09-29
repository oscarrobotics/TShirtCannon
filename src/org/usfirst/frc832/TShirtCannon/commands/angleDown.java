package org.usfirst.frc832.TShirtCannon.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc832.TShirtCannon.Robot;

public class  angleDown extends Command {
    public angleDown() {
        requires(Robot.anglePiston);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.anglePiston.angleMode(2);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.anglePiston.angleMode(0);
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
