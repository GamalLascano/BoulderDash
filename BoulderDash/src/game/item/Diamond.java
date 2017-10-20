package game.item;

import game.Position;

public class Diamond extends Item {

	public Diamond(StatusItem state, Position pos) {
		super(state, pos, true, false, true, false, true);
	}
	
}
