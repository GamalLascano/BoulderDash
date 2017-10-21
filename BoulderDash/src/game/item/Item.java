package game.item;

import game.Position;
import game.Entity;
import game.map.MapInstance;
import game.map.bdlevel.BDTile;

public abstract class Item extends Entity
{
	StatusItem state;
	Position pos = new Position();
	boolean collectable;
	boolean moveable;
	boolean fallable;
	boolean explodable;
	boolean rounded;

	public Item(StatusItem state, Position pos, boolean collectable, boolean moveable, boolean fallable,
			boolean explodable, boolean rounded)
	{
		super(pos);
		this.state = state;
		this.pos = pos;
		this.collectable = collectable;
		this.moveable = moveable;
		this.fallable = fallable;
		this.explodable = explodable;
		this.rounded = rounded;
	}

	public void fall()
	{
		while (MapInstance.getTile(pos.posX, pos.posY - 1) == BDTile.EMPTY)
		{
			pos.setY(pos.setY() - 1);
			state.setStateEnum(StatusItemEnum.FALLING);
		}
	}

	public StatusItem getState()
	{
		return state;
	}

	public void setState(StatusItem state)
	{
		this.state = state;
	}

	public Position getPosition()
	{
		return pos;
	}

	public void setPosition(Position pos)
	{
		this.pos = pos;
	}

	public boolean isCollectable()
	{
		return collectable;
	}

	public void setCollectable(boolean collectable)
	{
		this.collectable = collectable;
	}

	public boolean isMoveable()
	{
		return moveable;
	}

	public void setMoveable(boolean moveable)
	{
		this.moveable = moveable;
	}

	public boolean isFallable()
	{
		return fallable;
	}

	public void setFallable(boolean fallable)
	{
		this.fallable = fallable;
	}

	public boolean isExplodable()
	{
		return explodable;
	}

	public void setExplodable(boolean explodable)
	{
		this.explodable = explodable;
	}

	public boolean isRounded()
	{
		return rounded;
	}

	public void setRounded(boolean rounded)
	{
		this.rounded = rounded;
	}

	/**
	 * poner collect en Rockford? public void collected() { if ()
	 * BDLevelReader.field[pos.posX][pos.posY] = BDTile.EMPTY;
	 * 
	 * }
	 */
}
