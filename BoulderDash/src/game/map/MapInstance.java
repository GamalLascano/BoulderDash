package game.map;

import game.*;
import game.actor.*;
import game.cell.*;
import game.item.*;

public class MapInstance {
	private static MapInstance single;
	private static BDTile[][] tile;
	private static Cell[][] cell;
	private static Item[][] item;
	private static Actor[][] actor;
	
	/**
	* Constructor para el singleton
	*/
	private MapInstance() {
		tile=null;
		cell=null;
		item=null;
		actor=null;
	}
	
	/**
	* Metodo singleton
	*/
	public static synchronized MapInstance getInstance() {
		if (single == null) {
			single = new MapInstance();
		}
		return single;
	}
	
	/**
	 * Retorna una celda del mapa.
	 * @param x
	 * @param y
	 * @return
	 */
	public static BDTile getTile(int x, int y) {
		return tile[x][y];
	}
	
	/**
	 *Usado para cargar datos al mapa o cambiar casillas.
	 * @param tile : de BDlevelreader
	 * @param x
	 * @param y
	 */
	public static void setTile(BDTile til, int x, int y) {
		tile[x][y] = til;
	}
	
	/**
	 * Construye el mapa. Convierte la matriz tiles a 3 matrices de objectos.
	 * @param level : nivel.
	 */
	public static void buildTiles(BDLevelReader level) {
		Position pos = new Position();
		Status state = new Status();
		for (int y = 0; y < level.getHEIGHT(); y++) {
			for (int x = 0; x < level.getWIDTH(); x++) {
				tile[x][y] = level.getTile(x, y);
				pos.setPos(x, y);
				state.reset(false, false, false, false, true);
				
				switch (tile[x][y]) {
					case EMPTY :
						cell[x][y] = new Normal(pos);
						item[x][y] = new Empty(state,pos);
						actor[x][y] = null;
						break;
					case DIRT :
						item[x][y] = new Dirt(state,pos);
						break;
					case TITANIUM :
						cell[x][y] = new Titanium(pos);
						break;
					case WALL :
						cell[x][y] = new Wall(pos,false);
						break;
					case ROCK :
						item[x][y] = new Rock(state,pos);
						break;
					case FALLINGROCK :
						state.setFalling(true);
						item[x][y] = new Rock(state,pos);
						break;
					case DIAMOND :
						item[x][y] = new Diamond(state,pos);
						break;
					case FALLINGDIAMOND :
						state.setFalling(true);
						item[x][y] = new Diamond(state,pos);
						break;
					case AMOEBA :
						item[x][y] = new Amoeba(state,pos);
						break;
					case FIREFLY :
						actor[x][y] = new Firefly(state,pos);
						break;
					case BUTTERFLY :
						actor[x][y] = new Butterfly(state,pos);
						break;
					case EXIT :
						cell[x][y] = new Exit(pos);
						break;
					case PLAYER :
						actor[x][y] = new Rockford(state,pos);
						break;
					default :
						break;
				}
				
			}
		}
	}
}
