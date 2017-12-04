package game.controller;

import game.model.element.SpriteChar;
import game.model.map.MapInstance;
import game.model.map.MapVisual;

/**
 * 
 * Acceso del mapa.
 *
 */
public class MapAccess
{
	
	/**
	 * 
	 * @return
	 */
	public static Integer getHeight()
	{
		return MapInstance.getLevelReader().getHEIGHT();
	}
	
	/**
	 * 
	 * @return
	 */
	public static Integer getWidth()
	{
		return MapInstance.getLevelReader().getWIDTH();
	}
	
	/**
	 * Devuelve el nivel actual.
	 * @return
	 */
	public static Integer getLevel()
	{
		return MapInstance.getSelectedLevel();
	}
	
	/**
	 * Devuelve los diamantes necesarios.
	 * @return
	 */
	public static Integer getDiamondsneeded()
	{
		return MapInstance.getDiamondsneeded();
	}
	
	/**
	 * Devuelve el tiempo.
	 * @return
	 */
	public static Integer getTimer()
	{
		return MapInstance.getTimer().intValue();
	}
	
	/**
	 * 
	 * @return
	 */
	public static Integer getTotalScore()
	{
		return MapInstance.getPlayerscore();
	}
	
	/**
	 * 
	 * @return
	 */
	public static SpriteChar getCell(int x, int y)
	{
		return MapVisual.getChar(x,y);
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
