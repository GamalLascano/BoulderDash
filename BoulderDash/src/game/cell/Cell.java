package game.cell;

import game.Position;

public abstract class Cell {
	Position pos;
	boolean active;		//Para la salida y muros magicos.
	boolean solid;		//Si los personajes pueden caminar sobre la celda.
	
	Cell(Position pos, boolean solid, boolean active) {
		this.pos = pos;
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
