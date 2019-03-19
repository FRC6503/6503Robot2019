package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6503.robot.subsystems.Arm;
import org.usfirst.frc.team6503.robot.Robot;
import org.usfirst.frc.team6503.robot.commands.ArmPresetCommand;

/**
 *
 */
public class MoveArmToPreset extends Command {	
	// The following describe positions the arm can rest at. Imagine the floor
	// as 0 with positive values moving the arm "up" from that position.
	private static double DEGREES_STOW = 90.0;
	private static double DEGREES_LAUNCH = 60.0;
	private static double DEGREES_FLOOR = 0.0;
	
	// TODO: These values are made up. Actually measure them!
	private static double ARM_SPEED_UP = 0.6;
	private static double DEGREES_PER_SEC_UP = 100.0;
	
	// TODO: I'm assuming gravity causes the arm to crash to the ground.
	//       Consider tuning the "stable" power we use to hold the arm in place.
	private static double ARM_SPEED_DOWN = 0.2;
	private static double DEGREES_PER_SEC_DOWN = 60.0;
	private static double HOLD_STABLE_IDLE_POWER = 0.4;
	
	private static double DISABLE_IDLE_POWER = 0.0;
	private static double DISABLE_CUBE = 0.0;
	
	private double position;
	private double idlePower;
	private boolean shouldIdle;
	
	public MoveArmToPreset() {
		// TODO: When we get to the competition, the arm must start within the robot.
		//       For now we'll start with it on the floor.
		this.position = DEGREES_FLOOR;
		this.idlePower = DISABLE_IDLE_POWER;
		this.shouldIdle = false;
	}
	
	public void moveToStow() {
		moveTo(DEGREES_STOW);
		idle(DISABLE_IDLE_POWER);
	}
	
	public void moveToLaunch() {
		moveTo(DEGREES_LAUNCH);
		idle(HOLD_STABLE_IDLE_POWER);
	}
	
	public void moveToFloor() {
		moveTo(DEGREES_FLOOR);
		idle(DISABLE_IDLE_POWER);
	}
	
	public void moveTo(double destination) {
		double changeDegrees = destination - position;
		
		if (changeDegrees > 0) {
			double timeToExecute = changeDegrees / DEGREES_PER_SEC_UP;
			// The polarity of the arm is reversed, use a negative here.
			new ArmPresetCommand(-ARM_SPEED_UP, timeToExecute).start();
		} else {
			double timeToExecute = changeDegrees / DEGREES_PER_SEC_DOWN;
			new ArmPresetCommand(ARM_SPEED_DOWN, timeToExecute).start();
		}
	}
	
	public void idle(double power) {
		this.idlePower = power;
		this.shouldIdle = true;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (shouldIdle) {
		    Arm.getInstance().setSpeed(this.idlePower, DISABLE_CUBE);
		}
		
		if (idlePower < 0.0001) {
			shouldIdle = false;
		}
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
