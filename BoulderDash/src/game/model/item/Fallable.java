package game.model.item;

import game.model.Position;
import game.model.map.MapInstance;
import game.model.map.MapItem;

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
	
	public void fall() // Enumerativo para checkear si puede pasar
	{
		//Ve si la posicion que esta abajo esta vacia
		if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) < 1
				&& this.state == StatusFallableEnum.IDLE)
		{
			//Si esta vacia, lo deja cayendo
			this.state = StatusFallableEnum.FALLINGOFF;
		}
		else if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) < 2
				&& this.state == StatusFallableEnum.FALLINGOFF)
		{
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
			case FALLINGOFF:
					this.getPosition().goDown();
				break;
			case FALLING:
					this.getPosition().goDown();
					this.state = StatusFallableEnum.IDLE;
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
