package game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.model.Direction;
import game.model.element.entity.actor.Rockford;
import game.model.map.MapInstance;
import game.view.sound.SoundPlay;

/**
 * Panel del juego, donde aparece el mapa.
 *
 */
public class FrameMap extends JFrame implements KeyListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -678005524164695823L;
	private static int CELLSIZEX = 16;
	private static int CELLSIZEY = 16;
	private static PanelMap panelmap = new PanelMap();
	private static JPanel paneltop = new JPanel();
	private static FrameMap framemap;
	private static boolean up, down, right, left, fullscr;
	private static Dimension pastScreenSize = new Dimension(800, 600);
	// panel
	private static JLabel labeltop[][] = new JLabel[1][9];

	private FrameMap()
	{
		GridBagConstraints c = new GridBagConstraints();
		setTitle("Boulder Dash");
		setResizable(false);
		setSize(800, 600);
		getContentPane().setBackground(Color.BLACK);
		addKeyListener(this);
		setLayout(new GridBagLayout());
		panelmap.setLayout(new GridLayout(MapInstance.getLevelReader().getHEIGHT(), MapInstance.getLevelReader().getWIDTH()));
		buildPaneltop();
		add(paneltop, c);

		c.weighty = 10;
		c.weightx = 10;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		panelmap.setBackground(Color.BLACK);
		add(panelmap, c);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static FrameMap getInstance()
	{
		if (framemap == null)
		{
			framemap = new FrameMap();
		}
		return framemap;
	}

	/**
	 * Refresca el mapa.
	 */
	public static void refresh()
	{
		FrameMap.updateMove();
		FrameMap.refreshPaneltop();
		FrameMap.panelmap.repaint();
	}

	/**
	 * Refresca el panel superior, el score, diamantes obtenidos y otras
	 * informacion del jugador.
	 */
	public static void refreshPaneltop()
	{
		labeltop[0][1].setText(MapInstance.getSelectedLevel().toString());
		labeltop[0][3].setText(Rockford.getRockford().getDiamonds().toString());
		Integer diamondsneeded = MapInstance.getLevelReader().getDiamondsNeeded();
		labeltop[0][5].setText(diamondsneeded.toString());
		Integer timer = MapInstance.getTimer().intValue();
		labeltop[0][6].setText(timer.toString());
		labeltop[0][7].setText(Rockford.getRockford().getLives().toString());
		labeltop[0][8].setText(Rockford.getRockford().getScore().toString());
	}

	/**
	 * Se usa en el gamethread. Remueve todo los elementos del panel.
	 */
	public static void remove()
	{
		panelmap.removeAll();
	}

	/**
	 * Se usa en el gamethread. Hace dispose del frame.
	 */
	public static void disposeFrame()
	{
		framemap.dispose();
	}

	/**
	 * Inicializa el framemap.
	 */
	public static void start()
	{
		FrameMap.getInstance();
		SoundPlay.getInstance();
		SoundPlay.newgame();
		Rockford.getRockford().reset();
	}

	public static int getCellsizex()
	{
		return CELLSIZEX;
	}

	public static int getCellsizey()
	{
		return CELLSIZEY;
	}

	public static void setCellsize(int x, int y)
	{
		CELLSIZEX = x;
		CELLSIZEY = y;
	}

	public static void setFullscr(boolean enabled)
	{
		fullscr = enabled;
	}

	/**
	 * 
	 */
	public static void fullScr()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pastScreenSize = FrameMenu.getInstance().getSize();
		if (fullscr)
		{
			pastScreenSize = getInstance().getSize();
			getInstance().setSize(screenSize);
			panelmap.cambiarsize();
			getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
			getInstance().dispose();
			getInstance().setUndecorated(true);
			getInstance().pack();
			getInstance().setVisible(true);
			getInstance().setAlwaysOnTop(true);
			getInstance().setLocationRelativeTo(null);

		}
		else
		{
			getInstance().setExtendedState(JFrame.NORMAL);
			getInstance().dispose();
			getInstance().setUndecorated(false);
			getInstance().pack();
			getInstance().setSize(pastScreenSize);
			panelmap.cambiarsize();
			getInstance().setVisible(true);
			getInstance().setAlwaysOnTop(false);
			getInstance().setLocationRelativeTo(null);

		}
	}

	/**
	 * Hace que el jugador se mueva de manera fluida.
	 */
	public static void updateMove()
	{
		Rockford player = Rockford.getRockford();
		if (up)
		{
			player.move(Direction.UP);
		}
		if (down)
		{
			player.move(Direction.DOWN);
		}
		if (left)
		{
			player.move(Direction.LEFT);
		}
		if (right)
		{
			player.move(Direction.RIGHT);
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			Rockford.getRockford().die();
		}

		if (e.getKeyCode() == KeyEvent.VK_PAGE_UP)
		{
			MapInstance.setSelectedLevel(MapInstance.getSelectedLevel() + 1);
			MapInstance.buildSelectedLevel(MapInstance.getSelectedLevel());
		}

		if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN)
		{
			MapInstance.setSelectedLevel(MapInstance.getSelectedLevel() - 1);
			MapInstance.buildSelectedLevel(MapInstance.getSelectedLevel());
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	public static void setPanelTopSize(int size)
	{
		paneltop.setPreferredSize(new Dimension(643, size));
	}

	/**
	 * Construye el panel superior.
	 */
	public static void buildPaneltop()
	{
		GridBagConstraints c = new GridBagConstraints();

		c.weighty = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		paneltop.setBackground(Color.BLACK);

		paneltop.setLayout(new FlowLayout());
		paneltop.setPreferredSize(new Dimension(643, 22));

		labeltop[0][0] = new JLabel("<");
		labeltop[0][0].setForeground(Color.WHITE);
		labeltop[0][0].addMouseListener(new MouseListener()
		{

			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				Integer level = MapInstance.getSelectedLevel() - 1;
				MapInstance.buildSelectedLevel(level);
				labeltop[0][1].setText(level.toString());
			}
		});
		paneltop.add(labeltop[0][0]);

		labeltop[0][1] = new JLabel(MapInstance.getSelectedLevel().toString());
		labeltop[0][1].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][1]);

		labeltop[0][2] = new JLabel(">");
		labeltop[0][2].setForeground(Color.WHITE);
		labeltop[0][2].addMouseListener(new MouseListener()
		{

			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				Integer level = MapInstance.getSelectedLevel() + 1;
				MapInstance.buildSelectedLevel(level);
				labeltop[0][1].setText(level.toString());
			}
		});
		paneltop.add(labeltop[0][2]);

		labeltop[0][3] = new JLabel(Rockford.getRockford().getDiamonds().toString());
		labeltop[0][3].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][3]);

		labeltop[0][4] = new JLabel(":");
		labeltop[0][4].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][4]);

		Integer diamondsneeded = MapInstance.getDiamondsneeded();
		labeltop[0][5] = new JLabel(diamondsneeded.toString());
		labeltop[0][5].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][5]);

		Integer timer = MapInstance.getTimer().intValue();
		labeltop[0][6] = new JLabel(timer.toString());
		labeltop[0][6].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][6]);

		labeltop[0][7] = new JLabel(Rockford.getRockford().getLives().toString());
		labeltop[0][7].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][7]);

		labeltop[0][8] = new JLabel(Rockford.getRockford().getScore().toString());
		labeltop[0][8].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][8]);

	}

}
