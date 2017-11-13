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
	private int solid;

	public Entity()
	{
	}
	
	public Entity(Position pos)
	{
		this.pos = pos;
		this.solid = 0;
	}
	
	public Entity(Position pos, int solid)
	{
		this.pos = pos;
		this.solid = solid;
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
	
	public int getSolid()
	{
		return solid;
	}

	// SETTERS

	public void setSolid(int solid)
	{
		this.solid = solid;
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
