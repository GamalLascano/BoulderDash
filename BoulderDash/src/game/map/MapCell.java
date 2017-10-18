package game.map;
import game.cell.*;
import game.Position;

public class MapCell {
	private Cell[][] cell;
	private int width;
	private int height;
	
	public MapCell(Cell[][] cell, int width, int height) {
		this.cell = new Cell[width][height];
		this.width = width;
		this.height = height;
	}
	
	public Cell getActor(Position pos) {
		return cell[pos.getPosX()][pos.getPosY()];
	}
	
	/**
	 * 
	 * @param pos
	 * @param act
	 * @return : true si se agrego correctamente
	 */
	public boolean setActor(Position pos, Cell act) {
		if(this.width < pos.getPosX() && this.height < pos.getPosY()) {
			cell[pos.getPosX()][pos.getPosY()] = act;
			return true;
		}
		else {
			return false;
		}
	}
}
