package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Ellipse2D;


public class Donut extends Circle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int innerRadius;
	private int outerRadius;
	private Color outlineColor;
	private Color innerColor;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int outerRadius, int innerRadius) throws Exception {
		super(center,outerRadius);
		if(innerRadius < outerRadius) {
			this.innerRadius = innerRadius;
			this.outerRadius = outerRadius;
		}
		else
		{
			throw new Exception("Inner radius must be smaller than outer radius");
		}
	}
	
	public Donut(Point center, int outerRadius, int innerRadius, boolean selected) throws Exception {
		this(center,outerRadius,innerRadius);
		this.selected = selected;
	}
	
	public Donut(Point center, int outerRadius, int innerRadius, Color outlineColor, Color innerColor) throws Exception {
		super(center, outerRadius);
		if(innerRadius < outerRadius) 
		{
			this.innerRadius = innerRadius;
			this.outerRadius = outerRadius;
			this.outlineColor = outlineColor;
			this.innerColor = innerColor;
		}
		else
		{
			throw new Exception("Inner radius must be smaller than outer radius!");
		}
	}
	
	public double area() {
		return super.area() - (innerRadius*innerRadius*Math.PI);
	}
	
	public Donut clone() {
		try {
			return new Donut(center.clone(), outerRadius, innerRadius,getOutlineColor(), getInnerColor());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Path2D path = new Path2D.Double(Path2D.WIND_EVEN_ODD);
		
		path.append(new Ellipse2D.Double(getCenter().getX() - getOuterRadius(), this.getCenter().getY() - getOuterRadius(), getOuterRadius()*2, getOuterRadius()*2), false);
		path.append(new Ellipse2D.Double(getCenter().getX() - getInnerRadius(), this.getCenter().getY() - getInnerRadius(), getInnerRadius()*2, getInnerRadius()*2), false);
		
		g2d.setColor(getInnerColor());
		g2d.fill(path);
		
		g2d.setColor(getOutlineColor());
		g2d.drawOval(getCenter().getX() - getOuterRadius(), this.getCenter().getY() - getOuterRadius(), getOuterRadius()*2, getOuterRadius()*2);
		g2d.drawOval(getCenter().getX() - getInnerRadius(), this.getCenter().getY() - getInnerRadius(), getInnerRadius()*2, getInnerRadius()*2);
		
		if (isSelected()) {
			g2d.setColor(Color.BLUE);
			g2d.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g2d.drawRect(getCenter().getX() - 3 - getOuterRadius(), getCenter().getY() - 3, 6, 6);
			g2d.drawRect(getCenter().getX() - 3 + getOuterRadius(), getCenter().getY() - 3, 6, 6);
			g2d.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 + getOuterRadius() , 6, 6);
			g2d.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 - getOuterRadius(), 6, 6);
		}
	}
	
	public boolean contains(int x, int y) {
		return super.contains(x, y) && center.distance(x, y) > innerRadius;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) throws Exception {
		if(innerRadius > 0 && innerRadius < outerRadius) {
			this.innerRadius = innerRadius;
		}
		else 
			throw new Exception("Inner must be greather than zero and inner must be smaller than outer");
	}
	
	public int getOuterRadius() {
		return outerRadius;
	}

	public void setOuterRadius(int outerRadius) {
		this.outerRadius = outerRadius;
	}

	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public String toString() {
		return "Donut: radius=" + outerRadius + "; x=" + center.getX() + "; y=" + center.getY() + "; inner radius=" + innerRadius + "; outline color=" + getOutlineColor().toString().substring(14).replace('=', '-') + "; inner color=" + getInnerColor().toString().substring(14).replace('=', '-');
	}
	
}
