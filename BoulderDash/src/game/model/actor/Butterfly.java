package game.model.actor;

import game.model.Position;
import game.model.SpriteChar;

/**
 * 
 *
 */
public class Butterfly extends Enemy
{
	private SpriteChar spritechar = SpriteChar.B;

	/**
	 * 
	 * @param pos
	 */
	public Butterfly(Position pos)
	{
		super(pos);
		this.state = StatusActorEnum.MOVINGUP;
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
	@Override
	public void rotate()
	{
		switch (this.state)
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
