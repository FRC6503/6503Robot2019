package org.usfirst.frc.team6503.robot.commands;

import org.usfirst.frc.team6503.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class TurnCommand extends Command {
	
	private double rpower, lpower;
	public TurnCommand(double power, double time, boolean direction){
		super(time);
		requires(Drive.getInstance());
		
		this.rpower = (direction ? power : -power);
		this.lpower = (direction ? power : -power);
	}
	
	public TurnCommand(double time, boolean direction){
		this(1.0, time, direction);
	}
	
	@Override
	protected boolean isFinished() {
		Drive.getInstance().setSpeed(lpower, rpower);
		
		return isTimedOut();
	}
	
	@Override
	protected void end(){
		Drive.getInstance().stop();
	}

}
