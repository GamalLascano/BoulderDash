package game.controller;

import game.model.element.cell.Exit;
import game.model.element.entity.Entity;
import game.model.element.entity.ListOfEntities;

public class ElementAccess
{

	/**
	 * 
	 * @return
	 */
	public static boolean entityIsAlive(Entity ent)
	{
		return 	ListOfEntities.getList().contains(ent);
	}
	
	/**
	 * 
	 * @return
	 */
	public static void openExit()
	{
		Exit.getInstance().open();
	}
	


}
