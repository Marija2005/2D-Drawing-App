package commands;

import shapes.Command;
import shapes.Point;

public class CmdUpdatePoint implements Command {
	
	private Point oldPoint;
	private Point newPoint;
	private Point original = new Point();
	
	public CmdUpdatePoint(Point oldPoint, Point newPoint) {
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		original = oldPoint.clone(); //ovo nam treba da bismo sacuvali staro stanje
		//jer kad bismo hteli da izvrsimo unexecute ako bismo imali samo newPoint(oldPoint)
		//dobili bismo isti rezultat kao i u execute,jer smo gore u stvari novo stanje postavili na staro
		oldPoint.setX(newPoint.getX());
		oldPoint.setY(newPoint.getY());
		oldPoint.setOutlineColor(newPoint.getOutlineColor());
		oldPoint.setSelected(newPoint.isSelected());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldPoint.setX(original.getX());
		oldPoint.setY(original.getY());
		oldPoint.setOutlineColor(original.getOutlineColor());
		oldPoint.setSelected(original.isSelected());
	}
	
	@Override
	public String toString() {
		return "Updated->"+oldPoint.toString() + "->" + newPoint.toString();
	}
	

}
