package game.actor;
import game.CurrentDirection;
import game.Position;
import game.item.StatusItem;
import game.map.MapInstance;
import game.map.bdlevel.BDTile;

public abstract class Actor {
	StatusActor state = new StatusActor();
	Position pos = new Position();
	
	public Actor(StatusActor state, Position pos) {
		super();
		this.state = state;
		this.pos = pos;
	}

	/**
	 * Movimiento en el mapa, poner una direcion como parametro.
	 * usa game.Position y game.CurrentDirection.
	 */
	public void move(CurrentDirection direction) {
		switch (direction) {
			case UP :
				pos.setPosY(pos.getPosY() + 1);
				state.setStateEnum(StatusActorEnum.MOVING);
				break;
			case DOWN :
				pos.setPosY(pos.getPosY() - 1);
				state.setMoving(true);
				break;
			case LEFT :
				pos.setPosX(pos.getPosX() - 1);
				state.setMoving(true);
				break;
			case RIGHT :
				pos.setPosX(pos.getPosX() + 1);
				state.setMoving(true);
				break;
			default :
				break;
		}
		state.setMoving(false);
	}

	public StatusActor getState()
	{
		return state;
	}

	public void setState(StatusActor state)
	{
		this.state = state;
	}

	public Position getPos()
	{
		return pos;
	}

	public void setPos(Position pos)
	{
		this.pos = pos;
	}
	
	
	
}
