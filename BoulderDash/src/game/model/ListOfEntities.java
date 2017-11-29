package game.model;

import game.model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Lista que contiene las entidades.
 */
public class ListOfEntities
{
	private static ListOfEntities singleton;
	private static List<Entity> entityList;

	/**
	 * Constructor que setea la lista en null.
	 */
	private ListOfEntities()
	{
		entityList = null;
	}

	/**
	 * Singleton de la lista de entidades.
	 * 
	 * @return singleton
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
	 * start inicia la lista con una lista vacia, y lo setea como inicializado.
	 */
	public static void start()
	{
		entityList = new ArrayList<Entity>();
	}

	/**
	 * Devuelve la lista.
	 * 
	 * @return Retorna la lista de entidades
	 */
	public static List<Entity> getList()
	{
		return entityList;
	}

}
