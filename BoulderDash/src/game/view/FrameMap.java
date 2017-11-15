package game.view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import game.model.CurrentDirection;
import game.model.ListOfEntities;
import game.model.actor.Rockford;
import game.model.map.MapInstance;
import game.model.map.MapVisual;
import game.view.FrameMap;

public class FrameMap extends JFrame
{
	private static JPanel panelmap = new JPanel();
	private static JPanel paneltop = new JPanel();
	private static final long serialVersionUID = 1L;
	private static FrameMap framemap;
	
	//panelmap
	private static JLabel cellLabel[][] = new JLabel[MapInstance.getLevelReader().getWIDTH()][MapInstance.getLevelReader().getHEIGHT()];
	
	//panel
	private static JButton buttontop[][] = new JButton[1][4];
	
	private FrameMap()
	{
		setLocationRelativeTo(null);
		setTitle("test");
		setResizable(false);
		setSize(900, 600);
		setVisible(true);
		panelmap.setLayout(new GridLayout(MapInstance.getLevelReader().getHEIGHT(),MapInstance.getLevelReader().getWIDTH()));
		paneltop.setLayout(new GridLayout());
		for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 1; x++)
			{
				buttontop[x][y] = new JButton("0");
				paneltop.add(buttontop[x][y]);
			}
		}
		add(panelmap);
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
		final Rockford player = ListOfEntities.findRockford();
		framemap.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
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
				if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
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
				if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
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
				if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
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
				if(e.getKeyCode() == KeyEvent.VK_E)
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
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					player.isInExit();
				}
			}
		});

	}
	
	public static void draw()
	{
		for (int y = 0; y < MapInstance.getLevelReader().getHEIGHT(); y++)
		{
			for (int x = 0; x < MapInstance.getLevelReader().getWIDTH(); x++)
			{
				cellLabel[x][y] = new JLabel(MapVisual.getChar(x, y).toString());
				switch(cellLabel[x][y].getText().charAt(0))
				{
					case 'D':
						cellLabel[x][y].setBackground(Color.GRAY);
						cellLabel[x][y].setForeground(Color.GRAY);
						break;
					case '_':
						cellLabel[x][y].setBackground(Color.LIGHT_GRAY);
						cellLabel[x][y].setForeground(Color.LIGHT_GRAY);
						break;
					case 'W':
						cellLabel[x][y].setBackground(Color.BLUE);
						cellLabel[x][y].setForeground(Color.BLUE);
						break;
					case 'E':
						cellLabel[x][y].setBackground(Color.YELLOW);
						cellLabel[x][y].setForeground(Color.YELLOW);
						break;
					case 'e':
						cellLabel[x][y].setBackground(Color.GREEN);
						cellLabel[x][y].setForeground(Color.GREEN);
						break;
					case 'F':
						cellLabel[x][y].setBackground(Color.RED);
						cellLabel[x][y].setForeground(Color.YELLOW);
						break;
					case 'B':
						cellLabel[x][y].setBackground(Color.RED);
						cellLabel[x][y].setForeground(Color.YELLOW);
						break;
					case 'A':
						cellLabel[x][y].setBackground(Color.MAGENTA);
						cellLabel[x][y].setForeground(Color.YELLOW);
						break;
					case 'O':
						cellLabel[x][y].setBackground(Color.ORANGE);
						cellLabel[x][y].setForeground(Color.RED);
						break;
					case 'X':
						cellLabel[x][y].setBackground(Color.CYAN);
						cellLabel[x][y].setForeground(Color.WHITE);
						break;
					case 'T':
						cellLabel[x][y].setBackground(Color.BLACK);
						cellLabel[x][y].setForeground(Color.BLACK);
						break;
					case 'R':
						cellLabel[x][y].setBackground(Color.LIGHT_GRAY);
						cellLabel[x][y].setForeground(Color.RED);
						break;
					default:
						break;
				}
				cellLabel[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
				cellLabel[x][y].setAlignmentX(CENTER_ALIGNMENT);
				cellLabel[x][y].setAlignmentY(CENTER_ALIGNMENT);
				cellLabel[x][y].setOpaque(true);
				panelmap.add(cellLabel[x][y]);
			}
		}
		framemap.setVisible(true);
		panelmap.setVisible(true);
		FrameMap.returnMove();
	}
	
	public static void start()
	{
		FrameMap framemap = FrameMap.getInstance();
		framemap.isEnabled();
	}

}
