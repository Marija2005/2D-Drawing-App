package mvc;

import javax.swing.JFrame;

public class DrawingApp {

	public static void main(String[] args) {
		ApplicationModel model = new ApplicationModel();
		ApplicationFrame frame = new ApplicationFrame();
		frame.getApplicationView().setAppModel(model); //da ne koristi novi model,vec taj sto smo napravilii
		ApplicationController controller = new ApplicationController(model, frame); //imamo referencu u frame,ali nemamo objekat
		frame.setApplicationController(controller);//referenca jFrame na controler,imamo referencu u frame koja je null
		model.addPropertyChangeListener(controller); //na nas observarble prijavljuje se observer
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

} 
