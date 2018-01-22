package game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.controller.access.MapAccess;
import game.view.config.Config;
import game.view.config.ConfigFile;
import game.view.sound.Sound;

/**
 * Panel de reglas del juego.
 */
public class PanelConfig extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static PanelConfig configpanel;
	private JButton button;
	private Background panel;
	private static JComboBox<String> resolutionBox;
	private static JCheckBox fullscreenBox;
	private static JComboBox<String> topBox;
	private static JComboBox<String> levelBox;
	private static JLabel label;
	private static String[] levelArray =
	{ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private static String[] resolutionArray =
	{ "800x600", "1024x768", "1366x768", "1920x1080" };
	private static String[] topArray =
	{ "5", "10", "15", "20" };

	private PanelConfig(FrameMenu frame)
	{
		panel = frame.getPanel();
		button = frame.getButtons()[0];
	}

	private PanelConfig()
	{
		panel = null;
		button = null;
	}

	public static PanelConfig getInstance()
	{
		if (configpanel == null)
		{
			configpanel = new PanelConfig();
		}
		return configpanel;
	}

	/**
	 * Inicializacion.
	 * 
	 * @param frame
	 * @return configpanel
	 */
	private static PanelConfig initialize(FrameMenu frame)
	{
		if (configpanel == null)
		{
			configpanel = new PanelConfig(frame);
		}
		return configpanel;
	}

	/**
	 * Valores iniciales de configuracion.
	 */
	public static void defaultConfig(FrameMenu frame)
	{
		initialize(frame);

		topBox = new JComboBox<>(topArray);
		resolutionBox = new JComboBox<>(resolutionArray);
		fullscreenBox = new JCheckBox("Pantalla Completa");
		levelBox = new JComboBox<>(levelArray);
		
		topBox.setSelectedItem(Config.getInstance().getTop());
		resolutionBox.setSelectedItem(Config.getInstance().getResolution());
		levelBox.setSelectedItem(Config.getInstance().getInitialLevel());
		fullscreenBox.setSelected(Boolean.valueOf(Config.getInstance().getFullscreen()));

		MapAccess.setSelectedLevel(Integer.parseInt((String) levelBox.getSelectedItem()));
	}

	/**
	 * Panel de configuracion.
	 */
	public void showConfig(FrameMenu framemenu)
	{
		framemenu.refreshPanel(framemenu.getPanel());

		topBox.setPreferredSize(new Dimension(250, 40));

		resolutionBox.setPreferredSize(new Dimension(250, 40));
		resolutionBox.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Sound.button();
				@SuppressWarnings("rawtypes")
				JComboBox cb = (JComboBox) e.getSource();
				String res = (String) cb.getSelectedItem();
				setFrameResolution(res);
			}
		});
		fullscreenBox.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				setFrameFullscreen(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		fullscreenBox.setPreferredSize(new Dimension(250, 40));
		levelBox.setPreferredSize(new Dimension(250, 40));
		levelBox.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Sound.button();
			}
		});

		button.setText("Guardar");
		button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Sound.button();
				Config.getInstance().setTop((String) topBox.getSelectedItem());
				Config.getInstance().setResolution((String) resolutionBox.getSelectedItem());
				Config.getInstance().setInitialLevel((String) levelBox.getSelectedItem());
				Config.getInstance().setFullscreen((String) Boolean.toString(fullscreenBox.isSelected()));
				try
				{
					ConfigFile.getInstance().writeConfig();
				}
				catch (ClassNotFoundException | IOException | URISyntaxException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				FrameMenu.showMenu();
			}
		});

		
		label = new JLabel("Mostrar x Top");
		label.setForeground(Color.WHITE);
		Constraint.setup(0, 0, 1, 1, 1, 7, GridBagConstraints.SOUTH, GridBagConstraints.CENTER);
		panel.add(label, Constraint.get());
		
		Constraint.setup(0, 1, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(topBox, Constraint.get());

		label = new JLabel("Resolucion");
		label.setForeground(Color.WHITE);
		Constraint.setup(0, 2, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(label, Constraint.get());
		
		Constraint.setup(0, 3, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(resolutionBox, Constraint.get());

		label = new JLabel("Nivel Inicial");
		label.setForeground(Color.WHITE);
		Constraint.setup(0, 4, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(label, Constraint.get());
		
		Constraint.setup(0, 5, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(levelBox, Constraint.get());
		
		Constraint.setup(0, 6, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(fullscreenBox, Constraint.get());

		Constraint.setup(0, 7, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(button, Constraint.get());
	}
	
	/**
	 * Setea la resolution del frame.
	 * @param res
	 */
	public void setFrameResolution(String res)
	{
		if(!fullscreenBox.isSelected())
		{
			switch (res)
			{
				case "800x600":
				{
					FrameMenu.getInstance().setSize(800, 600);
					FrameMenu.getInstance().centerFrame();
					panel.putBackground(FrameMenu.getInstance());
					break;
				}
				case "1024x768":
				{
					FrameMenu.getInstance().setSize(1024, 768);
					FrameMenu.getInstance().centerFrame();
					panel.putBackground(FrameMenu.getInstance());
					break;
				}
				case "1366x768":
				{
					FrameMenu.getInstance().setSize(1366, 768);
					FrameMenu.getInstance().centerFrame();
					panel.putBackground(FrameMenu.getInstance());
					break;
				}
				case "1920x1080":
				{
					FrameMenu.getInstance().setSize(1920, 1080);
					FrameMenu.getInstance().centerFrame();
					panel.putBackground(FrameMenu.getInstance());
					break;
				}
				default:
					break;
				}
		}
	}

	/**
	 * Setea el frame en pantalla completa o en pantalla normal.
	 * @param isFullscreen
	 */
	public void setFrameFullscreen(boolean isFullscreen)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (isFullscreen)
		{
			FrameMenu.getInstance().setFullscreen(true);
			FrameMenu.getInstance().setSize(screenSize);
			panel.putBackground(FrameMenu.getInstance());
			FrameMenu.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
			FrameMenu.getInstance().dispose();
			FrameMenu.getInstance().setUndecorated(true);
			FrameMenu.getInstance().pack();
			FrameMenu.getInstance().setVisible(true);
			FrameMenu.getInstance().setAlwaysOnTop(true);
			FrameMenu.getInstance().setLocationRelativeTo(null);
		}
		else
		{
			String dim = (String) resolutionBox.getSelectedItem();
			String dimWidth = dim.substring(0, dim.indexOf('x'));
			String dimHeight = dim.substring(dim.indexOf('x') + 1);
			int width = Integer.parseInt(dimWidth);
			int height = Integer.parseInt(dimHeight);
			Dimension frameSize = new Dimension(width, height);
			
			FrameMenu.getInstance().setFullscreen(false);
			FrameMenu.getInstance().setSize(frameSize);
			panel.putBackground(FrameMenu.getInstance());
			FrameMenu.getInstance().setExtendedState(JFrame.NORMAL);
			FrameMenu.getInstance().dispose();
			FrameMenu.getInstance().setUndecorated(false);
			FrameMenu.getInstance().pack();
			FrameMenu.getInstance().setVisible(true);
			FrameMenu.getInstance().setAlwaysOnTop(false);
			FrameMenu.getInstance().setSize(frameSize);
			FrameMenu.getInstance().centerFrame();
		}
	}
}
