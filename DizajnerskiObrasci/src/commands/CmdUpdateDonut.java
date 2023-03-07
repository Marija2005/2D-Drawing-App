package commands;

import shapes.Command;
import java.awt.Color;
import shapes.Donut;

public class CmdUpdateDonut implements Command {
	private Donut oldDonut;
	private Donut newDonut;
	private Donut original;
	
	public CmdUpdateDonut(Donut oldDonut, Donut newDonut)
	{
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		original = oldDonut.clone();
		try {
			oldDonut.setInnerRadius(newDonut.getInnerRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldDonut.setOuterRadius(newDonut.getOuterRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldDonut.setCenter(newDonut.getCenter().clone());
		if(newDonut.getOutlineColor() == Color.BLACK && original.getOutlineColor() != Color.BLACK) {
			oldDonut.setOutlineColor(original.getOutlineColor());
		} else {
			oldDonut.setOutlineColor(newDonut.getOutlineColor());
		}

		if(newDonut.getInnerColor() == Color.WHITE && original.getInnerColor() != Color.WHITE) {
			oldDonut.setInnerColor(original.getInnerColor());
		} else {
			oldDonut.setInnerColor(newDonut.getInnerColor());
		}
		oldDonut.setSelected(newDonut.isSelected());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldDonut.setOuterRadius(original.getOuterRadius());
		try {
			oldDonut.setInnerRadius(original.getInnerRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldDonut.setCenter(original.getCenter());
		oldDonut.setOutlineColor(original.getOutlineColor());
		oldDonut.setInnerColor(original.getInnerColor());
		oldDonut.setSelected(original.isSelected());
	}
	
	public String toString() {
		return "Updated->" + oldDonut.toString() + newDonut.toString();
	}

}
