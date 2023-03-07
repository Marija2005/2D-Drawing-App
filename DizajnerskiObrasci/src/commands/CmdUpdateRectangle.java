package commands;

import shapes.Command;
import shapes.Point;

import java.awt.Color;
import shapes.Rectangle;

public class CmdUpdateRectangle implements Command {
	
	private Rectangle oldRectangle;
	private Rectangle newRectangle;
	private Rectangle original = new Rectangle(new Point(), 1, 1);
	
	public CmdUpdateRectangle(Rectangle oldRectangle, Rectangle newRectangle) {
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		original = oldRectangle.clone(); //u originalno klonira staro stanje
		oldRectangle.getUpperLeftPoint().setX(newRectangle.getUpperLeftPoint().getX()); //na staro stanje setuju novo
		oldRectangle.getUpperLeftPoint().setY(newRectangle.getUpperLeftPoint().getY()); //oldRectangle je ono sto se nalazi na ekranu
		try {
			oldRectangle.setHeight(newRectangle.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldRectangle.setWidth(newRectangle.getWidth());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldRectangle.setOutlineColor(newRectangle.getOutlineColor());
		oldRectangle.setInnerColor(newRectangle.getInnerColor());
		oldRectangle.setSelected(newRectangle.isSelected());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldRectangle.getUpperLeftPoint().setX(original.getUpperLeftPoint().getX()); //na staro stanje(trenutno) vraca originalno stanje
		oldRectangle.getUpperLeftPoint().setY(original.getUpperLeftPoint().getY());
		try {
			oldRectangle.setHeight(original.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldRectangle.setWidth(original.getWidth());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldRectangle.setOutlineColor(original.getOutlineColor());
		oldRectangle.setInnerColor(original.getInnerColor());
		oldRectangle.setSelected(original.isSelected());
	}
	
	@Override
	public String toString() {
		return "Updated->" + oldRectangle.toString() + "->" + newRectangle.toString();
	}

}
