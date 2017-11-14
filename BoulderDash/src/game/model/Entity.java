package game.model;

import game.model.actor.Actor;
import game.model.actor.Rockford;
import game.model.item.Item;

/**
 * Esta clase representa todas las unidades que se pueden mover
 * Tiene lo intrinseco de una unidad, su posicion
 */
public abstract class Entity
{
	private Position pos = new Position();
	private SolidTo solid;

	public Entity()
	{
	}
	
	public Entity(Position pos)
	{
		this.pos = pos;
		this.solid = SolidTo.NONE;
	}
	
	public Entity(Position pos, SolidTo solid)
	{
		this.pos = pos;
		this.solid = SolidTo.NONE;
	}

	// ENTITY TYPE
	
	public boolean isActor()
	{
		if(this instanceof Actor)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isRockford()
	{
		if(this instanceof Rockford)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isItem()
	{
		if(this instanceof Item)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// GETTERS
	
	public Position getPosition()
	{
		return pos;
	}
	
	public SolidTo getSolid()
	{
		return solid;
	}

	// SETTERS

	public void setSolid(int solid)
	{
		this.solid = SolidTo.NONE;
	}

	public void setPosition(Position pos)
	{
		this.pos = pos;
	}
	
	// POSITION
	
	abstract public void changePosition();
	
	abstract public void makeMove();
	
	// DIE
	
	abstract public void die();
	
	
}
