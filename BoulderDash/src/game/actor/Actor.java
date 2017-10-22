package game.actor;

import game.CurrentDirection;
import game.Position;
import game.SpriteChar;
import game.Entity;

public abstract class Actor extends Entity
{
	StatusActor state = new StatusActor();
	Position pos = new Position();
	SpriteChar spritechar;

	// CONSTRUCTORS
	
	public Actor()
	{
	}
	
	public Actor(StatusActor state, Position pos)
	{
		super(pos);
		this.state = state;
		this.pos = pos;
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
	
	public Position getPosition()
	{
		return pos;
	}

	// SETTERS
	
	public void setState(StatusActor state)
	{
		this.state = state;
	}

	public void setPosition(Position pos)
	{
		this.pos = pos;
	}
	
	// MOVE
	
	/**
	 * Movimiento en el mapa, poner una direcion como parametro. usa
	 * game.Position y game.CurrentDirection.
	 */
	public void move(CurrentDirection direction)
	{
		switch (direction)
		{
		case UP:
			pos.checkUp();
			state.setStateEnum(StatusActorEnum.MOVINGUP);
			break;
		case DOWN:
			pos.checkDown();
			state.setStateEnum(StatusActorEnum.MOVINGDOWN);
			break;
		case LEFT:
			pos.checkLeft();
			state.setStateEnum(StatusActorEnum.MOVINGLEFT);
			break;
		case RIGHT:
			pos.checkRight();
			state.setStateEnum(StatusActorEnum.MOVINGRIGHT);
			break;
		default:
			break;
		}
	}

}
