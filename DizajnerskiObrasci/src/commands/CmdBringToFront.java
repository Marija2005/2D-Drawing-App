package commands;

import mvc.ApplicationModel;
import shapes.Command;
import shapes.Shape;

public class CmdBringToFront implements Command{
	private ApplicationModel model;
	private Shape shape;
	private int indexBefore;
	
	public CmdBringToFront(ApplicationModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		indexBefore = model.getIndexOfShape(shape);
		model.getShapes().remove(shape);
		model.getShapes().add(shape);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.getShapes().remove(shape);
		model.getShapes().add(indexBefore, shape);
	}
	
	public String toString() {
		return "Bring to front->" + shape.toString();
	}
}
