package game.cell;
import game.Status;

public abstract class Cell {
	Status state;
	Sprite sprite;
	boolean isSolid;
	
	Cell(Status state, boolean isSolid, Sprite sprite) {
		this.state = state;
		this.isSolid = isSolid;
		this.sprite = sprite;
	}
}
