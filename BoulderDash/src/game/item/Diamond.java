package game.item;

import game.Position;
import game.Status;

public class Diamond extends Item {

	public Diamond(Status state, Position pos, boolean collectable, boolean moveable, boolean fallable, boolean explodable) {
		super(state, pos, collectable, moveable, fallable, explodable);
	}
	
	public Diamond(Status state, Position pos) {
		super(state, pos, true, false, true, false);
	}
	
	
	
}
