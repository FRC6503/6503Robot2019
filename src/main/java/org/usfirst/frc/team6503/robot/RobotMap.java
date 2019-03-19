package org.usfirst.frc.team6503.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static SpeedController driveLeftDrive1;
	public static SpeedController driveLeftDrive2;
	public static SpeedController driveRightDrive1;
	public static SpeedController driveRightDrive2;
	public static SpeedController liftMotor;
	public static SpeedController cubeMotor1;
	public static SpeedController cubeMotor2;
	public static SpeedController climberMotor;
	public static ADXRS450_Gyro gyro;

	public static int year;

	public static void init() {
		initialize2019();

		// initializeCallisto();
		// initializeGalaxy();
	}

	private static void initialize2019() {
		driveLeftDrive1 = new CANSparkMax(9, MotorType.kBrushless);
		driveLeftDrive2 = new CANSparkMax(8, MotorType.kBrushless);
		driveRightDrive1 = new CANSparkMax(2, MotorType.kBrushless);
		driveRightDrive2 = new CANSparkMax(1, MotorType.kBrushless);

		year = 2019;

		// Unused for 2019 -- include just to prove that the robot can drive
		liftMotor = new Spark(5);
		cubeMotor1 = new Spark(6);
		cubeMotor2 = new Spark(4);
		climberMotor = new Spark(7);
	}

	private static void initializeCallisto() {
		driveLeftDrive1 = new VictorSP(0);
		driveLeftDrive2 = new Spark(1);
		driveRightDrive1 = new Spark(2);
		driveRightDrive2 = new VictorSP(3);

		year = 2018;

		liftMotor = new Spark(5);
		cubeMotor1 = new Spark(6); // Left
		cubeMotor2 = new Spark(4); // Right

		// gyro = new ADXRS450_Gyro();
		// gyro.calibrate();

		climberMotor = new Spark(7); // Ignore me, unused for 2018
	}

	private static void initializeGalaxy() {
		driveLeftDrive1 = new Victor(0);
		driveLeftDrive2 = new Spark(1);
		driveRightDrive1 = new Spark(2);
		driveRightDrive2 = new Victor(3);

		year = 2017;

		liftMotor = new Victor(4);
		cubeMotor1 = new Spark(5);
		cubeMotor2 = new Spark(6);

		// gyro = new ADXRS450_Gyro();
		// gyro.calibrate();

		climberMotor = new Spark(7); // Ignore me, unused for 2018
	}
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
