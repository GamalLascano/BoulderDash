package game.view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.model.CurrentDirection;
import game.model.actor.Rockford;
import game.model.map.MapInstance;
import game.model.map.MapVisual;

public class FrameMap extends JFrame
{
	final static int CELLSIZE = 15;
	private static JPanel panelmap = new JPanel();
	private static JPanel paneltop = new JPanel();
	private static final long serialVersionUID = 1L;
	private static FrameMap framemap;

	// panelmap
	private static Paint paint = new Paint();

	// panel
	private static JButton buttontop[][] = new JButton[1][4];

	private FrameMap()
	{
		setLocationRelativeTo(null);
		setTitle("Boulder Dash");
		setResizable(false);
		setSize(900, 600);
		panelmap.setLayout(
				new GridLayout(MapInstance.getLevelReader().getHEIGHT(), MapInstance.getLevelReader().getWIDTH()));
		paneltop.setLayout(new GridLayout(4, 1, 2, 2));
		for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 1; x++)
			{
				buttontop[x][y] = new JButton("0");
				paneltop.add(buttontop[x][y]);
			}
		}
		add(panelmap);

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

	public static void putPanelTop()
	{

	}

	public static void remove()
	{
		panelmap.removeAll();
	}

	public static void disposeFrame()
	{
		framemap.dispose();
	}

	public static void returnMove()
	{
		final Rockford player = Rockford.getInstance();
		framemap.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
				{
					player.move(CurrentDirection.UP);
				}
			}
		});

		framemap.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					player.move(CurrentDirection.RIGHT);
				}

			}
		});

		framemap.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					player.move(CurrentDirection.DOWN);
				}

			}
		});

		framemap.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					player.move(CurrentDirection.LEFT);
				}

			}
		});

		framemap.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_E)
				{
					;
				}

			}
		});

		framemap.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					player.isInExit();
				}
			}
		});

	}

	public static void draw()
	{
		Graphics graphic = panelmap.getGraphics();
		framemap.repaint();
		framemap.revalidate();
		
		for (int y = 0; y < MapInstance.getLevelReader().getHEIGHT() * CELLSIZE; y += CELLSIZE)
		{
			for (int x = 0; x < MapInstance.getLevelReader().getWIDTH() * CELLSIZE; x += CELLSIZE)
			{

				String cellChar = MapVisual.getChar(x / CELLSIZE, y / CELLSIZE).toString();
				
				switch (cellChar.charAt(0))
				{
					case 'D':
						graphic.drawImage(paint.getDirt(), x, y, null);
						break;
					case '_':
						graphic.drawImage(paint.getEmpty(), x, y, null);
						break;
					case 'W':
						graphic.drawImage(paint.getWall(), x, y, null);
						break;
					case 'F':
						graphic.drawImage(paint.getFirefly(), x, y, null);
						break;
					case 'B':
						graphic.drawImage(paint.getButterfly(), x, y, null);
						break;
					case 'A':
						graphic.drawImage(paint.getAmoeba(), x, y, null);
						break;
					case 'O':
						graphic.drawImage(paint.getBoulder(), x, y, null);
						break;
					case 'X':
						graphic.drawImage(paint.getDiamond(), x, y, null);
						break;
					case 'T':
						graphic.drawImage(paint.getSteel(), x, y, null);
						break;
					case 'R':
						graphic.drawImage(paint.getRockford(), x, y, null);
						break;
					case 'E':
						graphic.drawImage(paint.getSteel(), x, y, null);
						break;
					case 'e':
						graphic.drawImage(paint.getExit(), x, y, null);
						break;
					default:
						break;
				}
			}
		}
		FrameMap.returnMove();
	}

	public static void start()
	{
		FrameMap.getInstance();
	}

}
