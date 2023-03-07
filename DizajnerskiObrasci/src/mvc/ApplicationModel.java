package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import shapes.Command;
import shapes.Point;
import shapes.Shape;

public class ApplicationModel {
	private List<Shape> shapes = new ArrayList<Shape>();
	private List<Shape> selectedShapes = new ArrayList<Shape>();
	public int undoCounter = 0;
	private Point startPoint;
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	private PropertyChangeSupport propertyChangeSupport;
	//private int i;
	
	public ApplicationModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.addPropertyChangeListener(pcl);
	} //prolazi kroz sve nase obzervere koji su se prijavili preko ove 2 metode
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.removePropertyChangeListener(pcl);
	}

	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(List<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public Stack<Command> getUndoStack() {
		return undoStack;
	}

	public void setUndoStack(Stack<Command> undoStack) {
		this.undoStack = undoStack;
	}

	public Stack<Command> getRedoStack() {
		return redoStack;
	}

	public void setRedoStack(Stack<Command> redoStack) {
		this.redoStack = redoStack;
	}

	public Shape getByIndex(int index) {
		return shapes.get(index);
	}
	
	public void addShape(Shape toBeAdded) {
		shapes.add(toBeAdded);
	}
	
	public ArrayList<Shape> getAllShapes() {
		return (ArrayList<Shape>) shapes;
	}
	
	public void addMultipleShapes(ArrayList<Shape> shapes) {
		this.shapes.addAll(shapes); 
	}
	
	public void removeAll() {
		shapes.clear();
	}
	
	public void removeShape(Shape toBeRemoved) {
		int selectedShapesSizeBefore = selectedShapes.size();
		if(shapes.remove(toBeRemoved) == false) {
			System.out.println("Shape does not exist in list of shapes!");
		}
		selectedShapes.remove(toBeRemoved);
		propertyChangeSupport.firePropertyChange("Deleted Shapes", selectedShapesSizeBefore, selectedShapes.size());
	}
	
	public void addSelectedShape(Shape toBeAdded) {
		int selectedShapesSizeBefore = selectedShapes.size();
		selectedShapes.add(toBeAdded);
	/*	for(int i=0; i < getShapes().size(); i++) {
			System.out.println(getShapes().get(i).isSelected());
		}*/
		
		propertyChangeSupport.firePropertyChange("Selected Shapes", selectedShapesSizeBefore, selectedShapes.size());
	}
	
	public void removeSelectedShape(Shape toBeRemoved) {
		int index = shapes.indexOf(toBeRemoved);
		shapes.get(index).setSelected(false);
		selectedShapes.remove(toBeRemoved);
	}
	
	public void pushToUndoStack(Command toBePushed) {
		undoCounter++;
		int undoStackSizeBefore = undoStack.size();
		this.undoStack.push(toBePushed);
		propertyChangeSupport.firePropertyChange("Undo Stack", undoStackSizeBefore, undoStack.size());
	}
	
	public void removeFromUndoStack() {
		undoCounter--;
		int undoStackSizeBefore = undoStack.size();
		if(undoStack.peek() != null) {
			this.undoStack.pop().unexecute();
		}
		propertyChangeSupport.firePropertyChange("Undo Stack Remove", undoStackSizeBefore, undoStack.size());
	}
	
	public void pushToRedoStack(Command toBePushed) {
		int redoStackSizeBefore = redoStack.size();
		this.redoStack.push(toBePushed);
		propertyChangeSupport.firePropertyChange("Redo Stack", redoStackSizeBefore, redoStack.size());
	}
	
	public void removeFromRedoStack() {
		int redoStackSizeBefore = redoStack.size();
		if(redoStack.peek() != null) {
			this.redoStack.pop().execute();
		}
		propertyChangeSupport.firePropertyChange("Redo Stack Remove", redoStackSizeBefore, redoStack.size());
		
	}
	

	public int getIndexOfShape(Shape s) {
		int listSize = shapes.size() - 1;
		for(int i=0; i<=listSize; i++) {
			if(shapes.get(i).equals(s)) {
				return i;
			}
		}
		return -1;
	}
	
}
