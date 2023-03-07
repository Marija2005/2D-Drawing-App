package strategy;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.AWTException;
import java.awt.Robot;

import mvc.ApplicationFrame;

public class SaveSerialized implements SaveOpen {
	private ApplicationFrame frame;
	
	public SaveSerialized(ApplicationFrame frame) {
		this.frame = frame;
	}
	@Override
	public void save(File file) {
		// TODO Auto-generated method stub
		BufferedImage imageBuffer = null;
		try {
			imageBuffer = new Robot().createScreenCapture(frame.getApplicationView().getBounds());
			frame.getApplicationView().paint(imageBuffer.createGraphics());
			ImageIO.write(imageBuffer, "jpeg", new File(file + ".jpeg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void open(File file) {
		// TODO Auto-generated method stub
		
	}

}
