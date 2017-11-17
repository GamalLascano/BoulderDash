package game.model.actor;

import game.model.Position;
import game.model.SpriteChar;

/**
 * 
 *
 */
public class Firefly extends Enemy
{
	private SpriteChar spritechar = SpriteChar.F;

	/**
	 * 
	 * @param pos
	 */
	public Firefly(Position pos)
	{
		super(pos);
		this.state = StatusActorEnum.MOVINGUP;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	/**
	 * 
	 */
	public void rotate()
	{
		switch (this.state)
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
