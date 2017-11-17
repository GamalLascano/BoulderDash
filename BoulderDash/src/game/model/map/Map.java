package game.model.map;

import game.model.map.bdlevel.BDLevelReader;

/**
 * 
 *
 */
public abstract class Map
{

	/**
	 * 
	 * @param levels
	 */
	abstract public void start(BDLevelReader levels);
	
	/**
	 * 
	 */
	abstract public void fill();

}
