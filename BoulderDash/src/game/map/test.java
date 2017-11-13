package game.map;

import java.awt.*;

import javax.swing.*;

import game.map.bdlevel.*;

public class test extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public test()
	{
		setTitle("test");
		setResizable(true);
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args)
	{
		BDLevelReader levelFrame = new BDLevelReader();
		test prueba = new test();
		JTextArea mapa = new JTextArea();
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
		prueba.setLayout(new GridLayout(levelFrame.getWIDTH(),levelFrame.getHEIGHT()));
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
				mapa.setText(MapVisual.getChar(x, y).toString());
				
			}
			texto = texto + "//n";
		}
		texto = texto.replaceAll("//n", System.getProperty("line.separator"));
		mapa.setText(texto);
		prueba.add(mapa);
		prueba.setVisible(true);
	}

}
