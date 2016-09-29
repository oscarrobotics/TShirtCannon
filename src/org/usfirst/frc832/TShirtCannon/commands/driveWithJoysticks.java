package org.usfirst.frc832.TShirtCannon.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc832.TShirtCannon.Robot;

public class  driveWithJoysticks extends Command {

    public driveWithJoysticks() {
	requires(Robot.driveTrain);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.driveTrain.takeJoystickInputs(Robot.oi.driverPad.getRawAxis(2), Robot.oi.driverPad.getRawAxis(5));
        Robot.driveTrain.twistToDrive(Robot.oi.controlStick.getTwist());
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.cannonComp.isDone;
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.stop();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
