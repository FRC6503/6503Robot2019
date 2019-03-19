package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team6503.robot.Robot;

public class AutonomousCommandFactory {
    // CONSTANTS ALL TESTED MANUALLY, ONLY CHANGE IF YOU'RE VERY SURE.
	final static double FT_PER_SEC = 3.0;
	final static double DRIVE_SPEED = 0.3;
	
	final static double DEG_PER_SEC = 113.0;
	final static double TURN_SPEED = 0.30;

    private CommandGroup autonomousGroup;

    public AutonomousCommandFactory(CommandGroup autonomousGroup) {
        this.autonomousGroup = autonomousGroup;
    }

    public void waitSeconds(double seconds) {
        autonomousGroup.addSequential(new DriveStraight(0.0, 0.0, seconds));
    }

    public void driveFeet(double feet) {
		double time = Math.abs(feet / FT_PER_SEC);
		int direction = (feet < 0) ? -1 : 1;
        autonomousGroup.addSequential(
            new DriveStraight(direction * DRIVE_SPEED, direction * DRIVE_SPEED * 0.9, time));
    }

    public void turnDegrees(double degrees) {
		double time = Math.abs(degrees / DEG_PER_SEC);
		int direction = (degrees < 0) ? -1 : 1;
        autonomousGroup.addSequential(
		    new DriveStraight(direction * TURN_SPEED, -direction * TURN_SPEED, time));
    }

	public void shootCube() {
		autonomousGroup.addSequential(new ShootCube());
	}
}
