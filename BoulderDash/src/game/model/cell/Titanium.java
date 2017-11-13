package game.model.cell;

import game.model.Position;
import game.model.SpriteChar;

public class Titanium extends Cell
{
	private SpriteChar spritechar = SpriteChar.T;
	
	// CONSTRUCTORS
	
	public Titanium(Position pos)
	{
		super(pos, 2);
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	// DIE
	
	public void clear()
	{
		
	}
}
