package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6503.robot.Robot;
import org.usfirst.frc.team6503.robot.subsystems.Arm;

public class TeleopArmCommand extends Command {
	static double BUTTON_SPEED = 1.0;
	private MoveArmToPreset presetController;
	private GyroArmCommand gyroArm;
	
	public TeleopArmCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.arm);
		requires(Arm.getInstance());
		presetController = new MoveArmToPreset();
		gyroArm = new GyroArmCommand();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		/*
		if (Robot.oi.driverJoystick.getRawButton(7)) {
			gyroArm.moveArmUp();
		} else if (Robot.oi.driverJoystick.getRawButton(8)) {
			gyroArm.moveArmDown();
		} else {
			gyroArm.holdArmSteady();
		}
*/
		/*
		if (presetsSelected()) {
			operateArmPreset();
		} else {
			operateArmManual();
		}
		*/
	}
	
	protected double positiveNegativeButtons(int firstButtonId, int secondButtonId) {
		if (Robot.oi.driverJoystick.getRawButton(firstButtonId)) {
			return BUTTON_SPEED;
		}
		
		if (Robot.oi.driverJoystick.getRawButton(secondButtonId)) {
			return -BUTTON_SPEED;
		}
		
		return 0.0;
	}
	
	protected boolean presetsSelected() {
		return Robot.oi.driverJoystick.getRawButton(11);
	}
	
	protected void operateArmPreset() {
		if (Robot.oi.driverJoystick.getRawButton(8)) {
			presetController.moveToStow();
		} else if (Robot.oi.driverJoystick.getRawButton(10)) {
			presetController.moveToLaunch();
		} else if (Robot.oi.driverJoystick.getRawButton(12)) {
			presetController.moveToFloor();
		}
	}
	
	protected void operateArmManual() {
		double lift = positiveNegativeButtons(7, 8);
		double cube = positiveNegativeButtons(9, 10);
		
		Robot.arm.setSpeed(lift, cube);
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
