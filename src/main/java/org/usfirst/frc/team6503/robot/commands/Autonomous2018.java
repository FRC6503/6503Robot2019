package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team6503.robot.Robot;

public class Autonomous2018 extends AutonomousBase {
	public Autonomous2018() {
		crossTheLine();
		// justShoot();
	}
	
	protected void scoreOnForwardSwitch() {
		driveFeet(5);
		turnDegrees(180);
		driveFeet(-8);
		shootCube();
	}
	
	protected void crossTheLine() {
		driveFeet(17);
	}
	
	protected void justShoot() {
		shootCube();
	}
}