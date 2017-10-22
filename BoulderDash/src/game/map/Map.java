package game.map;

import game.Position;
import game.SpriteChar;
import game.actor.None;
import game.map.bdlevel.BDLevelReader;

public class Map
{
	private static Map singleton;
	private static SpriteChar[][] map;
	private static BDLevelReader level;
	private static int width;
	private static int height;
	
	public Map()
	{
		map = new SpriteChar[width][height];
		level = null;
		width = level.getWIDTH();
		height = level.getHEIGHT();
	}
	
	// SINGLETON

	public static synchronized Map getInstance()
	{
		if (singleton == null)
		{
			singleton = new Map();
		}
		return singleton;
	}

	// GETTERS

	// SETTERS
	
	// GRAPHICS
	
	public static void drawMap(MapCell cellMap, MapItem itemMap, MapActor actorMap)
	{
		Position pos = new Position();
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				if( actorMap.getActor(pos) instanceof None )
				{
					map[x][y] = actorMap.getActor(pos).getSpritechar();
				}
				else if(itemMap.getItem(pos).getSpritechar() != SpriteChar.E)
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

		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				System.out.print(map[x][y]);
				System.out.print(" ");
			}
			System.out.println();

		}

		System.out.println("..............................................................");

	}

}
