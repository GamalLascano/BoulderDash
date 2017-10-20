package game.actor;
import game.CurrentDirection;
import game.Position;
import game.item.StatusItem;
import game.map.BDTile;
import game.map.MapInstance;

public abstract class Actor {
	StatusItem state = new StatusItem();
	Position pos = new Position();
	
	public Actor(StatusItem state, Position pos) {
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
				state.setMoving(true);
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

	public StatusItem getState()
	{
		return state;
	}

	public void setState(StatusItem state)
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
