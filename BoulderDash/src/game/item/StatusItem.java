package game.item;

public class StatusItem {
	StatusActorEnum stateEnum;
	boolean alive;

	public StatusItem(){
		
	}
	
	//status para cada uno actor y item
	//enumerador para status
	public StatusItem(StatusActorEnum stateEnum, boolean alive) {
		this.stateEnum = stateEnum;
		this.alive = alive;
	}
	
	

	public StatusActorEnum getStateEnum()
	{
		return stateEnum;
	}

	public void setStateEnum(StatusActorEnum state)
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
	 * @param alive
	 */
	public void reset(StatusActorEnum stateEnum, boolean alive) {
		this.stateEnum = stateEnum;
		this.alive = alive;
	}
	
}
