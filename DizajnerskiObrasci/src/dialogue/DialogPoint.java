package dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class DialogPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private boolean ok;
	private Color color;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogPoint dialog = new DialogPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogPoint() {
		setBounds(100, 100, 568, 391);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		setTitle("Point");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblKoordinataX = new JLabel("Coordinate X");
		lblKoordinataX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblKoordinataY = new JLabel("Coordinate Y");
		lblKoordinataY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtX = new JTextField();
		txtX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtX.setColumns(10);
		
		txtX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		txtY = new JTextField();
		txtY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtY.setColumns(10);
		
		txtY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		JButton btnBoja = new JButton("Color");
		btnBoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				color=JColorChooser.showDialog(null, "Izaberite boju tacke", null);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKoordinataX)
						.addComponent(lblKoordinataY))
					.addGap(52)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(313, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(499, Short.MAX_VALUE)
					.addComponent(btnBoja)
					.addGap(97))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKoordinataX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblKoordinataY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addComponent(btnBoja)
					.addContainerGap(243, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Integer.parseInt(getTxtX().getText());
							Integer.parseInt(getTxtY().getText());
							setOk(true);
							setVisible(false);
						} catch (NumberFormatException e1) 
						{
							JOptionPane.showMessageDialog(new JFrame(), "Fields must bre filled with numeric values!", "Error", JOptionPane.WARNING_MESSAGE);
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setTxtXEdt(boolean b)
	{
		this.txtX.setEditable(b);
	}
	
	public void setTxtYEdt(boolean b)
	{
		this.txtY.setEditable(b);
	}
}
