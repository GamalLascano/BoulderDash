package game.model.cell;

import game.model.Position;
import game.model.SpriteChar;

/**
 * Clase que representa el titanio.
 */
public class Titanium extends Cell
{

	/**
	 * Constructor del titanio.
	 * 
	 * @param pos
	 */
	public Titanium(Position pos)
	{
		super(pos);
		this.setSpritechar(SpriteChar.T);
	}

}
