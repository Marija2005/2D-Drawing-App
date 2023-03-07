package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape {
	protected Point center;
	protected int radius;
	protected boolean selected;
	private boolean potvrda;
	
	
	public boolean isPotvrda() {
		return potvrda;
	}

	public void setPotvrda(boolean potvrda) {
		this.potvrda = potvrda;
	}

	public Circle() {
		
	}
	
	public Circle(Point center, int radius) throws Exception {
		setCenter(center);
		setRadius(radius);
	}
	
	
	public Circle(Point center, int radius, Color outlineColor, Color innerColor) throws Exception {
		this(center,radius);
		setOutlineColor(outlineColor);
		setInnerColor(innerColor);
	}
	
	public Circle(Point center, int radius, boolean selected) throws Exception {
		this(center,radius);
		setSelected(selected);
	}
	
	
	public double area() {
		return radius*radius*Math.PI;
	}
	
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle circle = (Circle) obj;
			return center.equals(circle.getCenter()) && radius == circle.getRadius();
		}
		return false;
	}
	
	public int compareTo(Object o) {
		if(o instanceof Circle)
			return (int) ((this.area())-((Circle)o).area());
		return 0;
	}
	
	public void moveBy(int x, int y) {
		center.moveBy(x, y);
	}
	
	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		double temp = center.distance(x, y);
		return temp <= radius;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getInnerColor());
		g.fillOval(center.getX() - radius, center.getY() - radius, 2*radius-1, 2*radius-1);
		g.setColor(getOutlineColor());
	g.drawOval(center.getX()-radius, center.getY()-radius, 2*radius, radius+radius);
	if (isSelected()) {
		g.setColor(Color.BLUE);
		g.drawRect(center.getX() - radius - 3, center.getY() - 3, 6, 6);
		g.drawRect(center.getX() + radius - 3, center.getY() - 3, 6, 6);
		g.drawRect(center.getX() - 3, center.getY() - radius - 3, 6, 6);
		g.drawRect(center.getX() - 3, center.getY() + radius - 3, 6, 6);
		g.drawRect(center.getX() - 3, center.getY() - 3, 6, 6);
	}
	}
	
	public Circle clone() {
		Circle circle = new Circle();
		circle.setCenter(new Point(getCenter().getX(),getCenter().getY()));
		try {
			circle.setRadius(radius);
		} catch (Exception e) {
			e.printStackTrace();
		}
		circle.setOutlineColor(getOutlineColor());
		circle.setInnerColor(getInnerColor());
		circle.setSelected(isSelected());
		return circle;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setRadius(int radius) throws Exception {
		if(radius>=0)
			this.radius = radius;
		else
			throw new Exception("Radius can't bre a negative number");
	}
	
	public String toString() {
		return "Circle: radius=" + radius + "; x=" + center.getX() + "; y=" + center.getY() + "; outline color=" + getOutlineColor().toString().substring(14).replace('=', '-') + "; inner color=" + getInnerColor().toString().substring(14).replace('=', '-');
	}
	
	
}
