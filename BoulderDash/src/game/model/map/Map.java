package game.model.map;

import game.model.element.Element;
import game.model.element.Position;

/**
 * Clase padre de los mapas.
 */
public abstract class Map
{

	/**
	 * Inicializa el mapa.
	 */
	abstract public void start();

	/**
	 * Llena el mapa, inicializa la matriz con tierra, vacio o null.
	 */
	abstract public void fill();

	/**
	 * 
	 * @return Altura del mapa
	 */
	public static int mapHeight()
	{
		return MapInstance.getInstance().getLevelReader().getHEIGHT();
	}

	/**
	 * 
	 * @return Longitud del mapa
	 */
	public static int mapWidth()
	{
		return MapInstance.getInstance().getLevelReader().getWIDTH();
	}
	
	/**
	 * 
	 * @param pos
	 * @return si la posicion esta en el mapa
	 */
	public static boolean isInMap(Position pos)
	{
		return mapWidth() >= pos.getX() && 0 <= pos.getX()
				&& mapHeight() >= pos.getY() && 0 <= pos.getY();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return si las coordenadas esta en el mapa
	 */
	public static boolean isInMap(Integer x, Integer y)
	{
		return mapWidth() >= x && 0 <= x && mapHeight() >= y && 0 <= y;
	}
	
	/**
	 * 
	 * @param element
	 * @return si la pos del elemento esta en el mapa
	 */
	public static boolean elementPosIsInMap(Element element)
	{
		return mapWidth() >= element.getPosition().getX() && 0 <= element.getPosition().getX() && mapHeight() >= element.getPosition().getY() && 0 <= element.getPosition().getY();
	}
}
