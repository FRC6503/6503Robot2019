package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6503.robot.Robot;
import org.usfirst.frc.team6503.robot.RobotMap;
import org.usfirst.frc.team6503.robot.subsystems.Drive;

/**
 *
 */
public class TeleopDriveCommand extends Command {
	public TeleopDriveCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		requires(Drive.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double lJoy = deadzone(Robot.oi.driverJoystick.getRawAxis(0))/2; //sets the speed
		double rJoy = deadzone(Robot.oi.driverJoystick.getRawAxis(1))/2; //sets the speed

		double softGear = getSoftGear();

		double lSpeed = (lJoy + rJoy) * softGear;
		double rSpeed = (lJoy - rJoy) * softGear;
		// Robot.drive.setSpeed(lSpeed, rSpeed);
	}

	private double deadzone(double in){
		if(Math.abs(in)<0.1)
			return 0.0;
		return in;
	}
	
	private double getSoftGear() {
		if (Robot.oi.getDriverJoystick().getTriggerAxis(Hand.kLeft) > 0.5) {
			return 0.5;
		} else if (Robot.oi.getDriverJoystick().getTriggerAxis(Hand.kRight) > 0.5) {
			return 1.5;
		}
		
		return 1;
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
