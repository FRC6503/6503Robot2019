package org.usfirst.frc.team6503.robot.vision;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import edu.wpi.first.wpilibj.vision.VisionPipeline;

import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;

/**
* YellowMatcher class.
*
* An OpenCV pipeline.
*
* @author GRIP
*/
public class YellowMatcher implements VisionPipeline {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	double[] YELLOW = {170, 180};

	/**
	 * This is the primary method that runs the entire pipeline and updates the outputs.
	 */
	@Override	
	public void process(Mat source) {
		Mat threshHsv = hsvThreshold(source, YELLOW);
		Imgproc.cvtColor(threshHsv, source, Imgproc.COLOR_HSV2BGR);
	}
	
	private Mat hsvThreshold(Mat input, double[] hue) {
		Mat thresholded = new Mat();
		Imgproc.cvtColor(input, thresholded, Imgproc.COLOR_BGR2HSV);
		Core.inRange(thresholded, new Scalar(hue[0], 0, 0),
		                  new Scalar(hue[1], 255, 255), thresholded);
		return thresholded;
	}
}