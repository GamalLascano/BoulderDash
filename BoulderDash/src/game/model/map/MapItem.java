package game.model.map;

import game.model.element.Position;
import game.model.element.entity.item.Diamond;
import game.model.element.entity.item.Empty;
import game.model.element.entity.item.Item;
import game.model.element.entity.item.Rock;

/**
 * Matriz de los objetos.
 */
public class MapItem extends Map
{
	private static MapItem mapitem;
	private static Item[][] matrix;

	/**
	 * Constructor de mapItem.
	 */
	private MapItem()
	{
		matrix = null;
	}

	/**
	 * Singleton de MapItem.
	 * 
	 * @return mapitem
	 */
	public static MapItem getInstance()
	{
		if (mapitem == null)
		{
			mapitem = new MapItem();
		}
		return mapitem;
	}

	/**
	 * Devuelve un item de la matriz.
	 * 
	 * @param pos
	 * @return item
	 */
	public static Item getItem(Position pos)
	{
		return matrix[pos.getX()][pos.getY()];
	}

	/**
	 * Devuelve un item de la matriz, utiliza coordenadas X,Y.
	 * 
	 * @param x
	 * @param y
	 * @return item
	 */
	public static Item getItem(Integer x, Integer y)
	{
		return matrix[x][y];
	}

	/**
	 * Devuelve un diamante si no puede devuelve null.
	 * 
	 * @param pos
	 * @return diamante
	 */
	public static Diamond getDiamond(Position pos)
	{
		if (MapInstance.getInstance().getLevelReader().getWIDTH() >= pos.getX() && 0 <= pos.getX() && MapInstance.getInstance().getLevelReader().getHEIGHT() >= pos.getY()
				&& 0 <= pos.getY())
		{
			if (matrix[pos.getX()][pos.getY()].isDiamond())
			{
				return ((Diamond) matrix[pos.getX()][pos.getY()]);
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
	 * Devuelve un diamante si no puede devuelve null, utiliza coordenadas X,Y.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Diamond getDiamond(Integer x, Integer y)
	{
		if (MapInstance.getInstance().getLevelReader().getWIDTH() >= x && 0 <= x && MapInstance.getInstance().getLevelReader().getHEIGHT() >= y && 0 <= y)
		{
			if (matrix[x][y].isDiamond())
			{
				return ((Diamond) matrix[x][y]);
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
	 * Devuelve un rock si no puede devuelve null, utiliza coordenadas X,Y.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Rock getRock(Integer x, Integer y)
	{
		if (MapInstance.getInstance().getLevelReader().getWIDTH() >= x && 0 <= x && MapInstance.getInstance().getLevelReader().getHEIGHT() >= y && 0 <= y)
		{
			if (matrix[x][y].isRock())
			{
				return ((Rock) matrix[x][y]);
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
	 * Setea un item en la matriz.
	 * 
	 * @param ite
	 * @return
	 */
	public static boolean setItem(Item ite)
	{
		if (MapInstance.getInstance().getLevelReader().getWIDTH() >= ite.getPosition().getX() && 0 <= ite.getPosition().getX()
				&& MapInstance.getInstance().getLevelReader().getHEIGHT() >= ite.getPosition().getY() && 0 <= ite.getPosition().getY())
		{
			matrix[ite.getPosition().getX()][ite.getPosition().getY()] = ite;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Remueve un item de la matriz.
	 * 
	 * @param pos
	 * @return
	 */
	public static boolean removeItem(Position pos)
	{
		if (MapInstance.getInstance().getLevelReader().getWIDTH() >= pos.getX() && 0 <= pos.getX() && MapInstance.getInstance().getLevelReader().getHEIGHT() >= pos.getY()
				&& 0 <= pos.getY())
		{
			matrix[pos.getX()][pos.getY()] = new Empty(pos);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void start()
	{
		matrix = new Item[MapInstance.getInstance().getLevelReader().getWIDTH()][MapInstance.getInstance().getLevelReader().getHEIGHT()];
		fill();
	}

	@Override
	public void fill()
	{
		Position pos = new Position(0, 0);
		for (int x = 0; x < MapInstance.getInstance().getLevelReader().getWIDTH(); x++)
			for (int y = 0; y < MapInstance.getInstance().getLevelReader().getHEIGHT(); y++)
			{
				pos.setXY(x, y);
				matrix[x][y] = new Empty(pos);
			}
	}

}
