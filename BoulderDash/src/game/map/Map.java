package game.map;

import game.map.bdlevel.BDLevelReader;

public abstract class Map
{

	abstract public void start(BDLevelReader levels);
	
	abstract public void fill();

}
