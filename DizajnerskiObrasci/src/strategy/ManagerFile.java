package strategy;

import java.io.File;

public class ManagerFile implements SaveOpen {
	private SaveOpen saveOpen;
	
	public ManagerFile(SaveOpen saveOpen) {
		this.saveOpen = saveOpen;
	}
	@Override
	public void save(File file) {
		// TODO Auto-generated method stub
		saveOpen.save(file);
	}

	@Override
	public void open(File file) {
		// TODO Auto-generated method stub
		saveOpen.open(file);
	}

}
