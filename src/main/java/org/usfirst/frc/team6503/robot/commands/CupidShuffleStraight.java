package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CupidShuffleStraight extends CommandGroup {
	public CupidShuffleStraight(double speed, double time){
		addSequential(new DriveStraight(speed, -speed, time));
		addSequential(new WaitCommand(0.25));
		addSequential(new DriveStraight(speed, -speed, time));
		addSequential(new WaitCommand(0.25));
		addSequential(new DriveStraight(speed, -speed, time));
		addSequential(new WaitCommand(0.25));
		addSequential(new DriveStraight(speed, -speed, time));
		addSequential(new WaitCommand(0.25));
	}
}
