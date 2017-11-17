package game.model.actor;

import game.model.ListOfEntities;
import game.model.Position;
import game.model.SolidTo;
import game.model.item.Diamond;
import game.model.map.MapActor;
import game.model.map.MapInstance;
import game.model.map.MapItem;

/**
 * 
 *
 */
public abstract class Enemy extends Actor
{

	/**
	 * 
	 * @param pos
	 */
	public Enemy(Position pos)
	{
		super(pos);
		this.state = StatusActorEnum.IDLE;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	protected abstract void rotate();

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
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkLeft(), this.getPosition().getY())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkLeft(), this.getPosition().checkUp())));
	}

	/**
	 * Explosion, pone celda vacias alrededor del personaje. Explosion cuadrada
	 * 3x3.
	 * 
	 * @return
	 */
	public boolean isRockfordInRange()
	{
		if (MapActor.getRockford(this.getPosition().getX(), this.getPosition().getY()) != null
				|| MapActor.getRockford(this.getPosition().getX(), this.getPosition().checkUp()) != null
				|| MapActor.getRockford(this.getPosition().checkRight(), this.getPosition().checkUp()) != null
				|| MapActor.getRockford(this.getPosition().checkRight(), this.getPosition().getY()) != null
				|| MapActor.getRockford(this.getPosition().checkRight(), this.getPosition().checkDown()) != null
				|| MapActor.getRockford(this.getPosition().getX(), this.getPosition().checkDown()) != null
				|| MapActor.getRockford(this.getPosition().checkLeft(), this.getPosition().checkDown()) != null
				|| MapActor.getRockford(this.getPosition().checkLeft(), this.getPosition().getY()) != null
				|| MapActor.getRockford(this.getPosition().checkLeft(), this.getPosition().checkUp()) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 */
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

	/**
	 * Se occupa de hacer mover a los enemigos.
	 * 
	 */
	public void makeMove()
	{
		switch (this.state)
		{
			case MOVINGUP:
				makeMoveUp();
				break;
			case MOVINGDOWN:
				makeMoveDown();
				break;
			case MOVINGRIGHT:
				makeMoveRight();
				break;
			case MOVINGLEFT:
				makeMoveLeft();
				break;
			default:
				break;
		}
	}

	/**
	 * 
	 */
	public void makeMoveUp()
	{
		if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkUp()) == SolidTo.NONE)
		{
			this.getPosition().goUp();
			if (this.isRockfordInRange())
			{
				this.die();
			}
		}
		else
		{
			this.rotate();
		}
	}

	/**
	 * 
	 */
	public void makeMoveDown()
	{
		if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) == SolidTo.NONE)
		{
			this.getPosition().goDown();
			if (this.isRockfordInRange())
			{
				this.die();
			}
		}
		else
		{
			this.rotate();
		}
	}

	/**
	 * 
	 */
	public void makeMoveRight()
	{
		if (MapInstance.solid(this.getPosition().checkRight(), this.getPosition().getY()) == SolidTo.NONE)
		{
			this.getPosition().goRight();
			if (this.isRockfordInRange())
			{
				this.die();
			}
		}
		else
		{
			this.rotate();
		}
	}

	/**
	 * 
	 */
	public void makeMoveLeft()
	{
		if (MapInstance.solid(this.getPosition().checkLeft(), this.getPosition().getY()) == SolidTo.NONE)
		{
			this.getPosition().goLeft();
			if (this.isRockfordInRange())
			{
				this.die();
			}
		}
		else
		{
			this.rotate();
		}
	}

}
