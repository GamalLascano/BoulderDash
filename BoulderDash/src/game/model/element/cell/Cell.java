package game.model.element.cell;

import game.model.element.Element;
import game.model.element.Position;
import game.model.map.MapCell;

/**
 * Esta clase contiene todos los objetos del mapa que no se mueven o caen.
 */
public abstract class Cell extends Element
{

	/**
	 * Constructor de Celda.
	 * 
	 * @param pos
	 */
	Cell(Position pos)
	{
		super(pos);
	}

	/**
	 * 
	 * @return si la celda es tierra
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
	 * 
	 * @return si la celda es un muro.
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
	 * 
	 * @return si la celda es titanio
	 */
	public boolean isTitanium()
	{
		if (this instanceof Titanium)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @return si la celda es la salida.
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
