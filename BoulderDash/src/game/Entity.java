package game;

/**
 * Esta clase representa todas las unidades que se pueden mover
 * Tiene lo intrinseco de una unidad, su posicion
 */
public abstract class Entity
{
	private Position pos = new Position();
	private int solid;

	public Entity()
	{
	}
	
	public Entity(Position pos)
	{
		this.pos = pos;
		this.solid = 0;
	}
	
	public Entity(Position pos, int solid)
	{
		this.pos = pos;
		this.solid = solid;
	}

	// GETTERS
	
	public Position getPosition()
	{
		return pos;
	}
	
	public int isSolid()
	{
		return solid;
	}

	// SETTERS

	public void setSolid(int solid)
	{
		this.solid = solid;
	}

	public void setPosition(Position pos)
	{
		this.pos = pos;
	}
	
	
}
