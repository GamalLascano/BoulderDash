package game.item;

import game.Position;
import game.map.MapActor;
import game.map.MapCell;
import game.map.MapInstance;
import game.map.MapItem;

public class Fallable extends Item
{
	protected StatusFallableEnum state;
	
	public Fallable(Position pos, boolean collectable, boolean moveable, boolean fallable, boolean explodable,
			boolean rounded, int solid, StatusFallableEnum state)
	{
		super(pos, collectable, moveable, fallable, explodable, rounded, solid);
		this.state = state;
	}

	// GETTERS
	
	public StatusFallableEnum getState()
	{
		return state;
	}
	
	// SETTERS
	
	public void setState(StatusFallableEnum state)
	{
		this.state = state;
	}
	
	// FALL
	
	/** Este metodo se usa para comprobar si el item se cae o no, y setear el estado necesario
	 * 
	 */
	public void fall()
	{
		//Ve si la posicion que esta abajo esta vacia
		if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) < 1)
		{
			//Si esta vacia, lo deja cayendo
			this.state = StatusFallableEnum.FALLING;
		}
		else if (MapItem.getItem(this.getPosition().getX(), this.getPosition().checkDown()).isRounded())
		{
			// Si estan los los lados vacios, elije el lado de la izquierda
			if (MapInstance.solid(this.getPosition().checkLeft(), this.getPosition().getY()) < 1
					&& MapInstance.solid(this.getPosition().checkLeft(), this.getPosition().checkDown()) < 1)
			{
				this.state = StatusFallableEnum.SLIDINGLEFT;
			}
			else if (MapInstance.solid(this.getPosition().checkRight(), this.getPosition().getY()) < 1
					&& MapInstance.solid(this.getPosition().checkRight(), this.getPosition().checkDown()) < 1)
			{
				this.state = StatusFallableEnum.SLIDINGRIGHT;
			}
		}
	}

	// REFRESH POSITION

	public void changePosition()
	{
		MapItem.removeItem(this.getPosition());
		this.fall();
		this.makeMove();
		MapItem.setItem(this);
	}
	
	public void makeMove()
	{
		switch (this.state)
		{
			case FALLING:
				if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) < 1)
				{
					this.getPosition().goDown();
				}
				break;
			case SLIDINGRIGHT:
				if (MapInstance.solid(this.getPosition().checkRight(), this.getPosition().getY()) < 1)
				{
					this.getPosition().goRight();
				}
				break;
			case SLIDINGLEFT:
				if (MapInstance.solid(this.getPosition().checkLeft(), this.getPosition().getY()) < 1)
				{
					this.getPosition().goLeft();
				}
				break;
			default:
				break;
		}
		this.state = StatusFallableEnum.IDLE;
	}
	
}
