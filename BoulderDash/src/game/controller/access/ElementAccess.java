package game.controller.access;

import game.model.element.cell.Exit;
import game.model.element.entity.Entity;
import game.model.element.entity.ListOfEntities;

/**
 * Accede a los elementos del modelo.
 *
 */
public class ElementAccess
{

	/**
	 * Devuele si la entidad esta en la lista de entidades. (si esta viva)
	 * 
	 * @param ent
	 *            : entidad
	 * @return true si esta vivo ent
	 */

	public static boolean entityIsAlive(Entity ent)
	{
		return ListOfEntities.getList().contains(ent);
	}

	/**
	 * Abre la salida.
	 */
	public static void openExit()
	{
		Exit.getInstance().open();
	}

}
