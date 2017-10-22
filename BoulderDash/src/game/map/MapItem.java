package game.map;

import game.Position;
import game.item.*;

public class MapItem
{
	private Item[][] matrix;
	private int width;
	private int height;

	public MapItem(int width, int height)
	{
		this.matrix = new Item[width][height];
		this.width = width;
		this.height = height;
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
	
	/**
	 * 
	 * @param pos
	 * @param ite
	 * @return : true si se agrego correctamente
	 */
	public boolean setItem(Position pos, Item ite)
	{
		if (this.width >= pos.getX() && this.height >= pos.getY())
		{
			matrix[pos.getX()][pos.getY()] = ite;
			return true;
		} else
		{
			return false;
		}
	}

}
