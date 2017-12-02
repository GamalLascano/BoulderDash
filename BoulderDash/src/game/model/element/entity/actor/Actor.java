package game.model.element.entity.actor;

import game.model.Direction;
import game.model.element.Position;
import game.model.element.entity.Entity;
import game.model.element.entity.ListOfEntities;
import game.model.element.entity.Moveable;
import game.model.map.MapActor;
import game.model.map.MapInstance;

/**
 * Esta clase representa a todos los actores, que pueden moverse
 */
public abstract class Actor extends Entity implements Moveable
{
	protected StatusActorEnum state;

	/**
	 * Constructor de actor.
	 * 
	 * @param pos
	 */
	public Actor(Position pos)
	{
		super(pos);
		this.state = StatusActorEnum.IDLE;
		this.putEmptyPassable();
	}

	/**
	 * Retorna el estado del actor.
	 * 
	 * @return StatusActorEnum
	 */
	public StatusActorEnum getState()
	{
		return state;
	}

	/**
	 * Setea el estado del actor.
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
	 * Utiliza la direccion y cambia el estado para el movimiento.
	 * 
	 * @param direction
	 */
	public void move(Direction direction)
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

	@Override
	public void changePosition()
	{
		MapActor.removeActor(this.getPosition());
		this.makeMove();
		MapActor.setActor(this);
	}

	@Override
	public void die()
	{
		if (state != StatusActorEnum.DEAD)
		{
			state = StatusActorEnum.DEAD;
			this.explode();
		}
		ListOfEntities.getList().remove(this);
		MapActor.removeActor(this.getPosition());
	}

}
