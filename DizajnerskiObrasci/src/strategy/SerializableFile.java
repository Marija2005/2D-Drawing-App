package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import mvc.ApplicationModel;
import shapes.Shape;

public class SerializableFile implements SaveOpen {
	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;
	private ApplicationModel model;
	
	public SerializableFile(ApplicationModel model) {
		this.model = model;
	}
	
	@Override
	public void save(File file) {
		// TODO Auto-generated method stub
		try {
			fileOutputStream = new FileOutputStream(file + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(model.getAllShapes());
			out.close();
			fileOutputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void open(File file) {
		// TODO Auto-generated method stub
		try {
			fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			model.addMultipleShapes((ArrayList<Shape>) objectInputStream.readObject());
			objectInputStream.close();
			fileInputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

}
