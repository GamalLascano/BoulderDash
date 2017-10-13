package game.cell;
import game.Status;

public abstract class Cell {
	Status state;
	
	Cell(Status state) {
		this.state = state;
	}
}
