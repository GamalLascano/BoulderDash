package game.actor;

import game.Position;
import game.item.StatusItem;
import game.SpriteChar;

public class Butterfly extends Enemy
{
	SpriteChar spritechar = SpriteChar.B;
	
	public Butterfly(StatusActor state, Position pos)
	{
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
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
