package game.model.map;

import game.model.element.Position;
import game.model.element.cell.Cell;
import game.model.element.cell.Dirt;
import game.model.element.cell.Exit;
import game.model.element.cell.Wall;

/**
 * Matriz que contiene a los elementos celda.
 */
public class MapCell extends Map
{
	private static MapCell mapcell;
	private static Cell[][] matrix;

	/**
	 * Constructor de mapcell.
	 */
	private MapCell()
	{
		matrix = null;
	}

	/**
	 * Singleton de MapCell.
	 * 
	 * @return mapcell
	 */
	public static MapCell getInstance()
	{
		if (mapcell == null)
		{
			mapcell = new MapCell();
		}
		return mapcell;
	}

	/**
	 * Retorna una celda de la matriz.
	 * 
	 * @param pos
	 * @return celda de la matriz
	 */
	public static Cell getCell(Position pos)
	{
		return matrix[pos.getX()][pos.getY()];
	}

	/**
	 * Retorna una celda de la matriz, utilizando coordenadas X,Y.
	 * 
	 * @param x
	 * @param y
	 * @return una celda de la matriz
	 */
	public static Cell getCell(Integer x, Integer y)
	{
		return matrix[x][y];
	}

	/**
	 * 
	 * @param pos
	 * @return tierra si puede sino devuelve null
	 */
	public static Dirt getDirt(Position pos)
	{
		if (MapInstance.getLevelReader().getWIDTH() >= pos.getX() && 0 <= pos.getX() && MapInstance.getLevelReader().getHEIGHT() >= pos.getY()
				&& 0 <= pos.getY())
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

	/**
	 * Devuelve tierra si puede sino devuelve null.
	 * 
	 * @param x
	 * @param y
	 * @return tierra si puede sino devuelve null
	 */
	public static Dirt getDirt(Integer x, Integer y)
	{
		if (MapInstance.getLevelReader().getWIDTH() >= x && 0 <= x && MapInstance.getLevelReader().getHEIGHT() >= y && 0 <= y)
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

	/**
	 * Devuelve un muro si puede, sino devuelve null.
	 * 
	 * @param x
	 * @param y
	 * @return un muro si puede, sino null
	 */
	public static Wall getWall(Integer x, Integer y)
	{
		if (MapInstance.getLevelReader().getWIDTH() >= x && 0 <= x && MapInstance.getLevelReader().getHEIGHT() >= y && 0 <= y)
		{
			if (matrix[x][y].isWall())
			{
				return ((Wall) matrix[x][y]);
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

	/**
	 * Setea una celda en el mapa.
	 * 
	 * @param cel
	 * @return si pudo poner una celda en el mapa.
	 */
	public static boolean setCell(Cell cel)
	{
		if (MapInstance.getLevelReader().getWIDTH() >= cel.getPosition().getX() && 0 <= cel.getPosition().getX()
				&& MapInstance.getLevelReader().getHEIGHT() >= cel.getPosition().getY() && 0 <= cel.getPosition().getY())
		{
			matrix[cel.getPosition().getX()][cel.getPosition().getY()] = cel;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Remueve una celda del mapa.
	 * 
	 * @param pos
	 * @return si pudo sacar una celda del mapa
	 */
	public static boolean removeCell(Position pos)
	{
		if (MapInstance.getLevelReader().getWIDTH() >= pos.getX() && 0 <= pos.getX() && MapInstance.getLevelReader().getHEIGHT() >= pos.getY()
				&& 0 <= pos.getY())
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

	/**
	 * Devuelve la salida si esta, sino devuelve null.
	 * 
	 * @return la salida si esta, sino null
	 */
	public static Exit findExit()
	{
		Exit exit;
		for (int x = 0; x < MapInstance.getLevelReader().getWIDTH(); x++)
			for (int y = 0; y < MapInstance.getLevelReader().getHEIGHT(); y++)
			{
				if (matrix[x][y].isExit())
				{
					exit = (Exit) matrix[x][y];
					return exit;
				}
			}
		return null;
	}

	@Override
	public void start()
	{
		matrix = new Cell[MapInstance.getLevelReader().getWIDTH()][MapInstance.getLevelReader().getHEIGHT()];
		fill();
	}

	@Override
	public void fill()
	{
		for (int x = 0; x < MapInstance.getLevelReader().getWIDTH(); x++)
			for (int y = 0; y < MapInstance.getLevelReader().getHEIGHT(); y++)
			{
				Position pos = new Position(x, y);
				Dirt dirt = new Dirt(pos, false);
				matrix[x][y] = dirt;
			}
	}
}
