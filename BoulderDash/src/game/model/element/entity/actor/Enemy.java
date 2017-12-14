package game.model.element.entity.actor;

import game.model.element.Position;
import game.model.element.entity.item.Diamond;
import game.model.map.MapElement;
import game.model.map.MapInstance;

/**
 * Clase que representa a los enemigos, los que
 * pueden matar al jugador con proximidad.
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

	@Override
	public void die()
	{
		if (this.state != StatusActorEnum.DEAD)
		{
			this.state = StatusActorEnum.DEAD;
			this.explode();
		}
		removeEntity(this);
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
			if (isRockfordInRange())
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
			if (isRockfordInRange())
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
			if (isRockfordInRange())
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
			if (isRockfordInRange())
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
	 * Explosion, pone celda vacias alrededor del enemigo. Explosion cuadrada
	 * 3x3 que genera diamantes.
	 */
	@Override
	public void explode()
	{
		MapInstance.getInstance().kill(this.getPosition().getX(), this.getPosition().getY());
		MapInstance.getInstance().kill(this.getPosition().getX(), this.getPosition().checkUp());
		MapInstance.getInstance().kill(this.getPosition().checkRight(), this.getPosition().checkUp());
		MapInstance.getInstance().kill(this.getPosition().checkRight(), this.getPosition().getY());
		MapInstance.getInstance().kill(this.getPosition().checkRight(), this.getPosition().checkDown());
		MapInstance.getInstance().kill(this.getPosition().getX(), this.getPosition().checkDown());
		MapInstance.getInstance().kill(this.getPosition().checkLeft(), this.getPosition().checkDown());
		MapInstance.getInstance().kill(this.getPosition().checkLeft(), this.getPosition().getY());
		MapInstance.getInstance().kill(this.getPosition().checkLeft(), this.getPosition().checkUp());

		MapElement.setItem(new Diamond(new Position(this.getPosition().getX(), this.getPosition().getY())));
		MapElement.setItem(new Diamond(new Position(this.getPosition().getX(), this.getPosition().checkUp())));
		MapElement.setItem(new Diamond(new Position(this.getPosition().checkRight(), this.getPosition().checkUp())));
		MapElement.setItem(new Diamond(new Position(this.getPosition().checkRight(), this.getPosition().getY())));
		MapElement.setItem(new Diamond(new Position(this.getPosition().checkRight(), this.getPosition().checkDown())));
		MapElement.setItem(new Diamond(new Position(this.getPosition().getX(), this.getPosition().checkDown())));
		MapElement.setItem(new Diamond(new Position(this.getPosition().checkLeft(), this.getPosition().checkDown())));
		MapElement.setItem(new Diamond(new Position(this.getPosition().checkLeft(), this.getPosition().getY())));
		MapElement.setItem(new Diamond(new Position(this.getPosition().checkLeft(), this.getPosition().checkUp())));
	}

	/**
	 * Verifica si Rockford esta en el alcanze del enemigo. Verifica en su
	 * cuardrado 3x3.
	 * 
	 * @return
	 */
	public boolean isRockfordInRange()
	{
		if (MapElement.getRockford(this.getPosition().getX(), this.getPosition().getY()) != null
				|| MapElement.getRockford(this.getPosition().getX(), this.getPosition().checkUp()) != null
				|| MapElement.getRockford(this.getPosition().checkRight(), this.getPosition().checkUp()) != null
				|| MapElement.getRockford(this.getPosition().checkRight(), this.getPosition().getY()) != null
				|| MapElement.getRockford(this.getPosition().checkRight(), this.getPosition().checkDown()) != null
				|| MapElement.getRockford(this.getPosition().getX(), this.getPosition().checkDown()) != null
				|| MapElement.getRockford(this.getPosition().checkLeft(), this.getPosition().checkDown()) != null
				|| MapElement.getRockford(this.getPosition().checkLeft(), this.getPosition().getY()) != null
				|| MapElement.getRockford(this.getPosition().checkLeft(), this.getPosition().checkUp()) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


}
