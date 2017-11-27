package game.model;

import java.util.HashMap;

import game.model.actor.Actor;
import game.model.actor.Rockford;
import game.model.item.Item;

/**
 * Esta clase representa todas las unidades que se pueden mover Tiene lo
 * intrinseco de una unidad, su posicion
 */
public abstract class Entity
{
	private Position pos;
	private HashMap<Integer, SpriteChar> passable = new HashMap<>();

	/**
	 * 
	 * @param pos
	 */
	public Entity(Position pos)
	{
		this.pos = pos;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isActor()
	{
		if (this instanceof Actor)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isRockford()
	{
		if (this instanceof Rockford)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isItem()
	{
		if (this instanceof Item)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public Position getPosition()
	{
		return pos;
	}

	/**
	 * 
	 * @param pos
	 */
	public void setPosition(Position pos)
	{
		this.pos = pos;
	}
	

	/**
	 * 
	 * @return
	 */
	public HashMap<Integer, SpriteChar> getPassable()
	{
		return passable;
	}

	/**
	 * 
	 * @param passable
	 */
	public void setPassable(HashMap<Integer, SpriteChar> passable)
	{
		this.passable = passable;
	}

	/**
	 * 
	 */
	abstract public void changePosition();

	/**
	 * 
	 */
	abstract public void makeMove();

	/**
	 * 
	 */
	abstract public void die();

}
