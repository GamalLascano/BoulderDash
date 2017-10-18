package game.actor;

import game.Position;
import game.Status;

public class Firefly extends Enemy {

	public Firefly(Status state, Position pos) {
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void explode() {

	}
	
	@Override
	public boolean rotate() {
		return true;
	}
	
}
