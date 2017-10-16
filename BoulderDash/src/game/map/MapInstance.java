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
	
	public static BDTile returnTile(int x, int y) {
		return map[x][y];
	}
	// pasar paramA de bdlevelreader, usado para cargar datos al mapa o cambiar casillas
	public static void loadData(BDTile tile, int x, int y) {
		map[x][y] = tile;
	}
}
