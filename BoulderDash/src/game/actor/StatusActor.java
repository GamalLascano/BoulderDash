package game.actor;

/** Esta clase contiene todos los estados que puede tener un actor
 *  Todos los estados de movementState son de movimiento. y alive indica si esta vivo o no
 *
 */
public class StatusActor
{
	private StatusActorEnum movementState;
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
		this.movementState = stateEnum;
		this.alive = true;
	}
	
	/** Permite inicializar al status con un movimiento y estado de vida predeterminados
	 * @param stateEnum: movimiento
	 * @param alive: si esta vivo o no
	 */
	public StatusActor(StatusActorEnum stateEnum, boolean alive) 
	{
		this.movementState = stateEnum;
		this.alive = alive;
	}

	// GETTERS

	public StatusActorEnum getMovementState()
	{
		return movementState;
	}
	
	public boolean isAlive()
	{
		return alive;
	}

	// SETTERS
	
	public void setMovementState(StatusActorEnum stateEnum)
	{
		this.movementState = stateEnum;
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
		this.movementState = stateEnum;
		this.alive = alive;
	}

}
