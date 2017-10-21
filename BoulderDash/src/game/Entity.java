package game;

public abstract class Entity
{
	Position pos = new Position();

	public Entity(Position pos)
	{
		super();
		this.pos = pos;
	}

	public Position getPosition()
	{
		return pos;
	}

	public void setPosition(Position pos)
	{
		this.pos = pos;
	}
	
	
}
