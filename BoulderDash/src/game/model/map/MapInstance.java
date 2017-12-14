package game.model.map;

import game.exception.LevelNotValidException;
import game.exception.RockfordNotInLevelException;
import game.model.element.Position;
import game.model.element.cell.*;
import game.model.element.entity.Entity;
import game.model.element.entity.ListOfEntities;
import game.model.element.entity.actor.*;
import game.model.element.entity.item.*;
import game.model.map.bdlevel.BDLevelReader;

/**
 * Esta clase administra el mapa de elementos y se ocupa de refresar la
 * lista de entidades, de construir y almacenar informacion del nivel. Contiene
 * a un mapa de celdas, items y actores, junto con una lista con todas las
 * entidades vivas.
 */
public class MapInstance
{
	private static MapInstance mapinstance;
	private ListOfEntities listentity;
	private BDLevelReader levelReader;
	private Integer playerscore;
	private Integer selectedLevel;
	private Integer diamondsneeded;
	private Integer diamondvalue;
	private Integer diamondbonus;
	private Double timer;

	private static int[][] levelinfo = new int[][]
	{
			{ 1, 12, 10, 15, 110 },
			{ 2, 10, 20, 50, 110 },
			{ 3, 23, 15, 0, 100 },
			{ 4, 36, 5, 20, 100 },
			{ 5, 6, 30, 0, 100 },
			{ 6, 5, 50, 90, 120 },
			{ 7, 5, 50, 100, 100 },
			{ 8, 5, 50, 90, 120 },
			{ 9, 5, 50, 90, 120 }, };

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

	
	
	//////////
	
	
	
	
	
	/**
	 * Inicializa mapinstance, inicializa los tres mapas de elementos, carga el
	 * levelreader y inicializa la lista de entidades.
	 * 
	 * @param levelReader
	 */
	public static void start()
	{
		putLevelReader();
		initializeMapInstance();
	}

	/**
	 * Se occupa de leer el levelreader utilizando el nivel eligido y saca
	 * informacion de este.
	 */
	private void readLevel()
	{
		readingLevel();
		readLevelInfo();
	}
	
	/**
	 * Construye y setea el proximo nivel.
	 */
	public void levelNext()
	{
		setSelectedLevel(selectedLevel + 1);
		rebuildingSelectedLevel();
	}
	
	/**
	 * Reinicia el nivel.
	 */
	public void levelRestart()
	{
		rebuildingSelectedLevel();
	}
	
	/**
	 * Construye y setea el anterior nivel.
	 */
	public void levelPrevious()
	{
		setSelectedLevel(selectedLevel - 1);
		rebuildingSelectedLevel();
	}
	
	/**
	 * Resetea el mapinstance y construye el nivel numero x.
	 * 
	 * @param selectedLevel
	 */
	public void buildSelectedLevel(Integer selectedLevel) throws LevelNotValidException
	{
		if (levelNotValid(selectedLevel))
		{
			throw new LevelNotValidException("Nivel no valido");
		}
		else
		{
			initializeMapInstance();
			mapinstance.selectedLevel = selectedLevel;
			readLevel();
			buildingMap();
			MapChar.drawMap();
		}
	}

	/**
	 * Decrementa el cronometro del mapa.
	 * 
	 * @param timer
	 */
	private void decrementTimer()
	{
		if (timer > 0)
		{
			timer -= 0.1;
		}
	}

	/**
	 * Mata a un elemento, lo remueve de su matriz correspondiente.
	 * 
	 * @param pos
	 */
	public void kill(Position pos)
	{
		MapElement.getElement(pos.getX(), pos.getY()).die();
	}

	/**
	 * Mata a un elemento, lo remueve de su matriz correspondiente, utiliza
	 * coordenadas X,Y.
	 * 
	 * @param x
	 * @param y
	 */
	public void kill(Integer x, Integer y)
	{
		if (elementCanDie(x, y))
		{
			MapElement.getElement(x, y).die();
		}
	}

	/**
	 * Determina si el nivel es vacio. (Si no hay Rockford).
	 * 
	 * @return boolean
	 */
	public boolean levelHasRockford()
	{
		if (ListOfEntities.getList().contains(Rockford.getInstance()))
			return true;
		else
			return false;
	}

