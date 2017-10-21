package game.item;

public class StatusItem
{
	private StatusItemEnum stateEnum;
	private boolean alive;

	//CONSTRUCTOR
	
	public StatusItem()
	{

	}

	public StatusItem(StatusItemEnum stateEnum, boolean alive)
	{
		this.stateEnum = stateEnum;
		this.alive = alive;
	}
	
	// GETTERS

	public StatusItemEnum getStateEnum()
	{
		return stateEnum;
	}

	public boolean isAlive()
	{
		return alive;
	}
	
	// SETTERS
	
	public void setStateEnum(StatusItemEnum stateEnum)
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
	public void reset(StatusItemEnum stateEnum, boolean alive)
	{
		this.stateEnum = stateEnum;
		this.alive = alive;
	}

}
