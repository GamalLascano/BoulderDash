package game.view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JPanel;

import game.model.Game;
import game.model.map.MapInstance;
import game.view.sound.SoundPlay;

/**
 * Panel de reglas del juego.
 */
public class PanelMenu extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Hace panel menu.
	 */
	public static void makeMenu(Background panel, JButton[] button)
	{
		FrameMenu.getInstance().refreshPanel(panel);
		final double SPACEX = 0.3;

		button[0].setText("QUIERO JUGAR!");
		button[0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				MapInstance.setSelectedLevel(Integer.parseInt((String) PanelConfig.getInstance().getConfigLevel()));
				FrameMenu.getInstance().setVisible(false);
				Game.main(new String[0]);
				FrameMap.getInstance().setSize(FrameMenu.getInstance().getSize());
				FrameMap.setFullscr(FrameMenu.getInstance().isFullscreen());
				FrameMap.fullScr();
			}
		});

		Constraint.setup(0, 0, 1, 1, 0.5, 5, GridBagConstraints.SOUTH, GridBagConstraints.NONE);
		panel.add(button[0], Constraint.get());

		button[1].setText("TOP X");
		button[1].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				try
				{
					PanelTop.getInstance().showTopX(FrameMenu.getInstance());
				}
				catch (ClassNotFoundException | IOException | URISyntaxException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		Constraint.setup(0, 1, 1, 1, 0.5, SPACEX, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		panel.add(button[1], Constraint.get());

		button[2].setText("REGLAS DEL JUEGO");
		button[2].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				PanelRules.getInstance().showRules(FrameMenu.getInstance());;
			}
		});

		Constraint.setup(0, 2, 1, 1, 0.5, SPACEX, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		panel.add(button[2], Constraint.get());

		button[3].setText("CONFIGURACION");
		button[3].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				PanelConfig.getInstance().showConfig(FrameMenu.getInstance());
			}
		});

		Constraint.setup(0, 3, 1, 1, 0.5, SPACEX, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		panel.add(button[3], Constraint.get());

		button[4].setText("QUITAR");
		button[4].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				FrameMenu.getInstance().dispose();
				System.exit(0);
			}
		});

		Constraint.setup(0, 4, 1, 1, 0.5, SPACEX, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		panel.add(button[4], Constraint.get());
	}

}
