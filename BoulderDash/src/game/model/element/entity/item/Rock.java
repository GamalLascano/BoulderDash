package game.model.element.entity.item;

import game.model.element.Position;
import game.model.element.ElementChar;
import game.model.element.entity.actor.Rockford;
import game.model.map.MapActor;
import game.model.map.MapCell;

/**
 * Esta clase representa una roca, junto con su caracter de representacion y un
 * booleano para ver si esta siendo empujado o no, junto con otras propiedades
 * de otros items
 */
public class Rock extends Fallable
{

	/**
	 * Constructor de rocas.
	 * 
	 * @param pos
	 */
	public Rock(Position pos)
	{
		super(pos, false, true, true, StatusFallableEnum.IDLE);
		this.setSpritechar(ElementChar.O);
		this.getPassable().put(ElementChar._.hashCode(), ElementChar._);
	}

	/**
	 * Constructor de rocas con estado inicial.
	 * 
	 * @param pos
	 * @param state
	 */
	public Rock(Position pos, StatusFallableEnum state)
	{
		super(pos, false, true, true, state);
		this.setSpritechar(ElementChar.O);
		this.getPassable().put(ElementChar._.hashCode(), ElementChar._);
	}

	/**
	 * Permite modificar el estado de la roca si esta siendo pusheada por
	 * rockford
	 * 
	 * @param player:
	 *            El objeto que representa a rockford
	 */
	public void pushed(Rockford player)
	{
		if (player.isPushing())
		{
			switch (player.getState())
			{
				case MOVINGLEFT:
					this.state = StatusFallableEnum.PUSHEDLEFT;
					break;
				case MOVINGRIGHT:
					this.state = StatusFallableEnum.PUSHEDRIGHT;
					break;
				default:
					break;
			}
		}
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
		else if (this.state == StatusFallableEnum.PUSHEDLEFT)
		{
			this.state = StatusFallableEnum.SLIDINGLEFT;
		}
		else if (this.state == StatusFallableEnum.PUSHEDRIGHT)
		{
			this.state = StatusFallableEnum.SLIDINGRIGHT;
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
					this.putFallingPassables();
				}
				break;
			case FALLING:
				if (this.canGoDown())
				{
					this.getPosition().goDown();
				}
				else
				{
					this.removeFallingPassables();
					this.state = StatusFallableEnum.IDLE;
				}
				if (MapActor.getActor(this.getPosition()) != null)
				{
					MapActor.getActor(this.getPosition()).die();
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
			// case PUSHEDLEFT:
			// this.getPosition().goRight();
			// break;
			// case PUSHEDRIGHT:
			// this.getPosition().goLeft();
			// break;
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
	 * Pone los passables de Rock.
	 */
	private void putFallingPassables()
	{
		this.getPassable().put(ElementChar.R.hashCode(), ElementChar.R);
		this.getPassable().put(ElementChar.n.hashCode(), ElementChar.n);
		this.getPassable().put(ElementChar.u.hashCode(), ElementChar.u);
		this.getPassable().put(ElementChar.d.hashCode(), ElementChar.d);
		this.getPassable().put(ElementChar.b.hashCode(), ElementChar.b);
		this.getPassable().put(ElementChar.B.hashCode(), ElementChar.B);
		this.getPassable().put(ElementChar.F.hashCode(), ElementChar.F);
	}

	/**
	 * Remueve los passables de Rock.
	 */
	private void removeFallingPassables()
	{
		this.getPassable().remove(ElementChar.R.hashCode(), ElementChar.R);
		this.getPassable().remove(ElementChar.n.hashCode(), ElementChar.n);
		this.getPassable().remove(ElementChar.u.hashCode(), ElementChar.u);
		this.getPassable().remove(ElementChar.d.hashCode(), ElementChar.d);
		this.getPassable().remove(ElementChar.b.hashCode(), ElementChar.b);
		this.getPassable().remove(ElementChar.B.hashCode(), ElementChar.B);
		this.getPassable().remove(ElementChar.F.hashCode(), ElementChar.F);
	}

}
