package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	private int x;
	private int y;
	
	public Point() {
		
	}
	
	public Point(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		setOutlineColor(color);
	}
	
	
	public boolean equals(Object obj) {
		Point temp;
		if(obj instanceof Point) {
			temp=(Point)obj;
			if (x==temp.getX()&&y==temp.getY())
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public double distance(int x,int y) {
		int dx = this.x - x;
		int dy = this.y - y;
		double d = Math.sqrt(dx*dx + dy*dy);
		return d;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public String toString() {
		return "Point: x=" + x + "; y=" + y + "; color=" + getOutlineColor().toString().substring(14).replace('=', '-');
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return this.distance(x,y) <=3;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getOutlineColor());
		g.drawLine(x - 2, y, x + 2, y);
		g.drawLine(x, y + 2, x,y - 2);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(x-3,y-3,6,6);
		}
	}
	
	public Point clone() {
	    return new Point(x, y, getOutlineColor());
	}
	
	public void moveBy(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
}
