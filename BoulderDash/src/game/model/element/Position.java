package game.model.element;

/**
 * Esta clase tiene las coordenadas x e y de un objeto determinado Ademas puede
 * devolver las posiciones de sus alrededores
 */
public class Position
{
	private Integer X;
	private Integer Y;

	/**
	 * Constructor posicion.
	 * 
	 * @param x
	 * @param y
	 */
	public Position(int x, int y)
	{
		super();
		this.X = x;
		this.Y = y;
	}

	/**
	 * Devuelve coorenada X.
	 * 
	 * @return posx
	 */
	public Integer getX()
	{
		return X;
	}

	/**
	 * Devuelve coorenada Y.
	 * 
	 * @return posy
	 */
	public Integer getY()
	{
		return Y;
	}

	/**
	 * Setea las posiciones X y Y.
	 * 
	 * @param posX
	 * @param posY
	 */
	public void setXY(int posX, int posY)
	{
		this.X = posX;
		this.Y = posY;
	}

	/**
	 * Incrementa Y, utilizado para bajar en el mapa.
	 */
	public void goDown()
	{
		this.Y++;
	}

	/**
	 * Decrementa Y, utilizado para subir en el mapa.
	 */
	public void goUp()
	{
		this.Y--;
	}

	/**
	 * Decrementa X, utilizado para mover a la izquierda en el mapa.
	 */
	public void goLeft()
	{
		this.X--;
	}

	/**
	 * Incrementa X, utilizado para mover a la derecha en el mapa.
	 */
	public void goRight()
	{
		this.X++;
	}

	/**
	 * Devuelve que posicion esta a abajo de la posicion actual
	 * 
	 * @return la coordenada y abajo del objeto
	 */
	public Integer checkDown()
	{
		// array bound
		return this.Y + 1;
	}

	/**
	 * Devuelve que posicion esta arriba de la posicion actual
	 * 
	 * @return la coordenada y arriba del objeto
	 */
	public Integer checkUp()
	{
		return this.Y - 1;
	}

	/**
	 * Devuelve que posicion esta a la izquierda de la posicion actual
	 * 
	 * @return la coordenada x a la izquierda del objeto
	 */
	public Integer checkLeft()
	{
		return this.X - 1;
	}

	/**
	 * Devuelve que posicion esta a la derecha de la posicion actual
	 * 
	 * @return la coordenada x a la derecha del objeto
	 */
	public Integer checkRight()
	{
		return this.X + 1;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (X == null)
		{
			if (other.X != null)
				return false;
		}
		else if (!X.equals(other.X))
			return false;
		if (Y == null)
		{
			if (other.Y != null)
				return false;
		}
		else if (!Y.equals(other.Y))
			return false;
		return true;
	}

}
