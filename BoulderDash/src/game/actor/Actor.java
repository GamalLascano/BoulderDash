package game.actor;

import game.CurrentDirection;
import game.Position;
import game.SpriteChar;
import game.map.MapActor;
import game.map.MapInstance;
import game.Entity;
import game.ListOfEntities;

/**
 * Esta clase representa a todos los actores, que pueden moverse
 *
 */
public abstract class Actor extends Entity
{
	protected StatusActorEnum state;
	private SpriteChar spritechar;

	// CONSTRUCTORS
	
	public Actor(Position pos)
	{
		super(pos, 1);
		this.state = StatusActorEnum.IDLE;
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	public StatusActorEnum getState()
	{
		return state;
	}

	// SETTERS
	
	public void setState(StatusActorEnum state)
	{
		this.state = state;
	}

	// EXPLODE
	
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
	
	// MOVE
	
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
	
	// REFRESH POSITION
	
	public void changePosition()
	{
		MapActor.removeActor(this.getPosition());
		this.makeMove();
		MapActor.setActor(this);
	}
	
	public void makeMove()
	{
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

}
