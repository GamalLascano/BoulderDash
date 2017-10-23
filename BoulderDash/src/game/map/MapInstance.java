package game.map;

import game.actor.*;
import game.cell.*;
import game.item.*;
import game.ActiveEntities;
import game.Entity;
import game.map.bdlevel.BDLevelReader;
import game.Position;

public class MapInstance
{
	private static MapInstance singleton;
	private static MapCell cellMap;
	private static MapItem itemMap;
	private static MapActor actorMap;

	private static ActiveEntities entitiesAlive;

	// CONSTRUCTOR

	private MapInstance()
	{

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

	public static ActiveEntities getEntitiesActive()
	{
		return entitiesAlive;
	}

	// SETTERS
	

	// INICIALIZACION
	
	public static void start(BDLevelReader levels)
	{
		MapCell.getInstance().start(levels);
		cellMap = MapCell.getInstance();
		MapItem.getInstance().start(levels);
		itemMap = MapItem.getInstance();
		MapActor.getInstance().start(levels);
		actorMap = MapActor.getInstance();
		entitiesAlive = ActiveEntities.getInstance();
		ActiveEntities.start();
	}
	
	// ACTUALIZAR POSICION

	private static void movingRockford(Rockford player)
	{
		switch ( player.getState().getStateEnum() )
		{
			case MOVINGUP:
				player.getPosition().goUp();
				player.dig(cellMap.getDirt(player.getPosition()));
				break;
			case MOVINGDOWN:
				player.getPosition().goDown();
				player.dig(cellMap.getDirt(player.getPosition()));
				break;
			case MOVINGRIGHT:
				player.getPosition().goRight();
				player.dig(cellMap.getDirt(player.getPosition()));
				break;
			case MOVINGLEFT:
				player.getPosition().goLeft();
				player.dig(cellMap.getDirt(player.getPosition()));
				break;
			default:
				break;
		}
		player.getState().setStateEnum(StatusActorEnum.IDLE);
	}
	
	private static void movingActor(Actor actor)
	{
		switch ( actor.getState().getStateEnum() )
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

	private static void fallingItem(Item item)
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
		
		if (entity instanceof Rockford)
		{
			actorMap.removeActor(entity.getPosition());
			movingRockford((Rockford) entity);
			actorMap.setActor(entity.getPosition(), (Rockford) entity);
		}
		else if (entity instanceof Actor)
		{
			actorMap.removeActor(entity.getPosition());
			movingActor((Actor) entity);
			actorMap.setActor(entity.getPosition(), (Actor) entity);
		}
		else if (entity instanceof Item)
		{
			itemMap.removeItem(entity.getPosition());
			fallingItem((Item) entity);
			itemMap.setItem(entity.getPosition(), (Item) entity);
		}
	}

	// TURNOS

	public static void refresh()
	{
		int i;
		for (i = 0; i < ActiveEntities.getList().size(); ++i)
		{
			Entity ent = ActiveEntities.getList().get(i);
			changePosition(ent);
		}
	}

	// MAPA

	/**
	 * Construye el mapa. Convierte la matriz tiles a 3 matrices. 1 matriz de
	 * actores, 1 matriz de items, 1 matriz de celdas. Agrega actores y items
	 * en una lista de entities.
	 * @param level : nivel.
	 */
	public static void buildMap(BDLevelReader level)
	{
		for (int y = 0; y < level.getHEIGHT(); y++)
		{
			for (int x = 0; x < level.getWIDTH(); x++)
			{
				StatusItem stateItem = new StatusItem();
				StatusActor stateActor = new StatusActor();
				Position pos = new Position();
				
				pos.setXY(x, y);
				stateItem.reset(StatusItemEnum.IDLE, true);
				stateActor.reset(StatusActorEnum.IDLE, true);

				switch (level.getTile(x, y))
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
						ActiveEntities.getList().add(rock);
						break;
					case FALLINGROCK:
						stateItem.setStateEnum(StatusItemEnum.FALLING);
						Rock fallingRock = new Rock(stateItem, pos);
						itemMap.setItem(pos, fallingRock);
						ActiveEntities.getList().add(fallingRock);
						break;
					case DIAMOND:
						Diamond diamond = new Diamond(stateItem, pos);
						itemMap.setItem(pos, diamond);
						ActiveEntities.getList().add(diamond);
						break;
					case FALLINGDIAMOND:
						stateItem.setStateEnum(StatusItemEnum.FALLING);
						Diamond fallingDiamond = new Diamond(stateItem, pos);
						itemMap.setItem(pos, fallingDiamond);
						ActiveEntities.getList().add(fallingDiamond);
						break;
					case AMOEBA:
						Amoeba amoeba = new Amoeba(stateItem, pos);
						itemMap.setItem(pos, amoeba);
						ActiveEntities.getList().add(amoeba);
						break;
					case FIREFLY:
						Firefly firefly = new Firefly(stateActor, pos);
						actorMap.setActor(pos, firefly);
						ActiveEntities.getList().add(firefly);
						break;
					case BUTTERFLY:
						Butterfly butterfly = new Butterfly(stateActor, pos);
						actorMap.setActor(pos, butterfly);
						ActiveEntities.getList().add(butterfly);
						break;
					case EXIT:
						cellMap.setCell(pos, new Exit(pos));
						break;
					case PLAYER:
						Rockford player = new Rockford(stateActor, pos);
						actorMap.setActor(pos, player);
						ActiveEntities.getList().add(player);
						break;
					default:
						break;
				}

			}
		}
	}

}
