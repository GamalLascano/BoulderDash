package game.model.item;

import game.model.Position;
import game.model.SpriteChar;

/**
 * Clase que representa una item vacio.
 */
public class Empty extends Item
{

	/**
	 * Constructor de vacia.
	 * 
	 * @param pos
	 */
	public Empty(Position pos)
	{
		super(pos, false, false, false);
		this.setSpritechar(SpriteChar._);
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
