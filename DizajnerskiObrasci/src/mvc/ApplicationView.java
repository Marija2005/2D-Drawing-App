package mvc;

import java.util.ListIterator;
import java.awt.*;
import javax.swing.JPanel;

import shapes.Shape;

public class ApplicationView extends JPanel {
	
	private ApplicationModel AppModel = new ApplicationModel(); //kreiramo novi model da bi nam prikazao u window builder,zbog frame-a
	
	
	public ApplicationView() {
		
	}
	
	public ApplicationModel getAppModel() {
		return AppModel;
	}
	
	public void setAppModel(ApplicationModel appModel) {
		AppModel = appModel;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		ListIterator<Shape> it = AppModel.getShapes().listIterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}

}
