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
		//Se checkea la posicion de abajo del objeto
		Position posDown = new Position(super.getPosition().getX(), super.getPosition().checkDown());
		//Ve si la posicion que esta abajo esta vacia
		if (MapCell.isEmpty(posDown))
		{
			//Si esta vacia, lo deja cayendo
			this.state = StatusFallableEnum.FALLING;
		}
		else
		{
			//Sino, si el objeto es redondo, elije uno de los lados para caer, y cae
			if (MapItem.getItem(posDown).isRounded())
			{
				Position posLeft = new Position(this.getPosition().checkLeft(), this.getPosition().getY());
				Position posRight = new Position(this.getPosition().checkRight(), this.getPosition().getY());
				//Si estan los los lados vacios, elije el lado de la izquierda
				if (MapCell.isEmpty(posLeft))
				{
					this.state = StatusFallableEnum.SLIDINGLEFT;
				}
				else if (MapCell.isEmpty(posRight))
				{
					this.state = StatusFallableEnum.SLIDINGRIGHT;
				}
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
