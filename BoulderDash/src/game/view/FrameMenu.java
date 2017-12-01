package game.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import game.model.Game;
import game.model.map.MapInstance;

/**
 * Panel del menu. 
 *
 */
public class FrameMenu extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static FrameMenu framemenu;
	private static Background panel;
	private static boolean fullScreenState=false;
	// panel variables
	private static GridBagConstraints cons = new GridBagConstraints();
	// menu
	private static String dirwallpaper = "game/view/wallpaper.png";
	private static Image wallpaperimg;
	private static JButton button[] = new JButton[5];
	// rules
	private static BufferedImage instructionsImg;
	private static JLabel instructions;
	// top X
	private static String[] tablecolumn =
	{ "Puesto", "Nombre", "Puntos", "Tiempo", };
	private static Object[][] tabledata;
	private static JTable table = new JTable();
	private static DefaultTableModel tablemodel;
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
	{ "5", "10", "15", "20" };
	private static Dimension pastScreenSize = new Dimension(800, 600);

	private FrameMenu() throws ClassNotFoundException, FileNotFoundException, IOException, URISyntaxException
	{
		SoundPlay.getInstance();
		setupFrameMenu();
		setupPanelMenu();
		tabledata = new Object[4][20];
		ScoreBoard.getInstance().readScorenames(tabledata);

		add(panel);
		setPreferredSize(new Dimension(wallpaperimg.getWidth(null), wallpaperimg.getHeight(null)));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static FrameMenu getInstance()
	{
		if (framemenu == null)
		{
			try
			{
				framemenu = new FrameMenu();
			}
			catch (URISyntaxException | IOException | ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return framemenu;
	}

	/**
	 * 
	 */
	public void setupFrameMenu()
	{
		setTitle("Boulder Dash Menu");
		setResizable(false);
		setSize(800, 600);
		setVisible(true);
	}

	/**
	 * 
	 */
	public void setupPanelMenu()
	{
		panel = new Background(new GridBagLayout());
		putBackground();
		putButtons();
		
		top = new JComboBox<>(tops);
		resoluciones = new JComboBox<>(resolutions);
		fullScr = new JCheckBox("Pantalla Completa");
		levelsel = new JComboBox<>(levels);
		top.setSelectedIndex(0);
		resoluciones.setSelectedIndex(0);
		fullScr.setSelected(false);
		levelsel.setSelectedItem(0);
		MapInstance.setSelectedLevel(Integer.parseInt((String) levelsel.getSelectedItem()));
		
		menu();
	}

	/**
	 * 
	 */
	public void putButtons()
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
	 * 
	 */
	public void putBackground()
	{
		URL imgUrl = getClass().getClassLoader().getResource(dirwallpaper);
		if (imgUrl == null)
		{
			System.err.println("No se encuetra el archivo: " + dirwallpaper);
		}
		else
		{
			try
			{
				wallpaperimg = ImageIO.read(imgUrl);
				wallpaperimg = wallpaperimg.getScaledInstance(this.getSize().width, this.getSize().height, Image.SCALE_DEFAULT);
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		panel.setImage(wallpaperimg);
	}

	/**
	 * 
	 * @param jbutton
	 */
	public static void removeListeners(JButton[] jbutton)
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
	 * 
	 * @param jpanel
	 */
	public static void refreshPanel(JPanel jpanel)
	{
		jpanel.removeAll();
		removeListeners(button);
		jpanel.revalidate();
		jpanel.repaint();
	}

	/**
	 * 
	 * @param constraint
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param weightx
	 * @param weighty
	 * @param anchor
	 * @param fill
	 */
	public static void setupConstraint(GridBagConstraints constraint, int x, int y, int width, int height, double weightx, double weighty, int anchor, int fill)
	{
		constraint.gridx = x;
		constraint.gridy = y;
		constraint.gridwidth = width;
		constraint.gridheight = height;
		constraint.weightx = weightx;
		constraint.weighty = weighty;
		constraint.anchor = anchor;
		constraint.fill = fill;
	}

	/**
	 * 
	 */
	public static void menu()
	{
		refreshPanel(panel);
		final double SPACEX = 0.3;

		button[0].setText("QUIERO JUGAR!");
		button[0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				framemenu.setVisible(false);
				Game.main(new String[0]);
				FrameMap.getInstance().setSize(getInstance().getSize());
				FrameMap.setFullscr(fullScreenState);
				FrameMap.fullScr();
			}
		});

		setupConstraint(cons, 0, 0, 1, 1, 0.5, 5, GridBagConstraints.SOUTH, GridBagConstraints.NONE);
		panel.add(button[0], cons);

		button[1].setText("TOP X");
		button[1].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				try
				{
					topX();
				}
				catch (ClassNotFoundException | IOException | URISyntaxException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		setupConstraint(cons, 0, 1, 1, 1, 0.5, SPACEX, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		panel.add(button[1], cons);

		button[2].setText("REGLAS DEL JUEGO");
		button[2].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				rules();
			}
		});

		setupConstraint(cons, 0, 2, 1, 1, 0.5, SPACEX, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		panel.add(button[2], cons);

		button[3].setText("CONFIGURACION");
		button[3].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				config();
			}
		});

		setupConstraint(cons, 0, 3, 1, 1, 0.5, SPACEX, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		panel.add(button[3], cons);

		button[4].setText("QUITAR");
		button[4].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				framemenu.dispose();
				System.exit(0);
			}
		});

		setupConstraint(cons, 0, 4, 1, 1, 0.5, SPACEX, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		panel.add(button[4], cons);
	}

	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void topX() throws ClassNotFoundException, FileNotFoundException, IOException, URISyntaxException
	{
		refreshPanel(panel);

		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		Integer rowx = Integer.parseInt((String) top.getSelectedItem());
		framemenu.showXrow(rowx - 1);

		button[0].setText("Back");
		button[0].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				menu();
			}
		});
		toptitle = new JLabel("TOP X");
		toptitle.setForeground(Color.WHITE);

		setupConstraint(cons, 0, 0, 1, 1, 1, 0.5, GridBagConstraints.SOUTH, GridBagConstraints.CENTER);
		panel.add(toptitle, cons);

		setupConstraint(cons, 0, 1, 5, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		panel.add(scrollPane, cons);

		setupConstraint(cons, 0, 2, 1, 1, 1, 0.8, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(button[0], cons);

	}

	/**
	 * 
	 */
	public static void rules()
	{
		refreshPanel(panel);

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
				SoundPlay.button();
				menu();
			}
		});

		setupConstraint(cons, 0, 0, 1, 1, 1, 1, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(instructions, cons);

		setupConstraint(cons, 0, 1, 1, 1, 1, 0.8, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(button[0], cons);
	}

	/**
	 * 
	 */
	public static void config()
	{
		refreshPanel(panel);

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
		fullScr.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					pastScreenSize = FrameMenu.getInstance().getSize();
					FrameMenu.fullScreenState=true;
					FrameMenu.getInstance().setSize(screenSize);
					FrameMenu.getInstance().putBackground();
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
					FrameMenu.fullScreenState=false;
					FrameMenu.getInstance().setSize(pastScreenSize);
					FrameMenu.getInstance().putBackground();
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
				SoundPlay.button();
				menu();
			}
		});

		setupConstraint(cons, 0, 1, 1, 1, 1, 5, GridBagConstraints.SOUTH, GridBagConstraints.CENTER);
		panel.add(top, cons);

		setupConstraint(cons, 0, 2, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(resoluciones, cons);

		setupConstraint(cons, 0, 3, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(levelsel, cons);
		
		setupConstraint(cons, 0, 4, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(fullScr, cons);

		setupConstraint(cons, 0, 5, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
		panel.add(button[0], cons);
	}

	/**
	 * 
	 * @param x
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void showXrow(Integer x) throws ClassNotFoundException, FileNotFoundException, IOException, URISyntaxException
	{
		ScoreBoard.getInstance().readScorenames(tabledata);
		tablemodel = new DefaultTableModel(tabledata, tablecolumn);
		for (int i = 0; i < x; i++)
		{
			tablemodel.addRow(tabledata[i]);
		}
		table.setModel(tablemodel);
	}
	
	/**
	 * 
	 * @param name
	 * @param score
	 * @param time
	 */
	public void addNameTable(String name, Integer score, Integer time)
	{
		tabledata[0][tabledata.length] = "999";
		tabledata[1][tabledata.length] = name;
		tabledata[2][tabledata.length] = score.toString();
		tabledata[3][tabledata.length] = time.toString();
	}

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException, URISyntaxException
	{
		FrameMenu runFrameMenu = FrameMenu.getInstance();
		runFrameMenu.setVisible(true);
		ScoreBoard.getInstance().writeScorenames(tabledata);

	}

}
