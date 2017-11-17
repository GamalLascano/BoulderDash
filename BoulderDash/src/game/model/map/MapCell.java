package game.model.map;

import game.model.Position;
import game.model.cell.Cell;
import game.model.cell.Dirt;
import game.model.cell.Exit;
import game.model.map.bdlevel.BDLevelReader;

/**
 * 
 *
 */
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

	public static MapCell getInstance()
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

	public static Cell getCell(Position pos)
	{
		return matrix[pos.getX()][pos.getY()];
	}

	public static Cell getCell(Integer x, Integer y)
	{
		return matrix[x][y];
	}

	public static Dirt getDirt(Position pos)
	{
		if (level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY())
		{
			if (matrix[pos.getX()][pos.getY()].isDirt())
			{
				return ((Dirt) matrix[pos.getX()][pos.getY()]);
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

	public static Dirt getDirt(Integer x, Integer y)
	{
		if (level.getWIDTH() >= x && 0 <= x && level.getHEIGHT() >= y && 0 <= y)
		{
			if (matrix[x][y].isDirt())
			{
				return ((Dirt) matrix[x][y]);
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

	public static boolean setCell(Cell cel)
	{
		if (level.getWIDTH() >= cel.getPosition().getX() && 0 <= cel.getPosition().getX()
				&& level.getHEIGHT() >= cel.getPosition().getY() && 0 <= cel.getPosition().getY())
		{
			matrix[cel.getPosition().getX()][cel.getPosition().getY()] = cel;
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean removeCell(Position pos)
	{
		if (level.getWIDTH() >= pos.getX() && 0 <= pos.getX() && level.getHEIGHT() >= pos.getY() && 0 <= pos.getY())
		{
			matrix[pos.getX()][pos.getY()] = null;
			matrix[pos.getX()][pos.getY()] = new Dirt(pos, false);
			return true;
		}
		else
		{
			return false;
		}
	}

	// ENCONTRAR LA CELDA SALIDA

	public static Exit findExit()
	{
		Exit exit;
		for (int x = 0; x < level.getWIDTH(); x++)
			for (int y = 0; y < level.getHEIGHT(); y++)
			{
				if (matrix[x][y].isExit())
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
				Dirt dirt = new Dirt(pos, false);
				matrix[x][y] = dirt;
			}
	}
}
