package org.usfirst.frc832.TShirtCannon.commands;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc832.TShirtCannon.Robot;
import org.usfirst.frc832.TShirtCannon.RobotMap;

public class  angleDown extends Command {
    public angleDown() {
        requires(Robot.anglePiston);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        RobotMap.anglePiston.set(DoubleSolenoid.Value.kReverse);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
         RobotMap.anglePiston.set(DoubleSolenoid.Value.kOff);
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
