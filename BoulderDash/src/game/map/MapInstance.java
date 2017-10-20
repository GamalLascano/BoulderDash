package game.map;
import game.actor.*;
import game.cell.*;
import game.item.*;
import game.Position;
import game.ListActor;

public class MapInstance {
	private static MapInstance single;
	private static BDTile[][] tile;
	private static MapCell cell;
	private static MapItem item;
	private static MapActor actor;
	
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
	 * Get maps
	 * @return
	 */
	public static MapCell getMapCell()
	{
		return cell;
	}

	public static MapItem getMapItem()
	{
		return item;
	}

	public static MapActor getMapActor()
	{
		return actor;
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
		StatusItem state = new StatusItem();

		cell = new MapCell(level.getWIDTH(),level.getHEIGHT());
		item = new MapItem(level.getWIDTH(), level.getHEIGHT());
		actor = new MapActor(level.getWIDTH(), level.getHEIGHT());
		
		for (int y = 0; y < level.getHEIGHT(); y++) {
			for (int x = 0; x < level.getWIDTH(); x++) {
				tile[x][y] = level.getTile(x, y);
				pos.setPos(x, y);
				state.reset(StatusEnum.IDLE, true);
				
				switch (tile[x][y]) {
					case EMPTY :
						cell.setCell(pos, new Dirt(pos,false) );
						item.setItem(pos, new Empty(state,pos) );
						actor.setActor(pos, null );
						break;
					case DIRT :
						cell.setCell(pos, new Dirt(pos) );
						break;
					case TITANIUM :
						cell.setCell( pos, new Titanium(pos) );
						break;
					case WALL :
						cell.setCell( pos, new Wall(pos) );
						break;
					case ROCK :
						item.setItem(pos, new Rock(state,pos) );
						break;
					case FALLINGROCK :
						state.setStateEnum(StatusEnum.FALLING);
						item.setItem(pos, new Rock(state,pos) );
						break;
					case DIAMOND :
						item.setItem(pos, new Diamond(state,pos) );
						break;
					case FALLINGDIAMOND :
						state.setStateEnum(StatusEnum.FALLING);
						item.setItem(pos, new Diamond(state,pos) );
						break;
					case AMOEBA :
						item.setItem(pos, new Amoeba(state,pos) );
						break;
					case FIREFLY :
						actor.setActor(pos, new Firefly(state,pos) );
						break;
					case BUTTERFLY :
						actor.setActor(pos, new Butterfly(state,pos) );
						break;
					case EXIT :
						cell.setCell( pos, new Exit(pos) );
						break;
					case PLAYER :
						actor.setActor(pos, new Rockford(state,pos) );
						break;
					default :
						break;
				}
				
			}
		}
	}
}
