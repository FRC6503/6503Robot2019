package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6503.robot.subsystems.Arm;
import org.usfirst.frc.team6503.robot.Robot;

/**
 *
 */
public class ArmPresetCommand extends Command {
	private double speed;
	static double DISABLE_CUBE = 0.0;
	
	public ArmPresetCommand(double speed, double time) {
		super(time);
		// Use requires() here to declare subsystem dependencies
		requires(Arm.getInstance());
		this.speed = speed;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Arm.getInstance().setSpeed(this.speed, DISABLE_CUBE);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Arm.getInstance().stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
