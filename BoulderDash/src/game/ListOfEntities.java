package game;

import game.Entity;
import game.actor.Rockford;

import java.util.ArrayList;
import java.util.List;


	//	Lista de entities (Items y Actores)
public class ListOfEntities
{
	private static ListOfEntities singleton;
	private static List<Entity> list;

	// CONSTRUCTORS

	private ListOfEntities()
	{
		list = null;
	}

	// SINGLETON

	public static synchronized ListOfEntities getInstance()
	{
		if (singleton == null)
		{
			singleton = new ListOfEntities();
		}
		return singleton;
	}
	
	public static void start()
	{
		list = new ArrayList<Entity>();
	}
	
	// GETTERS
	
	public static List<Entity> getList()
	{
		return list;
	}

	// SETTERS	
	
	// SORT
	
	// FIND
	
	public static Rockford findRockford()
	{
		Rockford player;
		int i = 0;
		while(!(list.get(i) instanceof Rockford))
		{
			i++;
		}
		player = (Rockford) list.get(i);
		return player;
	}

}
