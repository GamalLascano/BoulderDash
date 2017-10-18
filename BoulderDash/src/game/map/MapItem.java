package game.map;

import game.Position;
import game.item.*;

public class MapItem {
	private Item[][] items;
	private int width;
	private int height;
	
	public MapItem(Item[][] items, int width, int height) {
		this.items = new Item[width][height];
		this.width = width;
		this.height = height;
	}
	
	public Item getActor(Position pos) {
		return items[pos.getPosX()][pos.getPosY()];
	}
	
	/**
	 * 
	 * @param pos
	 * @param act
	 * @return : true si se agrego correctamente
	 */
	public boolean setActor(Position pos, Item act) {
		if(this.width < pos.getPosX() && this.height < pos.getPosY()) {
			items[pos.getPosX()][pos.getPosY()] = act;
			return true;
		}
		else {
			return false;
		}
	}
	
}
