package game.actor;

import game.Position;
import game.Status;

public abstract class Enemy extends Actor {
	
	public Enemy(Status state, Position pos) {
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	protected abstract boolean rotate();

}
