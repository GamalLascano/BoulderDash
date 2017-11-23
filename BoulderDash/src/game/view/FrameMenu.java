package game.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import game.model.Game;

public class FrameMenu extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static JPanel panel = new JPanel(new GridBagLayout());
	private static FrameMenu framemenu;
	//panel variables
	private static JButton button[][] = new JButton[1][4];
	private static JList<String> listX = new JList<String>();
	private static JList<Integer> listLevel = new JList<Integer>();
	private static JTextField textRockford = new JTextField("Rockford: Your hero, guide him through the caves, searching for diamonds in order to activate the exit. Use the arrow keys to control Rockford. PageUp and PageDown to change level. Escape to restart level.");
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
	

	FrameMenu()
	{
		setLocationRelativeTo(null);
		setTitle("Boulder Dash Menu");
		setResizable(false);
		setSize(600, 600);
		setVisible(true);
		
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 1; x++)
			{
				button[x][y] = new JButton();
			}
		}
		menu();
	}

	public static FrameMenu getInstance()
	{
		if (framemenu == null)
		{
			framemenu = new FrameMenu();
		}
		return framemenu;
	}
	//panel
	public static void removeAllActionsListeners()
	{
		for( JButton[] xButton: button ) {
			for( JButton currentButton: xButton ) {
			    for( ActionListener al : currentButton.getActionListeners() ) {
			        currentButton.removeActionListener( al );
			    }
			}
		}
	}

	public static void menu()
	{
		panel.removeAll();
		removeAllActionsListeners();
		panel.revalidate();
		
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
		panel.add(button[0][0]);
		

		button[0][1].setText("TOP X");
		button[0][1].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				topX();
			}
		});
		panel.add(button[0][1]);

		button[0][2].setText("REGLAS DEL JUEGO");
		button[0][2].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				rules();
			}
		});
		panel.add(button[0][2]);

		button[0][3].setText("CONFIGURACION");
		button[0][3].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				FrameMenu.getInstance().setVisible(false);
				FrameConfig.getInstance().setVisible(true);
			}
		});
		panel.add(button[0][3]);
	}

	public static void topX()
	{
		panel.removeAll();
		panel.revalidate();
		
		removeAllActionsListeners();
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

	public static void rules()
	{
		panel.removeAll();
		panel.revalidate();
		
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
		
		panel.add(listX);
		panel.add(listLevel);
		
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

	public static void main(String[] args)
	{
		FrameMenu.getInstance();
	}

}
