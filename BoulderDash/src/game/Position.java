package game;

/**
 * Esta clase tiene las coordenadas x e y de un objeto determinado
 * Ademas puede devolver las posiciones de sus alrededores
 *
 */
public class Position
{
	private Integer X;
	private Integer Y;

	// CONSTRUCTORS
	
	public Position()
	{
		super();
	}

	public Position(int x, int y)
	{
		super();
		this.X = x;
		this.Y = y;
	}

	// GETTERS
	
	public Integer getX()
	{
		return X;
	}

	public Integer getY()
	{
		return Y;
	}
	
	// SETTERS
	
	public void setX(int posX)
	{
		this.X = posX;
	}
	
	public void setY(int posY)
	{
		this.Y = posY;
	}

	public void setXY(int posX, int posY)
	{
		this.X = posX;
		this.Y = posY;
	}
	
	// POS GO
	
	/** Mueve abajo al objeto
	 * 
	 */
	public void goDown()
	{
		this.Y++;
	}
	/** Mueve arriba al objeto
	 * 
	 */
	public void goUp()
	{
		this.Y--;
	}
	/** Mueve a la izquierda al objeto
	 * 
	 */
	public void goLeft()
	{
		this.X--;
	}
	/** Mueve a la derecha al objeto
	 * 
	 */
	public void goRight()
	{
		this.X++;
	}
	
	// POS CHECK
	/** Devuelve que posicion esta a abajo de la posicion actual
	 * @return la coordenada y abajo del objeto
	 */
	public Integer checkDown()
	{
		return this.Y - 1;
	}
	/** Devuelve que posicion esta arriba de la posicion actual
	 * @return la coordenada y arriba del objeto
	 */
	public Integer checkUp()
	{
		return this.Y + 1;
	}
	/** Devuelve que posicion esta a la izquierda de la posicion actual
	 * @return la coordenada x a la izquierda del objeto
	 */
	public Integer checkLeft()
	{
		return this.X - 1;
	}
	
	/** Devuelve que posicion esta a la derecha de la posicion actual
	 * @return la coordenada x a la derecha del objeto
	 */
	public Integer checkRight()
	{
		return this.X + 1;
	}
	
}
