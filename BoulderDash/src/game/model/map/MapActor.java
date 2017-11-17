package game.model.map;

import game.model.Position;
import game.model.actor.*;
import game.model.map.bdlevel.BDLevelReader;

/**
 * 
 *
 */
public class MapActor extends Map
{
	private static MapActor singleton;
	private static BDLevelReader level;
	private static Actor[][] matrix;

	/**
	 * 
	 */
	private MapActor()
	{
		matrix = null;
	}

	/**
	 * 
	 * @return
	 */
	public static MapActor getInstance()
	{
		if (singleton == null)
		{
			singleton = new MapActor();
		}
		return singleton;
	}

	/**
	 * 
	 */
	public void start(BDLevelReader levels)
	{
		level = levels;
		matrix = new Actor[level.getWIDTH()][level.getHEIGHT()];
		fill();
	}

	/**
	 * 
	 * @param pos
	 * @return
	 */
	public static Actor getActor(Position pos)
	{
		if (level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY())
		{
			return matrix[pos.getX()][pos.getY()];
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
	public static Actor getActor(Integer x, Integer y)
	{
		if (level.getWIDTH() >= x && 0 <= x && level.getHEIGHT() >= y && 0 <= y)
		{
			return matrix[x][y];
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
	public static Rockford getRockford(Integer x, Integer y)
	{
		if (level.getWIDTH() >= x && 0 <= x && level.getHEIGHT() >= y && 0 <= y)
		{
			if (matrix[x][y] != null)
			{
				if (matrix[x][y].isRockford())
				{
					return ((Rockford) matrix[x][y]);
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
		else
		{
			return null;
		}
	}

	/**
	 * 
	 * @param act
	 * @return
	 */
	public static boolean setActor(Actor act)
	{
		if (level.getWIDTH() >= act.getPosition().getX() && 0 <= act.getPosition().getX()
				&& level.getHEIGHT() >= act.getPosition().getY() && 0 <= act.getPosition().getY())
		{
			matrix[act.getPosition().getX()][act.getPosition().getY()] = act;
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
	public static boolean removeActor(Position pos)
	{
		if (level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY())
		{
			matrix[pos.getX()][pos.getY()] = null;
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
		for (int x = 0; x < level.getWIDTH(); x++)
			for (int y = 0; y < level.getHEIGHT(); y++)
			{
				matrix[x][y] = null;
			}
	}

}
