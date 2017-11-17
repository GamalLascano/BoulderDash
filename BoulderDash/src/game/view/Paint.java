package game.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Paint extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage empty;
	BufferedImage dirt;
	BufferedImage boulder;
	BufferedImage diamond;
	BufferedImage steel;
	BufferedImage wall;
	BufferedImage firefly;
	BufferedImage butterfly;
	BufferedImage magic;
	BufferedImage amoeba;
	BufferedImage rockford;
	BufferedImage exit;
	
	@Override
	public void paintComponents(Graphics graphic)
	{
		int x = 0,y = 0;
		try
		{
			empty = ImageIO.read(this.getClass().getResource("empty.jpeg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(empty, x, y, null);
		try
		{
			dirt = ImageIO.read(this.getClass().getResource("dirt.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(dirt, x, y, null);
		try
		{
			boulder = ImageIO.read(this.getClass().getResource("boulder.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(boulder, x, y, null);
		try
		{
			diamond = ImageIO.read(this.getClass().getResource("diamond.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(diamond, x, y, null);
		try
		{
			steel = ImageIO.read(this.getClass().getResource("steel.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(steel, x, y, null);
		try
		{
			wall = ImageIO.read(this.getClass().getResource("wall.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(wall, x, y, null);
		try
		{
			firefly = ImageIO.read(this.getClass().getResource("firefly.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(firefly, x, y, null);
		try
		{
			butterfly = ImageIO.read(this.getClass().getResource("butterfly.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(butterfly, x, y, null);
		try
		{
			magic = ImageIO.read(this.getClass().getResource("magic.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(magic, x, y, null);
		try
		{
			amoeba = ImageIO.read(this.getClass().getResource("amoeba.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(amoeba, x, y, null);
		try
		{
			rockford = ImageIO.read(this.getClass().getResource("rockford.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(rockford, x, y, null);
		try
		{
			exit = ImageIO.read(this.getClass().getResource("exit.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphic.drawImage(exit, x, y, null);
		
		super.paintComponents(graphic);
	}
}
