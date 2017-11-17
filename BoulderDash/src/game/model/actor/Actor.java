package game.model.actor;

import game.model.CurrentDirection;
import game.model.Entity;
import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.map.MapActor;
import game.model.map.MapInstance;

/**
 * Esta clase representa a todos los actores, que pueden moverse
 *
 */
public abstract class Actor extends Entity
{
	protected StatusActorEnum state;
	private SpriteChar spritechar;

	/**
	 * 
	 * @param pos
	 */
	public Actor(Position pos)
	{
		super(pos, SolidTo.ACTOR);
		state = StatusActorEnum.IDLE;
	}

	/**
	 * 
	 * @return
	 */
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	/**
	 * 
	 * @return
	 */
	public StatusActorEnum getState()
	{
		return state;
	}

	/**
	 * 
	 * @param stat
	 */
	public void setState(StatusActorEnum stat)
	{
		state = stat;
	}

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
	}

	/**
	 * 
	 * @param direction
	 */
	public void move(CurrentDirection direction)
	{
		switch (direction)
		{
			case UP:
				this.state = StatusActorEnum.MOVINGUP;
				break;
			case DOWN:
				this.state = StatusActorEnum.MOVINGDOWN;
				break;
			case LEFT:
				this.state = StatusActorEnum.MOVINGLEFT;
				break;
			case RIGHT:
				this.state = StatusActorEnum.MOVINGRIGHT;
				break;
			default:
				break;
		}
	}

	/**
	 * 
	 */
	public void changePosition()
	{
		MapActor.removeActor(this.getPosition());
		this.makeMove();
		MapActor.setActor(this);
	}

	// public void die()
	// {
	// if (state != StatusActorEnum.DEAD)
	// {
	// state = StatusActorEnum.DEAD;
	// this.explode();
	// }
	// ListOfEntities.getList().remove(this);
	// MapActor.removeActor(this.getPosition());
	// }

}
