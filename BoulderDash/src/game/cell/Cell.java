package game.cell;
import game.Status;

public abstract class Cell {
	boolean active;		//Para la salida y muros magicos.
	boolean solid;		//Si los personajes pueden caminar sobre la celda.
	
	Cell(Status state, boolean solid, boolean active) {
		this.solid = solid;
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	
	
}
