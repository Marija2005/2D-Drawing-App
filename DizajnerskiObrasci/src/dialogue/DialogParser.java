package dialogue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import strategy.SaveLog;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class DialogParser extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JList<String> list;
	private DefaultListModel<String> log;
	private SaveLog saveLog;
	
	public static void main(String[] args) {
		try {
			DialogParser dialog = new DialogParser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DialogParser() {
		setBounds(100, 100, 800, 500);
		setModal(true);
		setResizable(true);
		setLocationRelativeTo(null);
		setTitle("Log parser");
		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent click) {
				if(saveLog != null) {
					if(log.getElementAt(log.size() - 1) == null) {
						dispose();
					} else {
						saveLog.readLine(log.getElementAt(log.size() - 1));
					}
				}
			}
		});
		btnOk.setActionCommand("OK");
		buttonPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent click) {
				setVisible(false);
				dispose();
			}
		});
		btnCancel.setActionCommand("Cancel");
		buttonPane.add(btnCancel);
		
		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
		}
		
		
		
		list = new JList<String>();
		log = new DefaultListModel<>();
		list.setModel(log);
		list.setMinimumSize(getMinimumSize());
		list.setVisibleRowCount(30);
		list.setEnabled(false);
		scrollPane.setViewportView(list);
	}
	
	public void addCommand(String command) {
		log.addElement(command);
	}
	
	public void closeDialog() {
		dispose();
	}

	public SaveLog getSaveLog() {
		return saveLog;
	}

	public void setSaveLog(SaveLog saveLog) {
		this.saveLog = saveLog;
	}
	 

}
