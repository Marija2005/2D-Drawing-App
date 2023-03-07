package commands;

import shapes.Circle;
import shapes.Command;
import java.awt.*;

public class CmdUpdateCircle implements Command {
	
	private Circle oldCircle;
	private Circle newCircle;
	private Circle original = new Circle();
	
	public CmdUpdateCircle(Circle oldCircle, Circle newCircle ) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		original = oldCircle.clone();
		
		oldCircle.getCenter().setX(newCircle.getCenter().getX());
		oldCircle.getCenter().setY(newCircle.getCenter().getY());
		try {
			oldCircle.setRadius(newCircle.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(newCircle.getOutlineColor() == Color.BLACK && original.getOutlineColor() != Color.WHITE) {
			oldCircle.setOutlineColor(original.getOutlineColor());
		} else {
			oldCircle.setOutlineColor(newCircle.getOutlineColor());
		}
		
		if(newCircle.getInnerColor() == Color.WHITE && original.getInnerColor() != Color.WHITE) {
			oldCircle.setInnerColor(original.getInnerColor());
		} else {
			oldCircle.setInnerColor(newCircle.getInnerColor());
		}
		oldCircle.setSelected(newCircle.isSelected());
		//System.out.println(newCircle.isSelected());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldCircle.getCenter().setX(original.getCenter().getX());
		oldCircle.getCenter().setY(original.getCenter().getY());
		try {
			oldCircle.setRadius(original.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldCircle.setInnerColor(original.getInnerColor());
		oldCircle.setOutlineColor(original.getOutlineColor());
		oldCircle.setSelected(original.isSelected());
	}
	
	@Override
	public String toString() {
		return "Updated->" + oldCircle.toString() + "->" + newCircle.toString();
	}

}
