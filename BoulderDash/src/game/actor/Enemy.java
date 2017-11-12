package game.actor;

import game.Position;
import game.item.Diamond;
import game.map.MapCell;
import game.map.MapInstance;
import game.map.MapItem;

public abstract class Enemy extends Actor
{

	// CONSTRUCTOR
	
	public Enemy(Position pos)
	{
		super(pos, StatusActorEnum.IDLE);
		// TODO Auto-generated constructor stub
	}
	
	// ROTATE

	protected abstract void rotate();

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
	
	// EXPLODE WITH DIAMONDS
	
	/**
	 * Explosion, pone celda vacias alrededor del personaje. Explosion cuadrada
	 * 3x3.
	 */
	public void explode()
	{
		MapInstance.kill(this.getPosition().getX(), this.getPosition().getY());
		MapInstance.kill(this.getPosition().getX(), this.getPosition().checkUp());
		MapInstance.kill(this.getPosition().checkRight(), this.getPosition().checkUp());
		MapInstance.kill(this.getPosition().checkRight(), this.getPosition().getY());
		MapInstance.kill(this.getPosition().checkRight(), this.getPosition().checkDown());
		MapInstance.kill(this.getPosition().getX(), this.getPosition().checkDown());
		MapInstance.kill(this.getPosition().checkLeft(), this.getPosition().checkDown());
		MapInstance.kill(this.getPosition().checkLeft(), this.getPosition().getY());
		MapInstance.kill(this.getPosition().checkLeft(), this.getPosition().checkUp());
		
		MapItem.setItem( new Diamond( new Position( this.getPosition().getX() , this.getPosition().getY() ) ) );
		MapItem.setItem( new Diamond( new Position( this.getPosition().getX() , this.getPosition().checkUp() ) ) );
		MapItem.setItem( new Diamond( new Position( this.getPosition().checkRight() , this.getPosition().checkUp() ) ) );
		MapItem.setItem( new Diamond( new Position( this.getPosition().checkRight() , this.getPosition().getY() ) ) );
		MapItem.setItem( new Diamond( new Position( this.getPosition().checkRight() , this.getPosition().checkDown() ) ) );
		MapItem.setItem( new Diamond( new Position( this.getPosition().getX() , this.getPosition().checkDown() ) ) );
		MapItem.setItem( new Diamond( new Position( this.getPosition().checkLeft() , this.getPosition().checkDown() ) ) );
		MapItem.setItem( new Diamond( new Position( this.getPosition().checkRight() , this.getPosition().getY() ) ) );
		MapItem.setItem( new Diamond( new Position( this.getPosition().checkLeft() , this.getPosition().checkUp() ) ) );
	}
	
	// MAKE MOVE
	
	/**
	 * Se occupa de hacer mover a los enemigos.
	 * 
	 */
	public void makeMove()
	{
		switch (this.state)
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
	}
	
}
