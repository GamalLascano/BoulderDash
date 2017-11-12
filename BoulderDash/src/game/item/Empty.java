package game.item;

import game.Position;
import game.SpriteChar;

public class Empty extends Item
{
	private SpriteChar spritechar = SpriteChar._;
	
	// CONSTRUCTOR
	
	public Empty(Position pos)
	{
		super(pos, false, false, false, false, false, 0);
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
}
