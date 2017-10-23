package game.map;

import game.Position;
import game.item.Item;
import game.item.Empty;
import game.map.bdlevel.BDLevelReader;

public class MapItem
{
	private static MapItem singleton;
	private static BDLevelReader level;
	private static Item[][] matrix;

	private MapItem()
	{
		
	}

	// SINGLETON
	
	public static synchronized MapItem getInstance()
	{
		if (singleton == null)
		{
			singleton = new MapItem();
		}
		return singleton;
	}

	// INICIALIZACION
	
	public void start(BDLevelReader levels)
	{
		level = levels;
		matrix = new Item[level.getWIDTH()][level.getHEIGHT()];
		fill();
	}

	// GETTERS
	
	public Item[][] getMatrix()
	{
		return matrix;
	}
	
	public Item getItem(Position pos)
	{
		return matrix[pos.getX()][pos.getY()];
	}

	// SETTERS
	
	public boolean setItem(Position pos, Item ite)
	{
		if ( level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY() )
		{
			matrix[pos.getX()][pos.getY()] = ite;
			return true;
		} 
		else
		{
			return false;
		}
	}
	
	public boolean removeItem(Position pos)
	{
		if ( level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY() )
		{
			matrix[pos.getX()][pos.getY()] = new Empty(pos);
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
		Position pos = new Position();
		for (int x = 0; x < level.getWIDTH(); x++)
			for (int y = 0; y < level.getHEIGHT(); y++)
			{
				pos.setXY(x, y);
				matrix[x][y] = new Empty(pos);
			}
	}

}
