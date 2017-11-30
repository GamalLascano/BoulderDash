package game.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.model.map.MapInstance;
import game.model.map.MapVisual;

/**
 * Se occupa de levantar las imagenes para el mapa.
 *
 */
public class PanelMap extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage empty;
	private BufferedImage dirt;
	private BufferedImage boulder;
	private Image diamond;
	private BufferedImage steel;
	private BufferedImage wall;
	private Image firefly;
	private Image butterfly;
	// private Image magic;
	private Image amoeba;
	private Image rockford;
	private Image rockfordleft;
	private Image rockfordright;
	private Image rockfordup;
	private Image rockforddown;
	private Image exit;

	public PanelMap()
	{
		try
		{
			empty = ImageIO.read(this.getClass().getResource("empty.gif"));
			dirt = ImageIO.read(this.getClass().getResource("dirt.gif"));
			boulder = ImageIO.read(this.getClass().getResource("boulder.gif"));
			diamond = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("diamond.gif"));
			steel = ImageIO.read(this.getClass().getResource("steel.gif"));
			wall = ImageIO.read(this.getClass().getResource("wall.gif"));
			firefly = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("firefly.gif"));
			butterfly = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("butterfly.gif"));
			// magic =
			// Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("magic.gif"));
			amoeba = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("amoeba.gif"));
			rockford = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("rockford.gif"));
			rockfordleft = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("rockfordleft.gif"));
			rockfordright = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("rockfordright.gif"));
			rockfordup = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("rockfordup.gif"));
			rockforddown = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("rockforddown.gif"));
			exit = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("exit.gif"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics graphic)
	{
		super.paintComponent(graphic);
		for (int y = 0; y < MapInstance.getLevelReader().getHEIGHT() * FrameMap.getCellsize(); y += FrameMap.getCellsize())
		{
			for (int x = 0; x < MapInstance.getLevelReader().getWIDTH() * FrameMap.getCellsize(); x += FrameMap.getCellsize())
			{

				String cellChar = MapVisual.getChar(x / FrameMap.getCellsize(), y / FrameMap.getCellsize()).toString();
				drawCell(cellChar, x, y, graphic);

			}
		}
	}

	/**
	 * Dibuja una celda
	 * @param s
	 * @param x
	 * @param y
	 * @param graphic
	 */
	public void drawCell(String s, int x, int y, Graphics graphic)
	{
		switch (s.charAt(0))
		{
			case 'D':
				graphic.drawImage(dirt, x, y, null);
				break;
			case '_':
				graphic.drawImage(empty, x, y, null);
				break;
			case 'W':
				graphic.drawImage(wall, x, y, null);
				break;
			case 'w':
				graphic.drawImage(wall, x, y, null);
				break;
			case 'F':
				graphic.drawImage(firefly, x, y, null);
				break;
			case 'B':
				graphic.drawImage(butterfly, x, y, null);
				break;
			case 'A':
				graphic.drawImage(amoeba, x, y, null);
				break;
			case 'O':
				graphic.drawImage(boulder, x, y, null);
				break;
			case 'X':
				graphic.drawImage(diamond, x, y, null);
				break;
			case 'T':
				graphic.drawImage(steel, x, y, null);
				break;
			case 'R':
				graphic.drawImage(rockford, x, y, null);
				break;
			case 'd':
				graphic.drawImage(rockfordleft, x, y, null);
				break;
			case 'b':
				graphic.drawImage(rockfordright, x, y, null);
				break;
			case 'n':
				graphic.drawImage(rockfordup, x, y, null);
				break;
			case 'u':
				graphic.drawImage(rockforddown, x, y, null);
				break;
			case 'E':
				graphic.drawImage(steel, x, y, null);
				break;
			case 'e':
				graphic.drawImage(exit, x, y, null);
				break;
			default:
				break;
		}
	}

}
