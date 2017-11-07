package game.map;

import game.actor.*;
import game.cell.*;
import game.item.*;
import game.ListOfEntities;
import game.Entity;
import game.map.bdlevel.BDLevelReader;
import game.Position;

/**
 * Esta clase contiene la instancia del mapa principal del juego, y controla la
 * gran parte del movimiento del juego Es singleton, y contiene a un mapa de
 * celdas, items y actores, junto con una lista con todas las entidades vivas
 */
public class MapInstance
{
	private static MapInstance singleton;
	private static MapCell cellMap;
	private static MapItem itemMap;
	private static MapActor actorMap;
	private static ListOfEntities entitiesAlive;

	// CONSTRUCTOR

	private MapInstance()
	{
		cellMap = null;
		itemMap = null;
		actorMap = null;
		entitiesAlive = null;
	}

	// SINGLETON

	/**
	 * Devuelve la unica instancia del mapa
	 * 
	 * @return el singleton de la clase
	 */
	public static MapInstance getInstance()
	{
		// Si la instancia no se creo, se crea, y se devuelve la instancia
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

	public static ListOfEntities getEntitiesActive()
	{
		return entitiesAlive;
	}

	// SETTERS

	// INICIALIZACION

	/**
	 * Con este metodo, creo cada uno de los mapas para que se puedan usar
	 * independientemente En conjunto con una lista con todas las entidades que
	 * se mueven
	 * 
	 * @param levels:
	 *            El lector de niveles del juego
	 */
	public static void start(BDLevelReader levels)
	{
		MapCell.getInstance().start(levels);
		cellMap = MapCell.getInstance();
		MapItem.getInstance().start(levels);
		itemMap = MapItem.getInstance();
		MapActor.getInstance().start(levels);
		actorMap = MapActor.getInstance();
		entitiesAlive = ListOfEntities.getInstance();
		ListOfEntities.start();
	}

	// ACTUALIZAR POSICION

	/**
	 * Se occupa de mover a Rockford en la matriz, tambien verifica si la celda
	 * destino es solida para moverse. Rockford cava automaticamente la tierra.
	 * 
	 * @param player
	 */
	private static void movingRockford(Rockford player)
	{
		// En el cado de moverse arriba, abajo, izquierda o derecha, se mueve de
		// forma diferente
		switch (player.getState().getStateEnum())
		{
			case MOVINGUP:
				// Si las celdas e items de arriba de rockford no son solidos...
				if (cellMap.getCell(player.getPosition().getX(), player.getPosition().checkUp()).isSolid() < 2
						&& itemMap.getItem(player.getPosition().getX(), player.getPosition().checkUp()).isSolid() < 2)
				{
					// ..rompo la tierra arriba de rockford y agarro el diamante
					// en el caso de que haya
					player.getPosition().goUp();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				else
				{
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				break;
			case MOVINGDOWN:
				// Si las celdas e items de abajo de rockford no son solidos...
				if (cellMap.getCell(player.getPosition().getX(), player.getPosition().checkDown()).isSolid() < 2
						&& itemMap.getItem(player.getPosition().getX(), player.getPosition().checkDown()).isSolid() < 2)
				{
					// ..rompo la tierra abajo de rockford y agarro el diamante
					// en el caso de que haya
					player.getPosition().goDown();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				else
				{
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				break;
			case MOVINGRIGHT:
				// Si las celdas e items en la derecha de rockford no son
				// solidos...
				if (cellMap.getCell(player.getPosition().checkRight(), player.getPosition().getY()).isSolid() < 2
						&& itemMap.getItem(player.getPosition().checkRight(), player.getPosition().getY())
								.isSolid() < 2)
				{
					// ..rompo la tierra en la derecha de rockford y agarro el
					// diamante en el caso de que haya
					player.getPosition().goRight();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				// Si no es solido, es movible, y no hay tierra al lado.
				else if (cellMap.getCell(player.getPosition().checkRight(), player.getPosition().getY()).isSolid() < 2
						&& itemMap.getItem(player.getPosition().checkRight(), player.getPosition().getY())
								.isMoveable() == true
						&& itemMap.getItem(player.getPosition().checkRight() + 1, player.getPosition().getY())
								.isSolid() < 1
						&& cellMap.getCell(player.getPosition().checkRight() + 1, player.getPosition().getY())
								.isSolid() < 1)
				{
					// Se pushea lo que haya
					player.getPosition().goRight();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
					player.push(itemMap.getRock(player.getPosition()));
				}
				else
				{
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				break;
			case MOVINGLEFT:
				// Si las celdas e items en la izquierda de rockford no son
				// solidos...
				if (cellMap.getCell(player.getPosition().checkLeft(), player.getPosition().getY()).isSolid() < 2
						&& itemMap.getItem(player.getPosition().checkLeft(), player.getPosition().getY()).isSolid() < 2)
				{
					// ..rompo la tierra en la derecha de rockford y agarro el
					// diamante en el caso de que haya
					player.getPosition().goLeft();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				// Si no es solido, es movible, y no hay tierra al lado
				else if (cellMap.getCell(player.getPosition().checkLeft(), player.getPosition().getY()).isSolid() < 2
						&& itemMap.getItem(player.getPosition().checkLeft(), player.getPosition().getY())
								.isMoveable() == true
						&& itemMap.getItem(player.getPosition().checkLeft() + 1, player.getPosition().getY())
								.isSolid() < 1
						&& cellMap.getCell(player.getPosition().checkLeft() + 1, player.getPosition().getY())
								.isSolid() < 1)
				{
					// Se pushea lo que haya
					player.getPosition().goLeft();
					player.dig(cellMap.getDirt(player.getPosition()));
					player.collect(itemMap.getDiamond(player.getPosition()));
					player.push(itemMap.getRock(player.getPosition()));
				}
				else
				{
					player.collect(itemMap.getDiamond(player.getPosition()));
				}
				break;
			default:
				player.collect(itemMap.getDiamond(player.getPosition()));
				break;
		}
		// Por ultimo se setea al jugador en idle
		player.getState().setStateEnum(StatusActorEnum.IDLE);
	}

	/**
	 * Se occupa de hacer mover a los enemigos.
	 * 
	 * @param actor:
	 *            El actor que se necesita mover
	 */
	private static void movingEnemy(Enemy enemy)
	{
		switch (enemy.getState().getStateEnum())
		{
			case MOVINGUP:
				if (cellMap.getCell(enemy.getPosition().getX(), enemy.getPosition().checkUp()).isSolid() < 1
						&& itemMap.getItem(enemy.getPosition().getX(), enemy.getPosition().checkUp()).isSolid() < 1)
				{
					enemy.getPosition().goUp();
				}
				else
				{
					((Firefly) enemy).rotate();
					// ((Butterfly) enemy).rotate();
				}
				break;
			case MOVINGDOWN:
				if (cellMap.getCell(enemy.getPosition().getX(), enemy.getPosition().checkDown()).isSolid() < 1
						&& itemMap.getItem(enemy.getPosition().getX(), enemy.getPosition().checkDown()).isSolid() < 1)
				{
					enemy.getPosition().goDown();
				}
				else
				{
					((Firefly) enemy).rotate();
					// ((Butterfly) enemy).rotate();
				}
				break;
			case MOVINGRIGHT:
				if (cellMap.getCell(enemy.getPosition().checkRight(), enemy.getPosition().getY()).isSolid() < 1
						&& itemMap.getItem(enemy.getPosition().checkRight(), enemy.getPosition().getY()).isSolid() < 1)
				{
					enemy.getPosition().goRight();
				}
				else
				{
					((Firefly) enemy).rotate();
					// ((Butterfly) enemy).rotate();
				}
				break;
			case MOVINGLEFT:
				if (cellMap.getCell(enemy.getPosition().checkLeft(), enemy.getPosition().getY()).isSolid() < 1
						&& itemMap.getItem(enemy.getPosition().checkLeft(), enemy.getPosition().getY()).isSolid() < 1)
				{
					enemy.getPosition().goLeft();
				}
				else
				{
					((Firefly) enemy).rotate();
					// ((Butterfly) enemy).rotate();
				}
				break;
			default:
				break;
		}
		enemy.getState().setStateEnum(StatusActorEnum.IDLE);
	}

	/**
	 * Se occupa de hacer caer a los objetos en la matriz
	 * 
	 * @param item
	 */
	private static void fallingItem(Item item)
	{
		StatusItemEnum status = item.getState().getStateEnum();
		switch (status)
		{
			case FALLING:
				if (cellMap.getCell(item.getPosition().getX(), item.getPosition().checkDown()).isSolid() < 1
						&& itemMap.getItem(item.getPosition().getX(), item.getPosition().checkDown()).isSolid() < 1
						&& actorMap.getActor(item.getPosition().getX(), item.getPosition().checkDown()) != null)
				{
					item.getPosition().goDown();
				}
				break;
			case SLIDINGRIGHT:
				if (cellMap.getCell(item.getPosition().checkRight(), item.getPosition().getY()).isSolid() < 1
						&& itemMap.getItem(item.getPosition().checkRight(), item.getPosition().getY()).isSolid() < 1
						&& actorMap.getActor(item.getPosition().checkRight(), item.getPosition().getY()) != null)
				{
					item.getPosition().goRight();
				}
				break;
			case SLIDINGLEFT:
				if (cellMap.getCell(item.getPosition().checkLeft(), item.getPosition().getY()).isSolid() < 1
						&& itemMap.getItem(item.getPosition().checkLeft(), item.getPosition().getY()).isSolid() < 1
						&& actorMap.getActor(item.getPosition().checkLeft(), item.getPosition().getY()) != null)
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
	 * 
	 * @param entity
	 */
	public static void changePosition(Entity entity)
	{
		// Si esa entidad es rockford, se mueve y se hacen las operaciones
		// determinadas
		if (entity instanceof Rockford)
		{
			actorMap.removeActor(entity.getPosition());
			movingRockford((Rockford) entity);
			actorMap.setActor(entity.getPosition(), (Rockford) entity);
		}
		// Si esa entidad es otro actor, se hace lo mismo
		else if (entity instanceof Actor)
		{
			actorMap.removeActor(entity.getPosition());
			movingEnemy((Enemy) entity);
			actorMap.setActor(entity.getPosition(), (Actor) entity);
		}
		// Si esa entidad es otro item, se hace lo mismo, pero si es un item que
		// se cae, se checkea si cae
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
	 * Actualiza el mapa. Actualiza la posicion de todas las entidades en la
	 * matriz.
	 */
	public static void refresh()
	{
		int i;
		for (i = 0; i < ListOfEntities.getList().size(); ++i)
		{
			Entity ent = ListOfEntities.getList().get(i);
			changePosition(ent);
		}
	}

	// MAPA

	/**
	 * Construye el mapa. Convierte la matriz tiles a 3 matrices. 1 matriz de
	 * actores, 1 matriz de items, 1 matriz de celdas. Agrega actores y items en
	 * una lista de entities.
	 * 
	 * @param level
	 *            : nivel.
	 */
	public static void buildMap(BDLevelReader level)
	{
		// Para armar el mapa, voy por todo el nivel
		for (int y = 0; y < level.getHEIGHT(); y++)
		{
			for (int x = 0; x < level.getWIDTH(); x++)
			{
				// Hago un nuevo status para el item/actor nuevo, y uso la
				// posicion actual
				StatusItem stateItem = new StatusItem();
				StatusActor stateActor = new StatusActor();
				Position pos = new Position();

				pos.setXY(x, y);
				stateItem.reset(StatusItemEnum.IDLE, true);
				stateActor.reset(StatusActorEnum.IDLE, true);
				// y dependiendo de lo que se encuentre, se guarda en cada uno
				// de los mapas
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
						ListOfEntities.getList().add(rock);
						break;
					case FALLINGROCK:
						stateItem.setStateEnum(StatusItemEnum.FALLING);
						Rock fallingRock = new Rock(stateItem, pos);
						itemMap.setItem(pos, fallingRock);
						ListOfEntities.getList().add(fallingRock);
						break;
					case DIAMOND:
						Diamond diamond = new Diamond(stateItem, pos);
						itemMap.setItem(pos, diamond);
						ListOfEntities.getList().add(diamond);
						break;
					case FALLINGDIAMOND:
						stateItem.setStateEnum(StatusItemEnum.FALLING);
						Diamond fallingDiamond = new Diamond(stateItem, pos);
						itemMap.setItem(pos, fallingDiamond);
						ListOfEntities.getList().add(fallingDiamond);
						break;
					case AMOEBA:
						Amoeba amoeba = new Amoeba(stateItem, pos);
						itemMap.setItem(pos, amoeba);
						ListOfEntities.getList().add(amoeba);
						break;
					case FIREFLY:
						Firefly firefly = new Firefly(stateActor, pos);
						actorMap.setActor(pos, firefly);
						ListOfEntities.getList().add(firefly);
						break;
					case BUTTERFLY:
						Butterfly butterfly = new Butterfly(stateActor, pos);
						actorMap.setActor(pos, butterfly);
						ListOfEntities.getList().add(butterfly);
						break;
					case EXIT:
						cellMap.setCell(pos, new Exit(pos));
						break;
					case PLAYER:
						Rockford player = new Rockford(stateActor, pos);
						actorMap.setActor(pos, player);
						ListOfEntities.getList().add(player);
						break;
					default:
						break;
				}

			}
		}
	}

}
