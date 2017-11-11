package game.item;

import game.Entity;
import game.Position;
import game.SpriteChar;
import game.map.MapActor;
import game.map.MapCell;
import game.map.MapItem;

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
	/** state: setea el estado de movimiento del item
	 * 
	 */
	private StatusItem state;
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
	public Item(StatusItem state, Position pos, boolean collectable, boolean moveable, boolean fallable,
			boolean explodable, boolean rounded, int solid)
	{
		super(pos, solid);
		this.state = state;
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

	// FALL

	/** Este metodo se usa para comprobar si el item se cae o no, y setear el estado necesario
	 * 
	 */
	public void fall()
	{
		//Se checkea la posicion de abajo del objeto
		Position posDown = new Position(super.getPosition().getX(), super.getPosition().checkDown());
		//Ve si la posicion que esta abajo esta vacia
		if (MapCell.isEmpty(posDown))
		{
			//Si esta vacia, lo deja cayendo
			state.setStateEnum(StatusItemEnum.FALLING);
		}
		else
		{
			//Sino, si el objeto es redondo, elije uno de los lados para caer, y cae
			if (MapItem.getItem(posDown).isRounded())
			{
				Position posLeft = new Position(this.getPosition().checkLeft(), this.getPosition().getY());
				Position posRight = new Position(this.getPosition().checkRight(), this.getPosition().getY());
				//Si estan los los lados vacios, elije el lado de la izquierda
				if (MapCell.isEmpty(posLeft))
				{
					this.getState().setStateEnum(StatusItemEnum.SLIDINGLEFT);
				}
				else if (MapCell.isEmpty(posRight))
				{
					this.getState().setStateEnum(StatusItemEnum.SLIDINGRIGHT);
				}
			}
		}
	}
	
	// REFRESH POSITION
	
	public void changePosition()
	{
		MapItem.removeItem(this.getPosition());
		this.fall();
		this.makeMove();
		MapItem.setItem(this.getPosition(), this);
	}
	
	public void makeMove()
	{
		StatusItemEnum status = this.getState().getStateEnum();
		switch (status)
		{
			case FALLING:
				if (MapCell.getCell(this.getPosition().getX(), this.getPosition().checkDown()).isSolid() < 1
						&& MapItem.getItem(this.getPosition().getX(), this.getPosition().checkDown()).isSolid() < 1
						&& MapActor.getActor(this.getPosition().getX(), this.getPosition().checkDown()) != null)
				{
					this.getPosition().goDown();
				}
				break;
			case SLIDINGRIGHT:
				if (MapCell.getCell(this.getPosition().checkRight(), this.getPosition().getY()).isSolid() < 1
						&& MapItem.getItem(this.getPosition().checkRight(), this.getPosition().getY()).isSolid() < 1
						&& MapActor.getActor(this.getPosition().checkRight(), this.getPosition().getY()) != null)
				{
					this.getPosition().goRight();
				}
				break;
			case SLIDINGLEFT:
				if (MapCell.getCell(this.getPosition().checkLeft(), this.getPosition().getY()).isSolid() < 1
						&& MapItem.getItem(this.getPosition().checkLeft(), this.getPosition().getY()).isSolid() < 1
						&& MapActor.getActor(this.getPosition().checkLeft(), this.getPosition().getY()) != null)
				{
					this.getPosition().goLeft();
				}
				break;
			default:
				break;
		}
		this.getState().setStateEnum(StatusItemEnum.IDLE);
	}
	
}
