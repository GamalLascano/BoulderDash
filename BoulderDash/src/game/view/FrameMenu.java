package game.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import game.model.Game;
import game.model.map.MapInstance;
import game.view.sound.SoundPlay;

/**
 * Panel del menu. 
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
	private static ArrayList<Scorename> tabledata;
	private static Object[][] tableshow;
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

	private FrameMenu()
	{
		SoundPlay.getInstance();
		setupFrameMenu();
		setupPanelMenu();
		tabledata = new ArrayList<Scorename>();

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
			framemenu = new FrameMenu();
		}
		return framemenu;
	}

	/**
	 * 
	 */
	private void setupFrameMenu()
	{
		setTitle("Boulder Dash Menu");
		setResizable(false);
		setSize(800, 600);
		setVisible(true);
	}

	/**
	 * 
	 */
	private void setupPanelMenu()
	{
		panel = new Background(new GridBagLayout());
		putBackground();
		putButtons();

		try
		{
			ScoreBoard.getInstance().readScorenames(tabledata);
		}
		catch (ClassNotFoundException | IOException | URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	 * 
	 */
	private void putBackground()
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
	 * 
	 * @param jpanel
	 */
	private static void refreshPanel(JPanel jpanel)
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
	private static void setupConstraint(GridBagConstraints constraint, int x, int y, int width, int height, double weightx, double weighty, int anchor, int fill)
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
	private static void menu()
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
	private static void topX() throws ClassNotFoundException, FileNotFoundException, IOException, URISyntaxException
	{
		refreshPanel(panel);
		
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		Integer rowx = Integer.parseInt((String) top.getSelectedItem());
		framemenu.showXrow(rowx);

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
	private static void rules()
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
	private static void config()
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
	 * Carga los datos del archivo scoreboard y los pone en una tablemodel
	 * para monstrar solo x filas.
	 * @param x
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private void showXrow(Integer x) throws ClassNotFoundException, FileNotFoundException, IOException, URISyntaxException
	{
		makeTableshow(x);
		ScoreBoard.getInstance().readScorenames(tabledata);
		tablemodel = null;
		tablemodel = new DefaultTableModel(tableshow, tablecolumn);
		table.setModel(tablemodel);
	}
	
	/**
	 * Tabla que se monstrara en el menu top.
	 */
	private void makeTableshow(Integer x)
	{
		Scorename participant;
		tableshow = new Object[x][4];
		if(x > tabledata.size())
		{
			for (int i = 0; i < tabledata.size(); i++)
			{
				participant = tabledata.get(i);
				tableshow[i][0] = participant.getRank();
				tableshow[i][1] = participant.getName();
				tableshow[i][2] = participant.getPoints();
				tableshow[i][3] = participant.getTime();
			}
		}
		else
		{
			for (int i = 0; i < x; i++)
			{
				participant = tabledata.get(i);
				tableshow[i][0] = participant.getRank();
				tableshow[i][1] = participant.getName();
				tableshow[i][2] = participant.getPoints();
				tableshow[i][3] = participant.getTime();
			}
		}
	}
	
	/**
	 * Agrega un scorename en la lista y pone los datos
	 * de la lista en un archivo scoreboard.
	 * @param name
	 * @param score
	 * @param time
	 */
	public void addNameTable(Scorename scorename)
	{
		tabledata.add(scorename);
		try
		{
			ScoreBoard.getInstance().writeScorenames(tabledata);
		}
		catch (ClassNotFoundException | IOException | URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args)
	{
		FrameMenu runFrameMenu = FrameMenu.getInstance();
		runFrameMenu.setVisible(true);

	}

}
