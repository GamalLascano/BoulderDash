package game.model.map;

import game.model.Entity;
import game.model.ListOfEntities;
import game.model.Position;
import game.model.SolidTo;
import game.model.actor.*;
import game.model.cell.*;
import game.model.item.*;
import game.model.map.bdlevel.BDLevelReader;

/**
 * Esta clase contiene la instancia del mapa principal del juego, y controla la
 * gran parte del movimiento del juego Es singleton, y contiene a un mapa de
 * celdas, items y actores, junto con una lista con todas las entidades vivas
 */
public class MapInstance
{
	private static MapInstance singleton;
	private static ListOfEntities entitiesAlive;
	private static BDLevelReader levelReader;

	/**
	 * 
	 */
	private MapInstance()
	{
		entitiesAlive = null;
		levelReader = null;

	}

	/**
	 * 
	 * @return
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

	/**
	 * 
	 * @param levelReader
	 */
	public static void start(BDLevelReader levelReader)
	{
		MapInstance.getInstance();
		MapCell.getInstance().start(levelReader);
		MapItem.getInstance().start(levelReader);
		MapActor.getInstance().start(levelReader);
		MapInstance.setLevelReader(levelReader);
		entitiesAlive = ListOfEntities.getInstance();
		ListOfEntities.start();
	}

	/**
	 * 
	 * @return
	 */
	public static BDLevelReader getLevelReader()
	{
		return MapInstance.levelReader;
	}

	/**
	 * 
	 * @return
	 */
	public static ListOfEntities getEntitiesActive()
	{
		return entitiesAlive;
	}

	/**
	 * 
	 * @param level
	 */
	public static void setLevelReader(BDLevelReader level)
	{
		MapInstance.levelReader = level;
	}

	/**
	 * 
	 * @param pos
	 */
	public static void kill(Position pos)
	{
		MapCell.getCell(pos.getX(), pos.getY()).clear();
		MapItem.getItem(pos.getX(), pos.getY()).die();
		MapActor.getActor(pos.getX(), pos.getY()).die();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public static void kill(Integer x, Integer y)
	{
		MapCell.getCell(x, y).clear();
		MapItem.getItem(x, y).die();
		if (MapActor.getActor(x, y) != null)
		{
			MapActor.getActor(x, y).die();
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean isInMapLimits(Integer x, Integer y)
	{
		if (MapInstance.getLevelReader().getWIDTH() > x && MapInstance.getLevelReader().getHEIGHT() > y && 0 <= x
				&& 0 <= y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static SolidTo solid(Integer x, Integer y)
	{
		if (isInMapLimits(x, y))
		{
			SolidTo a, b, c, d;

			a = MapCell.getCell(x, y).getSolid();
			b = MapItem.getItem(x, y).getSolid();
			if (MapActor.getActor(x, y) != null)
			{
				c = MapActor.getActor(x, y).getSolid();
			}
			else
			{
				c = SolidTo.NONE;
			}

			d = a.ordinal() > b.ordinal() ? a : b;
			d = d.ordinal() > c.ordinal() ? d : c;
			return d;
		}
		else
		{
			return SolidTo.ALL;
		}
	}

	/**
	 * 
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

	/**
	 * 
	 */
	public static void buildMap()
	{
		// Para armar el mapa, voy por todo el nivel
		for (int y = 0; y < levelReader.getHEIGHT(); y++)
		{
			for (int x = 0; x < levelReader.getWIDTH(); x++)
			{
				// Hago un nuevo status para el item/actor nuevo, y uso la
				// posicion actual
				// y dependiendo de lo que se encuentre, se guarda en cada uno
				// de los mapas
				Position pos = new Position(x, y);
				switch (levelReader.getTile(x, y))
				{
					case EMPTY:
						MapCell.setCell(new Dirt(pos, false));
						MapItem.setItem(new Empty(pos));
						MapActor.removeActor(pos);
						break;
					case DIRT:
						MapCell.setCell(new Dirt(pos));
						break;
					case TITANIUM:
						MapCell.setCell(new Titanium(pos));
						break;
					case WALL:
						MapCell.setCell(new Wall(pos));
						break;
//					case MAGIC:
//						MapCell.setCell(new Wall(pos,5));
//						break;
					case ROCK:
						Rock rock = new Rock(pos);
						MapItem.setItem(rock);
						ListOfEntities.getList().add(rock);
						break;
					case FALLINGROCK:
						Rock fallingRock = new Rock(pos, StatusFallableEnum.FALLING);
						MapItem.setItem(fallingRock);
						ListOfEntities.getList().add(fallingRock);
						break;
					case DIAMOND:
						Diamond diamond = new Diamond(pos);
						MapItem.setItem(diamond);
						ListOfEntities.getList().add(diamond);
						break;
					case FALLINGDIAMOND:
						Diamond fallingDiamond = new Diamond(pos, StatusFallableEnum.FALLING);
						MapItem.setItem(fallingDiamond);
						ListOfEntities.getList().add(fallingDiamond);
						break;
					case AMOEBA:
						Amoeba amoeba = new Amoeba(pos);
						MapItem.setItem(amoeba);
						ListOfEntities.getList().add(amoeba);
						break;
					case FIREFLY:
						Firefly firefly = new Firefly(pos);
						MapActor.setActor(firefly);
						ListOfEntities.getList().add(firefly);
						break;
					case BUTTERFLY:
						Butterfly butterfly = new Butterfly(pos);
						MapActor.setActor(butterfly);
						ListOfEntities.getList().add(butterfly);
						break;
					case EXIT:
						MapCell.setCell(Exit.getInstance(pos));
						break;
					case PLAYER:
						Rockford player = Rockford.getInstance(pos);
						MapActor.setActor(player);
						ListOfEntities.getList().add(player);
						break;
					default:
						break;
				}

			}
		}
	}

}
