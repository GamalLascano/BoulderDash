package game.model.map;

import game.model.Entity;
import game.model.ListOfEntities;
import game.model.Position;
import game.model.actor.*;
import game.model.cell.*;
import game.model.item.*;
import game.model.map.bdlevel.BDLevelReader;

/**
 * Esta clase administra los 3 mapas de elementos y se ocupa de refresar la
 * lista de entidades, de construir y almacenar informacion del nivel. Contiene
 * a un mapa de celdas, items y actores, junto con una lista con todas las
 * entidades vivas.
 */
public class MapInstance
{
	private static MapInstance mapinstance;
	private static ListOfEntities listentity;
	private static BDLevelReader levelReader;
	private static Integer selectedLevel;
	private static Double timer;
	private static Integer diamondvalue;
	private static Integer diamondbonus;
	private static Integer playerscore;

	/**
	 * Constructor de MapInstance.
	 */
	private MapInstance()
	{
		listentity = null;
		levelReader = null;
		selectedLevel = null;
		timer = null;
		diamondvalue = null;
		diamondbonus = null;
		playerscore = 0;

	}

	/**
	 * Singleton de mapInstance.
	 * 
	 * @return mapinstance
	 */
	public static MapInstance getInstance()
	{
		// Si la instancia no se creo, se crea, y se devuelve la instancia
		if (mapinstance == null)
		{
			mapinstance = new MapInstance();
		}
		return mapinstance;
	}

	/**
	 * Inicializa mapinstance, inicializa los tres mapas de elementos, carga el
	 * levelreader y inicializa la lista de entidades.
	 * 
	 * @param levelReader
	 */
	public static void start()
	{
		BDLevelReader bdlevel = new BDLevelReader();
		MapInstance.getInstance();
		levelReader = bdlevel;
		listentity = ListOfEntities.getInstance();
		MapCell.getInstance().start();
		MapItem.getInstance().start();
		MapActor.getInstance().start();
		MapVisual.getInstance().start();
		ListOfEntities.start();
	}

	/**
	 * Se occupa de leer el levelreader utilizando el nivel eligido y saca
	 * informacion de este.
	 */
	public static void readLevel()
	{
		try
		{
			levelReader.readLevels("levels.xml");
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		try
		{
			levelReader.setCurrentLevel(selectedLevel);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		timer = 150.0 - selectedLevel;
		diamondvalue = 10 * selectedLevel;
		diamondbonus = 15 * selectedLevel;
	}

	/**
	 * Devuelve el levelreader. Util para acceder a su tamanio.
	 * 
	 * @return levelreader
	 */
	public static BDLevelReader getLevelReader()
	{
		return MapInstance.levelReader;
	}

	/**
	 * Devuelve la lista de entidades.
	 * 
	 * @return listentity
	 */
	public static ListOfEntities getEntitiesActive()
	{
		return listentity;
	}

	/**
	 * Setea el levelreader.
	 * 
	 * @param level
	 */
	public static void setLevelReader(BDLevelReader level)
	{
		MapInstance.levelReader = level;
	}

	/**
	 * Devuelve el numero del nivel actual.
	 * 
	 * @return selectedlevel
	 */
	public static Integer getSelectedLevel()
	{
		return selectedLevel;
	}

	/**
	 * Setea el numero del nivel actual.
	 * 
	 * @param selectedlevels
	 */
	public static void setSelectedLevel(Integer selectedlevels)
	{
		selectedLevel = selectedlevels;
	}

	/**
	 * Resetea el mapinstance y construye el nivel numero x.
	 * 
	 * @param selectedLevel
	 */
	public static void buildSelectedLevel(Integer selectedLevel)
	{
		MapInstance.getInstance();
		listentity = ListOfEntities.getInstance();
		MapCell.getInstance().start();
		MapItem.getInstance().start();
		MapActor.getInstance().start();
		ListOfEntities.start();

		MapInstance.selectedLevel = selectedLevel;
		MapInstance.readLevel();
		MapInstance.buildMap();
		MapVisual.drawMap();
	}

	/**
	 * Devuelve el cronometro del nivel.
	 * 
	 * @return timer
	 */
	public static Double getTimer()
	{
		return timer;
	}

	/**
	 * Devuelve el valor de los diamantes del nivel actual.
	 * 
	 * @return diamondvalue
	 */
	public static Integer getDiamondvalue()
	{
		return diamondvalue;
	}

	/**
	 * Devuelve el valor bonus de los diamantes del nivel actual.
	 * 
	 * @return diamondbonus
	 */
	public static Integer getDiamondbonus()
	{
		return diamondbonus;
	}

	/**
	 * Devuelve el score total del jugador.
	 * 
	 * @return playerscore
	 */
	public static Integer getPlayerscore()
	{
		return playerscore;
	}

	/**
	 * Setea el score total del jugador.
	 * 
	 * @param playerscore
	 */
	public static void setPlayerscore(Integer playerscore)
	{
		MapInstance.playerscore = playerscore;
	}

	/**
	 * Decrementa el cronometro del mapa.
	 * 
	 * @param timer
	 */
	public static void decrementTimer()
	{
		if (MapInstance.timer > 0)
		{
			MapInstance.timer -= 0.1;
		}
	}

	/**
	 * Mata a un elemento, lo remueve de su matriz correspondiente.
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
	 * Mata a un elemento, lo remueve de su matriz correspondiente, utiliza
	 * coordenadas X,Y.
	 * 
	 * @param x
	 * @param y
	 */
	public static void kill(Integer x, Integer y)
	{
		if(!MapCell.getCell(x, y).isTitanium())
		{
			MapCell.getCell(x, y).clear();
		}
		MapItem.getItem(x, y).die();
		if (MapActor.getActor(x, y) != null)
		{
			MapActor.getActor(x, y).die();
		}
	}

	/**
	 * Retorna si las coordenadas son validas.
	 * 
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public static boolean isInMapLimits(Integer x, Integer y)
	{
		if (MapInstance.getLevelReader().getWIDTH() > x && MapInstance.getLevelReader().getHEIGHT() > y && 0 <= x && 0 <= y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Determina si el nivel es vacio. (Si no hay Rockford).
	 * 
	 * @return boolean
	 */
	public static boolean levelHasRockford()
	{
		if (ListOfEntities.getList().contains(Rockford.getInstance()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Refresca el mapa, decrementa el timer, dibuja el mapa y cambia la
	 * posicion de los elementos.
	 */
	public static void refresh()
	{
		MapInstance.decrementTimer();
		MapVisual.drawMap();
		int i;
		for (i = 0; i < ListOfEntities.getList().size(); ++i)
		{
			Entity ent = ListOfEntities.getList().get(i);
			ent.changePosition();
		}
	}

	/**
	 * Genera el mapa utilizando los tiles del levelreader, creando los
	 * elementos y poniendolos en las matrices.
	 */
	public static void buildMap()
	{
		ListOfEntities.getList().clear();
		for (int y = 0; y < levelReader.getHEIGHT(); y++)
		{
			for (int x = 0; x < levelReader.getWIDTH(); x++)
			{
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
					// case MAGIC:
					// MapCell.setCell(new Wall(pos,5));
					// break;
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
						Exit door = Exit.getInstance();
						door.close();
						door.setPosition(pos);
						MapCell.setCell(door);
						break;
					case PLAYER:
						Rockford player = Rockford.getInstance();
						player.setPosition(pos);
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
