package game.actor;

import game.Position;
import game.item.StatusItem;

public class Firefly extends Enemy
{

	public Firefly(StatusActor state, Position pos)
	{
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void explode()
	{

	}

	@Override
	public boolean rotate()
	{
		return true;
	}

}
