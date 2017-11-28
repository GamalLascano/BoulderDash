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

public class FrameMenu extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static FrameMenu framemenu;
	private static Background panel;

	// panel variables
	private static GridBagConstraints cons = new GridBagConstraints();
	// menu
	private static String imgFileName = "game/view/wallpaper.jpg";
	private static Image img;
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
		setupFrameMenu();
		setupPanelMenu();
		tabledata = new Object[4][20];
		ScoreBoard.getInstance().loadMatrix(tabledata);

		add(panel);
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		pack();
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
			catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (URISyntaxException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	public static void refreshPanel(JPanel jpanel)
	{
		jpanel.removeAll();
		removeListeners(button);
		jpanel.revalidate();
		jpanel.repaint();
	}

	public static void setupConstraint(GridBagConstraints constraint, int x, int y, int width, int height,
			double weightx, double weighty, int anchor, int fill)
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

	public static void menu()
	{
		refreshPanel(panel);

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

		setupConstraint(cons, 0, 0, 1, 1, 0.5, 5, GridBagConstraints.SOUTH, GridBagConstraints.CENTER);
		panel.add(button[0], cons);

		button[1].setText("TOP X");
		button[1].addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
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

		setupConstraint(cons, 1, 0, 1, 1, 0.5, 0.5, GridBagConstraints.SOUTH, GridBagConstraints.CENTER);
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

		setupConstraint(cons, 2, 0, 1, 1, 0.5, 0.5, GridBagConstraints.SOUTH, GridBagConstraints.CENTER);
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

		setupConstraint(cons, 0, 1, 1, 1, 0.5, 0.5, GridBagConstraints.ABOVE_BASELINE, GridBagConstraints.CENTER);
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

		setupConstraint(cons, 2, 1, 1, 1, 0.5, 0.5, GridBagConstraints.ABOVE_BASELINE, GridBagConstraints.CENTER);
		panel.add(button[4], cons);
	}

	public static void topX() throws ClassNotFoundException, FileNotFoundException, IOException, URISyntaxException
	{
		refreshPanel(panel);

		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		Integer rowx = Integer.parseInt((String)top.getSelectedItem());
		framemenu.showXrow(rowx);

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

		setupConstraint(cons, 0, 0, 1, 1, 1, 0.5, GridBagConstraints.ABOVE_BASELINE, GridBagConstraints.CENTER);
		panel.add(toptitle, cons);

		setupConstraint(cons, 0, 1, 5, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		panel.add(scrollPane, cons);

		setupConstraint(cons, 0, 2, 1, 1, 1, 0.8, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(button[0], cons);

	}

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
				menu();
			}
		});

		setupConstraint(cons, 0, 0, 1, 1, 1, 1, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(instructions, cons);

		setupConstraint(cons, 0, 1, 1, 1, 1, 0.8, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(button[0], cons);
	}

	public static void config()
	{
		refreshPanel(panel);

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

		setupConstraint(cons, 0, 1, 1, 1, 1, 0.5, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(top, cons);

		setupConstraint(cons, 0, 2, 1, 1, 1, 0.5, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(resoluciones, cons);

		setupConstraint(cons, 0, 3, 1, 1, 1, 0.5, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(fullScr, cons);

		setupConstraint(cons, 0, 4, 1, 1, 1, 0.5, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(levelsel, cons);

		setupConstraint(cons, 0, 5, 1, 1, 1, 0.5, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(button[0], cons);
	}

	public void showXrow(Integer x) throws ClassNotFoundException, FileNotFoundException, IOException, URISyntaxException
	{
		ScoreBoard.getInstance().loadMatrix(tabledata);
		tablemodel = new DefaultTableModel(tabledata, tablecolumn);
		for(int i = 0; i < x; i++)
		{
			tablemodel.addRow(tabledata[i]);
		}
		table.setModel(tablemodel);
	}
	
	public void addNameTable(String name, Integer score, Integer time)
	{
		tablemodel.addRow(new Object[] {"999",name, score.toString(), time.toString() });
	}

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException, URISyntaxException
	{
		FrameMenu runFrameMenu = FrameMenu.getInstance();
		runFrameMenu.setVisible(true);
		ScoreBoard.getInstance().saveMatrix(tabledata);
	}

}
