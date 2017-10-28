package game.map;

import game.actor.*;
import game.Position;
import game.map.bdlevel.BDLevelReader;

public class MapActor
{
	private static MapActor singleton;
	private static BDLevelReader level;
	private static Actor[][] matrix;

	private MapActor()
	{
		matrix = null;
	}

	// SINGLETON

	public static synchronized MapActor getInstance()
	{
		if (singleton == null)
		{
			singleton = new MapActor();
		}
		return singleton;
	}
	
	// INICIALIZACION
	
	public void start(BDLevelReader levels)
	{
		level = levels;
		matrix = new Actor[level.getWIDTH()][level.getHEIGHT()];
		fill();
	}

	// GETTERS

	public Actor getActor(Position pos)
	{
		if ( level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY() )
		{
			return matrix[pos.getX()][pos.getY()];
		}
		else
		{
			return null;
		}
	}
	
	public Actor getActor(Integer x, Integer y)
	{
		if ( level.getWIDTH() >= x && 0 <= x && level.getHEIGHT() >= y && 0 <= y )
		{
			return matrix[x][y];
		}
		else
		{
			return null;
		}
	}

	// SETTERS

	/**
	 * 
	 * @param pos
	 * @param act
	 * @return : true si se agrego correctamente
	 */
	public boolean setActor(Position pos, Actor act)
	{
		if ( level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY() )
		{
			matrix[pos.getX()][pos.getY()] = act;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean removeActor(Position pos)
	{
		if ( level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY() )
		{
			matrix[pos.getX()][pos.getY()] = null;
			return true;
		}
		else
		{
			return false;
		}
	}

	// NULL FILL
	
	public void fill()
	{
		for (int x = 0; x < level.getWIDTH(); x++)
			for (int y = 0; y < level.getHEIGHT(); y++)
			{
				matrix[x][y] = null;
			}
	}
	
}