	/**
	 * Refresca el mapa, decrementa el timer, dibuja el mapa y cambia la
	 * posicion de las entidades.
	 */
	public void refresh()
	{
		decrementTimer();
		MapChar.drawMap();
		entitiesMove();
	}
	
	/**
	 * Genera el mapa utilizando los tiles del levelreader, creando los
	 * elementos y poniendolos en las matrices.
	 */
	private void buildMap() throws RockfordNotInLevelException
	{
		ListOfEntities.getList().clear();
		for (int y = 0; y < levelReader.getHEIGHT(); y++)
		{
			for (int x = 0; x < levelReader.getWIDTH(); x++)
			{
				putLevelTile(y, x);
			}
		}
		checkRockfordNotInLevel();
	}


	
	
	////////// GETTERS/SETTERS
	
	
	
	
	
	
	/**
	 * Devuelve el levelreader. Util para acceder a su tamanio.
	 * 
	 * @return levelreader
	 */
	public BDLevelReader getLevelReader()
	{
		return levelReader;
	}
	
	/**
	 * Devuelve la lista de entidades.
	 * 
	 * @return listentity
	 */
	public ListOfEntities getEntitiesActive()
	{
		return listentity;
	}

	/**
	 * Devuelve el numero del nivel actual.
	 * 
	 * @return selectedlevel
	 */
	public Integer getSelectedLevel()
	{
		return selectedLevel;
	}

	/**
	 * Setea el numero del nivel actual.
	 * 
	 * @param selectedlevels
	 */
	public void setSelectedLevel(Integer selectedlevels)
	{
		selectedLevel = selectedlevels;
	}
	
	/**
	 * Devuelve el cronometro del nivel.
	 * 
	 * @return timer
	 */
	public Double getTimer()
	{
		return timer;
	}

	/**
	 * Devuelve el valor de los diamantes del nivel actual.
	 * 
	 * @return diamondvalue
	 */
	public Integer getDiamondvalue()
	{
		return diamondvalue;
	}

	/**
	 * Devuelve el valor bonus de los diamantes del nivel actual.
	 * 
	 * @return diamondbonus
	 */
	public Integer getDiamondbonus()
	{
		return diamondbonus;
	}

	/**
	 * Devuelve el score total del jugador.
	 * 
	 * @return playerscore
	 */
	public Integer getPlayerscore()
	{
		return playerscore;
	}

	/**
	 * Setea el score total del jugador.
	 * 
	 * @param playerscore
	 */
	public void setPlayerscore(Integer playerscore)
	{
		mapinstance.playerscore = playerscore;
	}

	/**
	 * Devuelve la cantidad necesaria de diamantes para abrir la puerta.
	 * 
	 * @return diamondsneeded
	 */
	public Integer getDiamondsneeded()
	{
		return diamondsneeded;
	}
	
	
	
	
	
	//////////////////////////////////// PRIVATE METHODS
	
	
	
	
	
