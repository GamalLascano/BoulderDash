package game.model.map;

import game.model.element.Element;
import game.model.element.Position;
import game.model.element.cell.Cell;
import game.model.element.cell.Dirt;
import game.model.element.cell.Exit;
import game.model.element.cell.Wall;
import game.model.element.entity.actor.Actor;
import game.model.element.entity.actor.Rockford;
import game.model.element.entity.item.Diamond;
import game.model.element.entity.item.Empty;
import game.model.element.entity.item.Item;
import game.model.element.entity.item.Rock;

/**
 * Matriz de los elementos.
 */
public class MapElement extends Map
{
	private static MapElement mapelement;
	private static Element[][] matrix;

	/**
	 * Constructor de MapElement.
	 */
	private MapElement()
	{
		matrix = null;
	}

	/**
	 * Singleton de MapElement.
	 * 
	 * @return MapElement
	 */
	public static MapElement getInstance()
	{
		if (mapelement == null)
		{
			mapelement = new MapElement();
		}
		return mapelement;
	}

	/**
	 * Devuelve un elemento de la matriz.
	 * 
	 * @param pos
	 * @return item
	 */
	public static Element getElement(Position pos)
	{
		return matrix[pos.getX()][pos.getY()];
	}

	/**
	 * Devuelve un Elemento de la matriz, utiliza coordenadas X,Y.
	 * 
	 * @param x
	 * @param y
	 * @return item
	 */
	public static Element getElement(Integer x, Integer y)
	{
		return matrix[x][y];
	}
	
	/**
	 * Setea un Elemento en la matriz.
	 * 
	 * @param element
	 * @return si se seteo correctamente
	 */
	public static boolean setElement(Element element)
	{
		if (elementPosIsInMap(element))
		{
			matrix[element.getPosition().getX()][element.getPosition().getY()] = element;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Setea una celda en la matriz.
	 * 
	 * @param cell
	 * @return si se seteo correctamente
	 */
	public static boolean setCell(Cell cell)
	{
		if (elementPosIsInMap(cell))
		{
			matrix[cell.getPosition().getX()][cell.getPosition().getY()] = cell;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Setea un item en la matriz.
	 * 
	 * @param item
	 * @return si se seteo correctamente
	 */
	public static boolean setItem(Item item)
	{
		if (elementPosIsInMap(item))
		{
			matrix[item.getPosition().getX()][item.getPosition().getY()] = item;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Setea un celda en la matriz.
	 * 
	 * @param actor
	 * @return si se seteo correctamente
	 */
	public static boolean setActor(Actor actor)
	{
		if (elementPosIsInMap(actor))
		{
			matrix[actor.getPosition().getX()][actor.getPosition().getY()] = actor;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Remueve un Elemento de la matriz.
	 * 
	 * @param pos
	 * @return si se borro el elemento
	 */
	public static boolean removeElement(Position pos)
	{
		if (isInMap(pos))
		{
			matrix[pos.getX()][pos.getY()] = new Empty(pos);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Devuelve un item si no puede devuelve null, utiliza coordenadas X,Y.
	 * 
	 * @param x
	 * @param y
	 * @return un item si no puede devuelve null
	 */
	public static Item getItem(Integer x, Integer y)
	{
		if (isInMap(x, y))
		{
			if (matrix[x][y].isItem())
			{
				return ((Item) matrix[x][y]);
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
	 * Devuelve un diamante si no puede devuelve null.
	 * 
	 * @param pos
	 * @return diamante
	 */
	public static Diamond getDiamond(Position pos)
	{
		if (isInMap(pos))
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
	 * @return un diamante si no puede devuelve null
	 */
	public static Diamond getDiamond(Integer x, Integer y)
	{
		if (isInMap(x, y))
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
	 * @return un rock si no puede devuelve null
	 */
	public static Rock getRock(Integer x, Integer y)
	{
		if (isInMap(x, y))
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
	 * 
	 * @param pos
	 * @return tierra si puede sino devuelve null
	 */
	public static Dirt getDirt(Position pos)
	{
		if (isInMap(pos))
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
		if (isInMap(x, y))
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
		if (isInMap(x, y))
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
	 * Retorna Rockford si puede, sino retorna null.
	 * 
	 * @param x
	 * @param y
	 * @return a Rockford
	 */
	public static Rockford getRockford(Integer x, Integer y)
	{
		if (isInMap(x, y))
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
	 * Devuelve la salida si esta, sino devuelve null.
	 * 
	 * @return la salida si esta, sino null
	 */
	public static Exit findExit()
	{
		Exit exit;
		for (int x = 0; x < mapWidth(); x++)
			for (int y = 0; y < mapHeight(); y++)
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
		matrix = new Element[mapWidth()][mapHeight()];
		fill();
	}

	@Override
	public void fill()
	{
		Position pos = new Position(0, 0);
		for (int x = 0; x < mapWidth(); x++)
			for (int y = 0; y < mapHeight(); y++)
			{
				pos.setXY(x, y);
				matrix[x][y] = new Element(pos);
			}
	}

}
