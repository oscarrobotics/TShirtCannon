package org.usfirst.frc832.TShirtCannon.commands;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc832.TShirtCannon.Robot;
import org.usfirst.frc832.TShirtCannon.RobotMap;

public class  light extends Command {
    public light() {
        requires(Robot.cannonLight);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.leds.i2csend((byte)74);
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        RobotMap.cannonLight.set(Relay.Value.kForward);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.leds.i2csend((byte)50);
        RobotMap.cannonLight.set(Relay.Value.kOff);
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
