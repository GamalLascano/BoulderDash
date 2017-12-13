package game.model.element.entity.item;

import game.model.element.Position;
import game.model.element.ElementChar;
import game.model.map.MapCell;

/**
 * Clase del diamante.
 */
public class Diamond extends Fallable
{

	/**
	 * Constructor diamante.
	 * 
	 * @param pos
	 */
	public Diamond(Position pos)
	{
		super(pos, true, false, true, StatusFallableEnum.IDLE);
		this.setSpritechar(ElementChar.X);
		this.putPassables();
	}

	/**
	 * Constructor del diamante con estado inicial.
	 * 
	 * @param pos
	 * @param state
	 */
	public Diamond(Position pos, StatusFallableEnum state)
	{
		super(pos, true, false, true, state);
		this.setSpritechar(ElementChar.X);
		this.putPassables();
	}

	/**
	 * El diamante es recolectado
	 */
	public void collected()
	{
		this.die();
	}

	@Override
	public void fall()
	{
		if (this.canGoDown() && this.isIdle())
		{
			this.state = StatusFallableEnum.FALLINGOFF;
		}
		else if (this.canGoDown() && this.isFalling())
		{
			this.state = StatusFallableEnum.FALLING;
		}
		else if (this.itemBelowIsRounded() && this.itemCanSlide() && this.canGoUp())
		{
			if (this.canGoLeft() && this.canGoDownLeft())
			{
				this.state = StatusFallableEnum.SLIDINGLEFT;
			}
			else if (this.canGoRight() && this.canGoDownRight())
			{
				this.state = StatusFallableEnum.SLIDINGRIGHT;
			}
			else
			{
				this.state = StatusFallableEnum.IDLE;
			}
		}
		else if (this.itemBelowIsWall() && this.itemBelowIsMagic())
		{
			this.state = StatusFallableEnum.CONVERT;
		}
		else
		{
			this.state = StatusFallableEnum.IDLE;
		}
	}

	@Override
	public void makeMove()
	{
		switch (this.state)
		{
			case FALLINGOFF:
				if (this.canGoDown())
				{
					this.getPosition().goDown();
				}
				break;
			case FALLING:
				if (this.canGoDown())
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
			case CONVERT:
				MapCell.getWall(this.getPosition().getX(), this.getPosition().checkDown()).conversion(this);
				this.state = StatusFallableEnum.IDLE;
				break;
			default:
				this.state = StatusFallableEnum.IDLE;
				break;
		}
	}

	// METODOS SIMPLES

	/**
	 * Pone los passables de Diamante.
	 */
	private void putPassables()
	{
		this.getPassable().put(ElementChar._.hashCode(), ElementChar._);
		this.getPassable().put(ElementChar.R.hashCode(), ElementChar.R);
	}

}
