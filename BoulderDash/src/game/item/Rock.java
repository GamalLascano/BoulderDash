package game.item;

import game.Position;
import game.SpriteChar;
import game.actor.Rockford;

public class Rock extends Item
{
	SpriteChar spritechar = SpriteChar.O;
	boolean pushed;

	public Rock(StatusItem state, Position pos)
	{
		super(state, pos, false, true, true, false, true, true);
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
	
	public void pushed(Rockford player)
	{
		if (player.isPushing() && this.moveable)
		{
			switch ( player.getState().getStateEnum() )
			{
				case MOVINGLEFT:
					this.state.setStateEnum(StatusItemEnum.SLIDINGLEFT);
					this.pushed = true;
					break;
				case MOVINGRIGHT:
					this.state.setStateEnum(StatusItemEnum.SLIDINGRIGHT);
					this.pushed = true;
					break;
				default:
					break;
			}
		this.pushed = false;		
		}
	}

}
