package org.usfirst.frc.team6503.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team6503.robot.Robot;

public class AutonomousCenter extends AutonomousBase {
	public AutonomousCenter() {
	}

    public void setSwitch(char switchSide) {
        if (switchSide == 'L') {
            scoreOnLeftSwitch();
            return;
        } else if (switchSide == 'R') {
            scoreOnRightSwitch();
            return;
        }

        crossLeftLine();
    }
	
	protected void scoreOnLeftSwitch() {
		driveFeet(5);
		turnDegrees(90);
		driveFeet(-4.5);
        turnDegrees(90);
        driveFeet(-8);
		shootCube();
	}

    protected void scoreOnRightSwitch() {
        driveFeet(5);
        turnDegrees(90);
        driveFeet(4.5);
        turnDegrees(90);
        driveFeet(-8);
        shootCube();
    }
	
	protected void crossLeftLine() {
		driveFeet(5);
        turnDegrees(90);
        driveFeet(-10);
        turnDegrees(90);
        driveFeet(-10);
	}
}
