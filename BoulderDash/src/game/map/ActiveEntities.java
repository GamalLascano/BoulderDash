package game.map;

import game.Entity;
import game.actor.Actor;
import game.actor.Rockford;
import java.util.ArrayList;
import java.util.List;

public class ActiveEntities
{
	private List<Entity> list = new ArrayList<Entity>();

	// CONSTRUCTORS
	
	public ActiveEntities()
	{
		super();
	}
	
	public ActiveEntities(List<Entity> list)
	{
		super();
		this.list = list;
	}

	// GETTERS
	
	public List<Entity> getList()
	{
		return list;
	}

	// SETTERS	
	
	public void setList(List<Entity> list)
	{
		this.list = list;
	}
	
	// SORT
	
	// FIND
	
	public Rockford findRockford()
	{
		Rockford player;
		int i = 0;
		while(!(list.get(i) instanceof Rockford))
		{
			i++;
		}
		player = list.get(i);
		return player;
	}

}