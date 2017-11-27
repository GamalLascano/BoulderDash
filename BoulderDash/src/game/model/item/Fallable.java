package game.model.item;

import game.model.ListOfEntities;
import game.model.Position;
import game.model.map.MapItem;

/**
 * 
 *
 */
public class Fallable extends Item
{
	protected StatusFallableEnum state;

	/**
	 * 
	 * @param pos
	 * @param collectable
	 * @param moveable
	 * @param fallable
	 * @param explodable
	 * @param rounded
	 * @param solid
	 * @param state
	 */
	public Fallable(Position pos, boolean collectable, boolean moveable, boolean fallable, boolean explodable,
			boolean rounded, StatusFallableEnum state)
	{
		super(pos, collectable, moveable, fallable, explodable, rounded);
		this.state = state;
	}

	/**
	 * 
	 * @return
	 */
	public StatusFallableEnum getState()
	{
		return state;
	}

	/**
	 * 
	 * @param state
	 */
	public void setState(StatusFallableEnum state)
	{
		this.state = state;
	}

	/**
	 * 
	 */
	public void die()
	{
		this.state = StatusFallableEnum.DEAD;
		ListOfEntities.getList().remove(this);
		MapItem.removeItem(this.getPosition());
	}

	/**
	 * 
	 */
	public void changePosition()
	{
		MapItem.removeItem(this.getPosition());
		this.fall();
		this.makeMove();
		MapItem.setItem(this);
	}

	/**
	 * 
	 */
	public void fall()
	{

	}

	/**
	 * 
	 */
	public void makeMove()
	{

	}

}
