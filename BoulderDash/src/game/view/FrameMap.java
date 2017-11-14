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
	private static JPanel panel = new JPanel();;
	private static JLabel cellLabel[][] = new JLabel[MapInstance.getLevelReader().getWIDTH()][MapInstance.getLevelReader().getHEIGHT()];
	private static final long serialVersionUID = 1L;
	private static FrameMap theFrame;

	
	private FrameMap()
	{
		setTitle("test");
		setResizable(false);
		setSize(600, 600);
		setVisible(true);
		panel.setLayout(new GridLayout(MapInstance.getLevelReader().getHEIGHT(),MapInstance.getLevelReader().getWIDTH()));
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static FrameMap getInstance()
	{
		if (theFrame == null)
		{
			theFrame = new FrameMap();
		}
		return theFrame;
	}

	
	public static void remove()
	{
		panel.removeAll();
	}

	public static void move()
	{
		final Rockford player = ListOfEntities.findRockford();
		panel.addKeyListener(new KeyAdapter()
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
		
		panel.addKeyListener(new KeyAdapter()
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
		
		panel.addKeyListener(new KeyAdapter()
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
		
		panel.addKeyListener(new KeyAdapter()
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
		
		panel.addKeyListener(new KeyAdapter()
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
		
		panel.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					player.leaveLevel();
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
						break;
					case '_':
						cellLabel[x][y].setBackground(Color.LIGHT_GRAY);
						break;
					case 'W':
						cellLabel[x][y].setBackground(Color.BLUE);
						break;
					case 'O':
						cellLabel[x][y].setBackground(Color.ORANGE);
						break;
					case 'X':
						cellLabel[x][y].setBackground(Color.CYAN);
						break;
					case 'T':
						cellLabel[x][y].setBackground(Color.BLACK);
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
				cellLabel[x][y].setForeground(Color.white);
				cellLabel[x][y].setOpaque(true);
				panel.add(cellLabel[x][y]);
			}
		}
		theFrame.setVisible(true);
		panel.setVisible(true);
		FrameMap.move();
	}
	
	public static void start()
	{
		FrameMap framemap = FrameMap.getInstance();
		framemap.isEnabled();
	}

}
