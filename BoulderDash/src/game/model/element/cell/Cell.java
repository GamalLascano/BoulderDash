package game.model.element.cell;

import game.model.element.Element;
import game.model.element.Position;
import game.model.map.MapElement;

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
	 * Hacer un comportamiento y borra una celda.
	 */
	public void die()
	{
		MapElement.removeElement(this.getPosition());
	}

}
