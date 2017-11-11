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
	private static ListOfEntities entitiesAlive;

	// CONSTRUCTOR

	private MapInstance()
	{
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
		MapItem.getInstance().start(levels);
		MapActor.getInstance().start(levels);
		entitiesAlive = ListOfEntities.getInstance();
		ListOfEntities.start();
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
			ent.changePosition();
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
						MapCell.setCell(pos, new Dirt(pos, false));
						MapItem.setItem(pos, new Empty(stateItem, pos));
						MapActor.setActor(pos, null);
						break;
					case DIRT:
						MapCell.setCell(pos, new Dirt(pos));
						break;
					case TITANIUM:
						MapCell.setCell(pos, new Titanium(pos));
						break;
					case WALL:
						MapCell.setCell(pos, new Wall(pos));
						break;
					case ROCK:
						Rock rock = new Rock(stateItem, pos);
						MapItem.setItem(pos, rock);
						ListOfEntities.getList().add(rock);
						break;
					case FALLINGROCK:
						stateItem.setStateEnum(StatusItemEnum.FALLING);
						Rock fallingRock = new Rock(stateItem, pos);
						MapItem.setItem(pos, fallingRock);
						ListOfEntities.getList().add(fallingRock);
						break;
					case DIAMOND:
						Diamond diamond = new Diamond(stateItem, pos);
						MapItem.setItem(pos, diamond);
						ListOfEntities.getList().add(diamond);
						break;
					case FALLINGDIAMOND:
						stateItem.setStateEnum(StatusItemEnum.FALLING);
						Diamond fallingDiamond = new Diamond(stateItem, pos);
						MapItem.setItem(pos, fallingDiamond);
						ListOfEntities.getList().add(fallingDiamond);
						break;
					case AMOEBA:
						Amoeba amoeba = new Amoeba(stateItem, pos);
						MapItem.setItem(pos, amoeba);
						ListOfEntities.getList().add(amoeba);
						break;
					case FIREFLY:
						Firefly firefly = new Firefly(stateActor, pos);
						MapActor.setActor(pos, firefly);
						ListOfEntities.getList().add(firefly);
						break;
					case BUTTERFLY:
						Butterfly butterfly = new Butterfly(stateActor, pos);
						MapActor.setActor(pos, butterfly);
						ListOfEntities.getList().add(butterfly);
						break;
					case EXIT:
						MapCell.setCell(pos, new Exit(pos));
						break;
					case PLAYER:
						Rockford player = new Rockford(stateActor, pos);
						MapActor.setActor(pos, player);
						ListOfEntities.getList().add(player);
						break;
					default:
						break;
				}

			}
		}
	}

}
