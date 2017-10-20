package game;

public class Position
{
	public int posX;
	public int posY;

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

	public int getPosX()
	{
		return posX;
	}

	public void setPosX(int posX)
	{
		this.posX = posX;
	}

	public int getPosY()
	{
		return posY;
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

}
