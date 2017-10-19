package game;

public class Status {
	StatusEnum stateEnum;
	boolean alive;

	public Status(){
		
	}
	
	//status para cada uno actor y item
	//enumerador para status
	public Status(StatusEnum stateEnum, boolean alive) {
		this.stateEnum = stateEnum;
		this.alive = alive;
	}
	
	

	public StatusEnum getStateEnum()
	{
		return stateEnum;
	}

	public void setStateEnum(StatusEnum state)
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
	public void reset(StatusEnum stateEnum, boolean alive) {
		this.stateEnum = stateEnum;
		this.alive = alive;
	}
	
}
