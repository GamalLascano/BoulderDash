package game.map;

import game.actor.Actor;
import java.util.ArrayList;
import java.util.List;

public class ActiveActors
{
	private List<Actor> list = new ArrayList<Actor>();

	public ActiveActors()
	{
		super();
	}
	
	public ActiveActors(List<Actor> list)
	{
		super();
		this.list = list;
	}

	public List<Actor> getList()
	{
		return list;
	}

	public void setList(List<Actor> list)
	{
		this.list = list;
	}

}
