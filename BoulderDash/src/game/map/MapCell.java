package game.map;

import game.cell.*;
import game.Position;

public class MapCell
{
	private Cell[][] cell;
	private int width;
	private int height;

	public MapCell(int width, int height) 
	{
		this.cell = new Cell[width][height];
		this.width = width;
		this.height = height;
	}

	// DIRT FILL
	public void fill()
	{
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
			{
				Position pos = new Position(width, height);
				Dirt dirt = new Dirt(pos);
				this.cell[width][height] = dirt;
			}
	}

	// GETTERS
	
	public Cell getCell(Position pos)
	{
		return cell[pos.getX()][pos.getY()];
	}

	// SETTERS
	
	/**
	 * 
	 * @param pos
	 * @param cel
	 * @return : true si se agrego correctamente
	 */
	public boolean setCell(Position pos, Cell cel)
	{
		if (this.width >= pos.getX() && this.height >= pos.getY())
		{
			cell[pos.getX()][pos.getY()] = cel;
			return true;
		} else
		{
			return false;
		}
	}
}
