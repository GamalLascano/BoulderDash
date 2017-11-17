package game.model.item;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.map.MapInstance;
import game.model.map.MapItem;

/**
 * 
 *
 */
public class Diamond extends Fallable
{
	private SpriteChar spritechar = SpriteChar.X;

	/**
	 * 
	 * @param pos
	 */
	public Diamond(Position pos)
	{
		super(pos, true, false, true, false, true, SolidTo.ACTOR, StatusFallableEnum.IDLE);
	}

	/**
	 * 
	 * @param pos
	 * @param state
	 */
	public Diamond(Position pos, StatusFallableEnum state)
	{
		super(pos, true, false, true, false, true, SolidTo.ACTOR, state);
	}

	/**
	 * 
	 */
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	/**
	 * Collected setea el diamante como recolectado
	 * 
	 */
	public void collected()
	{
		this.die();
	}

	/**
	 * 
	 */
	public void fall()
	{
		if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.NONE
				|| MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.ACTOR
						&& MapItem.getDiamond(this.getPosition().getX(), this.getPosition().checkDown()) == null
						&& this.state == StatusFallableEnum.IDLE)
		{
			this.state = StatusFallableEnum.FALLINGOFF;
		}
		else if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.NONE
				|| MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.ACTOR
						&& MapItem.getDiamond(this.getPosition().getX(), this.getPosition().checkDown()) == null
						&& this.state == StatusFallableEnum.FALLINGOFF
				|| this.state == StatusFallableEnum.FALLING)
		{
			this.state = StatusFallableEnum.FALLING;
		}
		else if (MapItem.getItem(this.getPosition().getX(), this.getPosition().checkDown()).isRounded())
		{
			if (MapInstance.solid(this.getPosition().checkLeft(), this.getPosition().getY()) == SolidTo.NONE
					&& MapInstance.solid(this.getPosition().checkLeft(),
							this.getPosition().checkDown()) == SolidTo.NONE)
			{
				this.state = StatusFallableEnum.SLIDINGLEFT;
			}
			else if (MapInstance.solid(this.getPosition().checkRight(), this.getPosition().getY()) == SolidTo.NONE
					&& MapInstance.solid(this.getPosition().checkRight(),
							this.getPosition().checkDown()) == SolidTo.NONE)
			{
				this.state = StatusFallableEnum.SLIDINGRIGHT;
			}
		}
		else
		{
			this.state = StatusFallableEnum.IDLE;
		}
	}

	/**
	 * 
	 */
	public void makeMove()
	{
		switch (this.state)
		{
			case FALLINGOFF:
				this.getPosition().goDown();
				break;
			case FALLING:
				if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.NONE
						|| MapInstance.solid(this.getPosition().getX(),
								this.getPosition().checkDown()) == SolidTo.ACTOR)
				{
					this.getPosition().goDown();
				}
				else
				{
					this.state = StatusFallableEnum.IDLE;
				}
				break;
			case SLIDINGRIGHT:
				this.getPosition().goRight();
				this.state = StatusFallableEnum.IDLE;
				break;
			case SLIDINGLEFT:
				this.getPosition().goLeft();
				this.state = StatusFallableEnum.IDLE;
				break;
			default:
				break;
		}
	}

}
