package game.model.map;

import game.model.Position;
import game.model.item.Diamond;
import game.model.item.Empty;
import game.model.item.Item;
import game.model.item.Rock;
import game.model.map.bdlevel.BDLevelReader;

/**
 * 
 *
 */
public class MapItem
{
	private static MapItem singleton;
	private static BDLevelReader level;
	private static Item[][] matrix;

	/**
	 * 
	 */
	private MapItem()
	{
		matrix = null;
	}

	/**
	 * 
	 * @return
	 */
	public static MapItem getInstance()
	{
		if (singleton == null)
		{
			singleton = new MapItem();
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
		matrix = new Item[level.getWIDTH()][level.getHEIGHT()];
		fill();
	}

	/**
	 * 
	 * @return
	 */
	public static Item[][] getMatrix()
	{
		return matrix;
	}

	/**
	 * 
	 * @param pos
	 * @return
	 */
	public static Item getItem(Position pos)
	{
		return matrix[pos.getX()][pos.getY()];
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Item getItem(Integer x, Integer y)
	{
		return matrix[x][y];
	}

	/**
	 * 
	 * @param pos
	 * @return
	 */
	public static Diamond getDiamond(Position pos)
	{
		if (level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY())
		{
			if (matrix[pos.getX()][pos.getY()].isDiamond())
			{
				return ((Diamond) matrix[pos.getX()][pos.getY()]);
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Diamond getDiamond(Integer x, Integer y)
	{
		if (level.getWIDTH() >= x && 0 <= x && level.getHEIGHT() >= y && 0 <= y)
		{
			if (matrix[x][y].isDiamond())
			{
				return ((Diamond) matrix[x][y]);
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Rock getRock(Integer x, Integer y)
	{
		if (level.getWIDTH() >= x && 0 <= x && level.getHEIGHT() >= y && 0 <= y)
		{
			if (matrix[x][y].isRock())
			{
				return ((Rock) matrix[x][y]);
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * 
	 * @param ite
	 * @return
	 */
	public static boolean setItem(Item ite)
	{
		if (level.getWIDTH() >= ite.getPosition().getX() && 0 <= ite.getPosition().getX()
				&& level.getHEIGHT() >= ite.getPosition().getY() && 0 <= ite.getPosition().getY())
		{
			matrix[ite.getPosition().getX()][ite.getPosition().getY()] = ite;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @param pos
	 * @return
	 */
	public static boolean removeItem(Position pos)
	{
		if (level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY())
		{
			matrix[pos.getX()][pos.getY()] = new Empty(pos);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 */
	public void fill()
	{
		Position pos = new Position(0, 0);
		for (int x = 0; x < level.getWIDTH(); x++)
			for (int y = 0; y < level.getHEIGHT(); y++)
			{
				pos.setXY(x, y);
				matrix[x][y] = new Empty(pos);
			}
	}

}
