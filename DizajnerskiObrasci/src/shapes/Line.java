package shapes;

import java.awt.Color;
import java.awt.Graphics;


public class Line extends Shape {
	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, Color color) {
		this(startPoint,endPoint);
		setOutlineColor(color);
	}
	
	public String toString() {
		return "Line: start point x=" + startPoint.getX() + "; start point y=" + startPoint.getY() + "; end point x=" + endPoint.getX() + "; end point y=" + endPoint.getY() + "; color=" + getOutlineColor().toString().substring(14).replace('=', '-');
	}
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line line = (Line) obj;
			return startPoint.equals(line.startPoint) && endPoint.equals(line.endPoint);
		}
		return false;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		double temp = startPoint.distance(x, y) + endPoint.distance(x, y);
		return temp - this.length() <=3;
	}

	@Override
	public Line clone() {
		Line line =  new Line(new Point(startPoint.clone().getX(),startPoint.getY())
				,new Point(endPoint.getX(),endPoint.getY())
				,getOutlineColor());
		line.setSelected(isSelected());
		return line;
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getOutlineColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getX()-3, startPoint.getY()-3, 6, 6);
			g.drawRect(endPoint.getX()-3, endPoint.getY()-3, 6, 6);
		}
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
//	public Point middleOfLine() {
//		int middleByX = (this.getStartPoint().getX() + this.getEndPoint().getX()) / 2;
//		int middleByY = (this.getStartPoint().getY() + this.getEndPoint().getY()) / 2;
//		Point p = new Point(middleByX, middleByY);
//		return p;
//	}
	
}
