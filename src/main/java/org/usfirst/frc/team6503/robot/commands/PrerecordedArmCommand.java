package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6503.robot.Robot;
import org.usfirst.frc.team6503.robot.RobotMap;
import org.usfirst.frc.team6503.robot.subsystems.Arm;

public class PrerecordedArmCommand extends Command {
	private PrerecordedOperation currentOperation;
	
	// All powers range from 0 to 1
	private static double SHOOT_POWER = 1.0;
	private static double INTAKE_POWER = 0.6;
	
	// Be careful with this constant! It sets the maximum power for the arm motor
	// which is very touchy. Increasing beyond roughly 0.6 will make the arm
	// joystick difficult to control and possibly dangerous to the robot itself.
	private static double MAX_ARM_POWER = 0.6; 
	private static double ARM_DEADZONE = 0.1;
	
	public PrerecordedArmCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.arm);
		requires(Arm.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		/*
		if (Robot.oi.getArmJoystick().getRawButton(7)) {
			currentOperation = PrerecordedOperation.STOW_ARM();
			currentOperation.start();
		} else if (Robot.oi.getArmJoystick().getRawButton(11)) {
			currentOperation = PrerecordedOperation.DEPLOY_ARM();
			currentOperation.start();
		} else if (Robot.oi.getArmJoystick().getRawButton(9)) {
			currentOperation = PrerecordedOperation.SHOOT_CUBE();
			currentOperation.start();
		} else if (Robot.oi.getArmJoystick().getRawButton(12)) {
			cancelCurrentOperation();
		}
	
		if (currentOperation != null) {
			if (currentOperation.isActive()) {
				executeCurrentOperation();
			} else {
				cancelCurrentOperation();
			}
		}
		
		if (currentOperation == null) {
			humanDrive();
		}
		*/
		humanDrive();
	}
	
	protected void executeCurrentOperation() {
		Robot.arm.setArmSpeed(currentOperation.getPower());
		Robot.arm.setCubeSpeed(currentOperation.getCube());
	}
	
	protected void cancelCurrentOperation() {
		Robot.arm.setArmSpeed(0.0);
		Robot.arm.setCubeSpeed(0.0);
		currentOperation = null;
	}
	
	protected void humanDrive() {
		if (Robot.oi.getArmJoystick().getRawButton(2)) {
			Robot.arm.setCubeSpeed(-INTAKE_POWER);
		} else if (Robot.oi.getArmJoystick().getRawButton(1)) {
			Robot.arm.setCubeSpeed(SHOOT_POWER);
		} else if (Robot.oi.getArmJoystick().getRawButton(3)) {
			Robot.arm.setCubeSpeedUneven(-INTAKE_POWER, -INTAKE_POWER / 3.0);
		} else {
			Robot.arm.setCubeSpeed(0.0);
		}
		
		// Inverted y-axis for joystick
		double power = armDeadZone(Robot.oi.getArmJoystick().getRawAxis(1)) * -MAX_ARM_POWER;
		Robot.arm.setArmSpeed(power);
	}
	
	private double armDeadZone(double power) {
		if (power < -ARM_DEADZONE || power > ARM_DEADZONE) {
			return power;
		}
		
		return 0.0;
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
