package game.model.item;

import game.model.Position;
import game.model.SpriteChar;

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
	
	// CHANGE POSITION
	
	public void changePosition()
	{
	}
	
	public void makeMove()
	{
	}
	
}
