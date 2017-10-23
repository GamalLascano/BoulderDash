package game.map;

import game.cell.Cell;
import game.cell.Dirt;
import game.cell.Exit;
import game.Position;
import game.map.bdlevel.BDLevelReader;

public class MapCell
{
	private static MapCell singleton;
	private static BDLevelReader level;
	private static Cell[][] matrix;

	private MapCell() 
	{
		matrix = null;
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
	
	// INICIALIZACION

	public void start(BDLevelReader levels)
	{
		level = levels;
		matrix = new Cell[level.getWIDTH()][level.getHEIGHT()];
		fill();
	}
	
	// GETTERS
	
	public Cell getCell(Position pos)
	{
		return matrix[pos.getX()][pos.getY()];
	}
	
	public Cell getCell(Integer x, Integer y)
	{
		return matrix[x][y];
	}
	
	public Dirt getDirt(Position pos)
	{
		if( level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY() )
		{
			if( matrix[pos.getX()][pos.getY()] instanceof Dirt )
			{
				return ( (Dirt) matrix[pos.getX()][pos.getY()] );
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

	// SETTERS
	
	public boolean setCell(Position pos, Cell cel)
	{
		if ( level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY() )
		{
			matrix[pos.getX()][pos.getY()] = cel;
			return true;
		} 
		else
		{
			return false;
		}
	}
	
	// Devuelve True si la celda esta vacia
	
	public boolean isEmpty(Position pos)
	{
		boolean empty;
		if ( matrix[pos.getX()][pos.getY()] instanceof Dirt )
		{
			Dirt dirt = (Dirt) matrix[pos.getX()][pos.getY()];
			if( dirt.IsDirt() == false)
			{
				empty = true;
			}
			else
			{
			empty = false;
			}
		}
		else
		{
			empty = false;
		}
		return empty;
	}
	
	// ENCONTRAR LA CELDA SALIDA
	
	public Exit findExit()
	{
		Exit exit;
		for (int x = 0; x < level.getWIDTH(); x++)
			for (int y = 0; y < level.getHEIGHT(); y++)
			{
				if(matrix[x][y] instanceof Exit)
				{
				exit = (Exit) matrix[x][y];
				return exit;
				}
			}
		return null;
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
