package shapes;

import java.awt.*;
import java.awt.Graphics;
//za oblike koji imaju povrsinu, plus ima innerColor
public class SurfaceShape extends Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color innerColor;
	
	public Color getInnerColor() {
		return innerColor;
	}
	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	
}
