package game.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import game.model.Game;
import game.model.map.MapInstance;

public class FrameMenu extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static FrameMenu framemenu;
	private static Background panel;

	// panel variables
	private static GridBagConstraints cons = new GridBagConstraints();
	private static Insets ins = new Insets(0, 0, 0, 0);
	// menu
	private static String imgFileName = "game/view/wallpaper.jpg";
	private static Image img;
	private static JButton button[] = new JButton[5];
	// rules
	private static BufferedImage instructionsImg;
	private static JLabel instructions;
	// top X
	private static String[] columnNames =
	{ "Puesto", "Nombre", "Puntos", "Tiempo", };
	private static Object[][] data =
	{
			{ "1", "Max", new Integer(5000), new Integer(2000) },
			{ "2", "Walter", new Integer(3000), new Integer(2000) },
			{ "3", "Jesse", new Integer(2000), new Integer(2000) },
			{ "4", "Frank", new Integer(1000), new Integer(2000) },
			{ "5", "Tortuga", new Integer(10), new Integer(2000) },
			{ "6", "-", new Integer(0), new Integer(0) },
			{ "7", "-", new Integer(0), new Integer(0) },
			{ "8", "-", new Integer(0), new Integer(0) },
			{ "9", "-", new Integer(0), new Integer(0) },
			{ "10", "-", new Integer(0), new Integer(0) },
			{ "11", "-", new Integer(0), new Integer(0) },
			{ "12", "-", new Integer(0), new Integer(0) },
			{ "13", "-", new Integer(0), new Integer(0) },
			{ "14", "-", new Integer(0), new Integer(0) },
			{ "15", "-", new Integer(0), new Integer(0) },
			{ "16", "-", new Integer(0), new Integer(0) },
			{ "17", "-", new Integer(0), new Integer(0) },
			{ "18", "-", new Integer(0), new Integer(0) },
			{ "19", "-", new Integer(0), new Integer(0) },
			{ "20", "-", new Integer(0), new Integer(0) }, };
	private static JTable table = new JTable(data, columnNames);
	private static JScrollPane scrollPane = new JScrollPane(table);
	private static JLabel toptitle;
	RowFilter<Object, Object> filter;
	// config
	private static JComboBox<String> resoluciones;
	private static JCheckBox fullScr;
	private static JComboBox<String> top;
	private static JComboBox<String> levelsel;
	private static String[] levels =
	{ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private static String[] resolutions =
	{ "800x600", "1024x768", "1366x768", "1920x1080" };
	private static String[] tops =
	{ "TOP 5", "TOP 10", "TOP 15", "TOP 20" };
	private static Dimension pastScreenSize = new Dimension(800, 600);

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
		setSize(800, 600);
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
			button[y] = new JButton();
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
		for (JButton xButton : button)
		{
			for (ActionListener al : xButton.getActionListeners())
			{
				xButton.removeActionListener(al);
			}
		}
	}

	public static void menu()
	{
		panel.removeAll();
		removeAllActionsListeners();
		panel.revalidate();
		panel.repaint();

		button[0].setText("QUIERO JUGAR!");
		button[0].addActionListener(new ActionListener()
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
		cons.weighty = 5;
		cons.anchor = GridBagConstraints.SOUTH;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[0], cons);

		button[1].setText("TOP X");
		button[1].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				topX();
			}
		});
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.5;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.SOUTH;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[1], cons);

		button[2].setText("REGLAS DEL JUEGO");
		button[2].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				rules();
			}
		});
		cons.gridx = 2;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.5;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.SOUTH;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[2], cons);

		button[3].setText("CONFIGURACION");
		button[3].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				config();
			}
		});
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.5;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.ABOVE_BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[3], cons);

		button[4].setText("QUITAR");
		button[4].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				framemenu.dispose();
				System.exit(0);
			}
		});
		cons.gridx = 2;
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.5;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.ABOVE_BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[4], cons);
	}

	public static void topX()
	{
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		removeAllActionsListeners();

		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);

		button[0].setText("Back");
		button[0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				menu();
			}
		});
		toptitle = new JLabel("TOP X");
		toptitle.setForeground(Color.WHITE);

		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.ABOVE_BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(toptitle, cons);

		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 5;
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(scrollPane, cons);

		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 0.8;
		cons.anchor = GridBagConstraints.BELOW_BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[0], cons);

	}

	public static void rules()
	{
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		removeAllActionsListeners();

		try
		{
			instructionsImg = ImageIO.read(framemenu.getClass().getResource("instructions.png"));
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		instructions = new JLabel(new ImageIcon(instructionsImg));

		button[0].setText("Back");
		button[0].addActionListener(new ActionListener()
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
		cons.anchor = GridBagConstraints.BELOW_BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(instructions, cons);

		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 0.8;
		cons.anchor = GridBagConstraints.BELOW_BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[0], cons);
	}

	public static void config()
	{
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		removeAllActionsListeners();

		top = new JComboBox<>(tops);
		top.setSelectedIndex(0);
		resoluciones = new JComboBox<>(resolutions);
		resoluciones.setSelectedIndex(0);
		resoluciones.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				@SuppressWarnings("rawtypes")
				JComboBox cb = (JComboBox) e.getSource();
				String res = (String) cb.getSelectedItem();
				switch (res)
				{
					case "800x600":
					{
						FrameMenu.getInstance().setSize(800, 600);
						FrameMenu.getInstance().putBackground();
						break;
					}
					case "1024x768":
					{
						FrameMenu.getInstance().setSize(1024, 768);
						FrameMenu.getInstance().putBackground();
						break;
					}
					case "1366x768":
					{
						FrameMenu.getInstance().setSize(1366, 768);
						FrameMenu.getInstance().putBackground();
						break;
					}
					case "1920x1080":
					{
						FrameMenu.getInstance().setSize(1920, 1080);
						FrameMenu.getInstance().putBackground();
						break;
					}
					default:
						break;
				}
			}
		});
		fullScr = new JCheckBox("Pantalla Completa");
		fullScr.addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent e)
			{
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					pastScreenSize = FrameMenu.getInstance().getSize();
					FrameMenu.getInstance().setSize(screenSize);
					FrameMenu.getInstance().putBackground();
					FrameMenu.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
					FrameMenu.getInstance().setUndecorated(true);
				}
				else
				{
					FrameMenu.getInstance().setSize(pastScreenSize);
					FrameMenu.getInstance().putBackground();
					FrameMenu.getInstance().setExtendedState(JFrame.NORMAL);
					FrameMenu.getInstance().setUndecorated(false);
				}
			}
		});
		fullScr.setSelected(false);
		levelsel = new JComboBox<>(levels);
		levelsel.setSelectedItem(0);
		levelsel.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				@SuppressWarnings("rawtypes")
				JComboBox cb = (JComboBox) e.getSource();
				String res = (String) cb.getSelectedItem();
				MapInstance.setSelectedLevel(Integer.parseInt(res));
			}
		});

		button[0].setText("Back");
		button[0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				menu();
			}
		});

		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.BELOW_BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(top, cons);

		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.BELOW_BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(resoluciones, cons);

		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.BELOW_BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(fullScr, cons);

		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.BELOW_BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(levelsel, cons);

		cons.gridx = 0;
		cons.gridy = 5;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 0.5;
		cons.anchor = GridBagConstraints.BELOW_BASELINE;
		cons.fill = GridBagConstraints.CENTER;
		ins.bottom = 0;
		ins.left = 0;
		ins.right = 0;
		ins.top = 0;
		cons.insets = ins;
		cons.ipadx = 0;
		cons.ipady = 0;
		panel.add(button[0], cons);
	}

	public static void main(String[] args)
	{
		FrameMenu runFrameMenu = FrameMenu.getInstance();
		runFrameMenu.setVisible(true);
	}

}
