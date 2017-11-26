package game.view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.model.Game;
import game.view.FrameConfig.MiItemListener;
import game.view.FrameConfig.mouseListenerSalida;

public class FrameMenu extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static FrameMenu framemenu;
	private static Background panel;

	// panel variables
	private static GridBagConstraints cons = new GridBagConstraints();
	private static Insets ins = new Insets(0,0,0,0);
	// menu
	private static String imgFileName = "game/view/wallpaper.png";
	private static Image img;
	private static JButton button[][] = new JButton[5][1];
	// rules
	private static JTextField textRockford = new JTextField(
			"Rockford: Your hero, guide him through the caves, searching for diamonds in order to activate the exit. Use the arrow keys to control Rockford. PageUp and PageDown to change level. Escape to restart level.");
	private static JTextField textDirt = new JTextField("Dirt: ");
	private static JTextField textBoulder = new JTextField("Boulder: ");
	private static JTextField textDiamond = new JTextField("Diamond: ");
	private static JTextField textWall = new JTextField("Wall: ");
	private static JTextField textSteel = new JTextField("Steel: ");
	private static JTextField textMagic = new JTextField("Magic: ");
	private static JTextField textFirefly = new JTextField("Firefly: ");
	private static JTextField textButterfly = new JTextField("Butterfly: ");
	private static JTextField textAmoeba = new JTextField("Amoeba: ");
	private static JTextField textExit = new JTextField("Exit: ");
	// top X
	private static String[] columnNames = {"TOP","NAME","SCORE",};
	private static Object[][] data = {
		    {"1", "Max", new Integer(5000)},
		    {"2", "Walter", new Integer(3000)},
		    {"3", "Jesse", new Integer(2000)},
		    {"4", "Frank", new Integer(1000)},
		    {"5", "Tortuga", new Integer(10)}
		};
	private static JTable table = new JTable(data, columnNames);
	private static JScrollPane scrollPane = new JScrollPane(table);
	// config
	private static JComboBox<String> resoluciones;
	private static JCheckBox fullScr;
	private static JComboBox<String> top;

	private FrameMenu()
	{
		setupFrameMenu();
		setupPanelMenu();
		
		add(panel);
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static FrameMenu getInstance()
	{
		if (framemenu == null)
		{
			framemenu = new FrameMenu();
		}
		return framemenu;
	}

	// framee
	public void setupFrameMenu()
	{
		setLocationRelativeTo(null);
		setTitle("Boulder Dash Menu");
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(600, 600);
		setVisible(true);
	}
	
	public void setupPanelMenu()
	{
		panel = new Background(new GridBagLayout());
		putBackground();
		putButtons();
		menu();
	}
	
	public void putButtons()
	{
		for (int y = 0; y < 5; y++)
		{
			for (int x = 0; x < 1; x++)
			{
				button[y][x] = new JButton();
			}
		}
	}

	// panel
	public void putBackground()
	{
		URL imgUrl = getClass().getClassLoader().getResource(imgFileName);
		if (imgUrl == null)
		{
			System.err.println("No se encuetra el archivo: " + imgFileName);
		}
		else
		{
			try
			{
				img = ImageIO.read(imgUrl);
				img = img.getScaledInstance(this.getSize().width, this.getSize().height, Image.SCALE_DEFAULT);
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		panel.setImage(img);
	}

	public static void removeAllActionsListeners()
	{
		for (JButton[] xButton : button)
		{
			for (JButton currentButton : xButton)
			{
				for (ActionListener al : currentButton.getActionListeners())
				{
					currentButton.removeActionListener(al);
				}
			}
		}
	}

	public static void menu()
	{		
		panel.removeAll();
		removeAllActionsListeners();
		panel.revalidate();
		panel.repaint();
		
		button[0][0].setText("QUIERO JUGAR!");
		button[0][0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				framemenu.setVisible(false);
				Game.main(new String[0]);
			}
		});
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.5;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 5;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[0][0], cons);

		button[1][0].setText("TOP X");
		button[1][0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				topX();
			}
		});
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.5;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[1][0]);

		button[2][0].setText("REGLAS DEL JUEGO");
		button[2][0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				rules();
			}
		});
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.5;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[2][0]);

		button[3][0].setText("CONFIGURACION");
		button[3][0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				FrameMenu.getInstance().setVisible(false);
				FrameConfig.getInstance().setVisible(true);
				//config();
			}
		});
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.5;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[3][0]);

		button[4][0].setText("QUITAR");
		button[4][0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				framemenu.dispose();
				System.exit(0);
			}
		});
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.5;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[4][0]);
	}

	public static void topX()
	{
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		removeAllActionsListeners();

		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);

		button[0][0].setText("Back");
		button[0][0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				menu();
			}
		});
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(table, cons);
		
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.anchor = GridBagConstraints.BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[0][0], cons);

	}

	public static void rules()
	{
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		removeAllActionsListeners();

		panel.add(textRockford);
		panel.add(textDirt);
		panel.add(textBoulder);
		panel.add(textDiamond);
		panel.add(textWall);
		panel.add(textSteel);
		panel.add(textMagic);
		panel.add(textFirefly);
		panel.add(textButterfly);
		panel.add(textAmoeba);
		panel.add(textExit);

		button[0][0].removeActionListener(button[0][0].getActionListeners()[0]);
		button[0][0].setText("Back");
		button[0][0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				menu();
			}
		});
		panel.add(button[0][0]);
	}

	public static void config()
	{
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		
		removeAllActionsListeners();
		button[0][0].removeActionListener(button[0][0].getActionListeners()[0]);
		button[0][0].setText("Back");
		button[0][0].setBounds(0, 0, 100, 25);
		button[0][0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				menu();
			}
		});

		panel.add(top);
		panel.add(resoluciones);
		panel.add(button[0][0]);
	}

	public static void main(String[] args)
	{
		FrameMenu runFrameMenu = FrameMenu.getInstance();
		runFrameMenu.setVisible(true);
	}

}
