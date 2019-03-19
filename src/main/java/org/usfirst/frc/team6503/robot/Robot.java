package org.usfirst.frc.team6503.robot;

import java.awt.Image;

//import org.usfirst.frc.team6503.robot.commands.Autonomous2018;
import org.usfirst.frc.team6503.robot.commands.Autonomous1;
//import org.usfirst.frc.team6503.robot.commands.AutonomousCenter;
//import org.usfirst.frc.team6503.robot.commands.AutonomousLeft;
//import org.usfirst.frc.team6503.robot.commands.AutonomousRight;
//import org.usfirst.frc.team6503.robot.commands.CupidShuffle2018;
import org.usfirst.frc.team6503.robot.subsystems.Arm;
import org.usfirst.frc.team6503.robot.subsystems.Climber;
import org.usfirst.frc.team6503.robot.subsystems.Drive;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
// import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
// import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6503.robot.vision.*;

public class Robot extends TimedRobot {

	/**
	 * The currently running autonomous command.
	 */
	Command autonomousCommand;

	// All powers range from 0 to 1
	private static double SHOOT_POWER = 1.0;
	private static double INTAKE_POWER = 0.6;
	
	/** 
	 * Be careful with this constant! It sets the maximum power for the arm motor
	 * which is very touchy. Increasing beyond roughly 0.6 will make the arm
	 * joystick difficult to control and possibly dangerous to the robot itself.
	 */
	private static double MAX_ARM_POWER = 0.6; 
	private static double ARM_DEADZONE = 0.1;

	/**
	 * OI is perpherials.
	 */
	public static OI oi;

	/**
	 * The drive instance. This handles driving through mutliple setspeed commands.
	 */
	public static Drive drive;
	/**
	 * Arm instance. Handles the arm.
	 */
	public static Arm arm;
	/**
	 * Climber instance. Pointless for 2019.
	 */
	public static Climber climber;
	// public static Vision vision;

	// Open the Smart dashboard application by opening file explorer >
	// Windows(C:) > Users > Team 5588 > wpilib > tools
	public SendableChooser<CommandGroup> autoChooser;
	public SendableChooser<CommandGroup> autonomousDirectionChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		System.out.println(greenText() + "Beep boop. I am ze robot. Beep.");
		RobotMap.init();

		// In the pit rn, can't use robot until we git'em on
		// arm = Arm.getInstance();
		drive = Drive.getInstance();
		climber = Climber.getInstance();

		oi = new OI();

		// used with sendable chooser
		autoChooser = new SendableChooser<CommandGroup>();
		// autoChooser.addDefault("Autonomous 1", new Autonomous1());
		// autoChooser.addObject("CupidShuffle", new CupidShuffle());

		// autoChooser.addDefault("Drive 2 seconds", new Autonomous1());
		/*
		autoChooser.addObject("LEFT", new AutonomousLeft());
		autoChooser.addObject("CENTER", new AutonomousCenter());
		autoChooser.addObject("RIGHT", new AutonomousRight());
		autoChooser.addObject("Croadfge", new Autonomous2018());
		autoChooser.addDefault("Cupid Shuffle 2018", new CupidShuffle2018());
		// dont forget this line!
		SmartDashboard.putData("AutoMode", autoChooser
		*/
		CameraServer server = CameraServer.getInstance();
		server.startAutomaticCapture();
		
		SmartDashboard.putBoolean(greenText() + "> makes greentext option", false);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		System.out.println(greenText() + "Beep boop. Extremely limited human interface allowed.");
		// schedule the autonomous command (example)
		// autonomousCommand = (Command) autoChooser.getSelected();

		// String switches = DriverStation.getInstance().getGameSpecificMessage();

		// char ourSwitch = '?';
		// if (switches.length() > 0) {
		// 	ourSwitch = switches.charAt(0);
		// }

		// ((AutonomousBase) autonomousCommand).setSwitch(ourSwitch);

		//  autonomousCommand.start(); // TODO: Uncomment when ready for autonomous
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		teleOp2019();
	}

	public void teleopInit() {
		System.out.println(greenText() + "Beep boop. Allowing full human interface control. Beep.");
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		teleOp2019();
	}

	public void teleOp2019() {
		// Scheduler.getInstance().run();
		final double speedMult = Math.max(Math.min(oi.driverJoystick.getTriggerAxis(GenericHID.Hand.kLeft), 0.7), 0.3);

		double fSpeed = deadzone(Robot.oi.driverJoystick.getX(GenericHID.Hand.kLeft)) * speedMult;
		double sSpeed = deadzone(Robot.oi.driverJoystick.getY(GenericHID.Hand.kLeft)) * speedMult;

		// DO NOT REMOVE THE CLAMPS!
		// I wIlL lItErAlLy DiE
		// 
		//  dddddddd
		//  dd    dd
		// dddd   dddddd	
		double lSpeed = Math.max(Math.min(fSpeed - sSpeed, 1), -1);
		double rSpeed = Math.max(Math.min(fSpeed + sSpeed, 1), -1);
		// dddd   dddddd
		//  dd    dd
		//  dddddddd
		//   
		if (lSpeed > 1 || lSpeed < -1 || rSpeed > 1 || rSpeed < -1) {
			System.out.println(greenText() + "AAAAAAAAAAAAAAA *wood crashing and meat grinding*");
		}

		drive.setSpeed(lSpeed, rSpeed);

		// In the pits brah, not used!
		//
		// if (Robot.oi.getArmJoystick().getRawButton(2)) {
		// 	arm.setCubeSpeed(-INTAKE_POWER);
		// } else if (Robot.oi.getArmJoystick().getRawButton(1)) {
		// 	arm.setCubeSpeed(SHOOT_POWER);
		// } else if (Robot.oi.getArmJoystick().getRawButton(3)) {
		// 	arm.setCubeSpeedUneven(-INTAKE_POWER, -INTAKE_POWER / 3.0);
		// } else {
		// 	arm.setCubeSpeed(0.0);
		// }

		// // Inverted y-axis for joystick
		// double power = armDeadZone(Robot.oi.getArmJoystick().getRawAxis(1)) * -MAX_ARM_POWER;
		// arm.setArmSpeed(power); 
	}

	private double deadzone(double in) {
		if (Math.abs(in) < 0.2) {
			return 0.0;
		}
		return in;
	}

	private double armDeadZone(double power) {
		if (power < -ARM_DEADZONE || power > ARM_DEADZONE) {
			return power;
		}
		
		return 0.0;
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		// LiveWindow.run();
	}

	public String greenText() {
		return SmartDashboard.getBoolean("> makes greentext option", false) ? "> " : "";
	}
}
