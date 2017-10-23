package game.item;

import game.Position;
import game.SpriteChar;

public class Diamond extends Item
{
	SpriteChar spritechar = SpriteChar.X;
	
	public Diamond(StatusItem state, Position pos)
	{
		super(state, pos, true, false, true, false, true, false);
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	// COLLECTED
	
	public void collected()
	{
		this.collectable = false;
		this.spritechar = SpriteChar.E;
	}
	
}
