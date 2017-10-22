package game.item;

import game.Position;
import game.SpriteChar;

public class Rock extends Item
{
	SpriteChar spritechar = SpriteChar.O;
	boolean pushed;

	public Rock(StatusItem state, Position pos)
	{
		super(state, pos, false, true, true, false, true);
		this.pushed = false;
		// TODO Auto-generated constructor stub
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	// MOVIMIENTO
	
	/**
	 * Rockford empuja la roca. direciones?
	 * 
	 * @return
	 */
	/**
	private void pushed(Rockford player)
	{
		if (player.isPushing() && this.moveable)
		{
			this.pos.goRight();
			this.pushed = true;
		}
	*/

}
