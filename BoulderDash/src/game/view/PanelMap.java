package game.view;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.model.CurrentDirection;
import game.model.actor.Rockford;
import game.model.map.MapInstance;
import game.model.map.MapVisual;

public class PanelMap extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage empty;
	private BufferedImage dirt;
	private BufferedImage boulder;
	private BufferedImage diamond;
	private BufferedImage steel;
	private BufferedImage wall;
	private BufferedImage firefly;
	private BufferedImage butterfly;
	private BufferedImage magic;
	private BufferedImage amoeba;
	private BufferedImage rockford;
	private BufferedImage exit;

	public PanelMap()
	{
		try
		{
			empty = ImageIO.read(this.getClass().getResource("empty.jpeg"));
			dirt = ImageIO.read(this.getClass().getResource("dirt.gif"));
			boulder = ImageIO.read(this.getClass().getResource("boulder.gif"));
			diamond = ImageIO.read(this.getClass().getResource("diamond.gif"));
			steel = ImageIO.read(this.getClass().getResource("steel.gif"));
			wall = ImageIO.read(this.getClass().getResource("wall.gif"));
			firefly = ImageIO.read(this.getClass().getResource("firefly.gif"));
			butterfly = ImageIO.read(this.getClass().getResource("butterfly.gif"));
			magic = ImageIO.read(this.getClass().getResource("magic.gif"));
			amoeba = ImageIO.read(this.getClass().getResource("amoeba.gif"));
			rockford = ImageIO.read(this.getClass().getResource("rockford.gif"));
			exit = ImageIO.read(this.getClass().getResource("exit.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics graphic)
	{
		super.paintComponent(graphic);
		for (int y = 0; y < MapInstance.getLevelReader().getHEIGHT() * FrameMap.getCellsize(); y += FrameMap.getCellsize())
		{
			for (int x = 0; x < MapInstance.getLevelReader().getWIDTH() * FrameMap.getCellsize(); x += FrameMap.getCellsize())
			{

				String cellChar = MapVisual.getChar(x / FrameMap.getCellsize(), y / FrameMap.getCellsize()).toString();

				switch (cellChar.charAt(0))//
				{
					case 'D':
						graphic.drawImage(this.getDirt(), x, y, null);
						break;
					case '_':
						graphic.drawImage(this.getEmpty(), x, y, null);
						break;
					case 'W':
						graphic.drawImage(this.getWall(), x, y, null);
						break;
					case 'F':
						graphic.drawImage(this.getFirefly(), x, y, null);
						break;
					case 'B':
						graphic.drawImage(this.getButterfly(), x, y, null);
						break;
					case 'A':
						graphic.drawImage(this.getAmoeba(), x, y, null);
						break;
					case 'O':
						graphic.drawImage(this.getBoulder(), x, y, null);
						break;
					case 'X':
						graphic.drawImage(this.getDiamond(), x, y, null);
						break;
					case 'T':
						graphic.drawImage(this.getSteel(), x, y, null);
						break;
					case 'R':
						graphic.drawImage(this.getRockford(), x, y, null);
						break;
					case 'E':
						graphic.drawImage(this.getSteel(), x, y, null);
						break;
					case 'e':
						graphic.drawImage(this.getExit(), x, y, null);
						break;
					default:
						break;
				}
			}
		}
	}
	


	public BufferedImage getEmpty()
	{
		return empty;
	}

	public BufferedImage getDirt()
	{
		return dirt;
	}

	public BufferedImage getBoulder()
	{
		return boulder;
	}

	public BufferedImage getDiamond()
	{
		return diamond;
	}

	public BufferedImage getSteel()
	{
		return steel;
	}

	public BufferedImage getWall()
	{
		return wall;
	}

	public BufferedImage getFirefly()
	{
		return firefly;
	}

	public BufferedImage getButterfly()
	{
		return butterfly;
	}

	public BufferedImage getMagic()
	{
		return magic;
	}

	public BufferedImage getAmoeba()
	{
		return amoeba;
	}

	public BufferedImage getRockford()
	{
		return rockford;
	}

	public BufferedImage getExit()
	{
		return exit;
	}

}
