package game.actor;

import game.Position;
import game.StatusItem;

public class Butterfly extends Enemy {
	
	public Butterfly(StatusItem state, Position pos) {
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
