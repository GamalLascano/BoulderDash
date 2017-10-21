package game.map;

import game.map.bdlevel.BDLevelReader;
import game.Position;

public class Map
{
	private String[][] map;
	private MapActor actorMap;
	private MapCell cellMap;
	private MapItem itemMap;
	private int width;
	private int height;
	
	public Map(String[][] map, MapActor actorMap, MapCell cellMap, MapItem itemMap, int width, int height)
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
				pos.setXY(x, y);
				switch (cellMap.getCell(pos).ge)
				{
					case DIRT :
						break;
					default :
						break;
				}
				
				switch ()
				{
					case DIRT :
						break;
					default :
						break;
				}
				
				switch ()
				{
					case DIRT :
						break;
					default :
						break;
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

			}
			System.out.println();

		}

		System.out.println("..............................................................");

	}

}
