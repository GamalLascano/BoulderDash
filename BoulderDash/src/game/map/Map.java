package game.map;

import game.map.bdlevel.BDLevelReader;
import game.Position;
import game.SpriteChar;

public class Map
{
	private SpriteChar[][] map;
	private MapActor actorMap;
	private MapCell cellMap;
	private MapItem itemMap;
	private int width;
	private int height;
	
	public Map(SpriteChar[][] map, MapActor actorMap, MapCell cellMap, MapItem itemMap, int width, int height)
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
				if(actorMap.getActor(pos) != null)
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
	
	public void imprimirMapa()
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
