package mvc;

import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapters.HexagonAdapter;
import commands.CmdAddShape;
import commands.CmdBringToBack;
import commands.CmdBringToFront;
import commands.CmdDeselect;
import commands.CmdRemoveShape;
import commands.CmdSelect;
import commands.CmdToBack;
import commands.CmdToFront;
import commands.CmdUpdateCircle;
import commands.CmdUpdateDonut;
import commands.CmdUpdateHexagon;
import commands.CmdUpdateLine;
import commands.CmdUpdatePoint;
import commands.CmdUpdateRectangle;
import dialogue.DialogCircle;
import dialogue.DialogDonut;
import dialogue.DialogHexagon;
import dialogue.DialogLine;
import dialogue.DialogPoint;
import dialogue.DialogRectangle;
import hexagon.Hexagon;

import java.awt.*;

import shapes.Circle;
import shapes.Command;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import strategy.ManagerFile;
import strategy.SaveLog;
import strategy.SaveSerialized;
import strategy.SerializableFile;

public class ApplicationController implements PropertyChangeListener {
	private ApplicationModel model;
	private ApplicationFrame frame;

	private Color outlineColor = Color.BLACK;
	private Color innerColor = Color.WHITE;

	private ManagerFile manager;
	private DefaultListModel<String> activityLog;

	public ApplicationController(ApplicationModel model, ApplicationFrame frame) {
		this.model = model;
		this.frame = frame;
		this.activityLog = frame.getLoggList();
	}

	public void click(MouseEvent e) throws Exception {
		state(e);
		if (checkShapes(e) == false) {
			deselectAll();
		
			frame.getApplicationView().repaint();
		}
		
		frame.getApplicationView().repaint();
	}

