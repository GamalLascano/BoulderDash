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

	public Cell getCell(Position pos)
	{
		return cell[pos.getX()][pos.setY()];
	}

	/**
	 * 
	 * @param pos
	 * @param cel
	 * @return : true si se agrego correctamente
	 */
	public boolean setCell(Position pos, Cell cel)
	{
		if (this.width >= pos.getX() && this.height >= pos.setY())
		{
			cell[pos.getX()][pos.setY()] = cel;
			return true;
		} else
		{
			return false;
		}
	}
}
