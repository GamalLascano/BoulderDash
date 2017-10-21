package game.actor;

import game.CurrentDirection;
import game.Position;
import game.Entity;
import java.util.concurrent.TimeUnit;

public abstract class Actor extends Entity
{
	StatusActor state = new StatusActor();
	Position pos = new Position();

	// CONSTRUCTORS
	
	public Actor(StatusActor state, Position pos)
	{
		super(pos);
		this.state = state;
		this.pos = pos;
	}

	// GETTERS
	
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
			state.setStateEnum(StatusActorEnum.MOVING);
			TimeUnit.SECONDS.sleep(1);
			state.setStateEnum(StatusActorEnum.IDLE);
			break;
		case DOWN:
			pos.checkDown();
			state.setStateEnum(StatusActorEnum.MOVING);
			TimeUnit.SECONDS.sleep(1);
			state.setStateEnum(StatusActorEnum.IDLE);
			break;
		case LEFT:
			pos.checkLeft();
			state.setStateEnum(StatusActorEnum.MOVING);
			TimeUnit.SECONDS.sleep(1);
			state.setStateEnum(StatusActorEnum.IDLE);
			break;
		case RIGHT:
			pos.checkRight();
			state.setStateEnum(StatusActorEnum.MOVING);
			TimeUnit.SECONDS.sleep(1);
			state.setStateEnum(StatusActorEnum.IDLE);
			break;
		default:
			break;
		}
	}

}
