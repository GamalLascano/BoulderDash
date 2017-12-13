package game.model.map;

import game.model.element.Position;
import game.model.element.entity.actor.Actor;
import game.model.element.entity.actor.Rockford;

/**
 * Matriz que contiene a los elementos actores.
 */
public class MapActor extends Map
{
	private static MapActor mapactor;
	private static Actor[][] matrix;

	/**
	 * Constructor del mapa de actores.
	 */
	private MapActor()
	{
		matrix = null;
	}

	/**
	 * Singleton de MapActor.
	 * 
	 * @return mapa de los actores
	 */
	public static MapActor getInstance()
	{
		if (mapactor == null)
		{
			mapactor = new MapActor();
		}
		return mapactor;
	}

	/**
	 * Recupera un actor de la matriz, usando un objeto posicion.
	 * 
	 * @param pos
	 * @return un actor de esta posicion
	 */
	public static Actor getActor(Position pos)
	{
		if (MapInstance.getInstance().getLevelReader().getWIDTH() >= pos.getX() && 0 <= pos.getX() && MapInstance.getInstance().getLevelReader().getHEIGHT() >= pos.getY()
				&& 0 <= pos.getY())
		{
			return matrix[pos.getX()][pos.getY()];
		}
		else
		{
			return null;
		}
	}

	/**
	 * Recupera un actor de la matriz, usando coordenadas X,Y
	 * 
	 * @param x
	 * @param y
	 * @return un actor de esta posicion
	 */
	public static Actor getActor(Integer x, Integer y)
	{
		if (MapInstance.getInstance().getLevelReader().getWIDTH() >= x && 0 <= x && MapInstance.getInstance().getLevelReader().getHEIGHT() >= y && 0 <= y)
		{
			return matrix[x][y];
		}
		else
		{
			return null;
		}
	}

	/**
	 * Retorna Rockford si puede, sino retorna null.
	 * 
	 * @param x
	 * @param y
	 * @return a Rockford
	 */
	public static Rockford getRockford(Integer x, Integer y)
	{
		if (MapInstance.getInstance().getLevelReader().getWIDTH() >= x && 0 <= x && MapInstance.getInstance().getLevelReader().getHEIGHT() >= y && 0 <= y)
		{
			if (matrix[x][y] != null)
			{
				if (matrix[x][y].isRockford())
				{
					return ((Rockford) matrix[x][y]);
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
		else
		{
			return null;
		}
	}

	/**
	 * Setea un actor en la matriz, util para el cambio de posicion y
	 * movimiento.
	 * 
	 * @param act
	 * @return si pudo poner el actor
	 */
	public static boolean setActor(Actor act)
	{
		if (MapInstance.getInstance().getLevelReader().getWIDTH() >= act.getPosition().getX() && 0 <= act.getPosition().getX()
				&& MapInstance.getInstance().getLevelReader().getHEIGHT() >= act.getPosition().getY() && 0 <= act.getPosition().getY())
		{
			matrix[act.getPosition().getX()][act.getPosition().getY()] = act;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Remueve un actor de la matriz.
	 * 
	 * @param pos
	 * @return si pudo remover el actor
	 */
	public static boolean removeActor(Position pos)
	{
		if (MapInstance.getInstance().getLevelReader().getWIDTH() >= pos.getX() && 0 <= pos.getX() && MapInstance.getInstance().getLevelReader().getHEIGHT() >= pos.getY()
				&& 0 <= pos.getY())
		{
			matrix[pos.getX()][pos.getY()] = null;
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
		matrix = new Actor[MapInstance.getInstance().getLevelReader().getWIDTH()][MapInstance.getInstance().getLevelReader().getHEIGHT()];
		fill();
	}

	@Override
	public void fill()
	{
		for (int x = 0; x < MapInstance.getInstance().getLevelReader().getWIDTH(); x++)
			for (int y = 0; y < MapInstance.getInstance().getLevelReader().getHEIGHT(); y++)
			{
				matrix[x][y] = null;
			}
	}

}
