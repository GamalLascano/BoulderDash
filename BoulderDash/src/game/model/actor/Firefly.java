package game.model.actor;

import game.model.Position;
import game.model.SpriteChar;

public class Firefly extends Enemy
{
	private SpriteChar spritechar = SpriteChar.F;
	
	// CONSTRUCTOR
	
	public Firefly(Position pos)
	{
		super(pos);
		this.state = StatusActorEnum.MOVINGUP;
		// TODO Auto-generated constructor stub
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	// ROTACION
	
	public void rotate()
	{
		switch ( this.state )
		{
			case MOVINGUP:
				this.state = StatusActorEnum.MOVINGRIGHT;
				break;
			case MOVINGRIGHT:
				this.state = StatusActorEnum.MOVINGDOWN;
				break;
			case MOVINGDOWN:
				this.state = StatusActorEnum.MOVINGLEFT;
				break;
			case MOVINGLEFT:
				this.state = StatusActorEnum.MOVINGUP;
				break;
			default:
				break;
		}
	}
}
