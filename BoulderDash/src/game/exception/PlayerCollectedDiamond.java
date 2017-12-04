package game.exception;

public class PlayerCollectedDiamond extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerCollectedDiamond()
	{
		super();
	}

	public PlayerCollectedDiamond(String message)
	{
		super(message);
	}

	public PlayerCollectedDiamond(String message, Throwable cause)
	{
		super(message, cause);
	}

	public PlayerCollectedDiamond(Throwable cause)
	{
		super(cause);
	}
}
