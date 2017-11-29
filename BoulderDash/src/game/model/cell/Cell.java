package game.model.cell;

import game.model.Element;
import game.model.Position;
import game.model.map.MapCell;

/**
 * Esta clase contiene todos los objetos del mapa que no se mueven.
 */
public abstract class Cell extends Element
{

	/**
	 * Constructor de Celda.
	 * @param pos
	 */
	Cell(Position pos)
	{
		super(pos);
	}

	/**
	 * Retorna si la celda es tierra.
	 * @return
	 */
	public boolean isDirt()
	{
		if (this instanceof Dirt)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Retorna si la celda es un muro.
	 * @return
	 */
	public boolean isWall()
	{
		if (this instanceof Wall)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Retorna si la celda es la salida.
	 * @return
	 */
	public boolean isExit()
	{
		if (this instanceof Exit)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Hacer un comportamiento y borra una celda.
	 */
	public void clear()
	{
		MapCell.removeCell(this.getPosition());
	}

}
