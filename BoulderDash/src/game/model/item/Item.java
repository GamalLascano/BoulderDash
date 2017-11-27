package game.model.item;

import game.model.Entity;
import game.model.ListOfEntities;
import game.model.Position;
import game.model.SpriteChar;
import game.model.map.MapItem;

/**
 * Esta es la clase de todos los items, que son objetos no-enemigos que se
 * mueven Contienen una posicion heredada de entity, un Spritechar que
 * representa al item, y booleanos de estados.
 *
 */
public abstract class Item extends Entity
{

	private SpriteChar spritechar;
	private boolean collectable;
	private boolean moveable;
	private boolean fallable;
	private boolean explodable;
	private boolean rounded;

	/**
	 * 
	 * @param pos
	 * @param collectable
	 * @param moveable
	 * @param fallable
	 * @param explodable
	 * @param rounded
	 */
	public Item(Position pos, boolean collectable, boolean moveable, boolean fallable, boolean explodable,
			boolean rounded)
	{
		super(pos);
		this.collectable = collectable;
		this.moveable = moveable;
		this.fallable = fallable;
		this.explodable = explodable;
		this.rounded = rounded;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDiamond()
	{
		if (this instanceof Diamond)
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
	public boolean isRock()
	{
		if (this instanceof Rock)
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
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isCollectable()
	{
		return collectable;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isMoveable()
	{
		return moveable;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isFallable()
	{
		return fallable;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isExplodable()
	{
		return explodable;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isRounded()
	{
		return rounded;
	}

	/**
	 * 
	 * @param collectable
	 */
	public void setCollectable(boolean collectable)
	{
		this.collectable = collectable;
	}

	/**
	 * 
	 * @param moveable
	 */
	public void setMoveable(boolean moveable)
	{
		this.moveable = moveable;
	}

	/**
	 * 
	 * @param fallable
	 */
	public void setFallable(boolean fallable)
	{
		this.fallable = fallable;
	}

	/**
	 * 
	 * @param explodable
	 */
	public void setExplodable(boolean explodable)
	{
		this.explodable = explodable;
	}

	/**
	 * 
	 * @param rounded
	 */
	public void setRounded(boolean rounded)
	{
		this.rounded = rounded;
	}

	/**
	 * 
	 */
	public void die()
	{
		ListOfEntities.getList().remove(this);
		MapItem.removeItem(this.getPosition());
	}
}
