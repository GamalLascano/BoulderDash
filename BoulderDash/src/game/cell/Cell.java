package game.cell;
import game.Status;

public abstract class Cell {
	Status state;
	boolean isSolid;
	
	Cell(Status state, boolean isSolid) {
		this.state = state;
		this.isSolid = isSolid;
	}
}
