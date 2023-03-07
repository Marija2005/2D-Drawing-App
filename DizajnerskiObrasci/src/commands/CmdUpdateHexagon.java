package commands;

import adapters.HexagonAdapter;
import shapes.Command;

public class CmdUpdateHexagon implements Command {
	private HexagonAdapter oldHexagon;
	private HexagonAdapter newHexagon;
	private HexagonAdapter original;
	
	public CmdUpdateHexagon(HexagonAdapter oldHexagon, HexagonAdapter newHexagon) {
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		original = oldHexagon.clone();
		
		oldHexagon.setX(newHexagon.getX());
		oldHexagon.setY(newHexagon.getY());
		oldHexagon.setRadius(newHexagon.getRadius());
		oldHexagon.setOutlineColor(newHexagon.getOutlineColor());
		oldHexagon.setInnerColor(newHexagon.getInnerColor());
		
		oldHexagon.setSelected(newHexagon.isSelected());
		System.out.println(newHexagon.isSelected());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldHexagon.setX(original.getX());
		oldHexagon.setY(original.getY());
		oldHexagon.setRadius(original.getRadius());
		oldHexagon.setOutlineColor(original.getOutlineColor());
		oldHexagon.setInnerColor(original.getInnerColor());
		oldHexagon.setSelected(original.isSelected());
	}
	
	@Override
	public String toString() {
		return "Updated->" + oldHexagon.toString() + "->" + newHexagon.toString();
	}
}
