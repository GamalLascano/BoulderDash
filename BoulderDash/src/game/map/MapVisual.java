package game.map;

import game.Position;
import game.SpriteChar;
import game.item.Empty;
import game.map.bdlevel.BDLevelReader;

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
	public void start(BDLevelReader levels) {
		level=levels;
		map = new SpriteChar[level.getWIDTH()][level.getHEIGHT()];
	}

	// GETTERS

	// SETTERS
	
	// GRAPHICS
	
	public static void drawMap(MapCell cellMap, MapItem itemMap, MapActor actorMap)
	{
		Position pos = new Position();
		for (int y = 0; y < level.getHEIGHT(); y++)
		{
			for (int x = 0; x < level.getWIDTH(); x++)
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
				else
				{
					map[x][y] = cellMap.getCell(pos).getSpritechar();
				}
			}

		}
	}
	
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

		System.out.println("..............................................................");

	}

}
