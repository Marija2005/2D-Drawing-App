package adapters;

import hexagon.Hexagon;
import shapes.Point;
import shapes.SurfaceShape;
import java.awt.*;
import java.io.Serializable;


public class HexagonAdapter extends SurfaceShape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	public HexagonAdapter() {
		
	}
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	public HexagonAdapter(Point center, int radius) {
		this.hexagon = new Hexagon(center.getX(),center.getY(),radius);
	}
	
	public HexagonAdapter(Hexagon hexagon, Color innerColor, Color outlineColor) {
		this.hexagon = hexagon;
		this.hexagon.setAreaColor(innerColor);
		this.hexagon.setBorderColor(outlineColor); 	
	}
	
	public HexagonAdapter(Point center, int radius, Color outlineColor, Color innerColor, boolean selected) {
		this(center,radius);
		this.hexagon.setBorderColor(outlineColor);
		this.hexagon.setAreaColor(innerColor);
		this.hexagon.setSelected(selected);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			Hexagon hex = ((HexagonAdapter) obj).hexagon;
			return hexagon.getX() == hex.getX() && hexagon.getY() == hex.getY() && hexagon.getR() == hex.getR();
		}
		return false;
	}
	
	@Override
	public HexagonAdapter clone() {
		HexagonAdapter hex = new HexagonAdapter(new Point(hexagon.getX(), hexagon.getY()), hexagon.getR());
		hex.setOutlineColor(getOutlineColor());
		hex.setInnerColor(getInnerColor());
		hex.setSelected(isSelected());
		return hex;
	}
	
	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	
	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}
	
	@Override
	public String toString() {
		return "Hexagon:x=" + hexagon.getX() + "; y=" + hexagon.getY() + "; radius=" + hexagon.getR() + "; outline color=" + hexagon.getBorderColor().getRGB() + "; inner color=" + hexagon.getAreaColor().getRGB() + "; selected=" + isSelected();		
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	public boolean isSelected() {
		return this.hexagon.isSelected();
	}
	
	public void setSelected(boolean selected) {
		this.hexagon.setSelected(selected);
	}
	
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	public void setInnerColor(Color color) {
		hexagon.setAreaColor(color);
	}
	
	public Color getOutlineColor() {
		return hexagon.getBorderColor();
	}
	
	public void setOutlineColor(Color outline) {
		hexagon.setBorderColor(outline);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public void setY(int y) {
		hexagon.setY(y);
	}
	
	public void setRadius(int radius) {
		hexagon.setR(radius);
	}
	
	public int getRadius() {
		return hexagon.getR();
	}
}
