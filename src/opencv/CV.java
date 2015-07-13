package opencv;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.CvType;
import org.opencv.imgproc.Imgproc;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;
import javax.swing.JDialog;


public class CV {
	public void compare_image(BufferedImage img_1,BufferedImage img_2)
	{
		Mat mat_1 = conv_Mat(img_1);
		Mat mat_2 = conv_Mat(img_2);
		
		Mat hist_1 = new Mat();
		Mat hist_2 = new Mat();
		
		MatOfFloat ranges = new MatOfFloat(0f,256f);
		MatOfInt histSize = new MatOfInt(25);
		
		Imgproc.calcHist(Arrays.asList(mat_1), new MatOfInt(0), 
				new Mat(), hist_1, histSize, ranges);
		Imgproc.calcHist(Arrays.asList(mat_2), new MatOfInt(0),
				new Mat(), hist_2, histSize, ranges);
	    
		double res = Imgproc.compareHist(hist_1, hist_2, Imgproc.CV_COMP_CORREL);
		Double d = new Double(res*100);
		Result dialog = new Result();
		dialog.disp_percen(d.intValue());
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	private Mat conv_Mat(BufferedImage img){
		byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		Mat mat = new Mat(img.getHeight(),img.getWidth(),CvType.CV_8UC3);
		mat.put(0,0,data);
		
		Mat mat1 = new Mat(img.getHeight(),img.getWidth(),CvType.CV_8UC3);
		Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2HSV);
		
		return mat1;
	}
	
}
