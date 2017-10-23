package game.actor;

import game.Position;
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
	public void explode()
	{
	}

	// S


	@Override
	public void rotate()
	{
		
	}

}
