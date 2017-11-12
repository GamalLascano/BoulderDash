package game.item;

import game.Position;
import game.SpriteChar;

public class Diamond extends Fallable
{
	private SpriteChar spritechar = SpriteChar.X;
	
	// CONSTRUCTORS
	
	public Diamond(Position pos)
	{
		super(pos, true, false, true, false, true, 1, StatusFallableEnum.IDLE);
	}
	
	public Diamond(Position pos, StatusFallableEnum state)
	{
		super(pos, true, false, true, false, true, 1, state);
	}
	
	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	// COLLECTED
	
	/** Collected setea el diamante como recolectado
	 * 
	 */
	public void collected()
	{
		this.die();
	}
	
}
