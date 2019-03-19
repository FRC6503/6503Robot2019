package org.usfirst.frc.team6503.robot.vision;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {

	private static Vision singleton = new Vision();
	public static Vision getInstance(){
		return singleton;
	}
	
	GripPipeline pipeline = new GripPipeline();
	Mat image = new Mat();
	CvSink sink;
	CvSource output = new CvSource("gripOutput", VideoMode.PixelFormat.kMJPEG, 1920, 1080, 30);
	
	private Vision(){
		this.sink = null;
	}
	
	public void setSink(CvSink sink){
		this.sink = sink;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Command(){
			{
				requires(getInstance());
			}
			
			@Override
			protected boolean isFinished() {
				if(sink != null){
					sink.grabFrame(image);
					pipeline.process(image);
					output.putFrame(image);
				}
				return false;
			}
		});
	}

}
