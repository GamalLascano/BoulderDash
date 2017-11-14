package game.model.item;

import game.model.Entity;
import game.model.ListOfEntities;
import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.map.MapItem;

/** Esta es la clase de todos los items, que son objetos no-enemigos que se mueven
 *  Contienen una posicion heredada de entity, un Spritechar que representa al item,
 *  y booleanos de estados.
 *
 */
public abstract class Item extends Entity
{

	/** Spritechar: representa al personaje visualmente
	 * 
	 */
	private SpriteChar spritechar;
	/** collectable: indica si el objeto es recolectable o no
	 * 
	 */
	private	boolean collectable;
	/** moveable: indica si el objeto se puede mover o no
	 * 
	 */
	private boolean moveable;
	/** fallable: indica si el objeto se puede caer o no
	 * 
	 */
	private boolean fallable;
	/** explodable: indica si el objeto puede ser explotado o no
	 * 
	 */
	private boolean explodable;
	/** rounded: indica si el objeto es redondo o no
	 * 
	 */
	private boolean rounded;	//Si un objeto sobre otro se cae por los lados

	// CONSTRUCTOR
	
	/** Inicializa el item con un estado predeterminado
	 * @param state: estado de movimiento
	 * @param pos: posicion
	 * @param collectable: si se puede recolectar
	 * @param moveable: si se puede mover
	 * @param fallable: si se puede caer
	 * @param explodable: si puede explotar
	 * @param rounded: si es redondo o no
	 * @param solid: si es solido
	 */
	public Item(Position pos, boolean collectable, boolean moveable, boolean fallable,
			boolean explodable, boolean rounded, SolidTo solid)
	{
		super(pos, solid);
		this.collectable = collectable;
		this.moveable = moveable;
		this.fallable = fallable;
		this.explodable = explodable;
		this.rounded = rounded;
	}

	// ENTITY TYPE
	
	public boolean isDiamond()
	{
		if(this instanceof Diamond)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isRock()
	{
		if(this instanceof Rock)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	public boolean isCollectable()
	{
		return collectable;
	}

	public boolean isMoveable()
	{
		return moveable;
	}

	public boolean isFallable()
	{
		return fallable;
	}
	
	public boolean isExplodable()
	{
		return explodable;
	}
	
	public boolean isRounded()
	{
		return rounded;
	}

	// SETTERS

	public void setCollectable(boolean collectable)
	{
		this.collectable = collectable;
	}

	public void setMoveable(boolean moveable)
	{
		this.moveable = moveable;
	}

	public void setFallable(boolean fallable)
	{
		this.fallable = fallable;
	}

	public void setExplodable(boolean explodable)
	{
		this.explodable = explodable;
	}

	public void setRounded(boolean rounded)
	{
		this.rounded = rounded;
	}
	
	// DIE
	
	public void die()
	{
		ListOfEntities.getList().remove(this);
		MapItem.removeItem(this.getPosition());
	}
}
