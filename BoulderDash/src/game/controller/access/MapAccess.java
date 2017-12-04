package game.controller.access;

import game.model.element.SpriteChar;
import game.model.map.MapInstance;
import game.model.map.MapVisual;

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
		MapInstance.refresh();
	}

	/**
	 * 
	 * 
	 * @return altura del nivel
	 */
	public static Integer getHeight()
	{
		return MapInstance.getLevelReader().getHEIGHT();
	}

	/**
	 * 
	 * 
	 * @return longitud del nivel
	 */
	public static Integer getWidth()
	{
		return MapInstance.getLevelReader().getWIDTH();
	}

	/**
	 * 
	 * 
	 * @return nivel eligido.
	 */
	public static Integer getLevel()
	{
		return MapInstance.getSelectedLevel();
	}

	/**
	 * 
	 * 
	 * @return diamantes necesarios del nivel
	 */
	public static Integer getDiamondsneeded()
	{
		return MapInstance.getDiamondsneeded();
	}

	/**
	 * 
	 * 
	 * @return tiempo limitado del mapa
	 */
	public static Integer getTimer()
	{
		return MapInstance.getTimer().intValue();
	}

	/**
	 *
	 * 
	 * @return score total del jugador
	 */
	public static Integer getTotalScore()
	{
		return MapInstance.getPlayerscore();
	}

	/**
	 * 
	 * 
	 * @return caracter de un elemento
	 */
	public static SpriteChar getCell(int x, int y)
	{
		return MapVisual.getChar(x, y);
	}

	/**
	 * Setea el numero del nivel actual.
	 * 
	 * @param selectedlevels
	 */
	public static void setSelectedLevel(Integer selectedlevels)
	{
		MapInstance.setSelectedLevel(selectedlevels);
	}

}
