package game.model.map;

import game.model.map.bdlevel.BDLevelReader;

public abstract class Map
{

	abstract public void start(BDLevelReader levels);
	
	abstract public void fill();

}
