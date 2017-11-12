package game.actor;

import game.Position;
import game.SpriteChar;

public class Butterfly extends Enemy
{
	private SpriteChar spritechar = SpriteChar.B;
	
	// CONSTRUCTOR
	
	public Butterfly(Position pos)
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
	
	// 
	
	@Override
	public void explode()
	{
	}

	// ROTATE

	@Override
	public void rotate()
	{
		switch ( this.state )
		{
			case MOVINGUP:
				this.state = StatusActorEnum.MOVINGLEFT;
				break;
			case MOVINGLEFT:
				this.state = StatusActorEnum.MOVINGDOWN;
				break;
			case MOVINGDOWN:
				this.state = StatusActorEnum.MOVINGRIGHT;
				break;
			case MOVINGRIGHT:
				this.state = StatusActorEnum.MOVINGUP;
				break;
			default:
				break;
		}
	}
}
