package game.model.element;

/**
 * Elemento del juego. Celda, item o actor.
 * Tiene posicion y un caracter de identificacion.
 */
public abstract class Element
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
}
