package game.map;

import game.actor.*;
import game.Position;
import game.map.bdlevel.BDLevelReader;

public class MapActor
{
	private Actor[][] actor;
	private int width;
	private int height;

	public MapActor(int width, int height)
	{
		this.actor = new Actor[width][height];
		this.width = width;
		this.height = height;
	}

	public Rockford findPlayer()
	{

		for (int i = 0; i < levelFrame.getWIDTH(); ++i)
		{
			for (int j = 0; j < levelFrame.getHEIGHT(); ++j)
			{
				posmap.setPos(i, j);
				if (MapInstance.getMapActor().getActor(posmap) instanceof Rockford)
					playerPos.setPos(i, j);
			}
		}

	}

	public Actor getActor(Position pos)
	{
		return actor[pos.getPosX()][pos.getPosY()];
	}

	/**
	 * 
	 * @param pos
	 * @param act
	 * @return : true si se agrego correctamente
	 */
	public boolean setActor(Position pos, Actor act)
	{
		if (this.width >= pos.getPosX() && this.height >= pos.getPosY())
		{
			actor[pos.getPosX()][pos.getPosY()] = act;
			return true;
		} else
		{
			return false;
		}
	}

}
