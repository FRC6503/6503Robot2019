package org.usfirst.frc.team6503.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6503.robot.RobotMap;

public class Arm extends Subsystem {
	
	private final SpeedController liftDrive = RobotMap.liftMotor;
	private final SpeedController cubeDrive1 = RobotMap.cubeMotor1;
	// private final SpeedController cubeDrive2 = RobotMap.cubeMotor2;
	
	public Arm() {
		super();
		System.out.println("Arm: If you are seeing this message, please alert the nearest 6503 programmer.");
	}

	public void setArmSpeed(double armSpeed) {
		liftDrive.set(armSpeed);
	}
	
	public void setCubeSpeed(double cubeSpeed) {
		cubeDrive1.set(cubeSpeed);
		// cubeDrive2.set(cubeSpeed);
	}
	
	public void setCubeSpeedUneven(double leftCubeSpeed, double rightCubeSpeed) {
		cubeDrive1.set(leftCubeSpeed);
		// cubeDrive2.set(-rightCubeSpeed);
	}
	
	public void setSpeed(double liftSpeed, double cubeSpeed)
	{
		setArmSpeed(liftSpeed);
		setCubeSpeed(cubeSpeed);
	}

	public void initDefaultCommand() {
		// If using commands, this is the one to use.
		// setDefaultCommand(new PrerecordedArmCommand());
	}
	
	private static Arm instance = new Arm();
	
	public static Arm getInstance()
	{
		return instance;
	}
	
	public void stop()
	{
		liftDrive.set(0.0);
		cubeDrive1.set(0.0);
		// cubeDrive2.set(0.0);
	}
}