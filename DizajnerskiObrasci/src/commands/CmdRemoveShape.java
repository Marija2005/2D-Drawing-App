package commands;

import mvc.ApplicationModel;
import shapes.Command;
import shapes.Shape;

public class CmdRemoveShape implements Command {
	
	private ApplicationModel model;
	private Shape shape;
	
	public CmdRemoveShape(ApplicationModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.removeShape(shape); //brise se
		model.getSelectedShapes().remove(shape); //brise se iz selektovanih oblika
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.getShapes().add(shape);
		shape.isSelected();
	}
	
	@Override
	public String toString() {
		return "Deleted->" + shape.toString();
	}

}
