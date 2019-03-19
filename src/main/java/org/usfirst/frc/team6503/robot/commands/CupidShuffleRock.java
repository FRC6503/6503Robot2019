package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CupidShuffleRock extends CommandGroup {
	public CupidShuffleRock(double power, double time){
		addSequential(new DriveStraight(power, -power, time));
		addSequential(new WaitCommand(0.25));
		addSequential(new DriveStraight(-power, power, time));
		addSequential(new WaitCommand(0.25));
		addSequential(new DriveStraight(power, -power, time));
		addSequential(new WaitCommand(0.25));
		addSequential(new DriveStraight(-power, power, time));
		addSequential(new WaitCommand(0.25));
	}
}
