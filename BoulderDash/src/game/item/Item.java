package game.item;

import game.Position;
import game.Entity;
import game.map.MapInstance;
import game.SpriteChar;

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
	SpriteChar spritechar;
	/** state: setea el estado de movimiento del item
	 * 
	 */
	StatusItem state;
	/** solid: indica si el objeto es solido o no
	 * 
	 */
	boolean solid;
	/** collectable: indica si el objeto es recolectable o no
	 * 
	 */
	boolean collectable;
	/** moveable: indica si el objeto se puede mover o no
	 * 
	 */
	boolean moveable;
	/** fallable: indica si el objeto se puede caer o no
	 * 
	 */
	boolean fallable;
	/** explodable: indica si el objeto puede ser explotado o no
	 * 
	 */
	boolean explodable;
	/** rounded: indica si el objeto es redondo o no
	 * 
	 */
	boolean rounded;	//Si un objeto sobre otro se cae por los lados

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
	public Item(StatusItem state, Position pos, boolean collectable, boolean moveable, boolean fallable,
			boolean explodable, boolean rounded, boolean solid)
	{
		super(pos);
		this.state = state;
		this.collectable = collectable;
		this.moveable = moveable;
		this.fallable = fallable;
		this.explodable = explodable;
		this.rounded = rounded;
		this.solid = solid;
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	public StatusItem getState()
	{
		return state;
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

	public boolean isSolid()
	{
		return solid;
	}

	// SETTERS
	
	public void setState(StatusItem state)
	{
		this.state = state;
	}

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

	//COMPORTAMIENTO

	/** Este metodo se usa para comprobar si el item se cae o no, y setear el estado necesario
	 * 
	 */
	public void fall()
	{
		//Se checkea la posicion de abajo del objeto
		Position posDown = new Position(super.getPosition().getX(), super.getPosition().checkDown());
		//Ve si la posicion que esta abajo esta vacia
		if (MapInstance.getMapCell().isEmpty(posDown))
		{
			//Si esta vacia, lo deja cayendo
			state.setStateEnum(StatusItemEnum.FALLING);
		}
		else
		{
			//Sino, si el objeto es redondo, elije uno de los lados para caer, y cae
			if (MapInstance.getMapItem().getItem(posDown).isRounded())
			{
				Position posLeft = new Position(super.getPosition().checkLeft(), super.getPosition().getY());
				Position posRight = new Position(super.getPosition().checkRight(), super.getPosition().getY());
				//Si estan los los lados vacios, elije el lado de la izquierda
				if (MapInstance.getMapCell().isEmpty(posLeft))
				{
					state.setStateEnum(StatusItemEnum.SLIDINGLEFT);
				}
				else
				{
					if (MapInstance.getMapCell().isEmpty(posRight))
					{
						state.setStateEnum(StatusItemEnum.SLIDINGRIGHT);

					}
				}
			}
		}
	}
}
