package game.cell;

import game.Position;
import game.actor.Rockford;
import game.map.BDLevelReader;

public class Exit extends Cell {

	public Exit(Position pos) {
		super(pos, true);
	}

	public void open(BDLevelReader levelReader, Rockford player ) {
		if(player.getDiamonds() == levelReader.getDiamondsNeeded()) {
			this.setSolid(false);
		}
	}
	
}
