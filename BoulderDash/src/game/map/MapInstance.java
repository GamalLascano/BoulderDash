package game.map;


public class MapInstance {
	private static MapInstance single;
	private static BDTile[][] map;
	
	//Singleton
	private MapInstance() {
		map=null;
	}
	
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
		return map[x][y];
	}
	
	/**
	 *Usado para cargar datos al mapa o cambiar casillas.
	 * @param tile : de BDlevelreader
	 * @param x
	 * @param y
	 */
	public static void setTile(BDTile tile, int x, int y) {
		map[x][y] = tile;
	}
	
	/**
	 * Construye el mapa.
	 * @param level : nivel.
	 */
	public static void buildMap(BDLevelReader level) {
		for (int y = 0; y < level.getHEIGHT(); y++) {
			for (int x = 0; x < level.getWIDTH(); x++) {
				map[x][y] = level.getTile(x, y);
			}
		}
	}
}
