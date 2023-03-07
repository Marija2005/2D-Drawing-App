package commands;

import shapes.Command;
import shapes.Line;
import java.awt.*;

public class CmdUpdateLine implements Command {
	
	private Line oldLine;
	private Line newLine;
	private Line original = new Line();
	
	public CmdUpdateLine(Line oldLine, Line newLine) {
		this.oldLine = oldLine;
		this.newLine = newLine;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		original = oldLine.clone();
		
		oldLine.getStartPoint().setX(newLine.getStartPoint().getX());
		oldLine.getStartPoint().setY(newLine.getStartPoint().getY());
		oldLine.getEndPoint().setX(newLine.getEndPoint().getX());
		oldLine.getEndPoint().setY(newLine.getEndPoint().getY());
		oldLine.setOutlineColor(newLine.getOutlineColor());
		oldLine.setSelected(newLine.isSelected());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldLine.getStartPoint().setX(original.getStartPoint().getX());
		oldLine.getStartPoint().setY(original.getStartPoint().getY());
		oldLine.getEndPoint().setX(original.getEndPoint().getX());
		oldLine.getEndPoint().setY(original.getEndPoint().getY());
		oldLine.setOutlineColor(original.getOutlineColor());
		oldLine.setSelected(original.isSelected());
	}
	
	@Override
	public String toString() {
		return "Updated->" + oldLine.toString() + "->" + newLine.toString();
	}

}
