package game.item;

import game.Position;
import game.Status;
import game.map.BDLevelReader;
import game.map.BDTile;
import game.map.MapInstance;
import game.character.Character;
import game.character.Rockford;

public abstract class Item {
	Status state = new Status(false,false,false,false,false,false);
	Position pos = new Position();
	boolean collectable;
	boolean moveable;
	boolean fallable;
	boolean explodable;
	
	public Item(Status state, Position pos, boolean collectable, boolean moveable, boolean fallable, boolean explodable) {
		super();
		this.state = state;
		this.pos = pos;
		this.collectable = collectable;
		this.moveable = moveable;
		this.fallable = fallable;
		this.explodable = explodable;
	}

	public void fall() {
		while(MapInstance.returnTile(pos.posX, pos.posY - 1) == BDTile.EMPTY) {
			pos.setPosY(pos.getPosY() - 1);
			state.setFalling(true);
		}
	}
	/** poner collect en Rockford?
	public void collected() {
		if ()
		BDLevelReader.field[pos.posX][pos.posY] = BDTile.EMPTY;
		
	}
	*/
}
