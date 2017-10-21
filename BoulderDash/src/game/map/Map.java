package game.map;

import game.map.bdlevel.BDLevelReader;
import game.Position;

public class Map
{
	private Character[][] map;
	private MapActor actorMap;
	private MapCell cellMap;
	private MapItem itemMap;
	private int width;
	private int height;
	
	public Map(Character[][] map, MapActor actorMap, MapCell cellMap, MapItem itemMap, int width, int height)
	{
		this.map = map;
		this.actorMap = actorMap;
		this.cellMap = cellMap;
		this.itemMap = itemMap;
		this.width = width;
		this.height = height;
	}


	// GETTERS

	// SETTERS
	
	public void drawMap()
	{
		Position pos = new Position();
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				map[x][y] = cellMap.getCell(pos);
				map[x][y] = itemMap.getItem(pos);
				map[x][y] = actorMap.getActor(pos);
			}

		}
	}
	
	public void imprimirMapa()
	{
		System.out.println("..............................................................");

		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				System.out.println();
			}
			System.out.println();

		}

		System.out.println("..............................................................");

	}

}
