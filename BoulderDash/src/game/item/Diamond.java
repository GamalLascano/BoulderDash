package game.item;

import game.Position;
import game.Status;

public class Diamond extends Item {

	public Diamond(Status state, Position pos) {
		super(state, pos, true, false, true, false, true);
	}
	
}
