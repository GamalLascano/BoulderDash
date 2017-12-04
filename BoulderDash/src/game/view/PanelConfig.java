package game.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.model.map.MapInstance;
import game.view.sound.SoundPlay;

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
	private static JComboBox<String> resoluciones;
	private static JCheckBox fullScr;
	private static JComboBox<String> top;
	private static JComboBox<String> levelsel;
	private static String[] levels =
	{ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private static String[] resolutions =
	{ "800x600", "1024x768", "1366x768", "1920x1080" };
	private static String[] tops =
	{ "5", "10", "15", "20" };
	private Dimension pastScreenSize = new Dimension(800, 600);

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
		
		top = new JComboBox<>(tops);
		resoluciones = new JComboBox<>(resolutions);
		fullScr = new JCheckBox("Pantalla Completa");
		levelsel = new JComboBox<>(levels);
		top.setSelectedIndex(0);
		resoluciones.setSelectedIndex(0);
		fullScr.setSelected(false);
		levelsel.setSelectedItem(0);
		
		MapInstance.setSelectedLevel(Integer.parseInt((String) levelsel.getSelectedItem()));
	}

	/**
	 * Panel de configuracion.
	 */
	public void showConfig(FrameMenu framemenu)
	{
		framemenu.refreshPanel(framemenu.getPanel());

		top.setPreferredSize(new Dimension(250, 40));

		resoluciones.setPreferredSize(new Dimension(250, 40));
		resoluciones.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				@SuppressWarnings("rawtypes")
				JComboBox cb = (JComboBox) e.getSource();
				String res = (String) cb.getSelectedItem();
				switch (res)
				{
					case "800x600":
					{
						FrameMenu.getInstance().setSize(800, 600);
						panel.putBackground(FrameMenu.getInstance());
						break;
					}
					case "1024x768":
					{
						FrameMenu.getInstance().setSize(1024, 768);
						panel.putBackground(FrameMenu.getInstance());
						break;
					}
					case "1366x768":
					{
						FrameMenu.getInstance().setSize(1366, 768);
						panel.putBackground(FrameMenu.getInstance());
						break;
					}
					case "1920x1080":
					{
						FrameMenu.getInstance().setSize(1920, 1080);
						panel.putBackground(FrameMenu.getInstance());
						break;
					}
					default:
						break;
				}
			}
		});
		fullScr.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					pastScreenSize = FrameMenu.getInstance().getSize();
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
					FrameMenu.getInstance().setFullscreen(false);
					FrameMenu.getInstance().setSize(pastScreenSize);
					panel.putBackground(FrameMenu.getInstance());
					FrameMenu.getInstance().setExtendedState(JFrame.NORMAL);
					FrameMenu.getInstance().dispose();
					FrameMenu.getInstance().setUndecorated(false);
					FrameMenu.getInstance().pack();
					FrameMenu.getInstance().setVisible(true);
					FrameMenu.getInstance().setAlwaysOnTop(false);
					FrameMenu.getInstance().setLocationRelativeTo(null);
				}
			}
		});
		fullScr.setPreferredSize(new Dimension(250, 40));
		levelsel.setPreferredSize(new Dimension(250, 40));
		levelsel.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
			}
		});

		button.setText("Back");
		button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				FrameMenu.showMenu();
			}
		});

		Constraint.setup(0, 1, 1, 1, 1, 5, GridBagConstraints.SOUTH, GridBagConstraints.CENTER);
		panel.add(top, Constraint.get());

		Constraint.setup(0, 2, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(resoluciones, Constraint.get());

		Constraint.setup(0, 3, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(levelsel, Constraint.get());

		Constraint.setup(0, 4, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(fullScr, Constraint.get());

		Constraint.setup(0, 5, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(button, Constraint.get());
	}
	
	/**
	 * Devuelve el valor del nivel inicial
	 * de la configuracion que fue eligido.
	 */
	public Object getConfigLevel()
	{
		return levelsel.getSelectedItem();
	}
	
	/**
	 * Devuelve el valor de top X
	 * de la configuracion que fue eligido.
	 */
	public Object getConfigTop()
	{
		return top.getSelectedItem();
	}

}
