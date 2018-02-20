package game.model.element;

/**
 * Elemento del juego. Celda, item o actor.
 * Tiene posicion y un caracter de identificacion.
 */
public class Element
{
	private ElementTypes type;
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
	public ElementTypes getElementType()
	{
		return type;
	}

	/**
	 * Setea la representacion del elemento (SpriteChar) de la entidad.
	 * 
	 * @param spritechar
	 */
	public void setElementType(ElementTypes type)
	{
		this.type = type;
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
		if (this.getElementType() == ElementTypes.Diamond)
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
		if (this.getElementType() == ElementTypes.Rock)
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
		if (this.getElementType() == ElementTypes.Empty)
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
		if (this.getElementType() == ElementTypes.Dirt)
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
		if (this.getElementType() == ElementTypes.Wall)
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
		if (this.getElementType() == ElementTypes.Titanium)
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
		if (this.getElementType() == ElementTypes.ExitOpen
				|| this.getElementType() == ElementTypes.ExitClosed)
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
		if (this.getElementType().getKind().equals("Actor"))
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
		if (this.getElementType() == ElementTypes.Rockford
				|| this.getElementType() == ElementTypes.RockfordUp
				|| this.getElementType() == ElementTypes.RockfordDown
				|| this.getElementType() == ElementTypes.RockfordRight
				|| this.getElementType() == ElementTypes.RockfordLeft)
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
		if (this.getElementType().getKind().equals("Item"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
