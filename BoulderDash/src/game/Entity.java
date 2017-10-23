package game;

/**
 * Esta clase representa todas las unidades que se pueden mover
 * Tiene lo intrinseco de una unidad, su posicion
 */
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

	// GETTERS
	
	public Position getPosition()
	{
		return pos;
	}

	// SETTERS
	
	public void setPosition(Position pos)
	{
		this.pos = pos;
	}
	
	
}
