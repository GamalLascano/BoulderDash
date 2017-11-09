package game.actor;

import game.Position;
import game.SpriteChar;

public class Butterfly extends Enemy
{
	private SpriteChar spritechar = SpriteChar.B;
	
	public Butterfly(StatusActor state, Position pos)
	{
		super(state, pos, 1);
		// TODO Auto-generated constructor stub
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	// S
	
	@Override
	public void explode()
	{
	}

	// S


	@Override
	public void rotate()
	{
		switch ( this.getState().getMovementState() )
		{
			case MOVINGUP:
				this.getState().setMovementState(StatusActorEnum.MOVINGLEFT);
				break;
			case MOVINGLEFT:
				this.getState().setMovementState(StatusActorEnum.MOVINGDOWN);
				break;
			case MOVINGDOWN:
				this.getState().setMovementState(StatusActorEnum.MOVINGRIGHT);
				break;
			case MOVINGRIGHT:
				this.getState().setMovementState(StatusActorEnum.MOVINGUP);
				break;
			default:
				break;
		}
	}

}
