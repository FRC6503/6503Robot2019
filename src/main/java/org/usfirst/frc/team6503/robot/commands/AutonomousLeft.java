package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team6503.robot.Robot;

public class AutonomousLeft extends AutonomousBase {
	public AutonomousLeft() {
	}

    public void setSwitch(char switchSide) {
        if (switchSide == 'L') {
            scoreOnForwardSwitch();
            return;
        }

        crossTheLine();
    }

	protected void scoreOnForwardSwitch() {
		driveFeet(17);
		turnDegrees(-90);
		driveFeet(-6);
		shootCube();
	}
	
	protected void crossTheLine() {
		driveFeet(17);
	}
}
