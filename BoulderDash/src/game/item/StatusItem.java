package game.item;

public class StatusItem
{
	private StatusItemEnum stateEnum;
	private boolean alive;

	public StatusItem()
	{

	}

	// status para cada uno Item y item
	// enumerador para status
	public StatusItem(StatusItemEnum stateEnum, boolean alive)
	{
		this.stateEnum = stateEnum;
		this.alive = alive;
	}

	public StatusItemEnum getStateEnum()
	{
		return stateEnum;
	}

	public void setStateEnum(StatusItemEnum stateEnum)
	{
		this.stateEnum = stateEnum;
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}

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
