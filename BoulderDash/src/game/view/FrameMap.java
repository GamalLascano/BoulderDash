package game.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.model.CurrentDirection;
import game.model.actor.Rockford;
import game.model.map.MapInstance;

public class FrameMap extends JFrame implements KeyListener
{
	private static final long serialVersionUID = 1L;
	private static final int CELLSIZE = 15;
	private static JPanel panelmap = new PanelMap();
	private static JPanel paneltop = new JPanel();
	private static FrameMap framemap;

	// panel
	private static JButton buttontop[][] = new JButton[1][4];

	private FrameMap()
	{
		setLocationRelativeTo(null);
		setTitle("Boulder Dash");
		setResizable(false);
		setSize(900, 600);
		addKeyListener(this);
		setLayout(new GridLayout(0,1,1,1));
		panelmap.setLayout(
				new GridLayout(MapInstance.getLevelReader().getHEIGHT(), MapInstance.getLevelReader().getWIDTH()));
		paneltop.setLayout(new FlowLayout());
		for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 1; x++)
			{
				buttontop[x][y] = new JButton("0");
				paneltop.add(buttontop[x][y]);
			}
		}
		add(paneltop);
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
	
	public static void revalidateFrame()
	{
		framemap.revalidate();
	}
	
	public static void repaintFrame()
	{
		framemap.repaint();
	}

	public static void start()
	{
		FrameMap.getInstance();
	}
	
	public static int getCellsize()
	{
		return CELLSIZE;
	}

	public static JPanel getPanelmap()
	{
		return panelmap;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		final Rockford player = Rockford.getInstance();
		
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
		{
			player.move(CurrentDirection.UP);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			player.move(CurrentDirection.RIGHT);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player.move(CurrentDirection.DOWN);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			player.move(CurrentDirection.LEFT);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
