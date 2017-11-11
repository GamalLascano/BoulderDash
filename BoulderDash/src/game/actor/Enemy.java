package game.actor;

import game.Position;
import game.map.MapCell;
import game.map.MapItem;

public abstract class Enemy extends Actor
{

	public Enemy(StatusActor state, Position pos, int solid)
	{
		super(state, pos, solid);
		// TODO Auto-generated constructor stub
	}
	
	// S

	protected abstract void rotate();

	// S
	
	/**
	 * Explosion, pone celda vacias alrededor del personaje. Explosion cuadrada
	 * 3x3.
	 */
	public abstract void explode();

	// Recibe una posicion, y ve si esta en un radio de 3x3
	public boolean isInRange(Position pos) {
		if( ( pos.getX() == super.getPosition().checkLeft() ) || ( pos.getX() == super.getPosition().getX() ) || ( pos.getX() == super.getPosition().checkRight() ) ) 
		{
			if ( ( pos.getY() == super.getPosition().checkDown() ) || ( pos.getY() == super.getPosition().getY() ) || ( pos.getY() == super.getPosition().checkUp() ) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else 
		{
			return false;
		}
	}
	
	// MAKE MOVE
	
	/**
	 * Se occupa de hacer mover a los enemigos.
	 * 
	 */
	public void makeMove()
	{
		switch (this.getState().getMovementState())
		{
			case MOVINGUP:
				if (MapCell.getCell(this.getPosition().getX(), this.getPosition().checkUp()).isSolid() < 1
						&& MapItem.getItem(this.getPosition().getX(), this.getPosition().checkUp()).isSolid() < 1)
				{
					this.getPosition().goUp();
				}
				else
				{
					this.rotate();
				}
				break;
			case MOVINGDOWN:
				if (MapCell.getCell(this.getPosition().getX(), this.getPosition().checkDown()).isSolid() < 1
						&& MapItem.getItem(this.getPosition().getX(), this.getPosition().checkDown()).isSolid() < 1)
				{
					this.getPosition().goDown();
				}
				else
				{
					this.rotate();
				}
				break;
			case MOVINGRIGHT:
				if (MapCell.getCell(this.getPosition().checkRight(), this.getPosition().getY()).isSolid() < 1
						&& MapItem.getItem(this.getPosition().checkRight(), this.getPosition().getY()).isSolid() < 1)
				{
					this.getPosition().goRight();
				}
				else
				{
					this.rotate();
				}
				break;
			case MOVINGLEFT:
				if (MapCell.getCell(this.getPosition().checkLeft(), this.getPosition().getY()).isSolid() < 1
						&& MapItem.getItem(this.getPosition().checkLeft(), this.getPosition().getY()).isSolid() < 1)
				{
					this.getPosition().goLeft();
				}
				else
				{
					this.rotate();
				}
				break;
			default:
				break;
		}
		this.getState().setMovementState(StatusActorEnum.IDLE);
	}
	
}
