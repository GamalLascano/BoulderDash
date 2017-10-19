package game.map;
import game.cell.*;
import game.Position;

public class MapCell {
	private Cell[][] cell;
	private int width;
	private int height;
	
	public MapCell(int width, int height) {
		this.cell = new Cell[width][height];
		this.width = width;
		this.height = height;
	}
	
	public Cell getCell(Position pos) {
		return cell[pos.getPosX()][pos.getPosY()];
	}
	
	/**
	 * 
	 * @param pos
	 * @param cel
	 * @return : true si se agrego correctamente
	 */
	public boolean setCell(Position pos, Cell cel) {
		if(this.width >= pos.getPosX() && this.height >= pos.getPosY()) {
			cell[pos.getPosX()][pos.getPosY()] = cel;
			return true;
		}
		else {
			return false;
		}
	}
}
