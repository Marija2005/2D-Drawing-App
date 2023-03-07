package commands;

import mvc.ApplicationModel;
import shapes.Command;
import shapes.Shape;

public class CmdBringToBack implements Command {
	private ApplicationModel model;
	private int index;
	private Shape shape;
	
	public CmdBringToBack(ApplicationModel model, int index, Shape shape) {
		this.model = model;
		this.index = index;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(index != 0) {
			model.getShapes().remove(shape);
			model.getShapes().add(0, shape);
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.getShapes().remove(shape);
		model.getShapes().add(index, shape);
	}
	
	public String toString() {
		return "Bring to back->" + shape.toString();
	}

}
