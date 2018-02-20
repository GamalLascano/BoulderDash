package game.model.element.entity.item;

import game.model.element.Position;
import game.model.element.ElementTypes;
import game.model.element.entity.actor.Rockford;
import game.model.map.MapElement;

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
		this.setElementType(ElementTypes.Rock);
		this.getPassable().put(ElementTypes.Empty.hashCode(), ElementTypes.Empty);
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
		this.setElementType(ElementTypes.Rock);
		this.getPassable().put(ElementTypes.Empty.hashCode(), ElementTypes.Empty);
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
				if (MapElement.getElement(this.getPosition()) != null)
				{
					MapElement.getElement(this.getPosition()).die();
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
				MapElement.getWall(this.getPosition().getX(), this.getPosition().checkDown()).conversion(this);
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
		this.getPassable().put(ElementTypes.Rockford.hashCode(), ElementTypes.Rockford);
		this.getPassable().put(ElementTypes.RockfordUp.hashCode(), ElementTypes.RockfordUp);
		this.getPassable().put(ElementTypes.RockfordDown.hashCode(), ElementTypes.RockfordDown);
		this.getPassable().put(ElementTypes.RockfordLeft.hashCode(), ElementTypes.RockfordLeft);
		this.getPassable().put(ElementTypes.RockfordRight.hashCode(), ElementTypes.RockfordRight);
		this.getPassable().put(ElementTypes.Butterfly.hashCode(), ElementTypes.Butterfly);
		this.getPassable().put(ElementTypes.Firefly.hashCode(), ElementTypes.Firefly);
	}

	/**
	 * Remueve los passables de Rock.
	 */
	private void removeFallingPassables()
	{
		this.getPassable().remove(ElementTypes.Rockford.hashCode(), ElementTypes.Rockford);
		this.getPassable().remove(ElementTypes.RockfordUp.hashCode(), ElementTypes.RockfordUp);
		this.getPassable().remove(ElementTypes.RockfordDown.hashCode(), ElementTypes.RockfordDown);
		this.getPassable().remove(ElementTypes.RockfordLeft.hashCode(), ElementTypes.RockfordLeft);
		this.getPassable().remove(ElementTypes.RockfordRight.hashCode(), ElementTypes.RockfordRight);
		this.getPassable().remove(ElementTypes.Butterfly.hashCode(), ElementTypes.Butterfly);
		this.getPassable().remove(ElementTypes.Firefly.hashCode(), ElementTypes.Firefly);
	}

}
