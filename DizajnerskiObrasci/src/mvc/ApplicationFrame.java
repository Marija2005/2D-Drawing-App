package mvc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.GridLayout;

public class ApplicationFrame extends JFrame {
	private ApplicationView ApplicationView = new ApplicationView(); //ovo je okej, jer je frame top level,a view(JPanel) jer je deo
	private ApplicationController ApplicationController;
	private int mainState = 0;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JMenuBar menuBar;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut;
	private JToggleButton tglbtnHexagon;
	private JButton btnToFront;
	private JButton btnBringToFront;
	private JButton btnToBack;
	private JButton btnBringToBack;
	private JButton btnUndo;
	private JButton btnRedo;

	private JToggleButton tglbtnSelect;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton innerColor;
	private JButton outlineColor;
	private JPanel panel;
	private JButton btnOpen;
	private JButton btnSave;
	private JList<String> activity;
	private DefaultListModel <String> loggList;
	private JButton btnNew;
	

	public JList<String> getActivity() {
		return activity;
	}

	public void setActivity(JList<String> activity) {
		this.activity = activity;
	}

	public DefaultListModel<String> getLoggList() {
		return loggList;
	}

	public void setLoggList(DefaultListModel<String> loggList) {
		this.loggList = loggList;
	}

	public JButton getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(JButton innerColor) {
		this.innerColor = innerColor;
	}

	public JButton getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(JButton outlineColor) {
		this.outlineColor = outlineColor;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public ApplicationView getApplicationView() {
		return ApplicationView;
	}

	public void setApplicationView(ApplicationView applicationView) {
		ApplicationView = applicationView;
	}

	public ApplicationController getApplicationController() {
		return ApplicationController;
	}

	public void setApplicationController(ApplicationController applicationController) {
		ApplicationController = applicationController;
	}

	public int getMainState() {
		return mainState;
	}

	public void setMainState(int mainState) {
		this.mainState = mainState;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}


	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	

	public ApplicationFrame() {
		setExtendedState(MAXIMIZED_BOTH);
		loggList = new DefaultListModel<String>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(900,950));
		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(Color.DARK_GRAY);
		pnlWest.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlWest.setForeground(Color.WHITE);
		getContentPane().add(pnlWest, BorderLayout.WEST);
		GridBagLayout gbl_pnlWest = new GridBagLayout();
		gbl_pnlWest.columnWidths = new int[]{89, 0};
		gbl_pnlWest.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlWest.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlWest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlWest.setLayout(gbl_pnlWest);
		
		tglbtnPoint = new JToggleButton("Point");
		buttonGroup.add(tglbtnPoint);
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnPoint.gridx = 0;
		gbc_tglbtnPoint.gridy = 0;
		pnlWest.add(tglbtnPoint, gbc_tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		buttonGroup.add(tglbtnLine);
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnLine.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnLine.gridx = 0;
		gbc_tglbtnLine.gridy = 1;
		pnlWest.add(tglbtnLine, gbc_tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		buttonGroup.add(tglbtnRectangle);
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnRectangle.gridx = 0;
		gbc_tglbtnRectangle.gridy = 2;
		pnlWest.add(tglbtnRectangle, gbc_tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		buttonGroup.add(tglbtnCircle);
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnCircle.gridx = 0;
		gbc_tglbtnCircle.gridy = 3;
		pnlWest.add(tglbtnCircle, gbc_tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		buttonGroup.add(tglbtnDonut);
		GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
		gbc_tglbtnDonut.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnDonut.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnDonut.gridx = 0;
		gbc_tglbtnDonut.gridy = 4;
		pnlWest.add(tglbtnDonut, gbc_tglbtnDonut);
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		buttonGroup.add(tglbtnHexagon);
		GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
		gbc_tglbtnHexagon.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnHexagon.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnHexagon.gridx = 0;
		gbc_tglbtnHexagon.gridy = 5;
		pnlWest.add(tglbtnHexagon, gbc_tglbtnHexagon);
		
		tglbtnSelect = new JToggleButton("Select");
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
		gbc_tglbtnSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSelect.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnSelect.gridx = 0;
		gbc_tglbtnSelect.gridy = 6;
		pnlWest.add(tglbtnSelect, gbc_tglbtnSelect);
		tglbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationView.repaint();
			}
		});
		buttonGroup.add(tglbtnSelect);
		
		btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ApplicationController.editShape();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 0;
		gbc_btnEdit.gridy = 7;
		pnlWest.add(btnEdit, gbc_btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.delete();
				ApplicationView.repaint();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 8;
		pnlWest.add(btnDelete, gbc_btnDelete);
		
		
		btnToFront = new JButton("To Front");
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.toFront();
			}
		});
		GridBagConstraints gbc_btnToFront = new GridBagConstraints();
		gbc_btnToFront.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnToFront.insets = new Insets(0, 0, 5, 0);
		gbc_btnToFront.gridx = 0;
		gbc_btnToFront.gridy = 10;
		pnlWest.add(btnToFront, gbc_btnToFront);
		