	/**
	 * Lee el level reader.
	 */
	private void readingLevel()
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
	}
	
	/**
	 * Lee la informacion del nivel.
	 */
	private void readLevelInfo()
	{
		diamondsneeded = levelinfo[selectedLevel - 1][1];
		diamondvalue = levelinfo[selectedLevel - 1][2];
		diamondbonus = levelinfo[selectedLevel - 1][3];
		timer = (double) levelinfo[selectedLevel - 1][4];
	}
	
	/**
	 * Rehace el nivel.
	 */
	private void rebuildingSelectedLevel()
	{
		try
		{
			buildSelectedLevel(selectedLevel);
		}
		catch (LevelNotValidException e1)
		{
			try
			{
				buildSelectedLevel(1);
			}
			catch (LevelNotValidException e2)
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			e1.printStackTrace();
		}
	}
	
	/**
	 * Construyendo el mapa.
	 */
	private void buildingMap()
	{
		try
		{
			buildMap();
		}
		catch (RockfordNotInLevelException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Reinitializa MapInstance.
	 */
	private static void initializeMapInstance()
	{
		MapInstance.getInstance();
		mapinstance.listentity = ListOfEntities.getInstance();
		MapElement.getInstance().start();
		MapChar.getInstance().start();
		ListOfEntities.start();
	}
	
	/**
	 * Pone el lector de niveles
	 */
	private static void putLevelReader()
	{
		BDLevelReader bdlevel = new BDLevelReader();
		mapinstance.levelReader = bdlevel;
	}
	
	/**
	 * 
	 * @param selectedLevel
	 * @return si el nivel es valido
	 */
	private boolean levelNotValid(Integer selectedLevel)
	{
		return selectedLevel <= 0 || selectedLevel >= 10;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return si puede morir el elemento
	 */
	private boolean elementCanDie(Integer x, Integer y)
	{
		return MapElement.getElement(x, y) != null && !MapElement.getElement(x, y).isTitanium();
	}
	
	/**
	 * Cambia la posicion de las entidades.
	 */
	private void entitiesMove()
	{
		for (int i = 0; i < ListOfEntities.getList().size(); ++i)
		{
			Entity ent = ListOfEntities.getList().get(i);
			ent.changePosition();
		}
	}
	
	/**
	 * Checkea si rockford esta en el nivel.
	 * @throws RockfordNotInLevelException
	 */
	private void checkRockfordNotInLevel() throws RockfordNotInLevelException
	{
		if (!ListOfEntities.getList().contains(Rockford.getInstance()))
		{
			throw new RockfordNotInLevelException("Rockford no esta en el mapa");
		}
	}

	/**
	 * Convierte las tiles del levelreader a elementos y los pone en el
	 * mapa de elementos.
	 * @param y
	 * @param x
	 */
	private void putLevelTile(int y, int x)
	{
		Position pos = new Position(x, y);
		switch (levelReader.getTile(x, y))
		{
			case EMPTY:
				MapElement.setCell(new Dirt(pos, false));
				break;
			case DIRT:
				MapElement.setCell(new Dirt(pos));
				break;
			case TITANIUM:
				MapElement.setCell( new Titanium(pos));
				break;
			case WALL:
				MapElement.setCell(new Wall(pos));
				break;
			// case MAGIC:
			// MapElement.setElement(new Wall(pos,5));
			// break;
			case ROCK:
				Rock rock = new Rock(pos);
				MapElement.setItem(rock);
				ListOfEntities.getList().add(rock);
				break;
			case FALLINGROCK:
				Rock fallingRock = new Rock(pos, StatusFallableEnum.FALLING);
				MapElement.setItem(fallingRock);
				ListOfEntities.getList().add(fallingRock);
				break;
			case DIAMOND:
				Diamond diamond = new Diamond(pos);
				MapElement.setItem(diamond);
				ListOfEntities.getList().add(diamond);
				break;
			case FALLINGDIAMOND:
				Diamond fallingDiamond = new Diamond(pos, StatusFallableEnum.FALLING);
				MapElement.setItem(fallingDiamond);
				ListOfEntities.getList().add(fallingDiamond);
				break;
			case AMOEBA:
				Amoeba amoeba = new Amoeba(pos);
				MapElement.setItem(amoeba);
				ListOfEntities.getList().add(amoeba);
				break;
			case FIREFLY:
				Firefly firefly = new Firefly(pos);
				MapElement.setActor(firefly);
				ListOfEntities.getList().add(firefly);
				break;
			case BUTTERFLY:
				Butterfly butterfly = new Butterfly(pos);
				MapElement.setActor(butterfly);
				ListOfEntities.getList().add(butterfly);
				break;
			case EXIT:
				Exit door = Exit.getInstance();
				door.close();
				door.setPosition(pos);
				MapElement.setCell(door);
				break;
			case PLAYER:
				Rockford player = Rockford.getInstance();
				player.setPosition(pos);
				MapElement.setActor(player);
				ListOfEntities.getList().add(player);
				break;
			default:
				break;
		}
	}
	
	
	
	
	////////
	
	
	
	
//	/**
//	 * Retorna si las coordenadas son validas.
//	 * 
//	 * @param x
//	 * @param y
//	 * @return boolean
//	 */
//	public boolean isInMapLimits(Integer x, Integer y)
//	{
//		return getLevelReader().getWIDTH() > x && getLevelReader().getHEIGHT() > y && 0 <= x && 0 <= y;
//	}
}
