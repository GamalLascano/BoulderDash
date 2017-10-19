package game;

public class Status {
	StatusEnum state;
	boolean alive;

	public Status(){
		
	}
	
	//status para cada uno actor y item
	//enumerador para status
	public Status(StatusEnum state, boolean alive) {
		this.state = state;
		this.alive = alive;
	}
	
	

	public StatusEnum getState()
	{
		return state;
	}

	public void setState(StatusEnum state)
	{
		this.state = state;
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
	public void reset(StatusEnum state, boolean alive) {
		this.state = state;
		this.alive = alive;
	}
	
}
