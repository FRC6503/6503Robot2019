package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CupidShuffle extends CommandGroup {
	public CupidShuffle(){
		addSequential(new CupidShuffleStraight(0.5, 0.5));
		addSequential(new CupidShuffleStraight(-0.5, 0.5));
		addSequential(new TurnCommand(0.5, 1.3, true));
		addSequential(new CupidShuffleRock(0.5, 0.5));
		
	}
}
