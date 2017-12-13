package game.controller.access;

import game.model.element.ElementChar;
import game.model.map.MapInstance;
import game.model.map.MapChar;

/**
 * 
 * Acceso del mapa en el modelo.
 *
 */
public class MapAccess
{
	/**
	 * Refresca el mapa, y la informacion del nivel y score total.
	 */
	public static void refresh()
	{
		MapInstance.getInstance().refresh();
	}

	/**
	 * 
	 * 
	 * @return altura del nivel
	 */
	public static Integer getHeight()
	{
		return MapInstance.getInstance().getLevelReader().getHEIGHT();
	}

	/**
	 * 
	 * 
	 * @return longitud del nivel
	 */
	public static Integer getWidth()
	{
		return MapInstance.getInstance().getLevelReader().getWIDTH();
	}

	/**
	 * 
	 * 
	 * @return nivel eligido.
	 */
	public static Integer getLevel()
	{
		return MapInstance.getInstance().getSelectedLevel();
	}

	/**
	 * 
	 * 
	 * @return diamantes necesarios del nivel
	 */
	public static Integer getDiamondsneeded()
	{
		return MapInstance.getInstance().getDiamondsneeded();
	}

	/**
	 * 
	 * 
	 * @return tiempo limitado del mapa
	 */
	public static Integer getTimer()
	{
		return MapInstance.getInstance().getTimer().intValue();
	}

	/**
	 *
	 * 
	 * @return score total del jugador
	 */
	public static Integer getTotalScore()
	{
		return MapInstance.getInstance().getPlayerscore();
	}

	/**
	 * 
	 * 
	 * @return caracter de un elemento
	 */
	public static ElementChar getCell(int x, int y)
	{
		return MapChar.getChar(x, y);
	}

	/**
	 * Setea el numero del nivel actual.
	 * 
	 * @param selectedlevels
	 */
	public static void setSelectedLevel(Integer selectedlevels)
	{
		MapInstance.getInstance().setSelectedLevel(selectedlevels);
	}

}
