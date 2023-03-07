package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

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
import dialogue.DialogParser;
import hexagon.Hexagon;
import mvc.ApplicationController;
import mvc.ApplicationFrame;
import mvc.ApplicationModel;
import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class SaveLog implements SaveOpen {
	private BufferedWriter writer;
	private BufferedReader reader;
	private ApplicationFrame frame;
	private ApplicationModel model;
	private ApplicationController controller;
	private DialogParser parser;

	public SaveLog(ApplicationFrame frame, ApplicationModel model, ApplicationController controller) {
		this.frame = frame;
		this.model = model;
		this.controller = controller;
	}

	@Override
	public void save(File file) {
		// TODO Auto-generated method stub
		try {
			writer = new BufferedWriter(new FileWriter(file + ".log"));
			DefaultListModel<String> list = frame.getLoggList();
			for (int i = 0; i < frame.getLoggList().size(); i++) {
				writer.write(list.getElementAt(i));
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void open(File file) {
		// TODO Auto-generated method stub
		try {
			reader = new BufferedReader(new FileReader(file));
			parser = new DialogParser();
			parser.setSaveLog(this);
			parser.addCommand(reader.readLine());
			parser.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void readLine(String command) {
		try {
			String[] commands = command.split("->");
			switch (commands[0]) {
			case "Undo":
				controller.undo();
				//System.out.println("undo" + model.getUndoStack());
				//System.out.println("undo" + model.getUndoStack().size()); 
				break;
			case "Redo":
				controller.redo();
				//System.out.println("redo" + model.getRedoStack().size());
				break;
			case "Added":
				Shape shape = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
				controller.executeCommand(new CmdAddShape(model, shape));
				frame.getLoggList().addElement("Added->" + shape.toString());
				
				break;
			case "Updated":
				Shape oldShape = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
				int index = model.getIndexOfShape(oldShape);
				if (oldShape instanceof Point) {
					Point newPoint = parsePoint(commands[2].split(":")[1]);
					controller.executeCommand(new CmdUpdatePoint((Point) model.getByIndex(index), newPoint));
					frame.getLoggList().addElement("Updated->" + oldShape.toString() + "->" + newPoint.toString());
				} else if (oldShape instanceof Line) {
					Line newLine = parseLine(commands[2].split(":")[1]);
					controller.executeCommand(new CmdUpdateLine((Line) model.getByIndex(index), newLine));
					frame.getLoggList().addElement("Updated->" + oldShape.toString() + "->" + newLine.toString());
				} else if (oldShape instanceof Rectangle) {
					Rectangle newRectangle = parseRectangle(commands[2].split(":")[1]);
					controller
							.executeCommand(new CmdUpdateRectangle((Rectangle) model.getByIndex(index), newRectangle));
					frame.getLoggList().addElement("Updated->" + oldShape.toString() + "->" + newRectangle.toString());
				} else if (oldShape instanceof Donut) {
					Donut newDonut = parseDonut(commands[2].split(":")[1]);
					controller.executeCommand(new CmdUpdateDonut((Donut) model.getByIndex(index), newDonut));
					frame.getLoggList().addElement("Updated->" + oldShape.toString() + "->" + newDonut.toString());
				} else if (oldShape instanceof Circle) {
					Circle newCircle = parseCircle(commands[2].split(":")[1]);
					controller.executeCommand(new CmdUpdateCircle((Circle) model.getByIndex(index), newCircle));
					frame.getLoggList().addElement("Updated->" + oldShape.toString() + "->" + newCircle.toString());
				} else if (oldShape instanceof HexagonAdapter) {
					HexagonAdapter newHexagon = parseHexagon(commands[2].split(":")[1]);
					controller
							.executeCommand(new CmdUpdateHexagon((HexagonAdapter) model.getByIndex(index), newHexagon));
					frame.getLoggList().addElement("Updated->" + oldShape.toString() + "->" + newHexagon.toString());
				}
				break;
			case "Deleted":
				controller.logDelete();
				break;
			case "Selected":
				Shape selectedShape = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
				controller.logSelectShape(selectedShape);
				frame.getLoggList().addElement("Selected->" + selectedShape.toString());
				break;
			case "Deselected":
				Shape deselectedShape = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
				controller.executeCommand(new CmdDeselect(model, deselectedShape));
				frame.getLoggList().addElement("Deselected->" + deselectedShape.toString());
				break;
			case "Moved to front":
				Shape movedToFront = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
				controller.executeCommand(new CmdToFront(model, model.getShapes().indexOf(movedToFront), movedToFront));
				frame.getLoggList().addElement("Moved to front->" + movedToFront.toString());
				break;
			case "Moved to back":
				Shape movedToBack = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
				controller.executeCommand(new CmdToBack(model, model.getShapes().indexOf(movedToBack), movedToBack));
				frame.getLoggList().addElement("Moved to back->" + movedToBack.toString());
				break;
			case "Bring to front":
				Shape bringToFront = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
				controller.executeCommand(new CmdBringToFront(model, bringToFront));
				frame.getLoggList().addElement("Bring to front->" + bringToFront.toString());
				break;
			case "Bring to back":
				Shape bringToBack = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
				controller
						.executeCommand(new CmdBringToBack(model, model.getShapes().indexOf(bringToBack), bringToBack));
				frame.getLoggList().addElement("Bring to back->" + bringToBack.toString());
				break;

			}
			String line = reader.readLine();
			if (line != null)
				parser.addCommand(line);
			else {
				parser.closeDialog();
				return;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private HexagonAdapter parseHexagon(String string) {
		// TODO Auto-generated method stub
		String[] hexagonParts = string.split(";");
		int x = Integer.parseInt(hexagonParts[0].split("=")[1]);
		int y = Integer.parseInt(hexagonParts[1].split("=")[1]);
		int radius = Integer.parseInt(hexagonParts[2].split("=")[1]);
		/*
		 * String s = hexagonParts[3].split("=")[1].substring(1,
		 * hexagonParts[3].split("=")[1].length() - 1); String [] outlineColors =
		 * s.split(","); String s1 = hexagonParts[4].split("=")[1].substring(1,
		 * hexagonParts[4].split("=")[1].length() - 1); String [] innerColors =
		 * s1.split(",");
		 */

		boolean select = Boolean.parseBoolean(hexagonParts[5].split("=")[1]);
		return new HexagonAdapter(new Point(x, y), radius, new Color(Integer.parseInt(hexagonParts[3].split("=")[1])),
				new Color(Integer.parseInt(hexagonParts[4].split("=")[1])), select);
	}

	private Donut parseDonut(String string) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		String[] donutParts = string.split(";");
		int radius = Integer.parseInt(donutParts[0].split("=")[1]);
		int x = Integer.parseInt(donutParts[1].split("=")[1]);
		int y = Integer.parseInt(donutParts[2].split("=")[1]);
		int innerRadius = Integer.parseInt(donutParts[3].split("=")[1]);
		String s = donutParts[4].split("=")[1].substring(1, donutParts[4].split("=")[1].length() - 1);
		String[] outlineColors = s.split(",");
		String s1 = donutParts[5].split("=")[1].substring(1, donutParts[5].split("=")[1].length() - 1);
		String[] innerColors = s1.split(",");
		return new Donut(new Point(x, y), radius, innerRadius,
				new Color(Integer.parseInt(outlineColors[0].split("-")[1]),
						Integer.parseInt(outlineColors[1].split("-")[1]),
						Integer.parseInt(outlineColors[2].split("-")[1])),
				new Color(Integer.parseInt(innerColors[0].split("-")[1]),
						Integer.parseInt(innerColors[1].split("-")[1]),
						Integer.parseInt(innerColors[2].split("-")[1])));

	}

	private Circle parseCircle(String string) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		String[] circleParts = string.split(";");
		int radius = Integer.parseInt(circleParts[0].split("=")[1]);
		int x = Integer.parseInt(circleParts[1].split("=")[1]);
		int y = Integer.parseInt(circleParts[2].split("=")[1]);
		String s = circleParts[3].split("=")[1].substring(1, circleParts[3].split("=")[1].length() - 1);
		String[] outlineColors = s.split(",");
		String s1 = circleParts[4].split("=")[1].substring(1, circleParts[4].split("=")[1].length() - 1);
		String[] innerColors = s1.split(",");
		return new Circle(new Point(x, y), radius, new Color(Integer.parseInt(outlineColors[0].split("-")[1]),
				Integer.parseInt(outlineColors[1].split("-")[1]), Integer.parseInt(outlineColors[2].split("-")[1])),
				new Color(Integer.parseInt(innerColors[0].split("-")[1]),
						Integer.parseInt(innerColors[1].split("-")[1]),
						Integer.parseInt(innerColors[2].split("-")[1])));
	}

	private Rectangle parseRectangle(String string) {
		// TODO Auto-generated method stub
		String[] rectangleParts = string.split(";");
		int x = Integer.parseInt(rectangleParts[0].split("=")[1]);
		int y = Integer.parseInt(rectangleParts[1].split("=")[1]);
		int height = Integer.parseInt(rectangleParts[3].split("=")[1]);
		int width = Integer.parseInt(rectangleParts[2].split("=")[1]);
		String s = rectangleParts[4].split("=")[1].substring(1, rectangleParts[4].split("=")[1].length() - 1);
		String[] outlineColors = s.split(",");
		String s1 = rectangleParts[5].split("=")[1].substring(1, rectangleParts[5].split("=")[1].length() - 1);
		String[] innerColors = s1.split(",");
		return new Rectangle(new Point(x, y), width, height, new Color(Integer.parseInt(outlineColors[0].split("-")[1]),
				Integer.parseInt(outlineColors[1].split("-")[1]), Integer.parseInt(outlineColors[2].split("-")[1])),
				new Color(Integer.parseInt(innerColors[0].split("-")[1]),
						Integer.parseInt(innerColors[1].split("-")[1]),
						Integer.parseInt(innerColors[2].split("-")[1])));
	}

	private Line parseLine(String string) {
		// TODO Auto-generated method stub
		String[] lineParts = string.split(";");
		int xStart = Integer.parseInt(lineParts[0].split("=")[1]);
		int yStart = Integer.parseInt(lineParts[1].split("=")[1]);
		int xEnd = Integer.parseInt(lineParts[2].split("=")[1]);
		int yEnd = Integer.parseInt(lineParts[3].split("=")[1]);
		String s = lineParts[4].split("=")[1].substring(1, lineParts[4].split("=")[1].length() - 1);
		String[] outlineColors = s.split(",");
		Point startPoint = new Point(xStart, yStart);
		Point endPoint = new Point(xEnd, yEnd);
		Color lineColor = new Color(Integer.parseInt(outlineColors[0].split("-")[1]),
				Integer.parseInt(outlineColors[1].split("-")[1]), Integer.parseInt(outlineColors[2].split("-")[1]));
		return new Line(startPoint, endPoint, lineColor);
	}

	private Point parsePoint(String string) {
		// TODO Auto-generated method stub
		String[] pointParts = string.split(";");
		String s = pointParts[2].split("=")[1].substring(1, pointParts[2].split("=")[1].length() - 1);
		String[] colors = s.split(",");
		return new Point(Integer.parseInt(pointParts[0].split("=")[1]), Integer.parseInt(pointParts[1].split("=")[1]),
				new Color(Integer.parseInt(colors[0].split("-")[1]), Integer.parseInt(colors[1].split("-")[1]),
						Integer.parseInt(colors[2].split("-")[1])));
	}

	private Shape parseShape(String shape, String shapePar) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		if (shape.equals("Point"))
			return parsePoint(shapePar);
		else if (shape.equals("Hexagon"))
			return parseHexagon(shapePar);
		else if (shape.equals("Line"))
			return parseLine(shapePar);
		else if (shape.equals("Rectangle"))
			return parseRectangle(shapePar);
		else if (shape.equals("Circle"))
			return parseCircle(shapePar);
		else
			return parseDonut(shapePar);
	}

}
