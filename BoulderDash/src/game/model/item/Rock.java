package game.model.item;

import game.model.Position;
import game.model.SpriteChar;
import game.model.actor.Rockford;
import game.model.map.MapActor;
import game.model.map.MapCell;
import game.model.map.MapItem;
import game.model.map.MapVisual;

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
		this.setSpritechar(SpriteChar.O);
		this.getPassable().put(SpriteChar._.hashCode(), SpriteChar._);
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
		this.setSpritechar(SpriteChar.O);
		this.getPassable().put(SpriteChar._.hashCode(), SpriteChar._);
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
		else if (MapCell.getWall(this.getPosition().getX(), this.getPosition().checkDown()) != null
				&& MapCell.getWall(this.getPosition().getX(), this.getPosition().checkDown()).getMagicTimer() > 0)
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
				this.getPosition().goDown();
				this.getPassable().put(SpriteChar.R.hashCode(), SpriteChar.R);
				this.getPassable().put(SpriteChar.n.hashCode(), SpriteChar.n);
				this.getPassable().put(SpriteChar.u.hashCode(), SpriteChar.u);
				this.getPassable().put(SpriteChar.d.hashCode(), SpriteChar.d);
				this.getPassable().put(SpriteChar.b.hashCode(), SpriteChar.b);
				this.getPassable().put(SpriteChar.B.hashCode(), SpriteChar.B);
				this.getPassable().put(SpriteChar.F.hashCode(), SpriteChar.F);
				break;
			case FALLING:
				if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().getX(), this.getPosition().checkDown()).hashCode()))
				{
					this.getPosition().goDown();
				}
				else
				{
					this.getPassable().remove(SpriteChar.R.hashCode(), SpriteChar.R);
					this.getPassable().remove(SpriteChar.n.hashCode(), SpriteChar.n);
					this.getPassable().remove(SpriteChar.u.hashCode(), SpriteChar.u);
					this.getPassable().remove(SpriteChar.d.hashCode(), SpriteChar.d);
					this.getPassable().remove(SpriteChar.b.hashCode(), SpriteChar.b);
					this.getPassable().remove(SpriteChar.B.hashCode(), SpriteChar.B);
					this.getPassable().remove(SpriteChar.F.hashCode(), SpriteChar.F);
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
