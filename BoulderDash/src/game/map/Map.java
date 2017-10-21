package game.map;

import game.Position;
import game.SpriteChar;

public class Map
{
	private SpriteChar[][] map;

	private int width;
	private int height;
	
	public Map(int width, int height)
	{
		this.map = new SpriteChar[width][height];
		this.width = width;
		this.height = height;
	}


	// GETTERS

	// SETTERS
	
	// GRAPHICS
	
	public void drawMap(MapCell cellMap, MapItem itemMap, MapActor actorMap)
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
