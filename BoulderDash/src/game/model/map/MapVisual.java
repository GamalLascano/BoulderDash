package game.model.map;

import game.model.Position;
import game.model.SpriteChar;
import game.model.actor.Rockford;
import game.model.cell.Cell;
import game.model.item.Empty;
import game.model.map.bdlevel.BDLevelReader;

/**
 * Esta clase es la encargada de agarrar los objetos y imprimirlos en pantalla
 *
 */
public class MapVisual
{
	private static MapVisual singleton;
	private static SpriteChar[][] map;
	private static BDLevelReader level;

	/**
	 * 
	 */
	private MapVisual()
	{
		map = null;
	}

	/**
	 * 
	 * @return
	 */
	public static MapVisual getInstance()
	{
		if (singleton == null)
		{
			singleton = new MapVisual();
		}
		return singleton;
	}

	/**
	 * 
	 * @param levels
	 */
	public void start(BDLevelReader levels)
	{
		level = levels;
		map = new SpriteChar[level.getWIDTH()][level.getHEIGHT()];
	}

	/**
	 * 
	 */
	public static void drawMap()
	{
		Position pos = new Position(0, 0);
		int y;
		int x;
		for (y = 0; y < level.getHEIGHT(); y++)
		{
			for (x = 0; x < level.getWIDTH(); x++)
			{
				pos.setXY(x, y);
				if (MapActor.getActor(pos) != null)
				{
					map[x][y] = MapActor.getActor(pos).getSpritechar();
				}
				else if (MapItem.getItem(pos) instanceof Empty == false)
				{
					map[x][y] = MapItem.getItem(pos).getSpritechar();
				}
				else if (MapCell.getCell(pos) instanceof Cell)
				{
					map[x][y] = MapCell.getCell(pos).getSpritechar();
				}
			}
		}
	}

	/**
	 * 
	 */
	public static void imprimirMapa()
	{
		System.out.println("..............................................................");

		for (int y = 0; y < level.getHEIGHT(); y++)
		{
			for (int x = 0; x < level.getWIDTH(); x++)
			{
				System.out.print(map[x][y]);
				System.out.print(" ");
			}
			System.out.println();

		}

		Rockford player = Rockford.getInstance();
		if (player != null)
		{
			System.out.println("Rockford Pos: " + player.getPosition().getX() + "," + player.getPosition().getY());
			System.out.println("Rockford Diamantes: " + player.getDiamonds());
		}
		else
		{
			System.out.println("Rockford muerto");
		}
		System.out.println("..............................................................");

	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static SpriteChar getChar(int x, int y)
	{
		return map[x][y];
	}

}
