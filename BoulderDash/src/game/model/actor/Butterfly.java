package game.model.actor;

import game.model.Position;
import game.model.SpriteChar;

/**
 * Enemigo Mariposa. Hace la rotacion de manera Horaria.
 */
public class Butterfly extends Enemy
{

	/**
	 * Constructor de la mariposa.
	 * @param pos
	 */
	public Butterfly(Position pos)
	{
		super(pos);
		this.setSpritechar(SpriteChar.B);
		this.state = StatusActorEnum.MOVINGUP;
	}

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
