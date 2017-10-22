package game.map;

import game.cell.Cell;
import game.cell.Dirt;
import game.Position;
import game.map.bdlevel.BDLevelReader;

public class MapCell
{
	private static MapCell singleton;
	private static BDLevelReader level;
	private static Cell[][] matrix;

	private MapCell() 
	{

	}

	// SINGLETON

	public static synchronized MapCell getInstance()
	{
		if (singleton == null)
		{
			singleton = new MapCell();
		}
		return singleton;
	}
	
	// GETTERS
	
	public Cell getCell(Position pos)
	{
		return matrix[pos.getX()][pos.getY()];
	}
	
	public Dirt getDirt(Position pos)
	{
		if(matrix[pos.getX()][pos.getY()] instanceof Dirt)
		{
			return (Dirt) matrix[pos.getX()][pos.getY()];
		}
		else
		{
			return null;
		}
	}
	
	public boolean isEmpty(Position pos)
	{
		boolean empty;
		if (matrix[pos.getX()][pos.getY()] instanceof Dirt)
		{
			empty = true;
		}
		else
		{
			empty = false;
		}
		return empty;
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
		if (level.getWIDTH() >= pos.getX() && level.getHEIGHT() >= pos.getY())
		{
			matrix[pos.getX()][pos.getY()] = cel;
			return true;
		} else
		{
			return false;
		}
	}
	public void start(BDLevelReader levels) {
		level=levels;
		matrix = new Cell[level.getWIDTH()][level.getHEIGHT()];
		fill();
	}
	// DIRT FILL
	public void fill()
	{
		for (int x = 0; x < level.getWIDTH(); x++)
			for (int y = 0; y < level.getHEIGHT(); y++)
			{
				Position pos = new Position(x, y);
				Dirt dirt = new Dirt(pos);
				matrix[x][y] = dirt;
			}
	}
}
