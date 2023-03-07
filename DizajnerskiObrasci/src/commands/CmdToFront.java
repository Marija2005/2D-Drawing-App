package commands;

import java.util.Collections;

import mvc.ApplicationModel;
import shapes.Command;
import shapes.Shape;

public class CmdToFront implements Command {
	private ApplicationModel model;
	private int index;
	private Shape shape;
	
	public CmdToFront(ApplicationModel model, int index, Shape shape) {
		this.model = model;
		this.index = index;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(index != model.getShapes().size() - 1) {
			Collections.swap(model.getShapes(), index+1, index);
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		if(index != model.getShapes().size() - 1) {
			Collections.swap(model.getShapes(), index, index+1);
		}
	}
	
	public String toString() {
		return "To front->" + shape.toString();
	}

}