	private boolean checkShapes(MouseEvent e) {
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).contains(e.getX(), e.getY())) {
				return true;
			}
		}
		return false;
	}

	public void deselectAll() {
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {
				Shape shape = model.getShapes().get(i);
				CmdDeselect cmd = new CmdDeselect(model, shape);
				cmd.execute();
				model.getUndoStack().push(cmd);
				activityLog.addElement("Deselected->" + shape.toString());
			}
			
		}
		model.getSelectedShapes().clear();
		frame.getBtnEdit().setEnabled(false);
		frame.getBtnDelete().setEnabled(false);
	}

	public void state(MouseEvent e) throws Exception {
		
		if (frame.getTglbtnSelect().isSelected()) {
			selectShape(e);
			
		} else {
			if (frame.getTglbtnPoint().isSelected()) {
				//pravi se novi,inace se samo menjaju vrednosti nad referencom na istu tacku
				Point point = new Point(e.getX(), e.getY());
				point.setOutlineColor(getOutlineColor());
				CmdAddShape cmd = new CmdAddShape(model, point); 
				cmd.execute(); 
				model.pushToUndoStack(cmd);
				activityLog.addElement("Added->" + point.toString());
				model.getRedoStack().removeAllElements();
				frame.getBtnRedo().setEnabled(false);
				frame.getApplicationView().repaint();
			}
			if (frame.getTglbtnLine().isSelected()) {

				if (model.getStartPoint() == null)
					model.setStartPoint(new Point(e.getX(), e.getY()));
				else {
					Line line = new Line(model.getStartPoint(), new Point(e.getX(), e.getY()));
					line.setOutlineColor(getOutlineColor());
					CmdAddShape cmd = new CmdAddShape(model, line);
					cmd.execute();
					model.pushToUndoStack(cmd);
					activityLog.addElement("Added->" + line.toString());
					model.setStartPoint(null); // postavlja se na null,jer nam za sledecu liniju treba nova tacka
					model.getRedoStack().removeAllElements();
					frame.getBtnRedo().setEnabled(false);
					frame.getApplicationView().repaint();
				}
			}

			if (frame.getTglbtnRectangle().isSelected()) {

				DialogRectangle dlgRectangle = new DialogRectangle();
				dlgRectangle.getTxtXCoord().setText(Integer.toString(e.getX()));
				dlgRectangle.getTxtYCoord().setText(Integer.toString(e.getY()));
				dlgRectangle.getTxtXCoord().setEnabled(false);
				dlgRectangle.getTxtYCoord().setEnabled(false);
				dlgRectangle.setOutlineColor(outlineColor);
				dlgRectangle.setInnerColor(innerColor);
				dlgRectangle.setVisible(true);
				try {
					if (dlgRectangle.isConfirm()) {
						Rectangle rectangle = new Rectangle(new Point(e.getX(), e.getY()),
								Integer.parseInt(dlgRectangle.getTxtWidth().getText()),
								Integer.parseInt(dlgRectangle.getTxtHeight().getText()));
						rectangle.setOutlineColor(dlgRectangle.getOutlineColor());
						rectangle.setInnerColor(dlgRectangle.getInnerColor());
						CmdAddShape cmd = new CmdAddShape(model, rectangle);
						cmd.execute();
						model.pushToUndoStack(cmd);
						activityLog.addElement("Added->" + rectangle.toString());
						model.getRedoStack().removeAllElements();
						frame.getBtnRedo().setEnabled(false);
						frame.getApplicationView().repaint();
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Fields must bre filled with numeric values!", "Error",
							JOptionPane.WARNING_MESSAGE);
				}

			}
			if (frame.getTglbtnCircle().isSelected()) {

				DialogCircle dlgCircle = new DialogCircle();
				dlgCircle.getTxtCoordX().setText(Integer.toString(e.getX()));
				dlgCircle.getTxtCoordY().setText(Integer.toString(e.getY()));
				dlgCircle.getTxtCoordX().setEnabled(false);
				dlgCircle.getTxtCoordY().setEnabled(false);
				dlgCircle.setOutlineColor(outlineColor);
				dlgCircle.setInnerColor(innerColor);
				dlgCircle.setVisible(true);
				try {
					if (dlgCircle.isConfirm()) {
						int radius = Integer.parseInt(dlgCircle.getTxtRadius().getText());
						Circle circle = new Circle(new Point(e.getX(), e.getY()), radius);
						circle.setOutlineColor(dlgCircle.getOutlineColor());
						circle.setInnerColor(dlgCircle.getInnerColor());
						CmdAddShape cmd = new CmdAddShape(model, circle);
						cmd.execute();
						model.pushToUndoStack(cmd);
						activityLog.addElement("Added->" + circle.toString());
						model.getRedoStack().removeAllElements();
						frame.getBtnRedo().setEnabled(false);
						frame.getApplicationView().repaint();
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Fields must bre filled with numeric values!", "Error",
							JOptionPane.WARNING_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "Radius must be greater than 0!", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		if (frame.getTglbtnDonut().isSelected()) {

			DialogDonut dlgDonut = new DialogDonut();
			dlgDonut.getTxtCoordX().setText(Integer.toString(e.getX()));
			dlgDonut.getTxtCoordY().setText(Integer.toString(e.getY()));
			dlgDonut.getTxtCoordX().setEnabled(false);
			dlgDonut.getTxtCoordY().setEnabled(false);
			dlgDonut.setOutlineColor(outlineColor);
			dlgDonut.setInnerColor(innerColor);
			dlgDonut.setVisible(true);
			try {
				if (dlgDonut.isOk()) {
						Donut donut = new Donut(new Point(e.getX(), e.getY()),
								Integer.parseInt(dlgDonut.getTxtOuter().getText()),
								Integer.parseInt(dlgDonut.getTxtInner().getText()), dlgDonut.getOutlineColor(),
								dlgDonut.getInnerColor());
						CmdAddShape cmd = new CmdAddShape(model, donut);
						cmd.execute();
						model.pushToUndoStack(cmd);
						activityLog.addElement("Added->" + donut.toString());
						model.getRedoStack().removeAllElements();
						frame.getBtnRedo().setEnabled(false);
						frame.getApplicationView().repaint();
					}
				
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(new JFrame(), "Fields must be filled with numeric values!", "Error",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Outer and inner radius must be greater than zero and inner radius must be smaller than outer!", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		if (frame.getTglbtnHexagon().isSelected()) {

			DialogHexagon dlgHex = new DialogHexagon();
			dlgHex.getTxtCoordX().setText(Integer.toString(e.getX()));
			dlgHex.getTxtCoordY().setText(Integer.toString(e.getY()));
			dlgHex.getTxtCoordX().setEnabled(false);
			dlgHex.getTxtCoordY().setEnabled(false);
			dlgHex.setOutlineColor(getOutlineColor());
			dlgHex.setInnerColor(getInnerColor());
			dlgHex.setVisible(true);
			if (dlgHex.isConfirm()) {
				Hexagon hex = new Hexagon(e.getX(), e.getY(), Integer.parseInt(dlgHex.getTxtRadius().getText()));
				hex.setBorderColor(dlgHex.getOutlineColor());
				hex.setAreaColor(dlgHex.getInnerColor());
				HexagonAdapter adapter = new HexagonAdapter(hex);
				CmdAddShape cmd = new CmdAddShape(model, adapter);
				cmd.execute();
				model.pushToUndoStack(cmd);
				activityLog.addElement("Added->" + adapter.toString());
				model.getRedoStack().removeAllElements();
				frame.getBtnRedo().setEnabled(false);
				frame.getApplicationView().repaint();

			}
		}
		if (frame.getBtnEdit().isSelected()) {
			editShape();
		}
		if (frame.getBtnDelete().isSelected()) {
			delete();
		}
	}
	
/*	public void deselectFromLog(Shape shape) {
		int index = model.getShapes().indexOf(shape);
		Shape deselected = model.getShapes().get(index);
		CmdDeselect cmd = new CmdDeselect(model, deselected);
		cmd.execute();
		model.getUndoStack().push(cmd);
		activityLog.addElement("Deselected->" + deselected.toString());
		frame.getApplicationView().repaint();
	}*/
 
	public void selectShape(MouseEvent e) {
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).contains(e.getX(), e.getY())) {
				if (model.getShapes().get(i).isSelected()) {
					return;
				}
				Shape shape = model.getShapes().get(i);
				CmdSelect CmdSelect = new CmdSelect(model, shape);
				CmdSelect.execute();
				activityLog.addElement("Selected->" + shape.toString());
				model.getUndoStack().push(CmdSelect);
			}
		}
	}
	
	public void logSelectShape(Shape shape) {
		int index = model.getShapes().indexOf(shape);
		Shape selected = model.getShapes().get(index);
		CmdSelect cmd = new CmdSelect(model, selected);
		cmd.execute();
		model.getUndoStack().push(cmd);
		frame.getApplicationView().repaint();
	}

	public void editShape() throws Exception {
		if (model.getSelectedShapes().get(0) instanceof Point) {

			DialogPoint dlgPoint = new DialogPoint();
			Point oldPoint = (Point) model.getSelectedShapes().get(0);
			dlgPoint.getTxtX().setText(Integer.toString(oldPoint.getX()));
			dlgPoint.getTxtY().setText(Integer.toString(oldPoint.getY()));
			dlgPoint.setColor(getOutlineColor());
			dlgPoint.setVisible(true);
			if (dlgPoint.isOk()) {
				Point newPoint = new Point(Integer.parseInt(dlgPoint.getTxtX().getText()),
						Integer.parseInt(dlgPoint.getTxtY().getText()), dlgPoint.getColor());
				newPoint.setSelected(oldPoint.isSelected());
				activityLog.addElement("Updated->" + oldPoint.toString() + "->" + newPoint.toString());
				CmdUpdatePoint cmd = new CmdUpdatePoint(oldPoint, newPoint);
				cmd.execute();
				model.pushToUndoStack(cmd);
				frame.repaint();
			}
		} else if (model.getSelectedShapes().get(0) instanceof Line) {

			DialogLine dlgLine = new DialogLine();
			Line oldLine = (Line) model.getSelectedShapes().get(0);
			dlgLine.getTxtStartPointX().setText((Integer.toString(oldLine.getStartPoint().getX())));
			dlgLine.getTxtStartPointY().setText((Integer.toString(oldLine.getStartPoint().getY())));
			dlgLine.getTxtEndPointX().setText(Integer.toString(oldLine.getEndPoint().getX()));
			dlgLine.getTxtEndPointY().setText(Integer.toString(oldLine.getEndPoint().getY()));
			dlgLine.setColor(getOutlineColor());
			dlgLine.setVisible(true);
			if (dlgLine.isOk()) {
				Line newLine = new Line(
						new Point(Integer.parseInt(dlgLine.getTxtStartPointX().getText()),
								Integer.parseInt(dlgLine.getTxtStartPointY().getText())),
						new Point(Integer.parseInt(dlgLine.getTxtEndPointX().getText()),
								Integer.parseInt(dlgLine.getTxtEndPointY().getText())),
						dlgLine.getColor());
				newLine.setSelected(oldLine.isSelected());
				activityLog.addElement("Updated->" + oldLine.toString() + "->" + newLine.toString());
				CmdUpdateLine cmd = new CmdUpdateLine(oldLine, newLine);
				cmd.execute();
				model.pushToUndoStack(cmd);
				frame.repaint();
			}
		} else if (model.getSelectedShapes().get(0) instanceof Rectangle) {

			DialogRectangle dlgRectangle = new DialogRectangle();
			Rectangle oldRectangle = (Rectangle) model.getSelectedShapes().get(0);
			dlgRectangle.getTxtXCoord().setText(Integer.toString(oldRectangle.getUpperLeftPoint().getX()));
			dlgRectangle.getTxtYCoord().setText(Integer.toString(oldRectangle.getUpperLeftPoint().getY()));
			dlgRectangle.getTxtWidth().setText(Integer.toString(oldRectangle.getWidth()));
			dlgRectangle.getTxtHeight().setText(Integer.toString(oldRectangle.getHeight()));
			dlgRectangle.setOutlineColor(getOutlineColor());
			dlgRectangle.setInnerColor(getInnerColor());
			dlgRectangle.setVisible(true);
			try {
				if (dlgRectangle.isConfirm()) {
					Rectangle newRectangle = new Rectangle(
							new Point(Integer.parseInt(dlgRectangle.getTxtXCoord().getText()),
									Integer.parseInt(dlgRectangle.getTxtYCoord().getText())),
							Integer.parseInt(dlgRectangle.getTxtWidth().getText()),
							Integer.parseInt(dlgRectangle.getTxtHeight().getText()), dlgRectangle.getOutlineColor(),
							dlgRectangle.getInnerColor());
					newRectangle.setSelected(oldRectangle.isSelected());
					activityLog.addElement("Updated->" + oldRectangle.toString() + "->" + newRectangle.toString());
					CmdUpdateRectangle cmd = new CmdUpdateRectangle(oldRectangle, newRectangle);
					cmd.execute();
					model.pushToUndoStack(cmd);
					model.getRedoStack().removeAllElements();
					frame.repaint();

				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), "Height and width must bre positive", "Error",
						JOptionPane.WARNING_MESSAGE);
			}

		} else if (model.getSelectedShapes().get(0) instanceof Donut) {

			DialogDonut dlgDonut = new DialogDonut();
			Donut oldDonut = (Donut) model.getSelectedShapes().get(0);
			dlgDonut.getTxtCoordX().setText(Integer.toString(oldDonut.getCenter().getX()));
			dlgDonut.getTxtCoordY().setText(Integer.toString(oldDonut.getCenter().getY()));
			dlgDonut.getTxtInner().setText(Integer.toString(oldDonut.getInnerRadius()));
			dlgDonut.getTxtOuter().setText(Integer.toString(oldDonut.getOuterRadius()));
			dlgDonut.setOutlineColor(getOutlineColor());
			dlgDonut.setInnerColor(getInnerColor());
			dlgDonut.setVisible(true);
			try {
				if (dlgDonut.isOk()) {

					Donut newDonut = new Donut(
							new Point(Integer.parseInt(dlgDonut.getTxtCoordX().getText()),
									Integer.parseInt(dlgDonut.getTxtCoordY().getText())),
							Integer.parseInt(dlgDonut.getTxtOuter().getText()),
							Integer.parseInt(dlgDonut.getTxtInner().getText()), dlgDonut.getOutlineColor(),
							dlgDonut.getInnerColor());
					newDonut.setSelected(oldDonut.isSelected());
					activityLog.addElement("Updated->" + oldDonut.toString() + "->" + newDonut.toString());
					CmdUpdateDonut cmd = new CmdUpdateDonut(oldDonut, newDonut);
					cmd.execute();
					model.pushToUndoStack(cmd);
					model.getRedoStack().removeAllElements();
					frame.repaint();
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(new JFrame(), "Inner radius must be smaller than outer radius", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (model.getSelectedShapes().get(0) instanceof Circle) {
			DialogCircle dlgCircle = new DialogCircle();
			Circle oldCircle = (Circle) model.getSelectedShapes().get(0);
			dlgCircle.getTxtCoordX().setText(Integer.toString(oldCircle.getCenter().getX()));
			dlgCircle.getTxtCoordY().setText(Integer.toString(oldCircle.getCenter().getY()));
			dlgCircle.getTxtRadius().setText(Integer.toString(oldCircle.getRadius()));
			dlgCircle.setOutlineColor(getOutlineColor());
			dlgCircle.setInnerColor(getInnerColor());
			dlgCircle.setVisible(true);
			try {
				if (dlgCircle.isConfirm()) {
					Circle newCircle = new Circle(
							new Point(Integer.parseInt(dlgCircle.getTxtCoordX().getText()),
									Integer.parseInt(dlgCircle.getTxtCoordY().getText())),
							Integer.parseInt(dlgCircle.getTxtRadius().getText()), dlgCircle.getOutlineColor(),
							dlgCircle.getInnerColor());
					newCircle.setSelected(oldCircle.isSelected());
					activityLog.addElement("Updated->" + oldCircle.toString() + "->" + newCircle.toString());
					CmdUpdateCircle cmd = new CmdUpdateCircle(oldCircle, newCircle);
					cmd.execute();
					model.pushToUndoStack(cmd);
					model.getRedoStack().removeAllElements();
					frame.repaint();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), "Radius must be positive!", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
			

		} else if (model.getSelectedShapes().get(0) instanceof HexagonAdapter) {

			DialogHexagon dlgHexagon = new DialogHexagon();
			HexagonAdapter oldHexagon = (HexagonAdapter) model.getSelectedShapes().get(0);
			dlgHexagon.getTxtCoordX().setText(Integer.toString(oldHexagon.getHexagon().getX()));
			dlgHexagon.getTxtCoordY().setText(Integer.toString(oldHexagon.getHexagon().getY()));
			dlgHexagon.getTxtRadius().setText(Integer.toString(oldHexagon.getHexagon().getR()));
			dlgHexagon.setOutlineColor(getOutlineColor());
			dlgHexagon.setInnerColor(getInnerColor());
			dlgHexagon.setVisible(true);
			if (dlgHexagon.isConfirm()) {
				HexagonAdapter hex = new HexagonAdapter(
						new Point(Integer.parseInt(dlgHexagon.getTxtCoordX().getText()),
								Integer.parseInt(dlgHexagon.getTxtCoordY().getText())),
						Integer.parseInt(dlgHexagon.getTxtRadius().getText()), dlgHexagon.getOutlineColor(),
						dlgHexagon.getInnerColor(), true);
				CmdUpdateHexagon cmd = new CmdUpdateHexagon(oldHexagon, hex);
				activityLog.addElement("Updated->" + oldHexagon.toString() + "->" + hex.toString());
				cmd.execute();
				model.pushToUndoStack(cmd);
				model.getRedoStack().removeAllElements();
				frame.getApplicationView().repaint();
			}
		}
	}

	public void undo() {
		if (model.getUndoStack().size() > 0) {
			Command cmd = model.getUndoStack().peek();
			model.pushToRedoStack(cmd);
			activityLog.addElement("Undo->" + model.getUndoStack().peek().toString());
			model.removeFromUndoStack();
			frame.getApplicationView().repaint();
		}
	}

	public void redo() {
		if (model.getRedoStack().size() > 0) {
			model.pushToUndoStack(model.getRedoStack().peek());
			activityLog.addElement("Redo->" + model.getRedoStack().peek().toString());
			model.removeFromRedoStack();
			frame.getApplicationView().repaint();
		}
	}

	public void delete() {
		if (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure to delete these shapes?", "Sure?",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			while (model.getSelectedShapes().size() > 0) {
				for (int i = 0; i < model.getSelectedShapes().size(); i++) {
					Shape shape = model.getSelectedShapes().get(i); 
					CmdRemoveShape cmd = new CmdRemoveShape(model, shape);
					cmd.execute();
					activityLog.addElement("Deleted->" + shape.toString());
					model.getUndoStack().push(cmd);
				}
			}
		}
	}
	
	public void logDelete() {
		while(model.getSelectedShapes().size() > 0) {
			for(int i= 0; i<model.getSelectedShapes().size(); i++) {
				Shape shape = model.getSelectedShapes().get(i);
				CmdRemoveShape cmd = new CmdRemoveShape(model, shape);
				cmd.execute();
				activityLog.addElement("Deleted->" + shape.toString());
				model.getUndoStack().push(cmd);
			}
			frame.getApplicationView().repaint();
		}
	}

	public void bringToFront() {
		if (model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			CmdBringToFront cmd = new CmdBringToFront(model, shape);
			model.pushToUndoStack(cmd);
			activityLog.addElement("Brought to front->" + shape.toString());
			cmd.execute();
		}
		frame.repaint();
	}

	public void bringToBack() {
		if (model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			CmdBringToBack cmd = new CmdBringToBack(model, index, shape);
			model.pushToUndoStack(cmd);
			activityLog.addElement("Brought to back->" + shape.toString());
			cmd.execute();
		}
		frame.repaint();
	}

	public void toFront() {
		if (model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			CmdToFront cmd = new CmdToFront(model, index, shape);
			model.pushToUndoStack(cmd);
			activityLog.addElement("Moved to front->" + shape.toString());
			cmd.execute();
		}
		frame.repaint();
	}

	public void toBack() {
		if (model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			CmdToBack cmd = new CmdToBack(model, index, shape);
			model.pushToUndoStack(cmd);
			activityLog.addElement("Moved to back->" + shape.toString());
			cmd.execute();
		}
		frame.repaint();
	}

	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	private PropertyChangeEvent pce;

	@Override
	public void propertyChange(PropertyChangeEvent evt) { // pcl dobija ovaj event
		// TODO Auto-generated method stub
		this.pce = evt;

		if ((int) evt.getNewValue() == 1 && evt.getPropertyName() == "Selected Shapes" || model.getSelectedShapes().size() == 1) {
			frame.getBtnEdit().setEnabled(true);
		} else {
			frame.getBtnEdit().setEnabled(false);
		}

		if ((int) evt.getNewValue() == 1 && evt.getPropertyName() == "Selected Shapes" || model.getSelectedShapes().size() > 0) {
			frame.getBtnDelete().setEnabled(true);
		} else {
			frame.getBtnDelete().setEnabled(false);
		}

		if ((int) evt.getNewValue() == 0 && evt.getPropertyName() == "Deleted Shapes") {
			frame.getBtnEdit().setEnabled(false);
			frame.getBtnDelete().setEnabled(false);
		}

		if ((int) evt.getNewValue() > 0 && evt.getPropertyName() == "Undo Stack") {
			frame.getBtnUndo().setEnabled(true);
		} else if ((int) evt.getNewValue() == 0 && evt.getPropertyName() == "Undo Stack Remove") {
			frame.getBtnUndo().setEnabled(false);
		}

		if ((int) evt.getNewValue() > 0 && evt.getPropertyName() == "Redo Stack" ) {
			frame.getBtnRedo().setEnabled(true);
		} else if ((int) evt.getNewValue() == 0 && evt.getPropertyName() == "Redo Stack Remove") {
			frame.getBtnRedo().setEnabled(false);
		}
	}

	public void saveFile() {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jFileChooser.enableInputMethods(false);
		jFileChooser.setMultiSelectionEnabled(false);
		jFileChooser.setFileHidingEnabled(false);
		jFileChooser.setEnabled(true);
		jFileChooser.setDialogTitle("Save");
		jFileChooser.setAcceptAllFileFilterUsed(false);

		if (!model.getShapes().isEmpty()) {
			jFileChooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));

		}
		if (!model.getUndoStack().isEmpty() || model.getShapes().isEmpty())
			jFileChooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (jFileChooser.getFileFilter().getDescription().equals("Serialized draw"))
				manager = new ManagerFile(new SerializableFile(model));
			else if (jFileChooser.getFileFilter().getDescription().equals("Commands log"))
				manager = new ManagerFile(new SaveLog(frame, model, this));
			else
				manager = new ManagerFile(new SaveSerialized(frame));
			manager.save(jFileChooser.getSelectedFile());
		}
		jFileChooser.setVisible(false);
	}

	public void openFile() {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.enableInputMethods(true);
		jFileChooser.setMultiSelectionEnabled(false);
		jFileChooser.setFileHidingEnabled(false);
		jFileChooser.setEnabled(true);
		jFileChooser.setAcceptAllFileFilterUsed(false);
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jFileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
		jFileChooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
		jFileChooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			
			frame.getLoggList().clear();
			model.getShapes().clear();
			model.getUndoStack().clear();
			model.getRedoStack().clear();
			frame.getApplicationView().repaint();
			if (jFileChooser.getFileFilter().getDescription().equals("Serialized draw")) {
				manager = new ManagerFile(new SerializableFile(model));
			} else if (jFileChooser.getFileFilter().getDescription().equals("Commands log"))
				manager = new ManagerFile(new SaveLog(frame, model, this));
			manager.open(jFileChooser.getSelectedFile());

		}
		jFileChooser.setVisible(false);
	}

	public void newDraw() {
		model.removeAll();
		activityLog.removeAllElements();
		model.getRedoStack().clear();
		model.getUndoStack().clear();
		frame.getApplicationView().repaint();
	}

	public void executeCommand(Command command) {
		command.execute();
		model.pushToUndoStack(command);
		frame.getApplicationView().repaint();
	}
}
