package game.view;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class FrameMenuBeta extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1962753107456617139L;
	private JFrame frame;
	private Background panel;
	
	private JButton[] button = new JButton[5];
	private Insets margin = new Insets (0,0,0,0);
	
	private String imgFileName = "game/view/wallpaper.png";
	private Image img;
	
	public FrameMenuBeta() {
		
		frame = new JFrame("Boulder Dash");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagConstraints c = new GridBagConstraints();
		
		panel = new Background(new GridBagLayout());
		
		setLocationRelativeTo(null);
		URL imgUrl = getClass().getClassLoader().getResource(imgFileName);
		if (imgUrl == null) {
			System.err.println("No se encuetra el archivo: "+imgFileName);
		} else {
			try {
				img = ImageIO.read(imgUrl);				
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
		panel.setImage(img);
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(10,10,0,0);
		c.anchor = GridBagConstraints.WEST;
		
		button[0] = new JButton("Reglas");
		button[0].setMargin(margin);
	//	button[0].addMouseListener(new MiAdapter());
		c.gridx = 0;
		c.gridy = 0;
		panel.add(button[0], c);
		
		button[1] = new JButton("Top");
		button[1].setMargin(margin);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(button[1],c);
		
		button[3] = new JButton("C");
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
				
		
		frame.add(panel);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension (img.getWidth(null),img.getHeight(null)));
		frame.pack();
		frame.setVisible(true);
		
	}
	
	/*public class MiAdapter extends MouseAdapter {
		
	}*/
	

	
	
	
	public static void main(String[] args) {
		FrameMenuBeta app = new FrameMenuBeta();
		app.isActive();
	}
}
