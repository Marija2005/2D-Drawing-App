package commands;

import java.util.Collections;

import mvc.ApplicationModel;
import shapes.Command;
import shapes.Shape;

public class CmdToBack implements Command {
	private ApplicationModel model;
	private int index;
	private Shape shape;
	
	public CmdToBack(ApplicationModel model, int index, Shape shape)
	{
		this.model = model;
		this.index = index;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(index != 0) {
			Collections.swap(model.getShapes(), index-1, index);
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		if(index != 0) {
			Collections.swap(model.getShapes(), index, index-1);
		}
	}
	
	public String toString() {
		return "To back->" + shape.toString();
	}
}
