package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team6503.robot.Robot;

public class AutonomousRight extends AutonomousBase {
	public AutonomousRight() {
	}

    public void setSwitch(char switchSide) {
        if (switchSide == 'R') {
            scoreOnForwardSwitch();
            return;
        }

        crossTheLine();
    }
	
	protected void scoreOnForwardSwitch() {
		driveFeet(17);
		turnDegrees(90);
		driveFeet(-6);
		shootCube();
		/*
		driveFeet(5);
		turnDegrees(180);
		driveFeet(-8);
		shootCube();
		*/
	}
	
	protected void crossTheLine() {
		driveFeet(17);
	}
}
