package game.actor;

import game.CurrentDirection;
import game.Position;
import game.item.StatusItem;
import game.map.MapInstance;
import game.map.bdlevel.BDTile;
import java.util.concurrent.TimeUnit;

public abstract class Actor
{
	StatusActor state = new StatusActor();
	Position pos = new Position();

	// CONSTRUCTORS
	
	public Actor(StatusActor state, Position pos)
	{
		super();
		this.state = state;
		this.pos = pos;
	}

	// GETTERS
	
	public StatusActor getState()
	{
		return state;
	}
	
	public Position getPos()
	{
		return pos;
	}

	// SETTERS
	
	public void setState(StatusActor state)
	{
		this.state = state;
	}

	public void setPos(Position pos)
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
			pos.goUp();
			state.setStateEnum(StatusActorEnum.MOVING);
			TimeUnit.SECONDS.sleep(1);
			state.setStateEnum(StatusActorEnum.IDLE);
			break;
		case DOWN:
			pos.goDown();
			state.setStateEnum(StatusActorEnum.MOVING);
			TimeUnit.SECONDS.sleep(1);
			state.setStateEnum(StatusActorEnum.IDLE);
			break;
		case LEFT:
			pos.goLeft();
			state.setStateEnum(StatusActorEnum.MOVING);
			TimeUnit.SECONDS.sleep(1);
			state.setStateEnum(StatusActorEnum.IDLE);
			break;
		case RIGHT:
			pos.goRight();
			state.setStateEnum(StatusActorEnum.MOVING);
			TimeUnit.SECONDS.sleep(1);
			state.setStateEnum(StatusActorEnum.IDLE);
			break;
		default:
			break;
		}
	}

}
