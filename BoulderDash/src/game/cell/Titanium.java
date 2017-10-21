package game.cell;

import game.Position;
import game.SpriteChar;

public class Titanium extends Cell
{
	SpriteChar spritechar = SpriteChar.T;
	
	public Titanium(Position pos)
	{
		super(pos, true);
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
}
