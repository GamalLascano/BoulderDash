package game.model.cell;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;

/**
 * 
 *
 */
public class Titanium extends Cell
{
	private SpriteChar spritechar = SpriteChar.T;

	/**
	 * 
	 * @param pos
	 */
	public Titanium(Position pos)
	{
		super(pos, SolidTo.ALL);
	}

	/**
	 * 
	 */
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

}
