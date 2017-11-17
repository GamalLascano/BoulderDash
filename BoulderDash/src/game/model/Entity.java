package game.model;

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
	private SolidTo solid;

	/**
	 * 
	 * @param po
	 */
	public Entity(Position po)
	{
		pos = po;
		this.solid = SolidTo.NONE;
	}

	/**
	 * 
	 * @param po
	 * @param solid
	 */
	public Entity(Position po, SolidTo solid)
	{
		pos = po;
		this.solid = solid;
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
	 * @return
	 */
	public SolidTo getSolid()
	{
		return solid;
	}

	/**
	 * 
	 * @param solid
	 */
	public void setSolid(SolidTo solid)
	{
		this.solid = solid;
	}

	/**
	 * 
	 * @param po
	 */
	public void setPosition(Position po)
	{
		pos = po;
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
