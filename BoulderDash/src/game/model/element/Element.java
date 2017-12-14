package game.model.element;

import game.model.element.cell.Dirt;
import game.model.element.cell.Exit;
import game.model.element.cell.Titanium;
import game.model.element.cell.Wall;
import game.model.element.entity.actor.Actor;
import game.model.element.entity.actor.Rockford;
import game.model.element.entity.item.Diamond;
import game.model.element.entity.item.Empty;
import game.model.element.entity.item.Item;
import game.model.element.entity.item.Rock;

/**
 * Elemento del juego. Celda, item o actor.
 * Tiene posicion y un caracter de identificacion.
 */
public class Element
{
	private ElementChar spritechar;
	private Position pos;

	/**
	 * Constructor de elementos.
	 * 
	 * @param pos
	 */
	public Element(Position pos)
	{
		super();
		this.pos = pos;
	}

	/**
	 * Retorna la representacion del elemento (SpriteChar) de la entidad.
	 * 
	 * @return caracter de identificacion
	 */
	public ElementChar getSpritechar()
	{
		return spritechar;
	}

	/**
	 * Setea la representacion del elemento (SpriteChar) de la entidad.
	 * 
	 * @param spritechar
	 */
	public void setSpritechar(ElementChar spritechar)
	{
		this.spritechar = spritechar;
	}

	/**
	 * Devuelve el objeto posicion.
	 * 
	 * @return objeto posicion, (x,y)
	 */
	public Position getPosition()
	{
		return pos;
	}

	/**
	 * Setea el objeto posicion. (x,y)
	 * 
	 * @param pos
	 */
	public void setPosition(Position pos)
	{
		this.pos = pos;
	}
	
	/**
	 * Hace un comportamiento y borra el elemento.
	 */
	public void die()
	{
		
	}
	
	///////
	
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
	 * Devuelve si es vacio.
	 * 
	 * @return si es un bloque vacio
	 */
	public boolean isEmpty()
	{
		if (this instanceof Empty)
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
	 * @return si la celda es tierra
	 */
	public boolean isDirt()
	{
		if (this instanceof Dirt)
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
	 * @return si la celda es un muro.
	 */
	public boolean isWall()
	{
		if (this instanceof Wall)
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
	 * @return si la celda es titanio
	 */
	public boolean isTitanium()
	{
		if (this instanceof Titanium)
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
	 * @return si la celda es la salida.
	 */
	public boolean isExit()
	{
		if (this instanceof Exit)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Determina si esta entidad es un actor.
	 * 
	 * @return si esta entidad es un actor
	 */
	public boolean isActor()
	{
		if (this instanceof Actor)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Determina si esta entidad es Rockford.
	 * 
	 * @return si esta entidad es Rockford
	 */
	public boolean isRockford()
	{
		if (this instanceof Rockford)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Determina si esta entidad es un Item.
	 * 
	 * @return si esta entidad es un Item
	 */
	public boolean isItem()
	{
		if (this instanceof Item)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
