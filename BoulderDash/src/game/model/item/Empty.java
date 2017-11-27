package game.model.item;

import game.model.Position;
import game.model.SpriteChar;

/**
 * 
 *
 */
public class Empty extends Item
{
	private SpriteChar spritechar = SpriteChar._;

	/**
	 * 
	 * @param pos
	 */
	public Empty(Position pos)
	{
		super(pos, false, false, false, false, false);
	}

	/**
	 * 
	 */
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	/**
	 * 
	 */
	@Override
	public void changePosition()
	{

	}

	/**
	 * 
	 */
	@Override
	public void makeMove()
	{

	}

}
