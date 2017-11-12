package game.actor;

import game.CurrentDirection;
import game.Position;
import game.SpriteChar;
import game.map.MapActor;
import game.Entity;

/**
 * Esta clase representa a todos los actores, que pueden moverse
 *
 */
public abstract class Actor extends Entity
{
	protected StatusActorEnum state;
	private SpriteChar spritechar;

	// CONSTRUCTORS
	
	public Actor()
	{
	}
	
	public Actor(Position pos)
	{
		super(pos, 1);
		this.state = StatusActorEnum.IDLE;
	}
	
	public Actor(Position pos, StatusActorEnum state)
	{
		super(pos, 1);
		this.state = state;
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
		MapActor.removeActor(this.getPosition());
	}

}
