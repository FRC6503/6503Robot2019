package org.usfirst.frc.team6503.robot.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team6503.robot.Robot;

/**
 *
 */
public class Autonomous1 extends CommandGroup {
	public Autonomous1() {
		addSequential(new DriveStraight(0.3, -0.3, 2));
	}
}
	