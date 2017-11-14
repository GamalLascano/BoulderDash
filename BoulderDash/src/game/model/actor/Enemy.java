package game.model.actor;

import game.model.ListOfEntities;
import game.model.Position;
import game.model.SolidTo;
import game.model.item.Diamond;
import game.model.map.MapActor;
import game.model.map.MapInstance;
import game.model.map.MapItem;

public abstract class Enemy extends Actor
{

	// CONSTRUCTOR

	public Enemy(Position pos)
	{
		super(pos);
		this.state = StatusActorEnum.IDLE;
		// TODO Auto-generated constructor stub
	}

	// ROTATE

	protected abstract void rotate();

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

		MapItem.setItem(new Diamond(new Position(this.getPosition().getX(), this.getPosition().getY())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().getX(), this.getPosition().checkUp())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkRight(), this.getPosition().checkUp())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkRight(), this.getPosition().getY())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkRight(), this.getPosition().checkDown())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().getX(), this.getPosition().checkDown())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkLeft(), this.getPosition().checkDown())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkRight(), this.getPosition().getY())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkLeft(), this.getPosition().checkUp())));
	}

	// DIE
	
	public void die()
	{
		if (this.state != StatusActorEnum.DEAD)
		{
			this.state = StatusActorEnum.DEAD;
			this.explode();
		}
		ListOfEntities.getList().remove(this);
		MapActor.removeActor(this.getPosition());
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
				if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkUp()) == SolidTo.NONE)
				{
					this.getPosition().goUp();
				}
				else
				{
					this.rotate();
				}
				break;
			case MOVINGDOWN:
				if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.NONE)
				{
					this.getPosition().goDown();
				}
				else
				{
					this.rotate();
				}
				break;
			case MOVINGRIGHT:
				if (MapInstance.solid(this.getPosition().checkRight(), this.getPosition().getY()) == SolidTo.NONE)
				{
					this.getPosition().goRight();
				}
				else
				{
					this.rotate();
				}
				break;
			case MOVINGLEFT:
				if (MapInstance.solid(this.getPosition().checkLeft(), this.getPosition().getY()) == SolidTo.NONE)
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
