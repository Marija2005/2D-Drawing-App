package commands;

import mvc.ApplicationModel;
import shapes.Command;
import shapes.Shape;

public class CmdSelect implements Command {
	private Shape shape;
	private ApplicationModel appModel;
	
	public CmdSelect(ApplicationModel appModel, Shape shape) {
		this.appModel = appModel;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		shape.setSelected(true);
		appModel.addSelectedShape(shape);
	}
	
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		shape.setSelected(false);
		appModel.getSelectedShapes().remove(shape);
	}
	
	@Override
	public String toString() {
		return "Selected->" + shape.toString();
	}
}
