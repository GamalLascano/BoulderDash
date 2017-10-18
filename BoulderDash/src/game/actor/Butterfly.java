package game.actor;

import game.Position;
import game.Status;

public class Butterfly extends Enemy {
	
	public Butterfly(Status state, Position pos) {
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean explode() {
		return true;
	}

	@Override
	public boolean rotate() {
		return true;
	}
	
}
