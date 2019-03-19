package org.usfirst.frc.team6503.robot.subsystems;

import org.usfirst.frc.team6503.robot.RobotMap;
import org.usfirst.frc.team6503.robot.commands.ClimberCommand;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	
	private final SpeedController climber = RobotMap.climberMotor;
	public void setSpeed(double speed){
		climber.set(speed);
	}
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		setDefaultCommand(new ClimberCommand());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	private static Climber instance = new Climber();
	
	public static Climber getInstance()
	{
		return instance;
	}
}
