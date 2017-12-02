package game.model.element.entity.actor;

import game.model.element.Position;
import game.model.element.SpriteChar;

/**
 * Clase de la Luciernega. Hace una rotacion horaria.
 */
public class Firefly extends Enemy
{

	/**
	 * Constructor de la Luciernega.
	 * 
	 * @param pos
	 */
	public Firefly(Position pos)
	{
		super(pos);
		this.setSpritechar(SpriteChar.F);
		this.state = StatusActorEnum.MOVINGUP;
	}

	@Override
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
