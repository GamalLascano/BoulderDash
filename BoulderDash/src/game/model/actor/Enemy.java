package game.model.actor;

import game.model.ListOfEntities;
import game.model.Position;
import game.model.item.Diamond;
import game.model.map.MapActor;
import game.model.map.MapInstance;
import game.model.map.MapItem;

/**
 * Clase que representa a los enemigos.
 */
public abstract class Enemy extends Actor
{

	/**
	 * Constructor de los enemigos.
	 * 
	 * @param pos
	 */
	public Enemy(Position pos)
	{
		super(pos);
		this.state = StatusActorEnum.IDLE;
	}

	/**
	 * Explosion, pone celda vacias alrededor del enemigo. Explosion cuadrada
	 * 3x3 que genera diamantes.
	 */
	@Override
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
	 * Verifica si Rockford esta en el alcanze del enemigo. Verifica en su
	 * cuardrado 3x3.
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

	@Override
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

	@Override
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

	@Override
	public void makeMoveUp()
	{
		if (this.canGoUp())
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

	@Override
	public void makeMoveDown()
	{
		if (this.canGoDown())
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

	@Override
	public void makeMoveRight()
	{
		if (this.canGoRight())
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

	@Override
	public void makeMoveLeft()
	{
		if (this.canGoLeft())
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
