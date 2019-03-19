package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.Command;


import org.usfirst.frc.team6503.robot.Robot;

/**
 *
 */
public class ClimberCommand extends Command {
	public ClimberCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.climber);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		/*
		double fSpeed = deadzone(Robot.oi.ropeClimbJoystick.getRawAxis(1));
		if(fSpeed > 0.0)
			Robot.climber.setSpeed(0.0);
		else
			Robot.climber.setSpeed(fSpeed);
		*/
	}
	
	private double deadzone(double in){
		if(Math.abs(in)<0.2)
			return(0.0);
		return in;
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
