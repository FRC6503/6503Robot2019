package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team6503.robot.Robot;

public class CupidShuffle2018 extends AutonomousBase {
	public CupidShuffle2018() {
		for (int direction = 0; direction < 4; direction++) {
			toTheRight();
			toTheRight();
			waitSeconds(0.2);
			
			toTheLeft();
			toTheLeft();
			waitSeconds(0.2);
			
			kick();
			kick();
			walkItByYourself();
			waitSeconds(0.1);
		}
	}
	
	protected void toTheLeft() {
		toTheDirection(-1);
	}
	
	protected void toTheRight() {
		toTheDirection(1);	
	}
	
	protected void toTheDirection(int direction) {
		final double turn = 15;
		final double drive = 1.5;
		
		turnDegrees(direction * turn);
		driveFeet(drive);
		turnDegrees(-2 * direction * turn);
		driveFeet(-drive);
		turnDegrees(direction * turn);
	}
	
	protected void kick() {
		driveFeet(1.0);
		waitSeconds(0.5);
		driveFeet(-1.0);
		waitSeconds(0.5);
	}
	
	protected void walkItByYourself() {
		turnDegrees(-90);
		waitSeconds(1.0);
	}
}