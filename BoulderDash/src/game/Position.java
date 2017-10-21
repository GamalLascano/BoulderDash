package game;

import game.map.MapInstance;

public class Position
{
	private Integer posX;
	private Integer posY;

	// CONSTRUCTORS
	
	public Position()
	{
		super();
	}

	public Position(int posX, int posY)
	{
		super();
		this.posX = posX;
		this.posY = posY;
	}

	// GETTERS
	
	public int getPosX()
	{
		return posX;
	}

	public int getPosY()
	{
		return posY;
	}
	
	// SETTERS
	
	public void setPosX(int posX)
	{
		this.posX = posX;
	}
	
	public void setPosY(int posY)
	{
		this.posY = posY;
	}

	public void setPos(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	// POS GO
	
	public void goDown()
	{
		--this.posY;
	}

	public void goUp()
	{
		++this.posY;
	}
	
	public void goLeft()
	{
		--this.posX;
	}
	
	public void goRight()
	{
		++this.posX;
	}
	
	// POS CHECK
	
	public Integer checkDown()
	{
		return this.posY - 1;
	}

	public Integer checkUp()
	{
		return this.posY + 1;
	}
	
	public Integer checkLeft()
	{
		return this.posX - 1;
	}
	
	public Integer checkRight()
	{
		return this.posX + 1;
	}
	
}
