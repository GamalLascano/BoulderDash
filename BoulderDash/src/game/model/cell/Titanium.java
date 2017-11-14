package game.model.cell;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;

public class Titanium extends Cell
{
	private SpriteChar spritechar = SpriteChar.T;
	
	// CONSTRUCTORS
	
	public Titanium(Position pos)
	{
		super(pos, SolidTo.ALL);
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
