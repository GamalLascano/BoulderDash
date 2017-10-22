package game.actor;

public class StatusActor
{
	private StatusActorEnum stateEnum;
	private boolean alive;

	// CONSTRUCTORS

	public StatusActor()
	{

	}

	public StatusActor(StatusActorEnum stateEnum) 
	{
		this.stateEnum = stateEnum;
		this.alive = true;
	}
	
	public StatusActor(StatusActorEnum stateEnum, boolean alive) 
	{
		this.stateEnum = stateEnum;
		this.alive = alive;
	}

	// GETTERS

	public StatusActorEnum getStateEnum()
	{
		return stateEnum;
	}
	
	public boolean isAlive()
	{
		return alive;
	}

	// SETTERS
	
	public void setStateEnum(StatusActorEnum stateEnum)
	{
		this.stateEnum = stateEnum;
	}

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	
	// RESET

	/**
	 * Cambia todo los parametros.
	 * 
	 * @param alive
	 */
	public void reset(StatusActorEnum stateEnum, boolean alive)
	{
		this.stateEnum = stateEnum;
		this.alive = alive;
	}

}
