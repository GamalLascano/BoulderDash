package game.map;

import game.actor.*;
import game.Position;

public class MapActor
{
	private Actor[][] matrix;
	private int width;
	private int height;

	public MapActor(int width, int height)
	{
		this.matrix = new Actor[width][height];
		this.width = width;
		this.height = height;
	}

	// GETTERS

	public Actor[][] getMatrix()
	{
		return matrix;
	}

	public Actor getActor(Position pos)
	{
		if (this.width >= pos.getX() && this.height >= pos.getY())
		{
			return matrix[pos.getX()][pos.getY()];
		}
		else
		{
			return null;
		}
	}

	// SETTERS

	/**
	 * 
	 * @param pos
	 * @param act
	 * @return : true si se agrego correctamente
	 */
	public boolean setActor(Position pos, Actor act)
	{
		if (this.width >= pos.getX() && this.height >= pos.getY())
		{
			matrix[pos.getX()][pos.getY()] = act;
			return true;
		}
		else
		{
			return false;
		}
	}

}
