package game.item;

import game.Position;
import game.Status;

public abstract class Item {
	Status state = new Status(false,false,false,false,false);
	Position pos = new Position();
	
	public void fall() {
		pos.setPosY(pos.getPosY() - 1);
		state.setFalling(true);
	}
}
