package game.map;

import game.actor.*;
import game.cell.*;
import game.item.*;
import game.map.bdlevel.BDLevelReader;
import game.map.bdlevel.BDTile;
import game.Position;
import game.map.ActiveActors;
import game.map.ActiveItems;

public class MapInstance
{
	private static MapInstance singleton;
	private static BDTile[][] tileMap;
	private static MapCell cellMap;
	private static MapItem itemMap;
	private static MapActor actorMap;

	private static ActiveActors actorsActive;
	private static ActiveItems itemsActive;

	// CONSTRUCTOR

	private MapInstance()
	{
		tileMap = null;
		cellMap = null;
		itemMap = null;
		actorMap = null;
		actorsActive = null;
		itemsActive = null;
	}

	// SINGLETON
	
	public static synchronized MapInstance getInstance()
	{
		if (singleton == null)
		{
			singleton = new MapInstance();
		}
		return singleton;
	}

	// GETTERS
	
	public static BDTile getTile(int x, int y)
	{
		return tileMap[x][y];
	}
	
	public static BDTile[][] getMapTile()
	{
		return tileMap;
	}
	
	public static MapCell getMapCell()
	{
		return cellMap;
	}

	public static MapItem getMapItem()
	{
		return itemMap;
	}

	public static MapActor getMapActor()
	{
		return actorMap;
	}
	
	public static ActiveActors getActorsActive()
	{
		return actorsActive;
	}

	public static ActiveItems getItemsActive()
	{
		return itemsActive;
	}
	

	// SETTERS

	public static void setTile(BDTile til, int x, int y)
	{
		tileMap[x][y] = til;
	}

	// MAPA
	
	/**
	 * Construye el mapa. Convierte la matriz tiles a 3 matrices. 1 matriz de
	 * actores, 1 matriz de items, 1 matriz de celdas Agrega actores en una
	 * lista y agrega los items en una otra lista para el acceso.
	 * 
	 * @param level
	 *            : nivel.
	 */
	public static void buildMap(BDLevelReader level)
	{
		Position pos = new Position();
		StatusItem stateItem = new StatusItem();
		StatusActor stateActor = new StatusActor();

		cellMap = new MapCell(level.getWIDTH(), level.getHEIGHT());
		itemMap = new MapItem(level.getWIDTH(), level.getHEIGHT());
		actorMap = new MapActor(level.getWIDTH(), level.getHEIGHT());
		actorsActive = new ActiveActors();
		itemsActive = new ActiveItems();

		for (int y = 0; y < level.getHEIGHT(); y++)
		{
			for (int x = 0; x < level.getWIDTH(); x++)
			{
				tileMap[x][y] = level.getTile(x, y);
				pos.setPos(x, y);
				stateItem.reset(StatusItemEnum.IDLE, true);
				stateActor.reset(StatusActorEnum.IDLE, true);

				switch (tileMap[x][y])
				{
				case EMPTY:
					cellMap.setCell(pos, new Dirt(pos, false));
					itemMap.setItem(pos, new Empty(stateItem, pos));
					actorMap.setActor(pos, null);
					break;
				case DIRT:
					cellMap.setCell(pos, new Dirt(pos));
					break;
				case TITANIUM:
					cellMap.setCell(pos, new Titanium(pos));
					break;
				case WALL:
					cellMap.setCell(pos, new Wall(pos));
					break;
				case ROCK:
					Rock rock = new Rock(stateItem, pos);
					itemMap.setItem(pos, rock);
					itemsActive.getList().add(rock);
					break;
				case FALLINGROCK:
					stateItem.setStateEnum(StatusItemEnum.FALLING);
					Rock fallingRock = new Rock(stateItem, pos);
					itemMap.setItem(pos, fallingRock);
					itemsActive.getList().add(fallingRock);
					break;
				case DIAMOND:
					Diamond diamond = new Diamond(stateItem, pos);
					itemMap.setItem(pos, diamond);
					itemsActive.getList().add(diamond);
					break;
				case FALLINGDIAMOND:
					stateItem.setStateEnum(StatusItemEnum.FALLING);
					Diamond fallingDiamond = new Diamond(stateItem, pos);
					itemMap.setItem(pos, fallingDiamond);
					itemsActive.getList().add(fallingDiamond);
					break;
				case AMOEBA:
					Amoeba amoeba = new Amoeba(stateItem, pos);
					itemMap.setItem(pos, amoeba);
					itemsActive.getList().add(amoeba);
					break;
				case FIREFLY:
					Firefly firefly = new Firefly(stateActor, pos);
					actorMap.setActor(pos, firefly);
					actorsActive.getList().add(firefly);
					break;
				case BUTTERFLY:
					Butterfly butterfly = new Butterfly(stateActor, pos);
					actorMap.setActor(pos, butterfly);
					actorsActive.getList().add(butterfly);
					break;
				case EXIT:
					cellMap.setCell(pos, new Exit(pos));
					break;
				case PLAYER:
					Rockford player = new Rockford(stateActor, pos);
					actorMap.setActor(pos, player);
					actorsActive.getList().add(player);
					break;
				default:
					break;
				}

			}
		}
	}
}
