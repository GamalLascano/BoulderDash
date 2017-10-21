package game.item;

import game.Position;
import game.SpriteChar;

public class Empty extends Item
{
	SpriteChar spritechar = SpriteChar.E;
	public Empty(StatusItem state, Position pos)
	{
		super(state, pos, false, false, false, false, false);
		// TODO Auto-generated constructor stub
	}

	private void deleteItem()
	{

	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
}
