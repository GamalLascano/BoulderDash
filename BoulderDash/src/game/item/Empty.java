package game.item;

import game.Position;
import game.SpriteChar;

public class Empty extends Item
{
	private SpriteChar spritechar = SpriteChar.E;
	
	public Empty(Position pos)
	{
		super(null, pos, false, false, false, false, false, 0);
		// TODO Auto-generated constructor stub
	}
	
	public Empty(StatusItem state, Position pos)
	{
		super(state, pos, false, false, false, false, false, 0);
		// TODO Auto-generated constructor stub
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
}
