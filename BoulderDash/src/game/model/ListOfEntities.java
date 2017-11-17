package game.model;

import game.model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public class ListOfEntities
{
	private static ListOfEntities singleton;
	private static List<Entity> entityList;

	/**
	 * 
	 */
	private ListOfEntities()
	{
		entityList = null;
	}

	/**
	 * 
	 * @return
	 */
	public static ListOfEntities getInstance()
	{
		if (singleton == null)
		{
			singleton = new ListOfEntities();
		}
		return singleton;
	}

	/**
	 * start inicia la lista con una lista vacia, y lo setea como inicializado
	 * 
	 */
	public static void start()
	{
		entityList = new ArrayList<Entity>();
	}

	/**
	 * 
	 * @return
	 */
	public static List<Entity> getList()
	{
		return entityList;
	}

}
