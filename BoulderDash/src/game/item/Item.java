package game.item;

import game.Position;
import game.Status;
import game.map.BDLevelReader;
import game.map.BDTile;

public abstract class Item {
	Status state = new Status(false,false,false,false,false);
	Sprite sprite;
	Position pos = new Position();
	boolean collectable;
	boolean moveable;
	boolean explodable;
	
	public Item(Status state, Sprite sprite, Position pos, boolean collectable, boolean moveable, boolean explodable) {
		super();
		this.state = state;
		this.sprite = sprite;
		this.pos = pos;
		this.collectable = collectable;
		this.moveable = moveable;
		this.explodable = explodable;
	}

	public void fall() {
		while(BDLevelReader.field[pos.posX][pos.posY - 1] == BDTile.EMPTY)
			pos.setPosY(pos.getPosY() - 1);
			state.setFalling(true);
	}
}
