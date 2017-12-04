package game.exception;

public class PlayerDugDirt extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerDugDirt()
	{
		super();
	}

	public PlayerDugDirt(String message)
	{
		super(message);
	}

	public PlayerDugDirt(String message, Throwable cause)
	{
		super(message, cause);
	}

	public PlayerDugDirt(Throwable cause)
	{
		super(cause);
	}
}
