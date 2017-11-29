package game.model;

/**
 * Elemento del juego. Celda, item o actor.
 */
public abstract class Element
{
	private SpriteChar spritechar;
	private Position pos;
	
	/**
	 * Constructor de elementos.
	 * @param pos
	 */
	public Element(Position pos)
	{
		super();
		this.pos = pos;
	}

	/**
	 * Retorna la representacion del elemento (SpriteChar) de la entidad.
	 * @return SpriteChar
	 */
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	/**
	 * Setea la representacion del elemento (SpriteChar) de la entidad.
	 * @param spritechar
	 */
	public void setSpritechar(SpriteChar spritechar)
	{
		this.spritechar = spritechar;
	}

	/**
	 * Devuelve el objeto posicion.
	 * @return objeto posicion, coord x,y
	 */
	public Position getPosition()
	{
		return pos;
	}

	/**
	 * Setea el objeto posicion.
	 * @param pos
	 */
	public void setPosition(Position pos)
	{
		this.pos = pos;
	}
}
