package game.map;

import game.Position;
import game.item.*;

public class MapItem
{
	private Item[][] items;
	private int width;
	private int height;

	public MapItem(int width, int height)
	{
		this.items = new Item[width][height];
		this.width = width;
		this.height = height;
	}

	public Item getItem(Position pos)
	{
		return items[pos.getX()][pos.setY()];
	}

	/**
	 * 
	 * @param pos
	 * @param ite
	 * @return : true si se agrego correctamente
	 */
	public boolean setItem(Position pos, Item ite)
	{
		if (this.width >= pos.getX() && this.height >= pos.setY())
		{
			items[pos.getX()][pos.setY()] = ite;
			return true;
		} else
		{
			return false;
		}
	}

}
