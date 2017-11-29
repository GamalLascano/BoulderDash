package game.model.item;

import game.model.Position;
import game.model.SpriteChar;
import game.model.map.MapCell;
import game.model.map.MapItem;
import game.model.map.MapVisual;

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
		this.setSpritechar(SpriteChar.X);
		this.getPassable().put(SpriteChar._.hashCode(), SpriteChar._);
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
		this.setSpritechar(SpriteChar.X);
		this.getPassable().put(SpriteChar._.hashCode(), SpriteChar._);
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
		if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().getX(), this.getPosition().checkDown()).hashCode())
				&& this.state == StatusFallableEnum.IDLE)
		{
			this.state = StatusFallableEnum.FALLINGOFF;
		}
		else if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().getX(), this.getPosition().checkDown()).hashCode())
				&& this.state == StatusFallableEnum.FALLINGOFF || this.state == StatusFallableEnum.FALLING)
		{
			this.state = StatusFallableEnum.FALLING;
		}
		else if (MapItem.getItem(this.getPosition().getX(), this.getPosition().checkDown()).isRounded())
		{
			if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().checkLeft(), this.getPosition().getY()).hashCode())
					&& this.getPassable().containsKey(MapVisual.getChar(this.getPosition().checkLeft(), this.getPosition().checkDown()).hashCode()))
			{
				this.state = StatusFallableEnum.SLIDINGLEFT;
			}
			else if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().checkRight(), this.getPosition().getY()).hashCode())
					&& this.getPassable().containsKey(MapVisual.getChar(this.getPosition().checkRight(), this.getPosition().checkDown()).hashCode()))
			{
				this.state = StatusFallableEnum.SLIDINGRIGHT;
			}
		}
		else if (MapCell.getWall(this.getPosition().getX(), this.getPosition().checkDown()) != null)
		{
			if (MapCell.getWall(this.getPosition().getX(), this.getPosition().checkDown()).getMagicTimer() > 0)
			{
				this.state = StatusFallableEnum.CONVERT;
			}
			else
			{
				this.state = StatusFallableEnum.IDLE;
			}
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
				this.getPosition().goDown();
				break;
			case FALLING:
				if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().getX(), this.getPosition().checkDown()).hashCode()))
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

}
