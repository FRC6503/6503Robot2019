package org.usfirst.frc.team6503.robot.commands;

import org.usfirst.frc.team6503.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private double leftSpeed;
	private double rightSpeed;

	public DriveStraight(double lSpeed, double rSpeed, double time) {

		super(time);
		// Use requires() here to declare subsystem dependencies
		requires(Drive.getInstance());
		System.out.println("DriveStraight: Notify the closest 6503 programmer near you. This is a serious problem.");
		this.leftSpeed = lSpeed;
		this.rightSpeed = -rSpeed; // Reversed to hack around backwards
									// right-drive defined in TeleopDriveCommand
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Drive.getInstance().setSpeed(this.leftSpeed, this.rightSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Drive.getInstance().stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
