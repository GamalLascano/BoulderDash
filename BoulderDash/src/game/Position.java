package game;

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
	
	public int getX()
	{
		return X;
	}

	public int getY()
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
	
	public void goDown()
	{
		--this.Y;
	}

	public void goUp()
	{
		++this.Y;
	}
	
	public void goLeft()
	{
		--this.X;
	}
	
	public void goRight()
	{
		++this.X;
	}
	
	// POS CHECK
	
	public Integer checkDown()
	{
		return this.Y - 1;
	}

	public Integer checkUp()
	{
		return this.Y + 1;
	}
	
	public Integer checkLeft()
	{
		return this.X - 1;
	}
	
	public Integer checkRight()
	{
		return this.X + 1;
	}
	
}
