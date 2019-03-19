package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6503.robot.Robot;
import org.usfirst.frc.team6503.robot.RobotMap;
import org.usfirst.frc.team6503.robot.subsystems.Arm;

public class GyroArmCommand extends Command {
	// For a reading to "equal" the target, it may be this far off.
	private static double ANGLE_ACCEPT_RANGE = 1.0;
	private static double RATE_ACCEPT_RANGE = 0.05; // TODO: Consider 0.1?
	
	// The amount of power to add each time the method is called. Can be
	// thought of as arm acceleration. LOWER THIS IF ARM IS ACCELERATING FAST.
	private static double EPSILON_POWER = 0.0005;
	
	// The maximum power we'll allow the arm to move at
	private static double MAX_POWER_UP = 0.7;
	private static double MAX_POWER_DOWN = 0.7;

	// The rate of change (angular velocity) for the arm to move at.
	// LOWER THIS IF THE ARM IS MOVING TOO FAST.
	private static double DEG_PER_SEC = 3.0;
	private static double HOLD_ARM = 0.0;
	
	// The gyro counts up when turning clockwise.
	// Set this to 1 if clockwise motion moves the arm "up".
	// Set to -1 if counter-clockwise motion moves the arm "up".
	private static int ORIENTATION = -1;
	
	private double armPower = 0.0;
	private double targetAngle = 0.0;
	private double targetRate = 0.0;
	
	private boolean pressed = false;
	
	public GyroArmCommand() {
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
		// TODO: Filter Gyro readings using a moving window if needed

		if (Robot.oi.driverJoystick.getRawButton(11)) {
			//Robot.arm.setArmSpeed(0.8);
			
			if (!pressed) {
			    armPower += 0.1;
			    pressed = true;
			}
		} else if (Robot.oi.driverJoystick.getRawButton(12)) {
			if (!pressed) {
				armPower -= 0.1;
				pressed = true;
			}
			//Robot.arm.setArmSpeed(-0.8);
		} else {
			pressed = false;
			//Robot.arm.setArmSpeed(0.0);
		}
		
		/*
		if (Robot.oi.driverJoystick.getRawButton(7)) {
			moveArmUp();
		} else if (Robot.oi.driverJoystick.getRawButton(8)) {
			moveArmDown();
		} else {
			holdArmSteady();
		}
		
		if (Robot.oi.driverJoystick.getRawButton(11)) {
			armPower = -0.66;
		} else if (Robot.oi.driverJoystick.getRawButton(12)) {
			armPower = 0.66;
		}
		
		final int adjustRate = compareRate();

		if (adjustRate == -1) {
			increasePower();
		} else if (adjustRate == 1) {
			decreasePower();
		}
		*/

		Robot.arm.setArmSpeed(armPower);
		
		System.out.println(armPower + " " + RobotMap.gyro.getRate() + " " + RobotMap.gyro.getAngle());
		
		// System.out.println(armPower + " " + adjustRate + " " + "Rate " + RobotMap.gyro.getRate() + " " + targetRate + " Angle " + RobotMap.gyro.getAngle());
		// System.out.println("Angle: " + RobotMap.gyro.getAngle() + " Rate: " + RobotMap.gyro.getRate() + " " + targetRate + " " + armPower + " " + adjustRate);
		// System.out.println("Angle: " + RobotMap.gyro.getAngle() + " // " + armPower + " " + RobotMap.gyro.getRate() + " " + targetRate);
	}

	public void moveArmUp() {
		targetRate = DEG_PER_SEC * ORIENTATION;
	}
	
	public void moveArmDown() {
		targetRate = -DEG_PER_SEC * ORIENTATION;
	}
	
	public void holdArmSteady() {
		targetRate = HOLD_ARM;
	}
	
	private void increasePower() {
//		if (armPower < MAX_POWER_UP) {
		    armPower += (EPSILON_POWER * ORIENTATION);
//		}
	}

	private void decreasePower() {
//		if (armPower > -MAX_POWER_DOWN) {
			armPower += (EPSILON_POWER * -ORIENTATION);
//		}
	}
	
	private int compareAngle() {
		return compare(RobotMap.gyro.getAngle(), targetAngle, ANGLE_ACCEPT_RANGE);
	}
	
	private int compareRate() {
		return compare(RobotMap.gyro.getRate(), targetRate, RATE_ACCEPT_RANGE);
	}
	
	private int compare(double source, double target, double epsilon) {
		double difference = source - target;
		
		if (difference < -epsilon) {
			return -1 * ORIENTATION;
		}
		
		if (difference > epsilon) {
			return 1 * ORIENTATION;
		}
		
		return 0;
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
