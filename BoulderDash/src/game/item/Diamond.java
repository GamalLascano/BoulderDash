package game.item;

import game.Position;
import game.SpriteChar;

public class Diamond extends Item
{
	private SpriteChar spritechar = SpriteChar.X;
	
	public Diamond(StatusItem state, Position pos)
	{
		super(state, pos, true, false, true, false, true, 1);
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	// COLLECTED
	
	/** Collected setea el diamante como recolectado, y cambia el sprite a un vacio
	 * 
	 */
	public void collected()
	{
		this.setCollectable(true);
		this.spritechar = SpriteChar.E;
	}
	
}
