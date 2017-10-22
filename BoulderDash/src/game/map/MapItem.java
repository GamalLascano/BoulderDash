package game.map;

import game.Position;
import game.item.Item;
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
	
	// GETTERS
	public void start(BDLevelReader levels) {
		level=levels;
		matrix = new Item[level.getWIDTH()][level.getHEIGHT()];
	}
	public Item[][] getMatrix()
	{
		return matrix;
	}
	
	public Item getItem(Position pos)
	{
		return matrix[pos.getX()][pos.getY()];
	}

	// SETTERS
	
	/**
	 * 
	 * @param pos
	 * @param ite
	 * @return : true si se agrego correctamente
	 */
	public boolean setItem(Position pos, Item ite)
	{
		if (level.getWIDTH() >= pos.getX() && level.getHEIGHT() >= pos.getY())
		{
			matrix[pos.getX()][pos.getY()] = ite;
			return true;
		} else
		{
			return false;
		}
	}

}
