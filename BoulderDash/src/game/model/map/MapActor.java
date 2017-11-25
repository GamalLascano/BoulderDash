package game.model.map;

import game.model.Position;
import game.model.actor.Actor;
import game.model.actor.Rockford;

/**
 * 
 *
 */
public class MapActor extends Map
{
	private static MapActor mapactor;
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
		if (mapactor == null)
		{
			mapactor = new MapActor();
		}
		return mapactor;
	}

	/**
	 * 
	 */
	public void start()
	{
		matrix = new Actor[MapInstance.getLevelReader().getWIDTH()][MapInstance.getLevelReader().getHEIGHT()];
		fill();
	}

	/**
	 * 
	 * @param pos
	 * @return
	 */
	public static Actor getActor(Position pos)
	{
		if (MapInstance.getLevelReader().getWIDTH() >= pos.getX() && 0 <= pos.getX() && MapInstance.getLevelReader().getHEIGHT() >= pos.getY() && 0 <= pos.getY())
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
		if (MapInstance.getLevelReader().getWIDTH() >= x && 0 <= x && MapInstance.getLevelReader().getHEIGHT() >= y && 0 <= y)
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
		if (MapInstance.getLevelReader().getWIDTH() >= x && 0 <= x && MapInstance.getLevelReader().getHEIGHT() >= y && 0 <= y)
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
		if (MapInstance.getLevelReader().getWIDTH() >= act.getPosition().getX() && 0 <= act.getPosition().getX()
				&& MapInstance.getLevelReader().getHEIGHT() >= act.getPosition().getY() && 0 <= act.getPosition().getY())
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
		if (MapInstance.getLevelReader().getWIDTH() >= pos.getX() && 0 <= pos.getX() && MapInstance.getLevelReader().getHEIGHT() >= pos.getY() && 0 <= pos.getY())
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
		for (int x = 0; x < MapInstance.getLevelReader().getWIDTH(); x++)
			for (int y = 0; y < MapInstance.getLevelReader().getHEIGHT(); y++)
			{
				matrix[x][y] = null;
			}
	}

}
