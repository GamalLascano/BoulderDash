package game.view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.view.scoreboard.ListOfScorename;
import game.view.scoreboard.ScoreBoard;
import game.view.sound.SoundPlay;

/**
 * Panel del menu.
 * 
 *
 */
public class FrameMenu extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6110335355903294127L;
	private static FrameMenu framemenu;
	private static Background panel;
	private static boolean fullScreenState = false;

	// menu
	private static JButton button[] = new JButton[5];

	private FrameMenu()
	{
		SoundPlay.getInstance();
		setupFrameMenu();
		setupPanelMenu();
		ListOfScorename.getInstance().start();

		add(panel);
		setPreferredSize(new Dimension(panel.getImage().getWidth(null), panel.getImage().getHeight(null)));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		PanelConfig.defaultConfig(this);
	}
	
	private void start()
	{
		showMenu();
	}

	public static FrameMenu getInstance()
	{
		if (framemenu == null)
		{
			framemenu = new FrameMenu();
		}
		return framemenu;
	}

	/**
	 * Setea la configuracion del frame.
	 */
	private void setupFrameMenu()
	{
		setTitle("Boulder Dash Menu");
		setResizable(false);
		setSize(800, 600);
		setVisible(true);
	}

	/**
	 * Setea la configuracion del panel.
	 */
	private void setupPanelMenu()
	{
		panel = new Background(new GridBagLayout());
		panel.putBackground(this);
		putButtons();

		try
		{
			ScoreBoard.getInstance().readScorenames();
		}
		catch (ClassNotFoundException | IOException | URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Inicializa los bottones.
	 */
	private void putButtons()
	{
		Image buttonimg0;
		Image buttonimg1;
		Image buttonimg2;
		try
		{
			buttonimg0 = ImageIO.read(getClass().getResource("button0.png"));
			buttonimg1 = ImageIO.read(getClass().getResource("button1.png"));
			buttonimg2 = ImageIO.read(getClass().getResource("button2.png"));
		}
		catch (Exception ex)
		{
			System.out.println(ex);
			buttonimg0 = null;
			buttonimg1 = null;
			buttonimg2 = null;
		}

		for (int y = 0; y < 5; y++)
		{
			button[y] = new JButton();
			button[y].setPreferredSize(new Dimension(buttonimg0.getWidth(null), buttonimg0.getHeight(null)));
			button[y].setIcon(new ImageIcon(buttonimg0));
			button[y].setRolloverIcon(new ImageIcon(buttonimg1));
			button[y].setPressedIcon(new ImageIcon(buttonimg2));
			button[y].setContentAreaFilled(false);
			button[y].setMargin(new Insets(0, 0, 0, 0));
			button[y].setHorizontalAlignment(SwingConstants.CENTER);
			button[y].setBorder(BorderFactory.createEmptyBorder());
			button[y].setBorderPainted(false);
			button[y].setFocusPainted(false);
			button[y].setVerticalTextPosition(SwingConstants.CENTER);
			button[y].setHorizontalTextPosition(SwingConstants.CENTER);
		}
	}

	/**
	 * Remueve todos los listeners para refrescar el panel.
	 * 
	 * @param jbutton
	 */
	private static void removeListeners(JButton[] jbutton)
	{
		for (JButton xButton : jbutton)
		{
			for (ActionListener al : xButton.getActionListeners())
			{
				xButton.removeActionListener(al);
			}
		}
	}

	/**
	 * Refresca el panel.
	 * 
	 * @param jpanel
	 */
	void refreshPanel(JPanel jpanel)
	{
		jpanel.removeAll();
		removeListeners(button);
		jpanel.revalidate();
		jpanel.repaint();
	}


	/**
	 * Muestra el menu.
	 */
	public static void showMenu()
	{
		PanelMenu.makeMenu(panel, button);
	}
	
	/**
	 * Devuelve el panel;
	 * @return
	 */
	public Background getPanel()
	{
		return panel;
	}
	
	/**
	 * Devuelve los bottones;
	 * @return
	 */
	public JButton[] getButtons()
	{
		return button;
	}
	
	/**
	 * Devuelve si esta en fullscreen;
	 * @return
	 */
	public boolean isFullscreen()
	{
		return fullScreenState;
	}
	
	/**
	 * Devuelve el panel;
	 * @return
	 */
	public void setFullscreen(boolean isfullscr)
	{
		fullScreenState = isfullscr;
	}
	
	/**
	 * Inicializa el menu.
	 */
	public static void runFrameMenu()
	{
		FrameMenu runFrameMenu = FrameMenu.getInstance();
		runFrameMenu.start();
		runFrameMenu.setVisible(true);
	}

}
