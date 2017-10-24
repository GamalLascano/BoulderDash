package game.actor;

/** Esta clase contiene todos los estados que puede tener un actor
 *  Todos los estados de stateEnum son de movimiento. y alive indica si esta vivo o no
 *
 */
public class StatusActor
{
	private StatusActorEnum stateEnum;
	private boolean alive;

	// CONSTRUCTORS

	public StatusActor()
	{

	}

	/** Permite inicializar el status con un estado de movimiento predeterminado
	 * @param stateEnum: movimiento
	 */
	public StatusActor(StatusActorEnum stateEnum) 
	{
		this.stateEnum = stateEnum;
		this.alive = true;
	}
	
	/** Permite inicializar al status con un movimiento y estado de vida predeterminados
	 * @param stateEnum: movimiento
	 * @param alive: si esta vivo o no
	 */
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


	/** Permite resetear los valores del estado a otros
	 * @param stateEnum: el nuevo estado de movimiento
	 * @param alive: el nuevo estado de vida
	 */
	public void reset(StatusActorEnum stateEnum, boolean alive)
	{
		this.stateEnum = stateEnum;
		this.alive = alive;
	}

}
