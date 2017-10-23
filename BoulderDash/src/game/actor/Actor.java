package game.actor;

import game.CurrentDirection;
import game.Position;
import game.SpriteChar;
import game.Entity;

public abstract class Actor extends Entity
{
	StatusActor state = new StatusActor();
	SpriteChar spritechar;

	// CONSTRUCTORS
	
	public Actor()
	{
	}
	
	public Actor(StatusActor state, Position pos)
	{
		super(pos);
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
			state.setStateEnum(StatusActorEnum.MOVINGUP);
			break;
		case DOWN:
			state.setStateEnum(StatusActorEnum.MOVINGDOWN);
			break;
		case LEFT:
			state.setStateEnum(StatusActorEnum.MOVINGLEFT);
			break;
		case RIGHT:
			state.setStateEnum(StatusActorEnum.MOVINGRIGHT);
			break;
		default:
			break;
		}
	}

}
