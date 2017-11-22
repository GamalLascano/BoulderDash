package game.view;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class FrameMenuBeta extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1962753107456617139L;
	private JFrame frame;
	private JPanel panel;
	
	private JButton[] button = new JButton[5];
	private Dimension dimension;
	private Insets insets;
	private Insets margin = new Insets (0,0,0,0);
	
	public FrameMenuBeta() {
		frame = new JFrame("Boulder Dash");
		//frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagConstraints c = new GridBagConstraints();
		
		panel = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(10,10,0,0);
		c.anchor = GridBagConstraints.WEST;
		
		button[0] = new JButton("Reglas");
		button[0].setMargin(margin);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(button[0], c);
		
		button[1] = new JButton("Top");
		button[1].setMargin(margin);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(button[1],c);
		
		button[3] = new JButton("C"); //cambiar por imagen
		button[3].setMargin(margin);
		c.gridx = 1;
		c.gridy = 0;
		panel.add(button[3], c);		
		
		c.insets = new Insets(10,10,10,0);
		button[2] = new JButton("Quiero Jugar!!!");
		button[2].setMargin(margin);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(button[2],c);
		
		c.insets = new Insets(10,10,10,10);
		button[4] = new JButton("<html>Seleccionar<br/>nivel</html>");
		button[4].setMargin(margin);
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 2;
		panel.add(button[4], c);
		
		//frame.setPreferredSize(new Dimension (400,400));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		FrameMenuBeta app = new FrameMenuBeta();
	}
}
