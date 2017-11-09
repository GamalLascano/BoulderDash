package game.actor;

import game.CurrentDirection;
import game.Position;
import game.SpriteChar;
import game.Entity;

/**
 * Esta clase representa a todos los actores, que pueden moverse
 *
 */
public abstract class Actor extends Entity
{
	private StatusActor state = new StatusActor();
	private SpriteChar spritechar;

	// CONSTRUCTORS
	
	public Actor()
	{
	}
	
	public Actor(StatusActor state, Position pos)
	{
		super(pos, 0);
		this.state = state;
	}
	
	public Actor(StatusActor state, Position pos, int solid)
	{
		super(pos, solid);
		this.state = state;
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	public StatusActor getState()
	{
		return state;
	}

	// SETTERS
	
	public void setState(StatusActor state)
	{
		this.state = state;
	}

	// MOVE
	
	public void move(CurrentDirection direction)
	{
		switch (direction)
		{
		case UP:
			state.setMovementState(StatusActorEnum.MOVINGUP);
			break;
		case DOWN:
			state.setMovementState(StatusActorEnum.MOVINGDOWN);
			break;
		case LEFT:
			state.setMovementState(StatusActorEnum.MOVINGLEFT);
			break;
		case RIGHT:
			state.setMovementState(StatusActorEnum.MOVINGRIGHT);
			break;
		default:
			break;
		}
	}

}
