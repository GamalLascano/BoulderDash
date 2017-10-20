package game.map;

import java.util.ArrayList;
import java.util.List;

public class ListActor<Actor>
{
	private List<Actor> ac = new ArrayList<Actor>();

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
