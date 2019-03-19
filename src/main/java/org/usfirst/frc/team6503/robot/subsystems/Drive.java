package org.usfirst.frc.team6503.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6503.robot.RobotMap;
import org.usfirst.frc.team6503.robot.commands.*;

/**
 *
 */
public class Drive extends Subsystem {
	
	private final SpeedController leftDrive1 = RobotMap.driveLeftDrive1;
	private final SpeedController leftDrive2 = RobotMap.driveLeftDrive2;
	private final SpeedController rightDrive1 = RobotMap.driveRightDrive1;
	private final SpeedController rightDrive2 = RobotMap.driveRightDrive2;
	
	public void setSpeed(double lSpeed, double rSpeed)
	{
		leftDrive1.set(lSpeed);
		leftDrive2.set(lSpeed);
		rightDrive1.set(rSpeed);
		rightDrive2.set(rSpeed);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new TeleopDriveCommand());
	}
	
	private static Drive instance = new Drive();
	
	public static Drive getInstance()
	{
		return instance;
	}
	
	public void stop()
	{
		leftDrive1.set(0);
		leftDrive2.set(0);
		rightDrive1.set(0);
		rightDrive2.set(0);
	}
}