		btnBringToFront = new JButton("Bring To Front");
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.bringToFront();
			}
		});
		GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
		gbc_btnBringToFront.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBringToFront.insets = new Insets(0, 0, 5, 0);
		gbc_btnBringToFront.gridx = 0;
		gbc_btnBringToFront.gridy = 11;
		pnlWest.add(btnBringToFront, gbc_btnBringToFront);
		
		btnToBack = new JButton("To Back");
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.toBack();
			}
		});
		GridBagConstraints gbc_btnToBack = new GridBagConstraints();
		gbc_btnToBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnToBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnToBack.gridx = 0;
		gbc_btnToBack.gridy = 12;
		pnlWest.add(btnToBack, gbc_btnToBack);
		
		btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.bringToBack();
			}
		});
		GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
		gbc_btnBringToBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBringToBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBringToBack.gridx = 0;
		gbc_btnBringToBack.gridy = 13;
		pnlWest.add(btnBringToBack, gbc_btnBringToBack);
		
		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.undo();
			}
		});
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUndo.insets = new Insets(0, 0, 5, 0);
		gbc_btnUndo.gridx = 0;
		gbc_btnUndo.gridy = 14;
		pnlWest.add(btnUndo, gbc_btnUndo);
		
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnRedo.isEnabled())
				{
					ApplicationController.redo();
				}
			}
		});
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRedo.gridx = 0;
		gbc_btnRedo.gridy = 15;
		pnlWest.add(btnRedo, gbc_btnRedo);
		
		
		JPanel colorsParentPanel = new JPanel();
		colorsParentPanel.setBackground(Color.GRAY);
		getContentPane().add(colorsParentPanel,BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnOutlineColor = new JButton("Outline Color");
		btnOutlineColor.setBackground(Color.BLACK);
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outline = JColorChooser.showDialog(null, "Izaberite boju ivice", Color.BLACK);
				if(outline != null) {
					ApplicationController.setOutlineColor(outline);
					btnOutlineColor.setBackground(outline);
				}
			}
		});
		
		
		JButton btnInnerColor = new JButton("Inner Color");
		btnInnerColor.setBackground(Color.WHITE);
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color inner = JColorChooser.showDialog(null, "Izaberite boju unutrasnjosti", Color.WHITE);
				if(inner != null) {
					ApplicationController.setInnerColor(inner);
					btnInnerColor.setBackground(inner);
				}
			}
		}); 
		
		
		
		GroupLayout gl_colorsParentPanel = new GroupLayout(colorsParentPanel);
		gl_colorsParentPanel.setHorizontalGroup(
			gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorsParentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_colorsParentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnOutlineColor, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1245, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_colorsParentPanel.setVerticalGroup(
			gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorsParentPanel.createSequentialGroup()
					.addGroup(gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_colorsParentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_colorsParentPanel.createSequentialGroup()
							.addGap(23)
							.addComponent(btnInnerColor)
							.addGap(26)
							.addComponent(btnOutlineColor)))
					.addGap(13))
		);
		
		activity = new JList<String>();
		activity.setValueIsAdjusting(true);
		activity.setModel(loggList);
		scrollPane.setViewportView(activity);
		colorsParentPanel.setLayout(gl_colorsParentPanel);		
		
		
		
		ApplicationView.setBackground(Color.WHITE);
		ApplicationView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ApplicationController.click(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(ApplicationView, BorderLayout.CENTER);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setMargin(new Insets(0, 0, 80, 0));
		setJMenuBar(menuBar);
		
		panel = new JPanel();
		menuBar.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.openFile();
			}
		});
		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpen.insets = new Insets(5, 3, 5, 3);
		gbc_btnOpen.gridx = 1;
		gbc_btnOpen.gridy = 0;
		panel.add(btnOpen, gbc_btnOpen);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.saveFile();
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(5, 0, 5, 0);
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 0;
		panel.add(btnSave, gbc_btnSave);
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.newDraw();
			}
		});
		GridBagConstraints gbc_btnNew = new GridBagConstraints();
		gbc_btnNew.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNew.insets = new Insets(5, 3, 5, 0);
		gbc_btnNew.gridx = 0;
		gbc_btnNew.gridy = 0;
		panel.add(btnNew, gbc_btnNew);
		
		
		
		
	}
}