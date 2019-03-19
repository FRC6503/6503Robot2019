package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team6503.robot.Robot;

public class AutonomousBase extends CommandGroup {
    private AutonomousCommandFactory autonomous;

	public AutonomousBase() {
        this.autonomous = new AutonomousCommandFactory(this);
	}

    public void setSwitch(char switchSide) {
        // Override me in child class!
    }

    protected void waitSeconds(double seconds) {
        autonomous.waitSeconds(seconds);
    }

    protected void driveFeet(double feet) {
        autonomous.driveFeet(feet);
    }

    protected void turnDegrees(double degrees) {
        autonomous.turnDegrees(degrees);
    }

    protected void shootCube() {
        autonomous.shootCube();
    }
}
