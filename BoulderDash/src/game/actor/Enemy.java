package game.actor;

import game.Position;
import game.item.StatusItem;
import game.map.MapInstance;

public abstract class Enemy extends Actor
{

	public Enemy(StatusActor state, Position pos)
	{
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	protected abstract boolean rotate();

	/**
	 * Explosion, pone celda vacias alrededor del personaje. Explosion cuadrada
	 * 3x3.
	 */
	public abstract void explode();
	//Recibe una posicion, y ve si esta en un radio de 3x3
	public boolean isInRange(Position pos) {
		if ((pos.getX()==(this.pos.getX()-1))||(pos.getX()==this.pos.getX())||
		(pos.getX()==(this.pos.getX()+1))) {
			if ((pos.getY()==(this.pos.getY()-1))||(pos.getY()==this.pos.getY())||
					(pos.getY()==(this.pos.getY()+1))) {
				return true;
			}else return false;
		}else return false;
	}
}
