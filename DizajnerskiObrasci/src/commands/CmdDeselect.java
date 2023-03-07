package commands;

import java.util.List;

import mvc.ApplicationModel;
import shapes.Command;
import shapes.Shape;

public class CmdDeselect implements Command{
	private ApplicationModel model;
	private Shape selectedShape;
	
	public CmdDeselect(ApplicationModel model, Shape selectedShape) {
		this.model = model;
		this.selectedShape = selectedShape;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.getShapes().get(model.getShapes().indexOf(selectedShape)).setSelected(false);
		model.getSelectedShapes().remove(selectedShape);
	}
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.getShapes().get(model.getShapes().indexOf(selectedShape)).setSelected(true);
		model.getSelectedShapes().add(selectedShape);
	}
	
	public String toString() {
		return "Deselected->" + selectedShape.toString();
	}

}
