package game.model;

import game.model.Entity;
import game.model.actor.Rockford;

import java.util.ArrayList;
import java.util.List;

//	Lista de entities (Items y Actores)
public class ListOfEntities
{
	private static ListOfEntities singleton;
	private static List<Entity> entityList;
	private static boolean initiated = false;

	// CONSTRUCTORS

	private ListOfEntities()
	{
		entityList = null;
	}

	// SINGLETON

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
		initiated = true;
		entityList = new ArrayList<Entity>();
	}

	// GETTERS

	public static List<Entity> getList()
	{
		return entityList;
	}

	// SETTERS

	// SORT

	// FIND
	/**
	 * FindRockford busca en la lista de entidades a ver si se encuentra
	 * rockford
	 * 
	 */
	public static Rockford findRockford()
	{
		Rockford player = null;
		int i = 0;
		if (initiated)
		{
			while (entityList.size() > i)
			{
				if (entityList.get(i).isRockford())
				{
					player = (Rockford) entityList.get(i);
				}
				i++;
			}
			if (player != null)
			{
				return player;
			}
			else
			{
				System.out.println("ERROR: ROCKFORD NO ESTA EN LA LISTA");
				return null;
			}
		}
		else
		{
			System.out.println("ERROR: LA LISTA NO ESTA INICIALIZADA");
			return null;
		}
	}

}
