package game.model.item;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.actor.Rockford;
import game.model.map.MapActor;
import game.model.map.MapInstance;
import game.model.map.MapItem;

/**
 * Esta clase representa una roca, junto con su caracter de representacion y un
 * booleano para ver si esta siendo empujado o no, junto con otras propiedades
 * de otros items
 */
public class Rock extends Fallable
{
	private SpriteChar spritechar = SpriteChar.O;

	// CONSTRUCTOR

	public Rock(Position pos)
	{
		super(pos, false, true, true, false, true, SolidTo.ALL, StatusFallableEnum.IDLE);
	}

	public Rock(Position pos, StatusFallableEnum state)
	{
		super(pos, false, true, true, false, true, SolidTo.ALL, state);
	}

	// GETTERS

	/**
	 * Permite obtener al caracter de una roca
	 * 
	 */
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	// MOVIMIENTO

	/**
	 * Permite modificar el estado de la roca si esta siendo pusheada por
	 * rockford
	 * 
	 * @param player:
	 *            El objeto que representa a rockford
	 */
	public void pushed(Rockford player)
	{
		if (player.isPushing() && this.isMoveable())
		{
			switch (player.getState())
			{
				case MOVINGLEFT:
					this.state = StatusFallableEnum.SLIDINGLEFT;
					break;
				case MOVINGRIGHT:
					this.state = StatusFallableEnum.SLIDINGRIGHT;
					break;
				default:
					break;
			}
		}
	}

	// FALL

	public void fall()
	{
		if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.NONE
				&& this.state == StatusFallableEnum.IDLE)
		{
			this.state = StatusFallableEnum.FALLINGOFF;
		}
		else if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.NONE
				|| MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.ACTOR
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

	public void makeMove()
	{
		switch (this.state)
		{
			case FALLINGOFF:
				this.getPosition().goDown();
				break;
			case FALLING:
				if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.NONE
				|| MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.ACTOR)
				{
					this.getPosition().goDown();
				}
				else
				{
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
			default:
				break;
		}
	}

}
