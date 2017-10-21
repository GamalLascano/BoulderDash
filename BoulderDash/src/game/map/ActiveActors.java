package game.map;

import game.actor.*;
import java.util.ArrayList;
import java.util.List;

public class ActiveActors
{
	private List<Actor> list = new ArrayList<Actor>();

	// CONSTRUCTORS
	
	public ActiveActors()
	{
		super();
	}
	
	public ActiveActors(List<Actor> list)
	{
		super();
		this.list = list;
	}

	// GETTERS
	
	public List<Actor> getList()
	{
		return list;
	}

	// SETTERS	
	
	public void setList(List<Actor> list)
	{
		this.list = list;
	}
	
	// SORT
	
	// FIND
	
	public Actor findRockford()
	{
		Actor player;
		int i = 0;
		while(!(list.get(i) instanceof Rockford))
		{
			i++;
		}
		player = list.get(i);
		return player;
	}

}
