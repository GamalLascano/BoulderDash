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

	/**
	 * Se occupa de mover a Rockford en la matriz, tambien verifica si la celda destino es solida para moverse.
	 * Rockford cava automaticamente la tierra.
	 * @param player
	 */
	private static void movingRockford(Rockford player)
	{
		switch ( player.getState().getStateEnum() )
		{
			case MOVINGUP:
				if( cellMap.getCell(player.getPosition().getX(), player.getPosition().checkUp()).isSolid() == false
						&& itemMap.getItem(player.getPosition().getX(), player.getPosition().checkUp()).isSolid() == false)
				{
					player.getPosition().goUp();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				break;
			case MOVINGDOWN:
				if( cellMap.getCell(player.getPosition().getX(), player.getPosition().checkDown()).isSolid() == false
						&& itemMap.getItem(player.getPosition().getX(), player.getPosition().checkDown()).isSolid() == false)
				{
					player.getPosition().goDown();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				break;
			case MOVINGRIGHT:
				if( cellMap.getCell(player.getPosition().checkRight(), player.getPosition().getY()).isSolid() == false
						&& itemMap.getItem(player.getPosition().checkRight(), player.getPosition().getY()).isSolid() == false)
				{
					player.getPosition().goRight();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				// EMPUJA PIEDRA SI HAY (dps limpiar aca)
				else if( cellMap.getCell(player.getPosition().checkRight(), player.getPosition().getY()).isSolid() == false
						&& itemMap.getItem(player.getPosition().checkRight(), player.getPosition().getY()).isMoveable() == true
						&& itemMap.getItem(player.getPosition().checkRight() + 1, player.getPosition().getY()).isSolid() == false
						&& cellMap.getCell(player.getPosition().checkRight() + 1, player.getPosition().getY()).isSolid() == false
						&& cellMap.getDirt(player.getPosition().checkRight() + 1, player.getPosition().getY()) != null 
						&& cellMap.getDirt(player.getPosition().checkRight() + 1, player.getPosition().getY()).IsDirt() == false )
				{
					player.getPosition().goRight();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
					player.push(itemMap.getRock(player.getPosition()));
				}
				break;
			case MOVINGLEFT:
				if( cellMap.getCell(player.getPosition().checkLeft(), player.getPosition().getY()).isSolid() == false
						&& itemMap.getItem(player.getPosition().checkLeft(), player.getPosition().getY()).isSolid() == false)
				{
					player.getPosition().goLeft();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				// EMPUJA PIEDRA SI HAY
				else if( cellMap.getCell(player.getPosition().checkLeft(), player.getPosition().getY()).isSolid() == false
						&& itemMap.getItem(player.getPosition().checkLeft(), player.getPosition().getY()).isMoveable() == true
						&& itemMap.getItem(player.getPosition().checkLeft() - 1, player.getPosition().getY()).isSolid() == false
						&& cellMap.getCell(player.getPosition().checkLeft() - 1, player.getPosition().getY()).isSolid() == false
						&& cellMap.getDirt(player.getPosition().checkLeft() - 1, player.getPosition().getY()) != null 
						&& cellMap.getDirt(player.getPosition().checkLeft() - 1, player.getPosition().getY()).IsDirt() == false )
				{
					player.getPosition().goLeft();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
					player.push(itemMap.getRock(player.getPosition()));
				}
				break;
			default:
				break;
		}
		player.getState().setStateEnum(StatusActorEnum.IDLE);
	}
	
	/**
	 * Se occupa de hacer mover a los enemigos.
	 * @param actor
	 */
	private static void movingActor(Actor actor)
	{
		switch ( actor.getState().getStateEnum() )
		{
			case MOVINGUP:
				if( cellMap.getCell(actor.getPosition().getX(), actor.getPosition().checkUp()).isSolid() == false
				&& itemMap.getItem(actor.getPosition().getX(), actor.getPosition().checkUp()).isSolid() == false
				&& cellMap.getDirt(actor.getPosition().getX(), actor.getPosition().checkUp()) != null 
				&& cellMap.getDirt(actor.getPosition().getX(), actor.getPosition().checkUp()).IsDirt() == false )
				{
					actor.getPosition().goUp();
				}
				else
				{
					((Firefly) actor).rotate();
				}
				break;
			case MOVINGDOWN:
				if( cellMap.getCell(actor.getPosition().getX(), actor.getPosition().checkDown()).isSolid() == false
				&& itemMap.getItem(actor.getPosition().getX(), actor.getPosition().checkDown()).isSolid() == false
				&& cellMap.getDirt(actor.getPosition().getX(), actor.getPosition().checkDown()) != null 
				&& cellMap.getDirt(actor.getPosition().getX(), actor.getPosition().checkDown()).IsDirt() == false )
				{
					actor.getPosition().goDown();
				}
				else
				{
					((Firefly) actor).rotate();
				}
				break;
			case MOVINGRIGHT:
				if( cellMap.getCell(actor.getPosition().checkRight(), actor.getPosition().getY()).isSolid() == false
				&& itemMap.getItem(actor.getPosition().checkRight(), actor.getPosition().getY()).isSolid() == false
				&& cellMap.getDirt(actor.getPosition().checkRight(), actor.getPosition().getY()) != null 
				&& cellMap.getDirt(actor.getPosition().checkRight(), actor.getPosition().getY()).IsDirt() == false )
				{
					actor.getPosition().goRight();
				}
				else
				{
					((Firefly) actor).rotate();
				}
				break;
			case MOVINGLEFT:
				if( cellMap.getCell(actor.getPosition().checkLeft(), actor.getPosition().getY()).isSolid() == false
				&& itemMap.getItem(actor.getPosition().checkLeft(), actor.getPosition().getY()).isSolid() == false
				&& cellMap.getDirt(actor.getPosition().checkLeft(), actor.getPosition().getY()) != null 
				&& cellMap.getDirt(actor.getPosition().checkLeft(), actor.getPosition().getY()).IsDirt() == false )
				{
					actor.getPosition().goLeft();
				}
				else
				{
					((Firefly) actor).rotate();
				}
				break;
			default:
				break;
		}
	}

	/**
	 * Se occupa de hacer caer a los objetos en la matriz
	 * @param item
	 */
	private static void fallingItem(Item item)
	{
		StatusItemEnum status = item.getState().getStateEnum();
		switch (status)
		{
			case FALLING:
				if( cellMap.getCell(item.getPosition().getX(), item.getPosition().checkDown()).isSolid() == false
				&& itemMap.getItem(item.getPosition().getX(), item.getPosition().checkDown()).isSolid() == false)
				{
					item.getPosition().goDown();
				}
				break;
			case SLIDINGRIGHT:
				if( cellMap.getCell(item.getPosition().checkRight(), item.getPosition().getY()).isSolid() == false
				&& itemMap.getItem(item.getPosition().checkRight(), item.getPosition().getY()).isSolid() == false)
				{
					item.getPosition().goRight();
				}
				break;
			case SLIDINGLEFT:
				if( cellMap.getCell(item.getPosition().checkLeft(), item.getPosition().getY()).isSolid() == false
				&& itemMap.getItem(item.getPosition().checkLeft(), item.getPosition().getY()).isSolid() == false)
				{
					item.getPosition().goLeft();
				}
				break;
			default:
				break;
		}
		item.getState().setStateEnum(StatusItemEnum.IDLE);
	}

	/**
	 * Actualiza la posicion de la entidad en la matriz.
	 * @param entity
	 */
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
			((Item) entity).fall();
			fallingItem((Item) entity);
			itemMap.setItem(entity.getPosition(), (Item) entity);
		}
	}

	// TURNOS

	/**
	 * Actualiza el mapa. Actualiza la posicion de todas las entidades en la matriz.
	 */
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
