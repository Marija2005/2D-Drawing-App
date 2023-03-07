package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape {
	private Point upperLeftPoint;
	private int width;
	private int height;
	
	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height, Color outlineColor, Color fillColor) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
		setOutlineColor(outlineColor);
		setInnerColor(fillColor);
	}

	public Rectangle(Point upperLeftPoint,int width, int height, boolean selected) {
		this(upperLeftPoint,width,height);
		this.selected = selected;
	}
	public Rectangle(int x, int y, int width, int height)
	{
		upperLeftPoint.setX(x);
		upperLeftPoint.setY(y);
		this.width = width;
		this.height = height;
	}
	
	public int area() {
		return width*height;
	}
	
	public Rectangle clone() {
    	return new Rectangle(upperLeftPoint.clone(), width, height, getOutlineColor(), getInnerColor());
    }
	
	public boolean equals(Object obj) {
		Rectangle temp;
		if(obj instanceof Rectangle) {
			temp = (Rectangle) obj;
		if(this.area()==temp.area())
			return true;
		else
			return false;
		}
		else
			return false;
	}
	
	public String toString() {
		return "Rectangle: x=" + upperLeftPoint.getX() + "; y=" + upperLeftPoint.getY() + "; width=" + width + "; height=" + height + "; outline color=" + getOutlineColor().toString().substring(14).replace('=', '-') + "; inner color=" + getInnerColor().toString().substring(14).replace('=', '-');
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) throws Exception {
		if(width>=0)
		this.width = width;
		else
			throw new Exception();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) throws Exception {
		if(height>=0)
		this.height = height;
		else
			throw new Exception();
	}
	
	
 

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return (x >= this.getUpperLeftPoint().getX() && 
			x <= this.getUpperLeftPoint().getX() + width && 
			y >= this.getUpperLeftPoint().getY() && 
			y <= this.getUpperLeftPoint().getY() + height); 
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getInnerColor());
		g.fillRect(upperLeftPoint.getX()+1, upperLeftPoint.getY()+1, width-1, height-1);
		g.setColor(getOutlineColor());
	g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
	if (isSelected()) {
		g.setColor(Color.BLUE);
		g.drawRect(upperLeftPoint.getX() - 3, upperLeftPoint.getY() - 3, 6, 6);
		g.drawRect(upperLeftPoint.getX() + width - 3, upperLeftPoint.getY() - 3, 6, 6);
		g.drawRect(upperLeftPoint.getX() - 3, upperLeftPoint.getY() + height - 3, 6, 6);
		g.drawRect(upperLeftPoint.getX() + width  - 3, upperLeftPoint.getY() + height - 3, 6, 6);
	}
}
	
	
}
