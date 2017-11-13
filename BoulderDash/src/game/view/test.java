package game.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import game.model.map.MapInstance;
import game.model.map.MapVisual;
import game.model.map.bdlevel.BDLevelReader;
import game.view.test;
import game.model.map.bdlevel.*;

public class test extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public test()
	{
		setTitle("test");
		setResizable(false);
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args)
	{
		BDLevelReader levelFrame = new BDLevelReader();
		test prueba = new test();
		try
		{
			levelFrame.readLevels("levels.xml");
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		try
		{
			levelFrame.setCurrentLevel(1);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prueba.setLayout(new GridLayout(levelFrame.getHEIGHT(),levelFrame.getWIDTH()));
		MapInstance.getInstance();
		// el mapa se empieza con el frame del nivel actual
		MapInstance.start(levelFrame);
		// y luego se arma
		MapInstance.buildMap(levelFrame);
		MapVisual.getInstance().start(levelFrame);
		MapVisual.drawMap();
		for (int y = 0; y < levelFrame.getHEIGHT(); y++)
		{

			for (int x = 0; x < levelFrame.getWIDTH(); x++)
			{
				JLabel cellLabel = new JLabel(MapVisual.getChar(x, y).toString());
				cellLabel.setBorder(BorderFactory.createLineBorder(Color.black));
				cellLabel.setAlignmentX(CENTER_ALIGNMENT);
				cellLabel.setAlignmentY(CENTER_ALIGNMENT);
				prueba.add(cellLabel);
			}
		}
		prueba.setVisible(true);
	}

}
