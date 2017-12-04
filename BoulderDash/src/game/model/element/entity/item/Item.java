package game.model.element.entity.item;

import game.model.element.Position;
import game.model.element.entity.Entity;
import game.model.element.entity.ListOfEntities;
import game.model.map.MapItem;

/**
 * Esta es la clase de todos los items, que son objetos no-enemigos que se
 * mueven Contienen una posicion heredada de entity, un Spritechar que
 * representa al item, y booleanos de estados.
 *
 */
public abstract class Item extends Entity
{

	private boolean collectable;
	private boolean moveable;
	private boolean rounded;

	/**
	 * Constructor de un item.
	 * 
	 * @param pos
	 * @param collectable
	 * @param moveable
	 * @param rounded
	 */
	public Item(Position pos, boolean collectable, boolean moveable, boolean rounded)
	{
		super(pos);
		this.collectable = collectable;
		this.moveable = moveable;
		this.rounded = rounded;
	}

	/**
	 * Devuelve si el item es un diamante.
	 * 
	 * @return si el item es un diamante
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
	 * Devuelve si es una roca.
	 * 
	 * @return si es una roca
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
	 * Devuelve si es collectable el objeto.
	 * 
	 * @return si es collectable el objeto
	 */
	public boolean isCollectable()
	{
		return collectable;
	}

	/**
	 * Devuelve si se puede desplazar el objeto.
	 * 
	 * @return si se puede desplazar el objeto
	 */
	public boolean isMoveable()
	{
		return moveable;
	}

	/**
	 * Retorna si el objeto es redondo. Si un objeto puede deslizar.
	 * 
	 * @return si el objeto es redondo
	 */
	public boolean isRounded()
	{
		return rounded;
	}

	@Override
	public void die()
	{
		ListOfEntities.getList().remove(this);
		MapItem.removeItem(this.getPosition());
	}
}
