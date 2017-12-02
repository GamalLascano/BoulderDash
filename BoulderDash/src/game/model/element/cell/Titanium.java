package game.model.element.cell;

import game.model.element.Position;
import game.model.element.SpriteChar;

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
