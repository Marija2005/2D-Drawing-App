package commands;

import mvc.ApplicationModel;
import shapes.Command;
import shapes.Shape;

public class CmdAddShape implements Command {
	
	private ApplicationModel model; //dodaje se odredjeni shape na neki model
	private Shape shape;
	
	public CmdAddShape(ApplicationModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.addShape(shape);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.removeShape(shape);
	}
	
	@Override
	public String toString() {
		return "Added->" + shape.toString(); 
	}
}
