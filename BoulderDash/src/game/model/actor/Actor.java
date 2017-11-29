package game.model.actor;

import game.model.Direction;
import game.model.Entity;
import game.model.Position;
import game.model.SpriteChar;
import game.model.map.MapActor;
import game.model.map.MapInstance;

/**
 * Esta clase representa a todos los actores, que pueden moverse
 */
public abstract class Actor extends Entity
{
	protected StatusActorEnum state;

	/**
	 * Constructor de actor.
	 * @param pos
	 */
	public Actor(Position pos)
	{
		super(pos);
		this.state = StatusActorEnum.IDLE;
		this.getPassable().put(SpriteChar._.hashCode(), SpriteChar._);
	}

	/**
	 * Retorna el estado del actor.
	 * @return StatusActorEnum
	 */
	public StatusActorEnum getState()
	{
		return state;
	}

	/**
	 * Setea el estado del actor.
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
	
	/**
	 * Comportamiento para mover el actor hacia arriba.
	 */
	protected abstract void makeMoveUp();
	
	/**
	 * Comportamiento para mover el actor hacia abajo.
	 */
	protected abstract void makeMoveDown();
	
	/**
	 * Comportamiento para mover el actor hacia la izquierda.
	 */
	protected abstract void makeMoveLeft();
	
	/**
	 * Comportamiento para mover el actor hacia la derecha.
	 */
	protected abstract void makeMoveRight();


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
