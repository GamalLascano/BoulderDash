package game.actor;

import game.Position;
import game.item.StatusItem;

public class Butterfly extends Enemy
{
	
	public Butterfly(StatusActor state, Position pos)
	{
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	// S
	
	@Override
	public boolean explode()
	{
		return true;
	}

	// S
	
	@Override
	public boolean rotate()
	{
		return true;
	}

}
