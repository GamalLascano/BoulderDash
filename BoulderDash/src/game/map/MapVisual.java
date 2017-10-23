package game.map;

import game.ActiveEntities;
import game.Position;
import game.SpriteChar;
import game.item.Empty;
import game.cell.Cell;
import game.map.bdlevel.BDLevelReader;

/**
 * Esta clase es la encargada de agarrar los objetos y imprimirlos en pantalla
 *
 */
public class MapVisual
{
	private static MapVisual singleton;
	private static SpriteChar[][] map;
	private static BDLevelReader level;
	
	private MapVisual()
	{
		
	}
	
	// SINGLETON

	public static synchronized MapVisual getInstance()
	{
		if (singleton == null)
		{
			singleton = new MapVisual();
		}
		return singleton;
	}

	// INICIALIZACION
	
	public void start(BDLevelReader levels)
	{
		level = levels;
		map = new SpriteChar[level.getWIDTH()][level.getHEIGHT()];
	}

	// GETTERS

	// SETTERS
	
	// GRAPHICS
	
	/** Este metodo recibe todos los mapas y hace un mapa para imprimir
	 * @param cellMap EL mapa de celdas
	 * @param itemMap El mapa de items
	 * @param actorMap El mapa de actores
	 */
	public static void drawMap(MapCell cellMap, MapItem itemMap, MapActor actorMap)
	{
		Position pos = new Position();
		int y;
		int x;
		for (y = 0; y < level.getHEIGHT(); y++)
		{
			for (x = 0; x < level.getWIDTH(); x++)
			{
				pos.setXY(x, y);
				if( actorMap.getActor(pos) != null )
				{
					map[x][y] = actorMap.getActor(pos).getSpritechar();
				}
				else if( itemMap.getItem(pos) instanceof Empty == false )
				{
					map[x][y] = itemMap.getItem(pos).getSpritechar();
				}
				else if( cellMap.getCell(pos) instanceof Cell)
				{
					map[x][y] = cellMap.getCell(pos).getSpritechar();
				}
			}
		}
	}
	
	/** Este metodo usa el mapa creado en drawMap, y lo dibuja en pantalla
	 * 
	 */
	public static void imprimirMapa()
	{
		System.out.println("..............................................................");

		for (int y = 0; y < level.getHEIGHT(); y++)
		{
			for (int x = 0; x < level.getWIDTH(); x++)
			{
				System.out.print(map[x][y]);
				System.out.print(" ");
			}
			System.out.println();

		}
		System.out.println("Rockford Pos: " + ActiveEntities.findRockford().getPosition().getX() + "," + ActiveEntities.findRockford().getPosition().getY() );
		System.out.println("..............................................................");

	}

}
