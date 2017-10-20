package game.map;

import game.actor.Actor;
import java.util.ArrayList;
import java.util.List;

public class ListActor
{
	private List<Actor> ac = new ArrayList<Actor>();

	public ListActor()
	{
		super();
	}
	
	public ListActor(List<Actor> ac)
	{
		super();
		this.ac = ac;
	}

	public List<Actor> getAc()
	{
		return ac;
	}

	public void setAc(List<Actor> ac)
	{
		this.ac = ac;
	}

}
