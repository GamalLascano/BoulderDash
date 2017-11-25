package game.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Pageable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.model.CurrentDirection;
import game.model.actor.Rockford;
import game.model.map.MapInstance;

public class FrameMap extends JFrame implements KeyListener
{
	//
	private static final long serialVersionUID = 1L;
	private static final int CELLSIZE = 16;
	private static JPanel panelmap = new PanelMap();
	private static JPanel paneltop = new JPanel();
	private static FrameMap framemap;

	// panel
	private static JLabel labeltop[][] = new JLabel[1][9];

	private FrameMap()
	{
		GridBagConstraints c = new GridBagConstraints();

		setLocationRelativeTo(null);
		setTitle("Boulder Dash");
		setResizable(false);
		setSize(643, 433);
		getContentPane().setBackground(Color.BLACK);
		addKeyListener(this);
		setLayout(new GridBagLayout());
		panelmap.setLayout(
				new GridLayout(MapInstance.getLevelReader().getHEIGHT(), MapInstance.getLevelReader().getWIDTH()));
		buildPaneltop();
		add(paneltop, c);
		
		c.weighty = 10;
		c.weightx = 10;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		panelmap.setBackground(Color.BLACK);
		add(panelmap, c);

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

	public static void buildPaneltop()
	{
		GridBagConstraints c = new GridBagConstraints();
		
		c.weighty = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		paneltop.setBackground(Color.BLACK);
		
		paneltop.setLayout(new FlowLayout());
		paneltop.setSize(300, 100);
		
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
				MapInstance.clear();
				Integer level = MapInstance.getSelectedLevel() - 1;
				MapInstance.setSelectedLevel(level);
				MapInstance.readLevel();
				MapInstance.buildMap();
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
				MapInstance.clear();
				Integer level = MapInstance.getSelectedLevel() + 1;
				MapInstance.setSelectedLevel(level);
				MapInstance.readLevel();
				MapInstance.buildMap();
				labeltop[0][1].setText(level.toString());
			}
		});
		paneltop.add(labeltop[0][2]);
		
		labeltop[0][3] = new JLabel(Rockford.getInstance().getDiamonds().toString());
		labeltop[0][3].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][3]);
		
		labeltop[0][4] = new JLabel(":");
		labeltop[0][4].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][4]);
		
		Integer diamondsneeded = MapInstance.getLevelReader().getDiamondsNeeded();
		labeltop[0][5] = new JLabel(diamondsneeded.toString());
		labeltop[0][5].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][5]);
		
		labeltop[0][6] = new JLabel("TIMER");
		labeltop[0][6].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][6]);
		
		labeltop[0][7] = new JLabel(Rockford.getInstance().getLives().toString());
		labeltop[0][7].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][7]);
		
		labeltop[0][8] = new JLabel(Rockford.getInstance().getScore().toString());
		labeltop[0][8].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][6]);
		
	}
	
	public static void refreshPaneltop()
	{	
		labeltop[0][3] = new JLabel(Rockford.getInstance().getDiamonds().toString());
		Integer diamondsneeded = MapInstance.getLevelReader().getDiamondsNeeded();
		labeltop[0][5] = new JLabel(diamondsneeded.toString());
		labeltop[0][6] = new JLabel("TIMER");
		labeltop[0][7] = new JLabel(Rockford.getInstance().getLives().toString());
		labeltop[0][8] = new JLabel(Rockford.getInstance().getScore().toString());
	}

	public static void remove()
	{
		panelmap.removeAll();
	}

	public static void disposeFrame()
	{
		framemap.dispose();
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
