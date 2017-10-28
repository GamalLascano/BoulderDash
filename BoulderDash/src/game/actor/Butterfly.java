package game.actor;

import game.Position;
import game.SpriteChar;

public class Butterfly extends Enemy
{
	SpriteChar spritechar = SpriteChar.B;
	
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
		switch ( this.getState().getStateEnum() )
		{
			case MOVINGUP:
				this.getState().setStateEnum(StatusActorEnum.MOVINGRIGHT);
				break;
			case MOVINGRIGHT:
				this.getState().setStateEnum(StatusActorEnum.MOVINGDOWN);
				break;
			case MOVINGDOWN:
				this.getState().setStateEnum(StatusActorEnum.MOVINGLEFT);
				break;
			case MOVINGLEFT:
				this.getState().setStateEnum(StatusActorEnum.MOVINGUP);
				break;
			default:
				break;
		}
	}

}
