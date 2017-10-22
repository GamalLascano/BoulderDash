package game;

public abstract class Entity
{
	private Position pos = new Position();

	public Entity()
	{
	}
	
	public Entity(Position pos)
	{
		super();
		this.pos = pos;
	}

	// S
	
	public Position getPosition()
	{
		return pos;
	}

	// S
	
	public void setPosition(Position pos)
	{
		this.pos = pos;
	}
	
	
}
