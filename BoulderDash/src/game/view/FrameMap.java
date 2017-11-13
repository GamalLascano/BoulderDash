package game.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import game.model.map.MapInstance;
import game.model.map.MapVisual;
import game.model.map.bdlevel.BDLevelReader;
import game.view.FrameMap;
import game.model.map.bdlevel.*;

public class FrameMap extends JFrame
{

	private static FrameMap theFrame = new FrameMap();
	private static final long serialVersionUID = 1L;

	public FrameMap()
	{
		setTitle("test");
		setResizable(false);
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void remove()
	{
		theFrame.removeAll();
	}

	public static void draw()
	{
		JLabel cellLabel[][] = new JLabel[MapInstance.getLevelReader().getWIDTH()][MapInstance.getLevelReader().getHEIGHT()];

		theFrame.setLayout(new GridLayout(MapInstance.getLevelReader().getHEIGHT(),MapInstance.getLevelReader().getWIDTH()));
		
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
					default:
						break;
				}
				cellLabel[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
				cellLabel[x][y].setAlignmentX(CENTER_ALIGNMENT);
				cellLabel[x][y].setAlignmentY(CENTER_ALIGNMENT);
				cellLabel[x][y].setForeground(Color.white);
				cellLabel[x][y].setOpaque(true);
				theFrame.add(cellLabel[x][y]);
			}
		}
		theFrame.setVisible(true);
	}

}
