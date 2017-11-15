package game.model.item;

import game.model.ListOfEntities;
import game.model.Position;
import game.model.SolidTo;
import game.model.map.MapItem;

public class Fallable extends Item
{
	protected StatusFallableEnum state;

	public Fallable(Position pos, boolean collectable, boolean moveable, boolean fallable, boolean explodable,
			boolean rounded, SolidTo solid, StatusFallableEnum state)
	{
		super(pos, collectable, moveable, fallable, explodable, rounded, solid);
		this.state = state;
	}

	// GETTERS

	public StatusFallableEnum getState()
	{
		return state;
	}

	// SETTERS

	public void setState(StatusFallableEnum state)
	{
		this.state = state;
	}

	// DIE
	public void die()
	{
		this.state = StatusFallableEnum.DEAD;
		ListOfEntities.getList().remove(this);
		MapItem.removeItem(this.getPosition());
	}
	
	// REFRESH POSITION

	public void changePosition()
	{
		MapItem.removeItem(this.getPosition());
		this.fall();
		this.makeMove();
		MapItem.setItem(this);
	}

	
	// FALL

	public void fall()
	{

	}
	
	public void makeMove()
	{

	}

}
