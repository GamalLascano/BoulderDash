package game.model.map;

import game.model.element.Position;
import game.model.element.ElementTypes;
import game.model.element.entity.actor.Rockford;

/**
 * Esta clase es la encargada de agarrar los elementos y hacer una matriz de
 * carateres con estos.
 */
public class MapChar extends Map
{
	private static MapChar mapvisual;
	private static ElementTypes[][] map;

	/**
	 * Constructor de MapVisual.
	 */
	private MapChar()
	{
		map = null;
	}

	/**
	 * Singleton MapVisual.
	 * 
	 * @return
	 */
	public static MapChar getInstance()
	{
		if (mapvisual == null)
		{
			mapvisual = new MapChar();
		}
		return mapvisual;
	}

	/**
	 * Construye el mapa de elementos con sus caracteres, los actores tienen mas
	 * prioridad que los objetos que tienen mas prioridad que las celdas.
	 */
	public static void drawMap()
	{
		for (int y = 0; y < mapHeight(); y++)
		{
			for (int x = 0; x < mapWidth(); x++)
			{
				putElementChar(y, x);
			}
		}
	}

	/**
	 * Imprime el mapa y algunas informacion. Se utiliza para la consola y
	 * debugging.
	 */
	public static void imprimirMapa()
	{
		System.out.println("..............................................................");
		imprimiendoMapa();
		showInfo();
		System.out.println("..............................................................");

	}

	/**
	 * Devuelve un caracter de la matriz, utilizando coordenadas X,Y.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static ElementTypes getChar(int x, int y)
	{
		return map[x][y];
	}

	@Override
	public void start()
	{
		MapChar.getInstance();
		map = new ElementTypes[mapWidth()][mapHeight()];
		fill();
	}

	@Override
	public void fill()
	{
		for (int x = 0; x < mapWidth(); x++)
			for (int y = 0; y < mapHeight(); y++)
			{
				map[x][y] = ElementTypes.Dirt;
			}
	}
	
	
	
	
	//////////////////
	
	
	
	
	/**
	 * Pone el caracter en el mapa.
	 * @param y
	 * @param x
	 */
	private static void putElementChar(int y, int x)
	{
		Position pos = new Position(x, y);
		if (MapElement.getElement(pos).isActor())
		{
			map[x][y] = MapElement.getElement(pos).getElementType();
		}
		else if (!MapElement.getElement(pos).isEmpty())
		{
			map[x][y] = MapElement.getElement(pos).getElementType();
		}
		else
		{
			map[x][y] = MapElement.getElement(pos).getElementType();
		}
	}
	
	/**
	 * Muestra la informacion del turno.
	 */
	private static void showInfo()
	{
		Rockford player = Rockford.getInstance();
		if (player != null)
		{
			showTurnInfo(player);
		}
		else
		{
			showEndInfo();
		}
	}

	/**
	 * Mostra la informacion del ultimo turno.
	 */
	private static void showEndInfo()
	{
		System.out.println("Rockford muerto");
	}

	/**
	 * Muestra la informacion del turno.
	 * @param player
	 */
	private static void showTurnInfo(Rockford player)
	{
		System.out.println("Rockford Pos: " + player.getPosition().getX() + "," + player.getPosition().getY());
		System.out.println("Rockford Diamantes: " + player.getDiamonds());
	}

	/**
	 * Imprime el mapa.
	 */
	private static void imprimiendoMapa()
	{
		for (int y = 0; y < mapHeight(); y++)
		{
			for (int x = 0; x < mapWidth(); x++)
			{
				System.out.print(map[x][y].getLetter());
				System.out.print(" ");
			}
			System.out.println();
		}
	}


}
