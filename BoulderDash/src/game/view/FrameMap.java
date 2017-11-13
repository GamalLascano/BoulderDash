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
		BDLevelReader levelFrame = new BDLevelReader();

		theFrame.setLayout(new GridLayout(levelFrame.getHEIGHT(),levelFrame.getWIDTH()));
		
		for (int y = 0; y < levelFrame.getHEIGHT(); y++)
		{
			for (int x = 0; x < levelFrame.getWIDTH(); x++)
			{
				JLabel cellLabel = new JLabel(MapVisual.getChar(x, y).toString());
				switch(cellLabel.getText().charAt(0))
				{
					case 'D':
						cellLabel.setBackground(Color.GRAY);
						break;
					case '_':
						cellLabel.setBackground(Color.LIGHT_GRAY);
						break;
					case 'W':
						cellLabel.setBackground(Color.BLUE);
						break;
					case 'O':
						cellLabel.setBackground(Color.ORANGE);
						break;
					case 'X':
						cellLabel.setBackground(Color.CYAN);
						break;
					case 'T':
						cellLabel.setBackground(Color.BLACK);
						break;
					default:
						break;
				}
				cellLabel.setBorder(BorderFactory.createLineBorder(Color.black));
				cellLabel.setAlignmentX(CENTER_ALIGNMENT);
				cellLabel.setAlignmentY(CENTER_ALIGNMENT);
				cellLabel.setForeground(Color.white);
				cellLabel.setOpaque(true);
				theFrame.add(cellLabel);
			}
		}
		theFrame.setVisible(true);
	}

}
