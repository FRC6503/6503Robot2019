package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6503.robot.subsystems.Arm;
import org.usfirst.frc.team6503.robot.Robot;

/**
 *
 */
public class ShootCube extends Command {
	
	private static double SHOOT_TIME_SEC = 0.7;
	private static double SHOOT_SPEED = 1.0;
	private static double DISABLED_SPEED = 0.0;
	
	private double speed;

	public ShootCube() {
		super(SHOOT_TIME_SEC);
		requires(Arm.getInstance());
		
		this.speed = SHOOT_SPEED;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Arm.getInstance().setCubeSpeed(SHOOT_SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		this.speed = DISABLED_SPEED;
		Arm.getInstance().stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
