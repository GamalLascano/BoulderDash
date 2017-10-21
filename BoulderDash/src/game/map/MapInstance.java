package game.map;

import game.actor.*;
import game.cell.*;
import game.item.*;
import game.ActiveEntities;
import game.Entity;
import game.map.bdlevel.BDLevelReader;
import game.map.bdlevel.BDTile;
import game.Position;

public class MapInstance
{
	private static MapInstance singleton;
	private static BDTile[][] tileMap;
	private static MapCell cellMap;
	private static MapItem itemMap;
	private static MapActor actorMap;

	private static ActiveEntities entitiesAlive;

	// CONSTRUCTOR

	private MapInstance()
	{
		tileMap = null;
		cellMap = null;
		itemMap = null;
		actorMap = null;
		entitiesAlive = null;
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

	public static ActiveEntities getActorsActive()
	{
		return entitiesAlive;
	}

	// SETTERS

	public static void setTile(BDTile til, int x, int y)
	{
		tileMap[x][y] = til;
	}

	// ACTUALIZAR POSICION

	private static void movingPosition(Actor actor)
	{
		StatusActorEnum status = actor.getState().getStateEnum();
		switch (status)
		{
			case MOVINGUP:
				actor.getPosition().goUp();
				break;
			case MOVINGDOWN:
				actor.getPosition().goDown();
				break;
			case MOVINGRIGHT:
				actor.getPosition().goRight();
				break;
			case MOVINGLEFT:
				actor.getPosition().goLeft();
				break;
			default:
				break;
		}
		actor.getState().setStateEnum(StatusActorEnum.IDLE);
	}

	private static void fallingPosition(Item item)
	{
		StatusItemEnum status = item.getState().getStateEnum();
		switch (status)
		{
			case FALLING:
				item.getPosition().goUp();
				break;
			case SLIDINGRIGHT:
				item.getPosition().goRight();
				break;
			case SLIDINGLEFT:
				item.getPosition().goLeft();
				break;
			default:
				break;
		}
		item.getState().setStateEnum(StatusItemEnum.IDLE);
	}

	public static void changePosition(Entity entity)
	{
		Position pos = entity.getPosition();

		if (cellMap.getCell(pos).isSolid() == false)
		{
			if (entity instanceof Actor)
			{
				movingPosition((Actor) entity);
				actorMap.setActor(pos, (Actor) entity);
			}
			else if (entity instanceof Item)
			{
				fallingPosition((Item) entity);
				itemMap.setItem(pos, (Item) entity);
			}
		}

	}

	// TURNOS

	public static void refresh()
	{
		Rockford player;
		Butterfly butter;
		Firefly fire;

		int i;
		for (i = 0; i < entitiesAlive.getList().size(); ++i)
		{
			Actor unActor = (Actor) entitiesAlive.getList().get(i);
			changePosition(unActor);
		}
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
		entitiesAlive = new ActiveEntities();

		for (int y = 0; y < level.getHEIGHT(); y++)
		{
			for (int x = 0; x < level.getWIDTH(); x++)
			{
				tileMap[x][y] = level.getTile(x, y);
				pos.setXY(x, y);
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
						entitiesAlive.getList().add(rock);
						break;
					case FALLINGROCK:
						stateItem.setStateEnum(StatusItemEnum.FALLING);
						Rock fallingRock = new Rock(stateItem, pos);
						itemMap.setItem(pos, fallingRock);
						entitiesAlive.getList().add(fallingRock);
						break;
					case DIAMOND:
						Diamond diamond = new Diamond(stateItem, pos);
						itemMap.setItem(pos, diamond);
						entitiesAlive.getList().add(diamond);
						break;
					case FALLINGDIAMOND:
						stateItem.setStateEnum(StatusItemEnum.FALLING);
						Diamond fallingDiamond = new Diamond(stateItem, pos);
						itemMap.setItem(pos, fallingDiamond);
						entitiesAlive.getList().add(fallingDiamond);
						break;
					case AMOEBA:
						Amoeba amoeba = new Amoeba(stateItem, pos);
						itemMap.setItem(pos, amoeba);
						entitiesAlive.getList().add(amoeba);
						break;
					case FIREFLY:
						Firefly firefly = new Firefly(stateActor, pos);
						actorMap.setActor(pos, firefly);
						entitiesAlive.getList().add(firefly);
						break;
					case BUTTERFLY:
						Butterfly butterfly = new Butterfly(stateActor, pos);
						actorMap.setActor(pos, butterfly);
						entitiesAlive.getList().add(butterfly);
						break;
					case EXIT:
						cellMap.setCell(pos, new Exit(pos));
						break;
					case PLAYER:
						Rockford player = new Rockford(stateActor, pos);
						actorMap.setActor(pos, player);
						entitiesAlive.getList().add(player);
						break;
					default:
						break;
				}

			}
		}
	}

}
