package org.usfirst.frc.team6503.robot.commands;

import java.util.ArrayList;

public class PrerecordedOperation {
	private double[] powers;
	private long[] times;
	private double[] cubes;
	
	private static long UNINITIALIZED = -1l;
	private static double DISABLED_POWER = 0.0;
	
	private long startTime = UNINITIALIZED;
	private boolean active = false;
	
	public PrerecordedOperation(double[] powers, long[] times, double[] cubes) {
		this.powers = powers;
		this.times = times;
		this.cubes = cubes;
	}
	
	public static PrerecordedOperation STOW_ARM() {
		return new PrerecordedOperation(
			new double[] { 0.5 },
			new long[] { 1250 },
			new double[] { 0.0 }
		);
	}
	
	public static PrerecordedOperation DEPLOY_ARM() {
		return new PrerecordedOperation(
			new double[] { -0.4, 0.2 },
			new long[] { 1000, 1000 },
			new double [] { 0.0, 0.0 }
		);
	}
	
	public static PrerecordedOperation SHOOT_CUBE() {
		return new PrerecordedOperation(
			new double[] { 0.45, 0.35, -0.3 },
			new long[] { 700, 1000, 700 },
			new double[] { 0.0, 0.6, 0.0 }
		);
	}

	public void start() {
		startTime = System.currentTimeMillis();
		active = true;
	}
	
	public double getPower() {
		Double power = read(this.powers);
		
		if (power == null) {
			active = false;
			return DISABLED_POWER;
		}
		
		return power;
	}
	
	public double getCube() {
		Double cube = read(this.cubes);
		
		if (cube == null) {
			active = false;
			return DISABLED_POWER;
		}
		
		return cube;
	}
	
	private Double read(double[] arr) {
		if (!active) {
			return DISABLED_POWER;
		}
		
		long sumTime = 0;
		long currentTime = currentOffsetTime();
		
		for (int index = 0; index < times.length; index++) {
			sumTime += times[index];
			
			if (currentTime < sumTime) {
				return arr[index];
			}
		}
		
		return null;
	}
	
	public boolean isActive() {
		return active;
	}
	
	private long currentOffsetTime() {
		if (startTime == UNINITIALIZED) {
			return UNINITIALIZED;
		}
		
		return System.currentTimeMillis() - startTime;
	}
}
